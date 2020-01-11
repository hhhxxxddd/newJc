package com.jinchi.common.service;

import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.constant.QualitySampleStatusEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.CommonNameDTO;
import com.jinchi.common.dto.TaskDTO;
import com.jinchi.common.mapper.*;
import com.jinchi.common.service.feignservice.IRepoOutService;
import com.jinchi.common.utils.CommonBatchUtil;
import com.jinchi.common.utils.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author：XudongHu
 * @className:DataTaskRecordImp
 * @description: 待办事项
 * @See Task
 * @date:16:39 2018/12/7
 */
@Service
public class DataTaskRecordServiceImp implements DataTaskRecordService {
    public final static Logger logger = LoggerFactory.getLogger(DataTaskRecordService.class);
    @Autowired
    private DataTaskRecordMapper dataTaskRecordMapper;
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskHandlingRecordMapper taskHandlingRecordMapper;
    @Autowired
    private UnqualifiedTestReportRecordService unqualifiedTestReportRecordService;
    @Autowired
    private UnqualifiedTracingRecordService unqualifiedTracingRecordService;
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private IRepoOutService iRepoOutService;
    @Autowired
    private RepoRedTableService repoRedTableService;
    @Autowired
    private ProcedureTestRecordService procedureTestRecordService;
    @Autowired
    private SampleDeliveringRecordMapper sampleDeliveringRecordMapper;
    @Autowired
    private TestReportRecordMapper testReportRecordMapper;
    @Autowired
    private TestItemResultRecordMapper testItemResultRecordMapper;
    @Autowired
    private CommonBatchUtil commonBatchUtil;
    @Autowired
    private CommonBatchNumberChildMapper commonBatchNumberChildMapper;
    @Autowired
    private RepoOutApplyMapper repoOutApplyMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ProcessParametersListHeadMapper processParametersListHeadMapper;

    /**
     * 送审
     *
     * @param dataId 数据id
     * @param taskId 流程id
     * @return
     */
    @Override
    @Transactional
    public String send2audit(Integer dataId, Integer taskId, Integer isUrgent) {
        Assert.isTrue(isUrgent.equals(1) || isUrgent.equals(0), "请设置正确的紧急状况");
        //验证流程是否存在
        CommonBatchNumber taskCommonBatchNumber = commonBatchNumberMapper.byId(taskId);
        Assert.isTrue(null != taskCommonBatchNumber && taskCommonBatchNumber.getDataType().equals(BatchTypeEnum.QUALITY_AUDIT_TASK.typeCode()), "该流程批号不存在");

        //验证送审数据是否存在
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(dataId);
        Assert.notNull(commonBatchNumber, "找不到送审数据的批号!");

        //送审前处理
        String messageBeforeAudit = this.handleBeforeAudit(commonBatchNumber, isUrgent);
        logger.info("handleBeforeAudit=>{}", messageBeforeAudit);


        //储存流程&&任务对应表
        DataTaskRecord dataTaskRecord = new DataTaskRecord();
        dataTaskRecord.setCreateTime(commonBatchNumber.getCreateTime());
        dataTaskRecord.setCreatePerson(commonBatchNumber.getCreatePersonId());
        dataTaskRecord.setDataBatchNumberId(commonBatchNumber.getId());
        dataTaskRecord.setTaskBatchNumberId(taskId);

        dataTaskRecordMapper.insert(dataTaskRecord);

        Integer dataTaskRecordId = dataTaskRecord.getId();

        // 把数据批号交给流程批号进行处理
        List<TaskHandlingRecord> taskHandlingRecords = new ArrayList<>();
        //找到任务处理人列表
        List<Task> tasks = taskMapper.findByBatchNumberId(taskCommonBatchNumber.getId());
        Assert.notEmpty(tasks, "该流程不存在处理人!");
        //按照先后顺序排序
        tasks.sort(Comparator.comparing(Task::getPrevious));
        for (Task task : tasks) {
            TaskHandlingRecord taskHandlingRecord = new TaskHandlingRecord();

            taskHandlingRecord.setHandler(task.getOperator());

            taskHandlingRecord.setDataTaskRecordId(dataTaskRecordId);

            //新增初始化设置为对第一个人可见
            if (task.getPrevious() == 0) taskHandlingRecord.setVisible(1);
            else taskHandlingRecord.setVisible(0);

            taskHandlingRecords.add(taskHandlingRecord);
        }
        taskHandlingRecordMapper.insertAll(taskHandlingRecords);


        return String.format("%s任务已交由%s流程进行审批", commonBatchNumber.getBatchNumber(), taskCommonBatchNumber.getBatchNumber());
    }

    /**
     * 查询所有当前待办事项
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public List<CommonBatchNumberDTO> findToDoList(Integer userId) {
        //根据用户id和visible(是否可见)查到=>
        //反馈记录表TaskHandlingRecord
        List<TaskHandlingRecord> taskHandlingRecords = taskHandlingRecordMapper.toDoList(userId, 1);
        if (null == taskHandlingRecords || 0 == taskHandlingRecords.size()) return null;

        //根据反馈记录表找到=>数据流程对应表
        //根据数据流程对应表找到=>
        //流程批号=>流程找到所有处理人
        //数据批号=>找到当前处理的数据
        List<CommonBatchNumberDTO> commonBatchNumberDTOS = new ArrayList<>();
        for (TaskHandlingRecord taskHandlingRecord : taskHandlingRecords) {
            DataTaskRecord dataTaskRecord = dataTaskRecordMapper.findById(taskHandlingRecord.getDataTaskRecordId());

            //流程批号id
            Integer taskId = dataTaskRecord.getTaskBatchNumberId();
            //数据批号id
            Integer dataId = dataTaskRecord.getDataBatchNumberId();

            CommonBatchNumberDTO commonBatchNumberDTO = commonBatchNumberMapper.createDTOByIdAndDataType(dataId, null);

            List<TaskDTO> taskDTOS = taskMapper.toDoListDetails(taskId);
            if (null != taskDTOS && 0 != taskDTOS.size()) {
                for (TaskDTO taskDTO : taskDTOS) {
                    TaskHandlingRecord record = taskHandlingRecordMapper.toDoItem(taskDTO.getUserId(), 1, dataTaskRecord.getId());
                    if (record != null) taskDTO.setVisible(record.getVisible());
                }
            }

            commonBatchNumberDTO.setDetails(taskDTOS);

            commonBatchNumberDTOS.add(commonBatchNumberDTO);

        }
        return commonBatchNumberDTOS;
    }

    /**
     * 查询所有已完成待办事项
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public List<CommonBatchNumberDTO> findFinToDoList(Integer userId, String date) {
        //根据userId找到
        //审核反馈记录taskHandlingRecord
        //此处找到的是审核日期不为空的值,即确实是已处理完的流程
        List<TaskHandlingRecord> finRecords = taskHandlingRecordMapper.finTaskRecords(userId);
        //没有完成的审核反馈,则直接返回NULL
        if (null == finRecords || 0 == finRecords.size()) return null;

        //构建Map{key=数据流程对应表id value=tasks}
        Map<Integer, List<TaskDTO>> recordsOfDataMap = new HashMap<>();

        //根据finRecords=>
        //找到不重复的数据流程对应表ids=>
        Set<Integer> dataTaskIds = new HashSet<>();
        for (TaskHandlingRecord taskHandlingRecord : finRecords) {

            Integer dataTaskRecordId = taskHandlingRecord.getDataTaskRecordId();

            dataTaskIds.add(dataTaskRecordId);
        }

        //根据数据流程对应表的id 找出所有 评价
        for (Integer dataTaskId : dataTaskIds) {
            List<TaskHandlingRecord> taskHandlingRecords = taskHandlingRecordMapper.findAllByDataTaskId(dataTaskId);

            DataTaskRecord dataTaskRecord = dataTaskRecordMapper.findById(dataTaskId);

            Integer taskBatchNumberId = dataTaskRecord.getTaskBatchNumberId();

            List<TaskDTO> tasks = taskMapper.toDoListDetails(taskBatchNumberId);

            for (int i = 0; i < tasks.size(); i++) {
                TaskDTO taskDTO = tasks.get(i);

                TaskHandlingRecord taskHandlingRecord = taskHandlingRecords.get(i);

                taskDTO.setVisible(taskHandlingRecord.getVisible());

                logger.info("task的用户id{}=====handle的用户id{}", taskDTO.getUserId(), taskHandlingRecord.getHandler());
            }

            recordsOfDataMap.put(dataTaskId, tasks);

        }

        //根据Map的key去找到对应的dataId=>找到commonBatchNumber
        List<CommonBatchNumberDTO> commonBatchNumberDTOS = new ArrayList<>();

        for (Integer dataTaskId : recordsOfDataMap.keySet()) {
            DataTaskRecord dataTaskRecord = dataTaskRecordMapper.findById(dataTaskId);

            CommonBatchNumberDTO commonBatchNumberDTO = commonBatchNumberMapper.createDTOByIdAndCreateTime(dataTaskRecord.getDataBatchNumberId(), date);

            if (null != commonBatchNumberDTO) {

                commonBatchNumberDTO.setDetails(recordsOfDataMap.getOrDefault(dataTaskId, null));

                commonBatchNumberDTOS.add(commonBatchNumberDTO);
            }
        }

        return commonBatchNumberDTOS;
    }

    /**
     * 审核
     *
     * @param batchNumberId 审核的数据批号id
     * @param userId        用户id
     * @param reply         回复信息
     */
    @Override
    @Transactional
    public String audit(Integer batchNumberId, Integer userId, String reply, Integer isAccept) {
        Assert.isTrue(!(null == reply || reply.trim().isEmpty()), "请填写审核信息");

        //只能审核   状态为已提交未审核或者审核中的数据
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(batchNumberId);
        Integer dataType = commonBatchNumber.getDataType();
        //检查合法性
        Assert.notNull(commonBatchNumber, "审核的数据不存在");
        Integer status = commonBatchNumber.getStatus();
        Assert.isTrue(status.equals(BatchStatusEnum.COMMIT.status()) || status.equals(BatchStatusEnum.AUDITING.status()), "审核的数据为不可审核状态");

        //取出此数据
        Assert.isTrue(isAccept.equals(0) || isAccept.equals(1), "请选择审核是否通过");


        //找到数据流程对应表
        DataTaskRecord dataTaskRecord = dataTaskRecordMapper.findByDataBatchNumberId(batchNumberId);
        //找到审核记录表
        TaskHandlingRecord taskHandlingRecord = taskHandlingRecordMapper.toDoItem(userId, 1, dataTaskRecord.getId());
        Assert.notNull(taskHandlingRecord, "用户不存在或者无权审核");

        taskHandlingRecord.setVisible(0);
        taskHandlingRecord.setHandleTime(new Date());
        taskHandlingRecord.setHandleReply(reply);
        //更新审核记录表
        taskHandlingRecordMapper.update(taskHandlingRecord);

        //如果审核没有通过
        if (isAccept.equals(0)) {
            commonBatchNumber.setStatus(BatchStatusEnum.NOT_PASS.status());
            taskHandlingRecord.setVisible(-1);
            taskHandlingRecordMapper.update(taskHandlingRecord);
            commonBatchNumberMapper.update(commonBatchNumber);
            return handleAfterAudit(commonBatchNumber, userId, false);
        }

        //找下一条记录表
        TaskHandlingRecord taskHandlingRecordNext = taskHandlingRecordMapper.next(taskHandlingRecord.getId(), dataTaskRecord.getId());
        //更新下一条记录表
        //如果还有下一条
        if (taskHandlingRecordNext != null) {
            //状态改为审核中
            commonBatchNumber.setStatus(BatchStatusEnum.AUDITING.status());
            taskHandlingRecordNext.setVisible(1);
            taskHandlingRecordMapper.update(taskHandlingRecordNext);
            commonBatchNumberMapper.update(commonBatchNumber);

            //工艺参数改为审核中
            if(dataType.equals(BatchTypeEnum.PROCESS_PARAMETER.typeCode())){
                ProcessParametersListHeadExample example = new ProcessParametersListHeadExample();
                example.createCriteria().andApprovalProcessCodeEqualTo(batchNumberId);
                List<ProcessParametersListHead> heads = processParametersListHeadMapper.selectByExample(example);
                ProcessParametersListHead head = heads.get(0);
                head.setStatusFlag(new Integer(2).byteValue());
                processParametersListHeadMapper.updateByPrimaryKeySelective(head);
            }
            return String.format("%s数据审核通过,继续审核", commonBatchNumber.getBatchNumber());
        }

        //没有下一条 审核通过
        commonBatchNumber.setStatus(BatchStatusEnum.PASS.status());
        commonBatchNumber.setPassTime(new Date());
        commonBatchNumberMapper.update(commonBatchNumber);
        return handleAfterAudit(commonBatchNumber, userId, true);
    }

    /**
     * 审核前对不同数据要进行再处理
     */
    private String handleBeforeAudit(CommonBatchNumber batchData, Integer isUrgent) {
        Integer status = batchData.getStatus();
        Integer dataType = batchData.getDataType();
        // 所有的审核都不能在提交后或者审核中再提交
        Assert.isTrue(!(status.equals(BatchStatusEnum.COMMIT.status()) || status.equals(BatchStatusEnum.AUDITING.status())), String.format("%s批号已提交,请等待", batchData.getBatchNumber()));

        // 原料出库/成品出库,先修改状态
        if (dataType.equals(BatchTypeEnum.REPO_OUT_APPLY_RAW.typeCode())||dataType.equals(BatchTypeEnum.REPO_OUT_APPLY_END.typeCode())){
            iRepoOutService.outStart(batchData.getId(),BatchStatusEnum.AUDITING.status());
        }
        // 如果是原材料/中间品/成品检测项目的送审
        // 处于审核通过/不通过状态 查看检测项目,看是否能进行送审
        if (dataType.equals(BatchTypeEnum.QUALITY_INTERMEDIATE_TEST.typeCode())
                && (status.equals(BatchStatusEnum.PASS.status()) || status.equals(BatchStatusEnum.NOT_PASS.status()))) {


            // 迭代找出最新数据
            Integer lastedId = commonBatchUtil.lastId(batchData.getId());

            // 检测项目列表中 存在 没有进行过审核或者审核没有通过的,可以再次送审这些检测项目
            TestReportRecord testRecord = testReportRecordMapper.getByBatchNumberId(lastedId);

            //数据改变，删除缓存
            redisTemplate.delete("middle_pro_de_"+testRecord.getId());

            SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(testRecord.getId());
            logger.info("测试功能:化验重复批量送审操作=====================>");
            // ==>拿到每个检测项目了！
            List<TestItemResultRecord> testResults = testItemResultRecordMapper.getByTestReportId(testRecord.getId());
            AtomicReference<Integer> repeatVerify = new AtomicReference<>(0);
            testResults.forEach(e -> {
                if (e.getIsAudit() == null || e.getIsAudit().equals(0)) repeatVerify.set(1);
            });
            Assert.isTrue(repeatVerify.get().equals(1), "不存在没有审核通过的项目,不能重复送审");


            // 模仿CommonBatch  也会多一个新的commonBatchNumber todo 得想办法屏蔽掉
            CommonBatchNumber copyNumber = new CommonBatchNumber();
            copyNumber
                    .setDataType(batchData.getDataType())
                    .setBatchNumber(NumberGenerator.batchNumberGenerator(batchData.getDataType()))
                    .setCreatePersonId(batchData.getCreatePersonId())
                    .setCreateTime(batchData.getCreateTime())
                    .setMemo(batchData.getMemo())
                    .setIsPublished(batchData.getIsPublished())
                    .setDescription(batchData.getDescription());
            commonBatchNumberMapper.insert(copyNumber);

            commonBatchNumberChildMapper.insert(new CommonBatchNumberChild().setCommonBatchNumberId(batchData.getId()).setChildId(copyNumber.getId()));
            // 审的是这个！！！！！！
            batchData.setId(copyNumber.getId());


            // 模仿sampleDelivering 可能会多一个重复的样品送检 todo 得想办法屏蔽掉
            sampleDeliveringRecord.setId(null).setAcceptStatus(QualitySampleStatusEnum.SAMPLE_ACCEPTED.get());
            sampleDeliveringRecordMapper.insert(sampleDeliveringRecord);

            // 模仿testReportRecord
            testRecord
                    .setId(null)
                    .setBatchNumberId(copyNumber.getId())
                    .setSampleDeliveringRecordId(sampleDeliveringRecord.getId());

            testReportRecordMapper.saveOne(testRecord);


            // 模仿testReportRecordResult
            testResults.forEach(e -> {
                e.setId(null);
                e.setTestReportRecordId(testRecord.getId());
            });
            testItemResultRecordMapper.batchInsert(testResults);


        } else {
            Assert.isTrue(!(status.equals(BatchStatusEnum.PASS.status())), "批号已通过审核,无需重复送审");
        }
        //将送审数据状态设置为已提交未审核以及设置审核紧急程度
        batchData.setStatus(BatchStatusEnum.COMMIT.status());
        batchData.setIsUrgent(isUrgent);
        commonBatchNumberMapper.update(batchData);
        return String.format("%s批号进行审核前预处理", batchData.getBatchNumber());
    }

    /**
     * 审核完成后要对不同数据进行再处理
     */
    private String handleAfterAudit(CommonBatchNumber batchNumber, Integer userId, Boolean pass) {
        Integer batchNumberId = batchNumber.getId();
        Integer dataType = batchNumber.getDataType();
        // 原材料/中间品/成品数据审核后 要把结果录入到每个检测项目中
        if (dataType.equals(BatchTypeEnum.QUALITY_INTERMEDIATE_TEST.typeCode())) {
            TestReportRecord testReportRecord = testReportRecordMapper.getByBatchNumberId(batchNumberId);
            List<TestItemResultRecord> testResults = testItemResultRecordMapper.getByTestReportId(testReportRecord.getId());
            //数据改变，删除缓存
            redisTemplate.delete("middle_pro_de_"+testReportRecord.getSampleDeliveringRecordId());
            // 只审有结果的  没审核过的  审核不通过的
            testResults.forEach(e -> {
                if (e.getTestResult() != null &&e.getTestResult().trim().length()!=0&&(e.getIsAudit() == null || e.getIsAudit().equals(0))) {
                    e.setIsAudit(pass ? 1 : 0);
                }
            });
            testItemResultRecordMapper.batchUpdate(testResults);
        }
        //工艺参数审核后
        if(dataType.equals(BatchTypeEnum.PROCESS_PARAMETER.typeCode())){
            ProcessParametersListHeadExample example = new ProcessParametersListHeadExample();
            example.createCriteria().andApprovalProcessCodeEqualTo(batchNumberId);
            List<ProcessParametersListHead> heads = processParametersListHeadMapper.selectByExample(example);
            ProcessParametersListHead head = heads.get(0);
            if(pass){
                head.setStatusFlag(new Integer(3).byteValue());
            }else{
                head.setStatusFlag(new Integer(4).byteValue());
            }
            processParametersListHeadMapper.updateByPrimaryKeySelective(head);
        }

        String message;
        if (pass) message = handleAfterPass(batchNumber, userId);
        else message = handleAfterUnPass(batchNumber, userId);
        logger.info("handleAfterAudit===================>{}", message);
        return message;
    }

    /**
     * 审核通过后的处理
     */
    private String handleAfterPass(CommonBatchNumber batchNumber, Integer userId) {
        Integer batchNumberId = batchNumber.getId();
        Integer dataType = batchNumber.getDataType();

        //进货/成品检测 不合格审核通过后 需要生成不合格品评审表
        if (dataType.equals(BatchTypeEnum.QUALITY_PURCHASE.typeCode())
                || dataType.equals(BatchTypeEnum.QUALITY_PRODUCT_TEST.typeCode())) {
            unqualifiedTestReportRecordService.generate(batchNumberId, userId);
        }

        //不合格品评审表通过后 需要生成不合格品跟踪表
        if (dataType.equals(BatchTypeEnum.UNQUALIFIED_RECORD.typeCode())) {
            unqualifiedTracingRecordService.generate(batchNumberId, userId);
        }

        //仓库出库申请成功后 需要向仓库发送 出库数据
        if (dataType.equals(BatchTypeEnum.REPO_OUT_APPLY_END.typeCode()) || dataType.equals(BatchTypeEnum.REPO_OUT_APPLY_RAW.typeCode())) {
            iRepoOutService.outStart(batchNumberId,BatchStatusEnum.PASS.status());
        }

        //红单申请成功后 需要减少库存
        if (dataType.equals(BatchTypeEnum.REPO_RED_TABLE.typeCode())) {
            repoRedTableService.updateStock(batchNumberId);
        }

        //制程检测通过后 需要将之前的数据变为迭代数据
        if (dataType.equals(BatchTypeEnum.QUALITY_PRODUCT.typeCode())) {
            procedureTestRecordService.setIteration(batchNumberId);
        }

        return batchNumber.getBatchNumber() + ":审核通过";
    }

    /**
     * 审核未通过进行的处理
     */
    private String handleAfterUnPass(CommonBatchNumber batchNumber, Integer userId) {
        //出库申请不通过 发送feign
        Integer dataType = batchNumber.getDataType();
        if (dataType.equals(BatchTypeEnum.REPO_OUT_APPLY_END.typeCode()) || dataType.equals(BatchTypeEnum.REPO_OUT_APPLY_RAW.typeCode())) {
            iRepoOutService.outStart(batchNumber.getId(),BatchStatusEnum.NOT_PASS.status());
        }
        return batchNumber.getBatchNumber() + ":审核未通过";
    }

    /**
     * 查看数据的反馈信息
     *
     * @param batchNumberId
     * @return
     */
    @Override
    public List<CommonNameDTO> replies(Integer batchNumberId) {
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(batchNumberId);
        if (commonBatchNumber == null) return null;

        Integer status = commonBatchNumber.getStatus();
        logger.info("此批批号状态为" + status);

        //审核中 已提交未审核 已保存未提交 是没有反馈的
        if (status.equals(BatchStatusEnum.COMMIT.status()) || status.equals(BatchStatusEnum.SAVE.status()))
            return null;

        DataTaskRecord dataTaskRecord = dataTaskRecordMapper.findByDataBatchNumberId(batchNumberId);

        Assert.notNull(dataTaskRecord, "此批号没有送审信息,请检查");
        logger.info("此批批号对应id为" + dataTaskRecord.getId());
        List<TaskHandlingRecord> taskHandlingRecords = taskHandlingRecordMapper.findByDataTaskId(dataTaskRecord.getId());
        if (null == taskHandlingRecords || 0 == taskHandlingRecords.size()) return null;

        List<CommonNameDTO> commonNameDTOS = new ArrayList<>();

        //todo 查看历史审核信息

        for (TaskHandlingRecord taskHandlingRecord : taskHandlingRecords) {
            Integer handlerId = taskHandlingRecord.getHandler();

            AuthUserDTO userDTO = authUserMapper.byId(handlerId);

            String name = userDTO.getName();

            CommonNameDTO<TaskHandlingRecord> generalUserDTO = new CommonNameDTO<TaskHandlingRecord>();

            generalUserDTO.setName(name);
            generalUserDTO.setDetail(taskHandlingRecord);

            commonNameDTOS.add(generalUserDTO);

        }


        return commonNameDTOS;
    }


}
