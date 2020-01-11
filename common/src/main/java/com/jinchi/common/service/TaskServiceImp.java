package com.jinchi.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.constant.QualityTaskTypeEnum;
import com.jinchi.common.domain.CommonBatchNumber;
import com.jinchi.common.domain.Task;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.TaskDTO;
import com.jinchi.common.mapper.AuthUserMapper;
import com.jinchi.common.mapper.CommonBatchNumberMapper;
import com.jinchi.common.mapper.TaskMapper;
import com.jinchi.common.utils.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author：XudongHu
 * @description: 审核任务
 * @date:20:41 2018/11/19
 */
@Service
public class TaskServiceImp implements TaskService {
    private static final Integer BATCH_QUALITY_AUDITTASK = BatchTypeEnum.QUALITY_AUDIT_TASK.typeCode();
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private AuthUserMapper authUserMapper;

    /**
     * 新增审核流程
     */
    @Override
    @Transactional
    public CommonBatchNumberDTO<List<TaskDTO>> add(CommonBatchNumberDTO<List<TaskDTO>> batchTaskPersonDTO) {
        /**
         * 验证是否为空
         */
        CommonBatchNumber commonBatchNumber = batchTaskPersonDTO.getCommonBatchNumber();
        //名称不能为空
        Assert.notNull(commonBatchNumber.getDescription(), "流程名称不能为空");
        //状态不能为空
        Assert.notNull(commonBatchNumber.getStatus(), "流程状态不能为空");
        //操作人列表不能为空
        List<TaskDTO> persons = batchTaskPersonDTO.getDetails();
        Assert.notEmpty(persons, "操作人列表不能为空!");

        /**
         * 批号存储
         */
        commonBatchNumber.setBatchNumber(NumberGenerator.batchNumberGenerator(BATCH_QUALITY_AUDITTASK));
        //设定批号 时间
        commonBatchNumber.setCreateTime(new Date());
        //设定批号类型    因为是审核流程,设定成流程管理数据类型1
        commonBatchNumber.setDataType(BATCH_QUALITY_AUDITTASK);
        //先存储批号
        commonBatchNumberMapper.insert(commonBatchNumber);
        //Mybatis返回主键
        Integer batchNumberId = commonBatchNumber.getId();

        /**
         * 审核任务存储
         */
        this.insertOperatorOrder(persons, batchNumberId);

        return batchTaskPersonDTO;
    }


    /**
     * 根据batchNumber删除
     */
    @Override
    @Transactional
    public void deleteByBatchNumberId(Integer batchNumberId) {
        CommonBatchNumber byId = commonBatchNumberMapper.byId(batchNumberId);

        Assert.isTrue(null != byId && byId.getDataType().equals(BatchTypeEnum.QUALITY_AUDIT_TASK.typeCode()), "流程不存在");

        taskMapper.deleteByBatchNumberId(batchNumberId);

        commonBatchNumberMapper.deleteById(batchNumberId);

    }

    /**
     * 批量删除（根据batchNumber）
     */
    @Override
    @Transactional
    public void deleteByBatch(List<Integer> ids) {

        for (int i = 0; i < ids.size(); i++) {
            Integer id = ids.get(i);

            CommonBatchNumber byId = commonBatchNumberMapper.byId(id);

            Assert.isTrue(byId != null && byId.getDataType().equals(BATCH_QUALITY_AUDITTASK), "删除失败,流程管理数据不存在");
        }

        taskMapper.deleteByBatchNumberIds(ids);

        commonBatchNumberMapper.deleteByIds(ids);
    }

    /**
     * 更新
     */
    @Override
    @Transactional
    public CommonBatchNumberDTO<List<TaskDTO>> update(CommonBatchNumberDTO<List<TaskDTO>> batchTaskPersonDTO) {
        //名称不能为空
        CommonBatchNumber commonBatchNumber = batchTaskPersonDTO.getCommonBatchNumber();
        Assert.notNull(commonBatchNumber.getDescription(), "流程名称不能为空");
        Assert.notNull(commonBatchNumber.getStatus(), "流程状态不能为空");
        //查看是否存在
        Integer batchNumberId = commonBatchNumber.getId();
        Assert.notNull(batchNumberId, "更新批号id不能为空");
        CommonBatchNumber dataBatchNumber = commonBatchNumberMapper.byId(commonBatchNumber.getId());
        Assert.notNull(dataBatchNumber, "更新失败,批号不存在");
        dataBatchNumber.setStatus(commonBatchNumber.getStatus());
        dataBatchNumber.setDescription(commonBatchNumber.getDescription());

        //查看操作人列表是否为空
        List<TaskDTO> persons = batchTaskPersonDTO.getDetails();
        Assert.notEmpty(persons, "更新失败,操作人列表为空");


        //更新批号部分
        commonBatchNumberMapper.update(dataBatchNumber);
        //删除审核任务部分
        taskMapper.deleteByBatchNumberId(batchNumberId);
        //再新增审核人部分
        this.insertOperatorOrder(persons, batchNumberId);

        return batchTaskPersonDTO;
    }


    /**
     * 根据批号id查询
     */
    @Override
    public CommonBatchNumberDTO<List<TaskDTO>> findByBatchNumberId(Integer batchNumberId) {
        CommonBatchNumberDTO commonBatchNumberDTO;

        if (null == (commonBatchNumberDTO = commonBatchNumberMapper.createDTOByIdAndDataType(batchNumberId, BatchTypeEnum.QUALITY_AUDIT_TASK.typeCode())))
            return null;

        List<Task> tasks = taskMapper.findByBatchNumberId(batchNumberId);

        List<TaskDTO> taskDTOS = this.convert2DTO(tasks);

        commonBatchNumberDTO.setDetails(taskDTOS);

        return commonBatchNumberDTO;
    }


    /**
     * 查询所有
     */
    @Override
    public List<CommonBatchNumberDTO> findAll() {
        List<CommonBatchNumberDTO> commonBatchNumberDTOS = commonBatchNumberMapper.createDTOsByCommonDTO(new CommonBatchNumberDTO()
                .setCommonBatchNumber(new CommonBatchNumber().setDataType(BatchTypeEnum.QUALITY_AUDIT_TASK.typeCode())));
        return commonBatchNumberDTOS;
    }

    /**
     * 查询所有已提交的审核流程
     *
     * @return
     */
    @Override
    public List<CommonBatchNumberDTO> validTasks() {
        List<CommonBatchNumberDTO> commonBatchNumberDTOS = commonBatchNumberMapper.createDTOsByCommonDTO(new CommonBatchNumberDTO()
                .setCommonBatchNumber(new CommonBatchNumber()
                        .setStatus(BatchStatusEnum.PASS.status())
                        .setDataType(BatchTypeEnum.QUALITY_AUDIT_TASK.typeCode())));
        return commonBatchNumberDTOS;
    }

    /**
     * 根据流程名称模糊查询所有-分页
     *
     * @param taskName  实际上是批号的描述
     * @param page
     * @param size
     * @param fieldName
     * @param orderType
     * @return
     */
    @Override
    public PageInfo findAllByNameLikeByPage(String taskName, Integer page, Integer size, String fieldName, String orderType) {
        String order = fieldName + " " + orderType;
        PageHelper.startPage(page, size, order);
        List<CommonBatchNumberDTO> commonBatchNumberDTOS = commonBatchNumberMapper.createDTOsByCommonDTO(new CommonBatchNumberDTO()
                .setCommonBatchNumber(new CommonBatchNumber()
                        .setDescription(taskName)
                        .setDataType(BatchTypeEnum.QUALITY_AUDIT_TASK.typeCode())));
        PageInfo<CommonBatchNumberDTO> pageInfo = new PageInfo<>(commonBatchNumberDTOS);
        return pageInfo;
    }


    /**
     * 按顺序储存操作人
     *
     * @param persons
     * @param batchNumberId
     */
    @Transactional
    public void insertOperatorOrder(List<TaskDTO> persons, Integer batchNumberId) {
        //按顺序添加操作人
        List<Task> tasks = new ArrayList<>();
        //是否可见,新增时初始化只对第一个操作人可见
        ArrayList<Integer> personList = new ArrayList<>();
        //0开始 -1结束
        //1个人    0,1,-1
        //2个人    0,1,2,-1 以此类推
        for (int i = 0; i < persons.size(); i++) {
            personList.add(i);
        }
        personList.add(-1);
        Integer point = 0;
        for (TaskDTO person : persons) {
            Assert.notNull(authUserMapper.byId(person.getUserId()), "id为" + person.getUserId() + "的用户不存在");
            //批号id,用户id,指责描述,任务类型是确定的
            //TODO 任务类型默认为审核任务类型
            Task task = new Task(batchNumberId, person.getUserId(), person.getResponsibility(), QualityTaskTypeEnum.TASK_AUDIT.get(), personList.get(point), personList.get(point + 1));
            tasks.add(task);
            point++;
        }
        //储存审核任务
        taskMapper.insertAll(tasks);
    }


    /**
     * 转换成DTO
     *
     * @param tasks
     * @return
     */
    public List<TaskDTO> convert2DTO(List<Task> tasks) {
        if (null == tasks || 0 == tasks.size()) return null;

        //根据previous排序
        Collections.sort(tasks, Comparator.comparing(Task::getPrevious));

        List<TaskDTO> taskDTOS = new ArrayList<>();
        for (Task task : tasks) {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setUserId(task.getOperator());
            taskDTO.setResponsibility(task.getOperationDescription());
            taskDTO.setPersonName(authUserMapper.byId(task.getOperator()).getName());

            taskDTOS.add(taskDTO);
        }
        return taskDTOS;
    }

}
