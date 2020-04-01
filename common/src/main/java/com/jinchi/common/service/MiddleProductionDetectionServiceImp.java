package com.jinchi.common.service;

import com.alibaba.druid.sql.ast.statement.SQLUnionQuery;
import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.constant.QualitySampleStatusEnum;
import com.jinchi.common.constant.QualitySampleTypeEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.*;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.CommonBatchUtil;
import com.jinchi.common.utils.TestItemUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by WangZhihao on 2018/12/11.
 */
@Service
public class MiddleProductionDetectionServiceImp implements MiddleProductionDetectionService {
    @Autowired
    private SampleDeliveringRecordMapper sampleDeliveringRecordMapper;
    @Autowired
    private TestReportRecordMapper testReportRecordMapper;
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private DeliveryFactoryMapper deliveryFactoryMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    private TestItemResultRecordMapper testItemResultRecordMapper;
    @Autowired
    private TestItemMapper testItemMapper;
    @Autowired
    private AuthRoleMapper authRoleMapper;
    @Autowired
    private TestItemUtil testItemUtil;
    @Autowired
    private CommonBatchUtil commonBatchUtil;
    @Autowired
    private QualityCommonBatchNumberExtraMapper extraMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ProductionBatchInfoService batchInfoService;
    @Autowired
    ProductionBatchInfoMapper batchInfoMapper;
    @Autowired
    BasicInfoBatchConfigMapper configMapper;
    @Autowired
    QualityBaseDetectItemMapper detectItemMapper;

    private SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<RawTestReportDTO> findAll() {
        List<SampleDeliveringRecord> sampleDeliveringRecords = sampleDeliveringRecordMapper.getAllByFactors(QualitySampleTypeEnum.SAMPLE_INTERMEDIATE.get(), QualitySampleStatusEnum.SAMPLE_ACCEPTED.get(), null, null);
        if (null == sampleDeliveringRecords || 0 == sampleDeliveringRecords.size()) return null;
        List<RawTestReportDTO> dtoList = new ArrayList<>(sampleDeliveringRecords.size());

        for (SampleDeliveringRecord record : sampleDeliveringRecords) {
            RawTestReportDTO dto = new RawTestReportDTO();

            TestReportRecord testReportRecord = testReportRecordMapper.getBySampleDeliveringRecordId(record.getId());
            Assert.notNull(testReportRecord, "检测结果不存在");
            CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(testReportRecord.getBatchNumberId());
            AuthUserDTO authUserDTO1 = authUserMapper.byId(record.getDelivererId());
            DeliveryFactory deliveryFactory = deliveryFactoryMapper.findById(record.getDeliveryFactoryId());
            //RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(record.getSerialNumberId());
            QualityBaseDetectItem detectItem = detectItemMapper.selectByPrimaryKey(record.getSerialNumberId().longValue());

            String itemNames = testItemUtil.convertItemNames(record.getTestItems());

            dto.setTestReportRecord(testReportRecord);
            dto.setSampleDeliveringRecord(record);
            dto.setCommonBatchNumber(commonBatchNumber);
            dto.setDeliverer(authUserDTO1.getName());
            dto.setDeliveryFactoryName(deliveryFactory.getName());
            //dto.setSerialNumber(repoBaseSerialNumber.getSerialNumber());
            dto.setSerialNumber("新受检物料没有批号");
            dto.setTestItemString(itemNames);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public RawTestReportDTO findDetailsById(Integer id) {
        RawTestReportDTO rawTestReportDTO = new RawTestReportDTO();
        List<TestDTO> testDTOS = new ArrayList<>();

        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(id);
        if (null == sampleDeliveringRecord || sampleDeliveringRecord.getType() != QualitySampleTypeEnum.SAMPLE_INTERMEDIATE.get() || sampleDeliveringRecord.getAcceptStatus() != QualitySampleStatusEnum.SAMPLE_ACCEPTED.get())
            return null;
        //RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(sampleDeliveringRecord.getSerialNumberId());
        QualityBaseDetectItem detectItem = detectItemMapper.selectByPrimaryKey(sampleDeliveringRecord.getSerialNumberId().longValue());

        TestReportRecord testReportRecord = testReportRecordMapper.getBySampleDeliveringRecordId(sampleDeliveringRecord.getId());
        AuthUserDTO authUserDTO = authUserMapper.byId(testReportRecord.getJudger());
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(testReportRecord.getBatchNumberId());

        //=====================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        Integer lastId = commonBatchUtil.lastId(commonBatchNumber.getId());

        TestReportRecord lastReport = testReportRecordMapper.getByBatchNumberId(lastId);
        List<TestItemResultRecord> testItemResultRecords = testItemResultRecordMapper.getByTestReportId(lastReport.getId());

        testItemResultRecords.forEach(e -> {
            TestDTO testDTO = new TestDTO();
            TestItem testItem = testItemMapper.find(e.getTestItemId());
            testDTO.setName(testItem == null ? "" : testItem.getName());
            testDTO.setTestItemResultRecord(e);
            testDTO.setUnit(testItem == null ? "" : testItem.getUnit());
            testDTO.setAuditResult(e.getIsAudit());
            testDTOS.add(testDTO);
        });
        //====================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        rawTestReportDTO.setTestDTOS(testDTOS);
        rawTestReportDTO.setSampleDeliveringRecord(sampleDeliveringRecord);
        //rawTestReportDTO.setMaterialName(repoBaseSerialNumber.getMaterialName());
        //rawTestReportDTO.setSerialNumber(repoBaseSerialNumber.getSerialNumber());
        rawTestReportDTO.setMaterialName(detectItem.getName());
        rawTestReportDTO.setSerialNumber("新受检物料没有批号");
        String username = authUserDTO == null ? "" : authUserDTO.getName();
        rawTestReportDTO.setTester(username);
        rawTestReportDTO.setTestReportRecord(testReportRecord);
        rawTestReportDTO.setCommonBatchNumber(commonBatchNumber);

        return rawTestReportDTO;
    }

    /**
     * 录检
     *
     * @param rawTestReportDTO
     * @return
     */
    @Override
    public RawTestReportDTO update(RawTestReportDTO rawTestReportDTO) {
        //数据变化，删除缓存
        redisTemplate.delete("middle_pro_de_"+rawTestReportDTO.getSampleDeliveringRecord().getId());
        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(rawTestReportDTO.getSampleDeliveringRecord().getId());
        Assert.isTrue(null != sampleDeliveringRecord
                && sampleDeliveringRecord.getType().equals(QualitySampleTypeEnum.SAMPLE_INTERMEDIATE.get())
                && sampleDeliveringRecord.getAcceptStatus().equals(QualitySampleStatusEnum.SAMPLE_ACCEPTED.get()), "录检失败,送检数据异常");

        //更新审批记录
        TestReportRecord testReportRecord = testReportRecordMapper.getBySampleDeliveringRecordId(sampleDeliveringRecord.getId());

        //==========================================================>
        Integer batchNumberId = testReportRecord.getBatchNumberId();
        Integer lastId = commonBatchUtil.lastId(batchNumberId);
        TestReportRecord lastRecord = testReportRecordMapper.getByBatchNumberId(lastId);

        lastRecord
                .setIsQualified(rawTestReportDTO.getTestReportRecord().getIsQualified())
                .setJudger(rawTestReportDTO.getTestReportRecord().getJudger())
                .setJudgeDate(new Date());
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(lastId);
        testReportRecordMapper.update(lastRecord);

        List<TestDTO> testDTOS = rawTestReportDTO.getTestDTOS();
        Assert.notNull(testDTOS, "检测结果都为空");

        List<TestItemResultRecord> oldResults = testItemResultRecordMapper.getByTestReportId(lastRecord.getId());
        Map<Integer, TestItemResultRecord> oldResultMap = new HashMap<>();
        oldResults.forEach(e -> oldResultMap.put(e.getTestItemId(), e));

        //审核通过的 不能改结果了
        for (TestDTO dto : testDTOS) {
            TestItemResultRecord result = dto.getTestItemResultRecord();
            TestItemResultRecord oldResult = testItemResultRecordMapper.findById(result.getId());
            TestItemResultRecord newResult = oldResultMap.get(oldResult.getTestItemId());
            if (newResult.getIsAudit() == null || newResult.getIsAudit().equals(0)) {
                newResult.setTestResult(result.getTestResult());
            }
            testItemResultRecordMapper.update(newResult);
        }
        //============================================================>
        rawTestReportDTO.setCommonBatchNumber(commonBatchNumber);
        return rawTestReportDTO;
    }

    @Override
    public PageBean<RawTestReportDTO> findByFactoryNameByPage(String factoryName, PageBean pageBean) {
        ValueOperations<String,RawTestReportDTO> op = redisTemplate.opsForValue();
        List<SampleDeliveringRecord> sampleDeliveringRecords = sampleDeliveringRecordMapper.getAllByFactors(QualitySampleTypeEnum.SAMPLE_INTERMEDIATE.get(), QualitySampleStatusEnum.SAMPLE_ACCEPTED.get(), factoryName, pageBean);
        if (null == sampleDeliveringRecords || 0 == sampleDeliveringRecords.size()) return null;
        List<RawTestReportDTO> dtoList = new ArrayList<>(sampleDeliveringRecords.size());

        for (SampleDeliveringRecord record : sampleDeliveringRecords) {
            String key = "middle_pro_de_"+record.getId();
            if(redisTemplate.hasKey(key)){
                dtoList.add(op.get(key));
                continue;
            }
            RawTestReportDTO dto = new RawTestReportDTO();

            TestReportRecord testReportRecord = testReportRecordMapper.getBySampleDeliveringRecordId(record.getId());
            Assert.notNull(testReportRecord, "检测结果不存在");
            CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(testReportRecord.getBatchNumberId());

            //==>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            if (commonBatchNumber.getStatus().equals(BatchStatusEnum.PASS.status()) || commonBatchNumber.getStatus().equals(BatchStatusEnum.NOT_PASS.status())) {

                Integer batchNumberId = commonBatchNumber.getId();
                RawTestReportDTO detailsById = (RawTestReportDTO) findDetailsByBatchNumberId(batchNumberId);
                AtomicReference<Integer> isFullAudit = new AtomicReference<>(1);
                if(detailsById!=null) {
                    Integer testReportRecordId = detailsById.getTestReportRecord().getId();
                    List<TestItemResultRecord> results = testItemResultRecordMapper.getByTestReportId(testReportRecordId);

                    results.forEach(e -> {
                        if (e.getIsAudit() == null || e.getIsAudit().equals(0)) isFullAudit.set(0);
                    });
                }

                dto.setIsFullAudit(isFullAudit.get());
            }
            //==>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

            AuthUserDTO authUserDTO1 = authUserMapper.byId(record.getDelivererId());
            DeliveryFactory deliveryFactory = deliveryFactoryMapper.findById(record.getDeliveryFactoryId());

            //RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(record.getSerialNumberId());
            QualityBaseDetectItem detectItem = detectItemMapper.selectByPrimaryKey(record.getSerialNumberId().longValue());

            String itemNames = testItemUtil.convertItemNames(record.getTestItems());

            QualityCommonBatchNumberExtraExample extraExample = new QualityCommonBatchNumberExtraExample();
            extraExample.createCriteria().andCommonBatchIdEqualTo(record.getId());
            List<QualityCommonBatchNumberExtra> extras = extraMapper.selectByExample(extraExample);
            if(extras.size() != 0){
                dto.setBatch(extras.get(0).getBatch());
            }
            dto.setTestReportRecord(testReportRecord);
            dto.setSampleDeliveringRecord(record);
            dto.setCommonBatchNumber(commonBatchNumber);
            dto.setDeliverer(authUserDTO1.getName());
            dto.setDeliveryFactoryName(deliveryFactory.getName());
            //dto.setSerialNumber(repoBaseSerialNumber.getMaterialName());
            dto.setSerialNumber(detectItem==null?"历史物料（未知）":detectItem.getName());
            dto.setTestItemString(itemNames);
            dtoList.add(dto);
            op.set(key,dto);
        }

        pageBean.setList(dtoList);
        pageBean.setTotal(sampleDeliveringRecordMapper.countSum(QualitySampleTypeEnum.SAMPLE_INTERMEDIATE.get(), QualitySampleStatusEnum.SAMPLE_ACCEPTED.get(), factoryName));
        return pageBean;
    }

    @Override
    public Result<Object> publish(Integer id) {
        //数据改变，删除缓存
        redisTemplate.delete("middle_pro_de_"+id);
        TestReportRecord testReportRecord = testReportRecordMapper.getBySampleDeliveringRecordId(id);
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(testReportRecord.getBatchNumberId());
        commonBatchNumber.setIsPublished(1);
        commonBatchNumberMapper.update(commonBatchNumber);
        // 1、根据id  在 quality_common_batch_number_extra 查到 batch

        QualityCommonBatchNumberExtraExample extraExample = new QualityCommonBatchNumberExtraExample();
        extraExample.createCriteria().andCommonBatchIdEqualTo(id);
        List<QualityCommonBatchNumberExtra> extras = extraMapper.selectByExample(extraExample);

        String batch1 = extras.get(0).getBatch();

        // 2、拿着这个batch 与 production_batch_info 之中 batch 字段 进行匹配
        List<ProductionBatchInfo> list = batchInfoService.compareByBatch(batch1);
        if (list.size() > 0) {
            String batch = list.get(0).getBatch();

            String process = batch1.substring(batch.length()).substring(0, 2);

            //查批次信息基础数据表 获取batch_num
            BasicInfoBatchConfigExample example = new BasicInfoBatchConfigExample();
            example.createCriteria();
            List<BasicInfoBatchConfig> basicInfoBatchConfigs = configMapper.selectByExample(example);

            Byte batchNum = basicInfoBatchConfigs.size() == 0 ? 0 : basicInfoBatchConfigs.get(0).getBatchNum();

            if (process.equals("JQ")) {
                for (ProductionBatchInfo batchInfo : list) {
                    if (batchInfo.getProcess().equals("JQ")) {
                        batchInfo.setEndTime(new Date());
                        batchInfo.setStatusFlag(true);//工序完成 更新这条记录
                        batchInfoMapper.updateByPrimaryKeySelective(batchInfo);

//                        ProductionBatchInfo batchInfo1 = batchInfo;

                        //生成batch_num-1个 流水号依次+1 其他一模一样的记录
                        for (int i = 1; i < batchNum; i++) {
                            batchInfoService.genSameRecord(batchInfo);
                        }


                        //在上面基础上 流水号 +1 还有其他 修改 生成新记录
//                        batchInfoService.genNewRecord(batchInfo1);
                    }
                }
            }

            if (process.equals("JH")) {
                for (ProductionBatchInfo batchInfo : list) {
                    if (batchInfo.getProcess().equals("JQ")) {
                        batchInfo.setEndTime(new Date());
                        batchInfo.setStatusFlag(true);//工序完成 更新这条记录
                        batchInfoMapper.updateByPrimaryKeySelective(batchInfo);


//                        ProductionBatchInfo info1 = batchInfo;
                        //生成batch_num-1个 流水号依次+1 其他一模一样的JQ记录
                        for (int i = 1; i < batchNum; i++) {
                            batchInfoService.genSameRecord(batchInfo);
                        }

                        //生成JH批次信息
                        batchInfo.setCode(null);
                        batchInfo.setProcess("JH");
                        batchInfoMapper.insertSelective(batchInfo);

//                        ProductionBatchInfo info2 = batchInfo;

                        //生成batch_num-1个 流水号依次+1 其他一模一样的JH记录
                        for (int i = 1; i < batchNum + 1; i++) {
                            batchInfoService.genSameRecord(batchInfo);
                        }

                        //生成新记录
//                        batchInfoService.genNewRecord(info1);
                    }
                }
            }
        }


        // 3、如果production_batch_info batch 字段包含于 1中的batch
        // 4、3中的batch 剪掉 1中 batch 剩余的头两个字符
        //    如果是 不是"JH" "JQ" 跳过
        //    6如果是 "JQ" 查找production_batch_info process 是 JQ 的记录 结束时间插入为 发布时间
        //    7如果是 "JH" 查找production_batch_info process 是 JQ 的记录 结束时间插入为 发布时间
        //      同时新增一条工序为JH的批次信息 除工序其他一样
        // 6 7 完成后 都要生成JQ新记录
        return null;
    }

    @Override
    public Object findDetailsByBatchNumberId(Integer id) {
        RawTestReportDTO rawTestReportDTO = new RawTestReportDTO();
        List<TestDTO> testDTOS = new ArrayList<>();
        // =======================================>
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(id);
        Integer lastId = commonBatchUtil.lastId(commonBatchNumber.getId());
        commonBatchNumber = commonBatchNumberMapper.byId(lastId);
        // =========================================>
        if (commonBatchNumber == null || !commonBatchNumber.getDataType().equals(BatchTypeEnum.QUALITY_INTERMEDIATE_TEST.typeCode())) {
            return null;
        }
        TestReportRecord testReportRecord = testReportRecordMapper.getByBatchNumberId(lastId);
        if (testReportRecord == null) {
            return null;
        }
        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(testReportRecord.getSampleDeliveringRecordId());
        if (null == sampleDeliveringRecord || !sampleDeliveringRecord.getType().equals(QualitySampleTypeEnum.SAMPLE_INTERMEDIATE.get()) || !sampleDeliveringRecord.getAcceptStatus().equals(QualitySampleStatusEnum.SAMPLE_ACCEPTED.get())) {
            return null;
        }
        //RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(sampleDeliveringRecord.getSerialNumberId());
        QualityBaseDetectItem detectItem = detectItemMapper.selectByPrimaryKey(sampleDeliveringRecord.getSerialNumberId().longValue());

        AuthUserDTO authUserDTO = authUserMapper.byId(testReportRecord.getJudger());
        String username = authUserDTO == null ? "" : authUserDTO.getName();
        List<TestItemResultRecord> testItemResultRecords = testItemResultRecordMapper.getByTestReportId(testReportRecord.getId());

        //==================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        // 只显示 有数据的并(没审核过or审核没通过的)
        for (TestItemResultRecord record : testItemResultRecords) {
            TestDTO testDTO = new TestDTO();
            if (record.getTestResult() != null && record.getTestResult().trim().length()!=0) {
                TestItem testItem = testItemMapper.find(record.getTestItemId());
                testDTO.setName(testItem == null ? "" : testItem.getName());
                testDTO.setTestItemResultRecord(record);
                testDTO.setUnit(testItem == null ? "" : testItem.getUnit());
                //审核结果展示
                testDTO.setAuditResult(record.getIsAudit());
                testDTOS.add(testDTO);
            }
        }
        //==================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        rawTestReportDTO.setTestDTOS(testDTOS);
        rawTestReportDTO.setSampleDeliveringRecord(sampleDeliveringRecord);
        //rawTestReportDTO.setMaterialName(repoBaseSerialNumber.getMaterialName());
        //rawTestReportDTO.setSerialNumber(repoBaseSerialNumber.getSerialNumber());
        rawTestReportDTO.setSerialNumber("新受检物料没有批号");
        rawTestReportDTO.setMaterialName(detectItem==null?"历史物料（未知）":detectItem.getName());
        rawTestReportDTO.setTester(username);
        rawTestReportDTO.setTestReportRecord(testReportRecord);
        rawTestReportDTO.setCommonBatchNumber(commonBatchNumber);

        return rawTestReportDTO;
    }

    /**
     * 朱工客户端需求 批号和编号互换
     * @param id
     * @return
     */
    @Override
    public Object findDetailsByBatchNumberIdForClient(Integer id) {
        RawTestReportDTO rawTestReportDTO = new RawTestReportDTO();
        List<TestDTO> testDTOS = new ArrayList<>();
        // =======================================>
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(id);
        Integer lastId = commonBatchUtil.lastId(commonBatchNumber.getId());
        commonBatchNumber = commonBatchNumberMapper.byId(lastId);
        // =========================================>
        if (commonBatchNumber == null || !commonBatchNumber.getDataType().equals(BatchTypeEnum.QUALITY_INTERMEDIATE_TEST.typeCode())) {
            return null;
        }
        TestReportRecord testReportRecord = testReportRecordMapper.getByBatchNumberId(lastId);
        if (testReportRecord == null) {
            return null;
        }
        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(testReportRecord.getSampleDeliveringRecordId());
        if (null == sampleDeliveringRecord || !sampleDeliveringRecord.getType().equals(QualitySampleTypeEnum.SAMPLE_INTERMEDIATE.get()) || !sampleDeliveringRecord.getAcceptStatus().equals(QualitySampleStatusEnum.SAMPLE_ACCEPTED.get())) {
            return null;
        }
        //RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(sampleDeliveringRecord.getSerialNumberId());
        QualityBaseDetectItem detectItem = detectItemMapper.selectByPrimaryKey(sampleDeliveringRecord.getSerialNumberId().longValue());

        AuthUserDTO authUserDTO = authUserMapper.byId(testReportRecord.getJudger());
        String username = authUserDTO == null ? "" : authUserDTO.getName();
        List<TestItemResultRecord> testItemResultRecords = testItemResultRecordMapper.getByTestReportId(testReportRecord.getId());

        //==================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        // 只显示 有数据的并(没审核过or审核没通过的)
        for (TestItemResultRecord record : testItemResultRecords) {
            TestDTO testDTO = new TestDTO();
            if (record.getTestResult() != null && record.getTestResult().trim().length()!=0) {
                TestItem testItem = testItemMapper.find(record.getTestItemId());
                testDTO.setName(testItem == null ? "" : testItem.getName());
                testDTO.setTestItemResultRecord(record);
                testDTO.setUnit(testItem == null ? "" : testItem.getUnit());
                //审核结果展示
                testDTO.setAuditResult(record.getIsAudit());
                testDTOS.add(testDTO);
            }
        }
        //==================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        QualityCommonBatchNumberExtraExample extraExample = new QualityCommonBatchNumberExtraExample();
        extraExample.createCriteria().andCommonBatchIdEqualTo(sampleDeliveringRecord.getId());
        List<QualityCommonBatchNumberExtra> extras = extraMapper.selectByExample(extraExample);

        String batch = "not exist";
        if(extras.size() != 0){
            batch = extras.get(0).getBatch();
        }

        rawTestReportDTO.setTestDTOS(testDTOS);
        rawTestReportDTO.setSampleDeliveringRecord(sampleDeliveringRecord);
        //rawTestReportDTO.setMaterialName(repoBaseSerialNumber.getMaterialName());
        //rawTestReportDTO.setSerialNumber(repoBaseSerialNumber.getSerialNumber());
        rawTestReportDTO.setSerialNumber(batch);
        rawTestReportDTO.setMaterialName(detectItem==null?"历史物料（未知）":detectItem.getName());
        rawTestReportDTO.setTester(username);
        rawTestReportDTO.setTestReportRecord(testReportRecord);
        rawTestReportDTO.setCommonBatchNumber(commonBatchNumber);

        return rawTestReportDTO;
    }

    /**
     * 获取送样人24小时之内的送样结果
     *
     * @param idCard
     * @return
     */
    @Override
    public List<Map<Object, Object>> delivererTask(String idCard) {
        Integer userId = authUserMapper.byIdCard(idCard);
        Assert.notNull(userId, "无法识别此id卡");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -2); //朱工修改为48小时
        Date yesterday = calendar.getTime();

        List<CommonBatchNumber> results = commonBatchNumberMapper.delivererTask(BatchTypeEnum.QUALITY_INTERMEDIATE_TEST.typeCode(), userId, yesterday);

        if (null == results || results.isEmpty()) return null;

        List<Map<Object, Object>> listMain = new ArrayList<>();

        results.forEach(e -> {
            if (!e.getStatus().equals(BatchStatusEnum.SAVE.status())) {
                Map<Object, Object> map = new HashMap<>();
                map.put("id", e.getId());

                map.put("deliverDate", SDF.format(e.getCreateTime()));
                //map.put("batchNumber", e.getBatchNumber());
                TestReportRecord record = testReportRecordMapper.getByBatchNumberId(e.getId());
                if(record == null) {
                    map.put("batchNumber", "不存在");
                }else {
                    SampleDeliveringRecord sample = sampleDeliveringRecordMapper.getById(record.getSampleDeliveringRecordId());
                    if (sample == null)
                        map.put("batchNumber", "不存在");
                    else{
                        QualityCommonBatchNumberExtraExample extraExample = new QualityCommonBatchNumberExtraExample();
                        extraExample.createCriteria().andCommonBatchIdEqualTo(sample.getId());
                        List<QualityCommonBatchNumberExtra> extras = extraMapper.selectByExample(extraExample);

                        if(extras.size() == 0)
                            map.put("batchNumber","不存在");
                        else{
                            String batch1 = extras.get(0).getBatch();
                            map.put("batchNumber",batch1);
                        }
                    }
                }
                //朱工，批号改编号
                map.put("status", BatchStatusEnum.getDescription(e.getStatus()));
                listMain.add(map);
            }
        });

        return listMain;
    }
}
