package com.jinchi.common.service;

import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.quality.unqualified.GeneralHeadDTO;
import com.jinchi.common.dto.quality.unqualified.UnqualifiedDetailDTO;
import com.jinchi.common.dto.quality.unqualified.UnqualifiedResultDTO;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.NumberGenerator;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author：XudongHu
 * @className:UnqualifiedTestReportRecordImp
 * @description: 不合格评审
 * @date:15:41 2018/12/20
 */
@Service
public class UnqualifiedTestReportRecordServiceImp implements UnqualifiedTestReportRecordService {
    private final static Integer BATCH_QUALITY_PURCHASE = BatchTypeEnum.QUALITY_PURCHASE.typeCode();
    private final static Integer BATCH_QUALITY_PRODUCT_TEST = BatchTypeEnum.QUALITY_PRODUCT_TEST.typeCode();
//    private final static Integer UNQUALIFIED_RECORD = BatchTypeEnum.UNQUALIFIED_RECORD.typeCode();

    //Mapper用来操作数据库的
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private UnqualifiedTestReportRecordMapper unqualifiedTestReportRecordMapper;
    @Autowired
    private PurchaseReportRecordMapper purchaseReportRecordMapper;
    @Autowired
    private TestItemResultRecordMapper testItemResultRecordMapper;
    @Autowired
    private TestReportRecordMapper testReportRecordMapper;
    @Autowired
    private UnqualifiedTestItemResultRecordMapper unqualifiedTestItemResultRecordMapper;
    @Autowired
    private SampleDeliveringRecordMapper sampleDeliveringRecordMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    private DeliveryFactoryMapper deliveryFactoryMapper;
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private TechniqueBaseRawMaterialMapper techniqueBaseRawMaterialMapper;
    @Autowired
    private TechniqueBaseRawManufacturerMapper techniqueBaseRawManufacturerMapper;
    @Autowired
    private TechniqueRawStandardRecordService techniqueRawStandardRecordService;
    @Autowired
    private TechniqueProductStandardRecordService techniqueProductStandardRecordService;


    /**
     * 查询所有-分页  不合格审评表
     *
     * @param createPerson 创建人名称名称模糊
     * @return
     */
    @Override
    public PageBean getAllByPage(String createPerson, PageBean pageBean) {


        //  查到所有不合格记录

        List<UnqualifiedTestReportRecord> unqualifiedTestReportRecords = unqualifiedTestReportRecordMapper.getAll(createPerson, pageBean);
        if(null==unqualifiedTestReportRecords||unqualifiedTestReportRecords.isEmpty()) return null;
        Integer sum = unqualifiedTestReportRecordMapper.countSum(createPerson);


        //==> 装载DTO
        List<CommonBatchNumberDTO> commonBatchNumberDTOS = new ArrayList<>();
        for (int i = 0; i < unqualifiedTestReportRecords.size(); i++) {
            UnqualifiedTestReportRecord unqualifiedTestReportRecord = unqualifiedTestReportRecords.get(i);

            //批号id
            Integer batchNumberId = unqualifiedTestReportRecord.getBatchNumberId();

            //批号
            CommonBatchNumberDTO bnDTO = commonBatchNumberMapper.createDTOByIdAndDataType(batchNumberId,null);

            //进货id
            Integer purchaseReportRecordId = unqualifiedTestReportRecord.getPurchaseReportRecordId();

            String materialClass;
            String manufactureName;
            Date receivedDate;

            //如果是进货---查technique_base_raw_material
            if (null != purchaseReportRecordId) {
                //  物料类型-原材料
                materialClass = techniqueBaseRawMaterialMapper.getById(purchaseReportRecordMapper.getById(purchaseReportRecordId).getTestMaterialId()).getName();

                //厂商名称
                manufactureName = techniqueBaseRawManufacturerMapper.getById(purchaseReportRecordMapper.getById(purchaseReportRecordId).getManufacturerId()).getName();

                //到货日期
                receivedDate = purchaseReportRecordMapper.getById(purchaseReportRecordId).getReceiveDate();

            } else {
                //  成品
                //样品送检id
                Integer sampleDeliveringRecordId = unqualifiedTestReportRecord.getSampleDeliveringRecordId();

                //送检实体
                SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(sampleDeliveringRecordId);

                //  物料类型  成品
                materialClass = repoBaseSerialNumberMapper.findById(sampleDeliveringRecord.getSerialNumberId()).getMaterialName();

                //  厂商  送样工厂
                manufactureName = repoBaseSerialNumberMapper.findById(sampleDeliveringRecord.getSerialNumberId()).getManufacturerName();

                //  到货日期  即送检日期
                receivedDate = sampleDeliveringRecord.getSampleDeliveringDate();
            }


            GeneralHeadDTO generalHead = new GeneralHeadDTO();
            generalHead
                    .setFactory(manufactureName)
                    .setDate(receivedDate)
                    .setMaterialName(materialClass);

            bnDTO.setDetails(generalHead);

            commonBatchNumberDTOS.add(bnDTO);

        }

        pageBean.setTotal(sum);
        pageBean.setList(commonBatchNumberDTOS);

        return pageBean;
    }


    /**
     * 更新
     *
     */
    @Override
    public void update(UnqualifiedDetailDTO unqualifiedDetailDTO) {

        List<UnqualifiedResultDTO> results = unqualifiedDetailDTO.getResultDTOList();
        Integer tester = unqualifiedDetailDTO.getTesterId();

        List<UnqualifiedTestItemResultRecord> allResults = new ArrayList<>();

        List<UnqualifiedTestReportRecord> records = new ArrayList<>();
        Integer isQualified = 1;
        for (int i = 0; i < results.size(); i++) {
            UnqualifiedResultDTO resultDTO = results.get(i);
            Integer id = resultDTO.getId();
            UnqualifiedTestReportRecord record = unqualifiedTestReportRecordMapper.findById(id);

            Integer decision = 1;

            //数据详情值
            List<UnqualifiedTestItemResultRecord> itemResults = resultDTO.getTestItemResults();
            allResults.addAll(itemResults);

            for (UnqualifiedTestItemResultRecord resultRecord : itemResults) {
                if (resultRecord.getIsValid() == 0) {
                    isQualified = 0;
                    decision = 0;
                }
            }

            record
                    .setJudger(tester)
                    .setDecision(decision)
                    .setJudgeDate(new Date());

            records.add(record);

        }

        for (UnqualifiedTestReportRecord record:records){
            record.setIsQualified(isQualified);
            unqualifiedTestReportRecordMapper.update(record);
        }

        unqualifiedTestItemResultRecordMapper.batchUpdate(allResults);
    }

    /**
     * 查看详情
     *
     * @param batchNumberId 批号id
     * @return
     */
    @Override
    public Map<Object, Object> findByBatchNumberId(Integer batchNumberId) {
        List<UnqualifiedTestReportRecord> records = unqualifiedTestReportRecordMapper.findByBatchNumberId(batchNumberId);
        if (null == records || records.isEmpty()) return null;

        Map<Object, Object> bigMap = new HashMap<>();
        GeneralHeadDTO head = new GeneralHeadDTO();
        //每一条值
        List<UnqualifiedResultDTO> unqualifiedResultDTOS = new ArrayList<>();
        //放表头
        bigMap.put("batchNumberId", batchNumberId);
        bigMap.put("type", null);
        bigMap.put("isQualified", 1);
        bigMap.put("unqualifiedHead", head);
        bigMap.put("unqualifiedDetail", unqualifiedResultDTOS);
        bigMap.put("standard", null);
        //放detail

        //两种情况 成品的不合格数据或者进货的不合格数据
        for (int i = 0; i < records.size(); i++) {
            UnqualifiedTestReportRecord record = records.get(i);
            Integer decision = record.getDecision();
            Integer sampleId = record.getSampleDeliveringRecordId();
            SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(sampleId);
            Integer serialNumberId = sampleDeliveringRecord.getSerialNumberId();
            RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(serialNumberId);

            //==>此处放入bigMap中的Detail
            List<UnqualifiedTestItemResultRecord> results = unqualifiedTestItemResultRecordMapper.getByUnqualifiedRecordId(record.getId());
            results.sort(Comparator.comparing(UnqualifiedTestItemResultRecord::getTestItemId));

            UnqualifiedResultDTO unqualifiedResultDTO = new UnqualifiedResultDTO();
            unqualifiedResultDTO.setDecision(decision);
            unqualifiedResultDTO
                    .setId(record.getId())
                    .setSerialNumber(repoBaseSerialNumber.getSerialNumber())
                    .setTestItemResults(results);
            unqualifiedResultDTOS.add(unqualifiedResultDTO);
            //==>
            //只要有一条不合格就是不合格
            if (0 == decision) bigMap.put("isQualified", 0);
            //其余数据只需要判断第一条
            if (i < 1) {
                Integer purchaseReportRecordId = record.getPurchaseReportRecordId();
                //0是成品 1是原材料
                String materialName = repoBaseSerialNumber.getMaterialName();
                String rawManufacturerName = repoBaseSerialNumber.getManufacturerName();

                AuthUserDTO user = authUserMapper.byId(record.getJudger());
                String username = user == null ? "无" : user.getName();

                head.setTester(username);
                head.setMaterialName(materialName);
                //成品
                if (null == purchaseReportRecordId) {
                    bigMap.put("type", 0);

                    List<Integer> testItemIds = new ArrayList<>();
                    for(String stringId:sampleDeliveringRecord.getTestItems().replaceAll("[^0-9]", ",").split(",")){
                        if(stringId.length()>0){
                            testItemIds.add(Integer.parseInt(stringId));
                        }
                    }

                    bigMap.put("standard", techniqueProductStandardRecordService.lastStandard(serialNumberId,testItemIds));


                    //如果是成品 工厂就是送样工厂
                    head.setFactory(deliveryFactoryMapper.findById(sampleDeliveringRecord.getDeliveryFactoryId()).getName());
                    head.setDate(sampleDeliveringRecord.getSampleDeliveringDate());

                } else
                    //进货
                    {
                    bigMap.put("type", 1);
                    bigMap.put("standard", techniqueRawStandardRecordService.lastStandard(null, materialName, rawManufacturerName));

                    PurchaseReportRecord purchaseReportRecord = purchaseReportRecordMapper.getById(purchaseReportRecordId);
                    Assert.notNull(purchaseReportRecord, String.format("id为%d的进货数据不存在", purchaseReportRecordId));

                    //如果是进货 工厂就是生产厂家
                    head.purchaseSetting(purchaseReportRecord);
                    head.setFactory(techniqueBaseRawManufacturerMapper.getById(purchaseReportRecord.getManufacturerId()).getName());

                }
            }


        }
        return bigMap;
    }


    /**
     * 生成不合格品 评审表
     *
     * @param batchNumberId 批号id
     * @param userId
     */
    @Override
    @Transactional
    public void generate(Integer batchNumberId, Integer userId) {
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(batchNumberId);
        if (null == commonBatchNumber) return;
        //==>首先新增一个批号
        CommonBatchNumber newCommonBatchNumber = new CommonBatchNumber();
        newCommonBatchNumber
                .setBatchNumber(NumberGenerator.batchNumberGenerator(BatchTypeEnum.UNQUALIFIED_RECORD.typeCode()))
                .setStatus(BatchStatusEnum.SAVE.status())
                .setCreatePersonId(userId)
                .setDescription(BatchTypeEnum.getByDataType(BatchTypeEnum.UNQUALIFIED_RECORD.typeCode()).description())
                .setCreateTime(new Date())
                .setDataType(BatchTypeEnum.UNQUALIFIED_RECORD.typeCode());

        commonBatchNumberMapper.insert(newCommonBatchNumber);

        Integer newBatchNumberId = newCommonBatchNumber.getId();
        //如果是进货
        if (commonBatchNumber.getDataType().equals(BATCH_QUALITY_PURCHASE)) {
            PurchaseReportRecord purchaseReportRecord = purchaseReportRecordMapper.getByBatchNumberId(commonBatchNumber.getId());

            //找到检测记录表
            List<TestReportRecord> testReportRecords = testReportRecordMapper.getByPurchaseReportRecordId(purchaseReportRecord.getId());
            //拿到Map
            List<Integer> unQualifiedRecordsIds = new ArrayList<>();

            for (int i = 0; i < testReportRecords.size(); i++) {
                TestReportRecord testReportRecord = testReportRecords.get(i);
                Integer decision = testReportRecord.getDecision();
                if (decision.equals(0)) {
                    unQualifiedRecordsIds.add(testReportRecord.getId());
                } else testReportRecords.remove(testReportRecord);
            }
            if (unQualifiedRecordsIds.isEmpty()) return;

            //创建Map  key是不合格id value是testReportRecordId
            Map<Integer, Integer> unTestMap = new HashMap<>();

            for (TestReportRecord testReportRecord : testReportRecords) {
                UnqualifiedTestReportRecord unqualifiedTestReportRecord = new UnqualifiedTestReportRecord();

                unqualifiedTestReportRecord
                        .setIsQualified(0)
                        .setSampleDeliveringRecordId(testReportRecord.getSampleDeliveringRecordId())
                        .setBatchNumberId(newBatchNumberId)
                        .setPurchaseReportRecordId(purchaseReportRecord.getId())
                        .setDecision(0);

                //新增不合格品评审 表头
                unqualifiedTestReportRecordMapper.insert(unqualifiedTestReportRecord);

                unTestMap.put(testReportRecord.getId(), unqualifiedTestReportRecord.getId());
            }

            //根据检测记录表找到检测接口
            List<UnqualifiedTestItemResultRecord> unqualifiedTestItemResultRecords = new ArrayList<>();
            for (Integer id : unQualifiedRecordsIds) {
                List<TestItemResultRecord> results = testItemResultRecordMapper.getByTestReportId(id);

                for (TestItemResultRecord result : results) {
                    UnqualifiedTestItemResultRecord unqualifiedResult = new UnqualifiedTestItemResultRecord(result);
                    unqualifiedResult.setUnqualifiedTestReportRecordId(unTestMap.get(id));
                    unqualifiedTestItemResultRecords.add(unqualifiedResult);
                }
            }

            unqualifiedTestItemResultRecordMapper.batchInsert(unqualifiedTestItemResultRecords);

            //如果是成品
        } else if (commonBatchNumber.getDataType().equals(BATCH_QUALITY_PRODUCT_TEST)) {
            TestReportRecord testReportRecord = testReportRecordMapper.getByBatchNumberId(batchNumberId);

            Assert.notNull(testReportRecord, "检测记录不存在");

            if (0 != testReportRecord.getIsQualified()) return;

            UnqualifiedTestReportRecord unqualifiedTestReportRecord = new UnqualifiedTestReportRecord();

            unqualifiedTestReportRecord
                    .setIsQualified(0)
                    .setBatchNumberId(newBatchNumberId)
                    .setSampleDeliveringRecordId(testReportRecord.getSampleDeliveringRecordId())
                    .setQualityLevel(testReportRecord.getQualityLevel())
                    .setDecision(0);

            //新增不合格品评审 表头
            unqualifiedTestReportRecordMapper.insert(unqualifiedTestReportRecord);
            Integer unqualifiedTestReportRecordId = unqualifiedTestReportRecord.getId();

            //找到成品的结果
            List<TestItemResultRecord> results = testItemResultRecordMapper.getByTestReportId(testReportRecord.getId());

            List<UnqualifiedTestItemResultRecord> unqualifiedTestItemResultRecords = new ArrayList<>();

            for (TestItemResultRecord record : results) {
                UnqualifiedTestItemResultRecord unqualifiedTestItemResultRecord = new UnqualifiedTestItemResultRecord(record);

                unqualifiedTestItemResultRecord.setUnqualifiedTestReportRecordId(unqualifiedTestReportRecordId);

                unqualifiedTestItemResultRecords.add(unqualifiedTestItemResultRecord);
            }

            unqualifiedTestItemResultRecordMapper.batchInsert(unqualifiedTestItemResultRecords);
        } else return;

    }


}