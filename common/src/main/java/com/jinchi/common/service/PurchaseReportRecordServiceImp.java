package com.jinchi.common.service;

import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.quality.purchase.PurchaseRawDTO;
import com.jinchi.common.dto.quality.purchase.PurchaseRecordHeadDTO;
import com.jinchi.common.dto.quality.purchase.ValidTestRecordDTO;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.NumberGenerator;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author：XudongHu
 * @className:PurchaseReportRecordService2Imp
 * @description: 进货检测
 * @date:13:51 2018/12/29
 */
@Service
public class PurchaseReportRecordServiceImp implements PurchaseReportRecordService {
    private final static Integer BATCH_QUALITY_RAW_TEST = BatchTypeEnum.QUALITY_RAW_TEST.typeCode();
    private final static Integer BATCH_QUALITY_PURCHASE = BatchTypeEnum.QUALITY_PURCHASE.typeCode();
    private static Logger logger = LoggerFactory.getLogger(PurchaseReportRecordServiceImp.class);
    @Autowired
    private PurchaseReportRecordMapper purchaseReportRecordMapper;
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private TestReportRecordMapper testReportRecordMapper;
    @Autowired
    private SampleDeliveringRecordMapper sampleDeliveringRecordMapper;
    @Autowired
    private DeliveryFactoryMapper deliveryFactoryMapper;
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    private TestItemMapper testItemMapper;
    @Autowired
    private TestItemResultRecordMapper testItemResultRecordMapper;
    @Autowired
    private TechniqueBaseRawMaterialMapper techniqueBaseRawMaterialMapper;
    @Autowired
    private TechniqueBaseRawManufacturerMapper techniqueBaseRawManufacturerMapper;
    @Autowired
    private TechniqueRawStandardRecordService techniqueRawStandardRecordService;
    @Autowired
    private TechniqueProductNewStandardRecordMapper newStandardRecordMapper;
    @Autowired
    private TechniqueBaseProductMaterialMapper materialMapper;
    @Autowired
    private QualityCommonBatchNumberExtraMapper extraMapper;

    /**
     * 查询所有-分页 进货检验报告单 生成页面
     *
     * @param personName 送样人名称模糊
     * @param isGenerate 0为未生成的 空为所有
     * @return
     */
    @Override
    public PageBean allPurchaseRaw(String personName, Integer isGenerate, PageBean pageBean) {
        //0为查询未生成的 不传为查询所有
        Integer status = BatchStatusEnum.PASS.status();
        //查到所有检测记录
        List<TestReportRecord> testReportRecords = testReportRecordMapper.byIsGenerate(status, isGenerate, pageBean);
        if (null == testReportRecords || testReportRecords.isEmpty()) return null;

        Integer sum = testReportRecordMapper.countSum(status, isGenerate);
        //==> DTO list装载
        List<PurchaseRawDTO> purchaseRawDTOS = new ArrayList<>();

        for (int i = 0; i < testReportRecords.size(); i++) {
            PurchaseRawDTO rawDTO = new PurchaseRawDTO();
            TestReportRecord testReportRecord = testReportRecords.get(i);
            //批号id
            Integer batchNumberId = testReportRecord.getBatchNumberId();
            //样品送检
            SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(testReportRecord.getSampleDeliveringRecordId());
            //是否生成flag
            Integer isGenerateFlag = testReportRecord.getPurchaseReportRecordId() == null ? 0 : 1;
            //检测项目
            String testItemString = sampleDeliveringRecord.getTestItems();
            StringBuffer testItemBuffer = new StringBuffer();
            for (String item : testItemString.replaceAll("[^0-9]", ",").split(",")) {
                if (item.length() > 0) {
                    Integer id = Integer.parseInt(item);
                    TestItem testItem = testItemMapper.find(id);
                    Assert.notNull(testItem, String.format("id为%d的检测项目不存在", id));
                    testItemBuffer.append(testItem.getName()).append(",");
                }
            }
            testItemBuffer.deleteCharAt(testItemBuffer.length() - 1);

            String deliverName = authUserMapper.byId(sampleDeliveringRecord.getDelivererId()).getName();

            if(!StringUtils.isEmpty(personName)&&!deliverName.contains(personName)){
                sum--;
                continue;
            }

            rawDTO
                    .setBatchNumberId(batchNumberId)
                    .setIsGenerate(isGenerateFlag)
                    .setTestItemString(testItemBuffer.toString())
                    .setDeliveringDate(sampleDeliveringRecord.getSampleDeliveringDate())
                    .setExceptionHandle(sampleDeliveringRecord.getExceptionComment())
                    //编号
                    //.setSerialNumber(repoBaseSerialNumberMapper.findById(sampleDeliveringRecord.getSerialNumberId()).getSerialNumber())
                    //工厂名称
                    .setManufacturerName(deliveryFactoryMapper.findById(sampleDeliveringRecord.getDeliveryFactoryId()).getName())
                    //送样人名称
                    .setDeliverName(deliverName);

            QualityCommonBatchNumberExtraExample example = new QualityCommonBatchNumberExtraExample();
            example.createCriteria().andCommonBatchIdEqualTo(sampleDeliveringRecord.getId());
            List<QualityCommonBatchNumberExtra> extras = extraMapper.selectByExample(example);
            if(extras.size() > 0){
                rawDTO.setSerialNumber(extras.get(0).getBatch());
            }
            purchaseRawDTOS.add(rawDTO);

        }

        pageBean.setList(purchaseRawDTOS);
        pageBean.setTotal(sum);
        return pageBean;
    }


    /**
     * 生成进货检验报告单   一次确认
     *
     * @param batchNumberIds 多个批号
     * @return
     */
    public Map<Object, Object> previewPurchase(List<Integer> batchNumberIds) {
        //原材料名称/生产厂家
        //标准(材料名/标准值)
        //编号/所有项目值
        Map<Object, Object> previewMap = new HashMap<>();
        //{k:编号,v:项目值}
        Map<Integer, String> valueMap = new HashMap<>();
        //1==>首先判断检测标准是否存在/是否为原材料数据/是否为可以生成的状态
        //设定第一条为基准List 其他序列都要与此序列相等
        //0为原材料名称 1为厂商名称
        List<Object> baseList = new ArrayList<>();
        for (int i = 0; i < batchNumberIds.size(); i++) {
            //对比序列
            List<Object> thisList = new ArrayList<>();
            CommonBatchNumber bn = commonBatchNumberMapper.byId(batchNumberIds.get(i));
            //TODO 验证可以写个统一类管理
            Assert.isTrue(null != bn
                            && BATCH_QUALITY_RAW_TEST.equals(bn.getDataType())
                            && BatchStatusEnum.PASS.status().equals(bn.getStatus()),
                    String.format("id为%s的批号不存在或者不能生成进货检验报告单", batchNumberIds.get(i)));

            //2==>拿到记录表
            TestReportRecord record = testReportRecordMapper.getByBatchNumberId(bn.getId());
            //验证是否已生成
            Assert.isTrue(null != record && null == record.getPurchaseReportRecordId(), "只能生成未生成的原材料数据");
            //3==>拿到样品送检表
            SampleDeliveringRecord sampleRecord = sampleDeliveringRecordMapper.getById(record.getSampleDeliveringRecordId());
            //拿到编号
            RepoBaseSerialNumber serialNumber = repoBaseSerialNumberMapper.findById(sampleRecord.getSerialNumberId());
            String materialName = serialNumber.getMaterialName();
            String manufacturerName = serialNumber.getManufacturerName();
            String sn = serialNumber.getSerialNumber();


            //编号和值都用String存储
            StringBuffer value = new StringBuffer();
            value.append(sn + ",");

            //4==>拿到记录结果值
            List<TestItemResultRecord> results = testItemResultRecordMapper.getByTestReportId(record.getId());
            results.sort(Comparator.comparing(TestItemResultRecord::getTestItemId));
            results.stream().forEach(e -> value.append(e.getTestResult() + ","));

            value.deleteCharAt(value.length() - 1);

            valueMap.put(bn.getId(), value.toString());

            //将一个放入基准序列 其他的放入对比序列
            if (i < 1) {
                baseList.add(materialName);
                baseList.add(manufacturerName);
                results.stream().forEach(e -> baseList.add(e.getTestItemId()));
            } else {
                thisList.add(materialName);
                thisList.add(manufacturerName);
                results.stream().forEach(e -> thisList.add(e.getTestItemId()));
                Assert.isTrue(baseList.equals(thisList), "无法生成进货,原料名称/生产厂家/检测项目中存在不一致");
            }


        }

        String materialName = (String) baseList.get(0);
        String manufactureName = (String) baseList.get(1);

        //建立标准
        previewMap.put("materialName", materialName);
        previewMap.put("manufactureName", manufactureName);
        previewMap.put("standard", techniqueRawStandardRecordService.lastStandard(null, materialName, manufactureName));
        previewMap.put("value", valueMap);

        return previewMap;
    }

    /**
     * 生成进货检验报告单  二次确认
     *
     * @param batchNumberIds 多个批号
     */
    @Override
    @Transactional
    public String generatePurchase(List<Integer> batchNumberIds, Integer createPersonId) {
        //第一步 生成批号
        CommonBatchNumber commonBatchNumber = new CommonBatchNumber();

        commonBatchNumber
                .setBatchNumber(NumberGenerator.batchNumberGenerator(BATCH_QUALITY_PURCHASE))
                .setStatus(BatchStatusEnum.SAVE.status())
                .setDataType(BATCH_QUALITY_PURCHASE)
                .setDescription(BatchTypeEnum.getByDataType(BATCH_QUALITY_PURCHASE).description())
                .setCreateTime(new Date())
                .setIsPublished(0)
                .setCreatePersonId(createPersonId);

        commonBatchNumberMapper.insert(commonBatchNumber);

        Integer commonBatchNumberId = commonBatchNumber.getId();

        //第二步 生成进货检验报告单表头
        PurchaseReportRecord purchaseReportRecord = new PurchaseReportRecord();

        Integer purchaseRecordId = null;

        //第三步 检测项目验证基准list
        List<Object> baseResults = new ArrayList<>();

        for (int i = 0; i < batchNumberIds.size(); i++) {
            Integer batchNumberId = batchNumberIds.get(i);

            CommonBatchNumber rawCommonBatchNumber = commonBatchNumberMapper.byId(batchNumberId);

            Assert.isTrue(null != rawCommonBatchNumber
                    && BATCH_QUALITY_RAW_TEST == rawCommonBatchNumber.getDataType()
                    && BatchStatusEnum.PASS.status() == rawCommonBatchNumber.getStatus(), String.format("id为%s的批号不存在或者不能生成进货检验报告单", batchNumberId));

            TestReportRecord testReportRecord = testReportRecordMapper.getByBatchNumberId(batchNumberId);

            Assert.isTrue(null != testReportRecord && null == testReportRecord.getPurchaseReportRecordId(), "只能生成未生成的原材料数据");

            //1.样品送检查询

            SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(testReportRecord.getSampleDeliveringRecordId());

            RepoBaseSerialNumber baseSerialNumber = repoBaseSerialNumberMapper.findById(sampleDeliveringRecord.getSerialNumberId());

            //2.取得样品名称
            String materialName = baseSerialNumber.getMaterialName();

            //3.取得厂家名称
            String manufacturerName = baseSerialNumber.getManufacturerName();


            //验证检测项目是否一致

            Integer testReportRecordId = testReportRecord.getId();

            List<TestItemResultRecord> results = testItemResultRecordMapper.getByTestReportId(testReportRecordId);

            Collections.sort(results, Comparator.comparing(TestItemResultRecord::getTestItemId));


            if (i < 1) {
                TechniqueBaseRawMaterial material = techniqueBaseRawMaterialMapper.getByName(materialName);
                Assert.notNull(material, String.format("%s-受检物料不存在", materialName));
                TechniqueBaseRawManufacturer manufacturer = techniqueBaseRawManufacturerMapper.getByName(manufacturerName);
                Assert.notNull(manufacturer, String.format("%s-原料厂家不存在", manufacturerName));

                purchaseReportRecord
                        //初始化都设置为合格
                        .setJudgement(1)
                        .setBatchNumberId(commonBatchNumberId)
                        .setTestMaterialId(material.getId())
                        .setManufacturerId(manufacturer.getId());

                purchaseReportRecordMapper.insert(purchaseReportRecord);

                purchaseRecordId = purchaseReportRecord.getId();

                //物料 厂商 都要一样
                baseResults.add(materialName);
                baseResults.add(manufacturerName);
                results.forEach(e -> baseResults.add(e.getTestItemId()));
            } else {
                List<Object> nextResults = new ArrayList<>();

                nextResults.add(materialName);
                nextResults.add(manufacturerName);

                results.forEach(e -> nextResults.add(e.getTestItemId()));

                logger.info(nextResults.toString());
                logger.info(baseResults.toString());

                Assert.isTrue(baseResults.equals(nextResults), "无法生成,样品名称/厂家/检测项目存在不一致");
            }


            //初始化都设置为合格
            results.forEach(e -> e.setIsValid(1));

            testItemResultRecordMapper.batchUpdateIsValid(results);

            testReportRecord
                    .setPurchaseReportRecordId(purchaseRecordId)
                    .setDecision(1);

            testReportRecordMapper.update(testReportRecord);
        }
        return String.format("进货检验报告单已生成,批号为%s", commonBatchNumber.getBatchNumber());
    }

    /**
     * 查询所有-分页  进货检验报告单
     *
     * @param materialName 原材料名称
     * @param pageBean     分页实体
     * @return
     */
    @Override
    public PageBean allPurchase(String materialName, PageBean pageBean) {
        //进货检验报告单数量
        List<PurchaseReportRecord> purchaseReportRecords;

        //总条目
        Integer sum;

        if (StringUtils.isEmpty(materialName)) {
            purchaseReportRecords = purchaseReportRecordMapper.getAll(pageBean);
            sum = purchaseReportRecordMapper.countSum(null);
        } else {
            List<TechniqueBaseRawMaterial> nameLike = techniqueBaseRawMaterialMapper.getByNameLike(materialName);

            if (null == nameLike || 0 == nameLike.size()) return null;

            List<Integer> materialIds = new ArrayList<>();

            nameLike.forEach(e -> materialIds.add(e.getId()));

            purchaseReportRecords = purchaseReportRecordMapper.getByMaterialIds(materialIds, pageBean);
            sum = purchaseReportRecordMapper.countSum(materialIds);
        }

        if (null == purchaseReportRecords || 0 == purchaseReportRecords.size()) return null;

        List<CommonBatchNumberDTO> commonBatchNumberDTOS = new ArrayList<>();

        for (int i = 0; i < purchaseReportRecords.size(); i++) {
            PurchaseReportRecord purchaseReportRecord = purchaseReportRecords.get(i);

            CommonBatchNumberDTO commonBatchNumberDTO = commonBatchNumberMapper.createDTOByIdAndDataType(purchaseReportRecord.getBatchNumberId(), null);
            //必定存在 就不验证了

            Integer manufacturerId = purchaseReportRecord.getManufacturerId();
            Integer testMaterialId = purchaseReportRecord.getTestMaterialId();

            TechniqueBaseRawMaterial rawMaterial = techniqueBaseRawMaterialMapper.getById(testMaterialId);
            TechniqueBaseRawManufacturer rawManufacturer = techniqueBaseRawManufacturerMapper.getById(manufacturerId);

            Assert.notNull(rawMaterial, String.format("Id为%d的技术受检物料不存在", testMaterialId));
            Assert.notNull(rawManufacturer, String.format("Id为%d的技术原材料厂家不存在", manufacturerId));

            logger.info("技术原材料==>", rawMaterial);
            logger.info("技术厂家==>", rawManufacturer);

            String rawName = rawMaterial == null ? "" : rawMaterial.getName();
            String manufacturerName = rawManufacturer == null ? "" : rawManufacturer.getName();

            PurchaseRecordHeadDTO purchaseRecordHeadDTO = new PurchaseRecordHeadDTO();

            purchaseRecordHeadDTO
                    .setReceiveDate(purchaseReportRecord.getReceiveDate())
                    .setMaterialName(rawName)
                    .setManufactureName(manufacturerName);

            commonBatchNumberDTO.setDetails(purchaseRecordHeadDTO);

            commonBatchNumberDTOS.add(commonBatchNumberDTO);
        }

        pageBean.setTotal(sum);
        pageBean.setList(commonBatchNumberDTOS);
        return pageBean;
    }

    /**
     * 查看进货详情
     *
     * @param batchNumberId 批号id
     * @return
     */
    @Override
    public PurchaseRecordHeadDTO byBatchNumberId(Integer batchNumberId) {
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(batchNumberId);

        if (null == commonBatchNumber || !commonBatchNumber.getDataType().equals(BATCH_QUALITY_PURCHASE)) return null;

        Integer commonBatchNumberId = commonBatchNumber.getId();

        PurchaseRecordHeadDTO purchaseRecordHeadDTO = new PurchaseRecordHeadDTO();

        //==>Setting 进货检测实体
        PurchaseReportRecord purchaseReportRecord = purchaseReportRecordMapper.getByBatchNumberId(commonBatchNumberId);

        Integer testMaterialId = purchaseReportRecord.getTestMaterialId();

        Integer manufacturerId = purchaseReportRecord.getManufacturerId();

        Integer judger = purchaseReportRecord.getJudger();

        TechniqueBaseRawMaterial rawMaterial = techniqueBaseRawMaterialMapper.getById(testMaterialId);
        TechniqueBaseRawManufacturer rawManufacturer = techniqueBaseRawManufacturerMapper.getById(manufacturerId);

        if (null != judger) {
            AuthUserDTO authUser = authUserMapper.byId(judger);
            Assert.notNull(authUser, String.format("Id为%s的检测人找不到", judger));
            purchaseRecordHeadDTO.setTester(authUser.getName());
        }
        purchaseRecordHeadDTO.setManufactureName(rawManufacturer.getName())
                .setMaterialName(rawMaterial.getName())
                .setReceiveDate(purchaseReportRecord.getReceiveDate())
                .setPurchaseReportRecord(purchaseReportRecord);

        //==>Setting 检测数据
        List<ValidTestRecordDTO> validTestRecordDTOS = new ArrayList<>();

        Integer purchaseReportRecordId = purchaseReportRecord.getId();

        List<TestReportRecord> testReportRecords = testReportRecordMapper.getByPurchaseReportRecordId(purchaseReportRecordId);

        Assert.notEmpty(testReportRecords, String.format("id为%s的进货检测没有详情,请删除", purchaseReportRecordId));

        for (int i = 0; i < testReportRecords.size(); i++) {
            TestReportRecord testReportRecord = testReportRecords.get(i);

            Integer decision = testReportRecord.getDecision();
            Integer testReportRecordId = testReportRecord.getId();
            Integer sampleDeliveringRecordId = testReportRecord.getSampleDeliveringRecordId();
            //样品送检实体
            SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(sampleDeliveringRecordId);

            //基本编号实体
            Integer serialNumberId = sampleDeliveringRecord.getSerialNumberId();
            RepoBaseSerialNumber serialNumber = repoBaseSerialNumberMapper.findById(serialNumberId);

            //检测结果实体
            List<TestItemResultRecord> itemResults = testItemResultRecordMapper.getByTestReportId(testReportRecordId);

            //根据检测项目的id排序
            Collections.sort(itemResults, Comparator.comparing(TestItemResultRecord::getTestItemId));

            ValidTestRecordDTO validTestRecordDTO = new ValidTestRecordDTO();

            validTestRecordDTO.setId(testReportRecordId)
                    .setSerialNumber(serialNumber.getSerialNumber())
                    .setDecision(decision)
                    .setResultRecordList(itemResults);

            validTestRecordDTOS.add(validTestRecordDTO);
        }

        purchaseRecordHeadDTO.setValidTestRecords(validTestRecordDTOS);

        /**
         * 设置标准
         */

        purchaseRecordHeadDTO.setStandards(techniqueRawStandardRecordService.lastStandard(null, rawMaterial.getName(), rawManufacturer.getName()));

        return purchaseRecordHeadDTO;
    }

    /**
     * 编辑进货
     *
     * @param purchaseRecordHeadDTO
     */
    @Override
    @Transactional
    public Integer updatePurchase(PurchaseRecordHeadDTO purchaseRecordHeadDTO) {
        //报告单实体
        PurchaseReportRecord newPurchaseValue = purchaseRecordHeadDTO.getPurchaseReportRecord();
        //检测项目值
        List<ValidTestRecordDTO> validTestRecords = purchaseRecordHeadDTO.getValidTestRecords();


        Integer purchaseReportRecordId = newPurchaseValue.getId();
        PurchaseReportRecord oldPurchaseValue = purchaseReportRecordMapper.getById(purchaseReportRecordId);

        Assert.notNull(oldPurchaseValue, "更新失败,找不到该进货id");
        Integer batchNumberId = oldPurchaseValue.getBatchNumberId();

        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(batchNumberId);

        Assert.isTrue(null != commonBatchNumber && commonBatchNumber.getDataType().equals(BATCH_QUALITY_PURCHASE), "数据错误");

        oldPurchaseValue
                .setJudger(newPurchaseValue.getJudger())
                .setJudgeDate(new Date())
                .setNorm(newPurchaseValue.getNorm())
                .setQuantity(newPurchaseValue.getQuantity())
                .setReceiveDate(newPurchaseValue.getReceiveDate())
                .setWeight(newPurchaseValue.getWeight());

        Integer judgement = 1;
        Integer decision = 1;

        for (ValidTestRecordDTO validTestRecordDTO : validTestRecords) {

            List<TestItemResultRecord> resultRecordList = validTestRecordDTO.getResultRecordList();
            Assert.notEmpty(resultRecordList, "数据库数据错误,详情不存在");

            for (TestItemResultRecord testItemResultRecord : resultRecordList) {
                if (testItemResultRecord.getIsValid() != 1) {
                    judgement = 0;
                    decision = 0;
                }
            }

            Integer testReportRecordId = validTestRecordDTO.getId();
            TestReportRecord testReportRecord = testReportRecordMapper.getById(testReportRecordId);

            testReportRecord.setDecision(decision);

            //单条判定
            testReportRecordMapper.update(testReportRecord);

            testItemResultRecordMapper.batchUpdateIsValid(resultRecordList);
        }

        oldPurchaseValue.setJudgement(judgement);

        purchaseReportRecordMapper.update(oldPurchaseValue);

        return batchNumberId;
    }

    /**
     * 根据批号id删除
     *
     * @param batchNumberId 批号id
     */
    @Override
    @Transactional
    public void deleteByBatchNumberId(Integer batchNumberId) {
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(batchNumberId);

        Assert.isTrue(null != commonBatchNumber && commonBatchNumber.getDataType().equals(BATCH_QUALITY_PURCHASE)
                && commonBatchNumber.getStatus().equals(BatchStatusEnum.SAVE.status()), "删除失败,该批数据不存在或者为不可删除的状态");

        PurchaseReportRecord purchaseReportRecord = purchaseReportRecordMapper.getByBatchNumberId(batchNumberId);

        List<TestReportRecord> testReportRecords = testReportRecordMapper.getByPurchaseReportRecordId(purchaseReportRecord.getId());

        for (int i = 0; i < testReportRecords.size(); i++) {
            TestReportRecord testReportRecord = testReportRecords.get(i);
            testReportRecord.setPurchaseReportRecordId(null);
            testReportRecordMapper.update(testReportRecord);
        }

        purchaseReportRecordMapper.deleteById(purchaseReportRecord.getId());
        commonBatchNumberMapper.deleteById(batchNumberId);
    }


    /**
     * 查询所有-分页  待发布数据
     *
     * @param createPerson
     * @param pageBean
     * @return
     */
    @Override
    public PageBean allRelease(String createPerson, PageBean pageBean) {
        CommonBatchNumberDTO factors = new CommonBatchNumberDTO();
        factors.setCreatePersonName(createPerson)
                .setCommonBatchNumber(new CommonBatchNumber()
                        .setStatus(BatchStatusEnum.PASS.status())
                        .setDataType(BATCH_QUALITY_PURCHASE));

        List<CommonBatchNumberDTO> pages = commonBatchNumberMapper.createDTOsByCommonDTObyPage(factors, pageBean);

        Integer sum = commonBatchNumberMapper.countSum(factors);

        for (CommonBatchNumberDTO commonBatchNumberDTO : pages) {
            Integer id = commonBatchNumberDTO.getCommonBatchNumber().getId();

            PurchaseReportRecord purchaseReportRecord = purchaseReportRecordMapper.getByBatchNumberId(id);

            Integer manufacturerId = purchaseReportRecord.getManufacturerId();
            Integer testMaterialId = purchaseReportRecord.getTestMaterialId();

            PurchaseRecordHeadDTO head = new PurchaseRecordHeadDTO();


            head
                    .setMaterialName(techniqueBaseRawMaterialMapper.getById(testMaterialId).getName())
                    .setManufactureName(techniqueBaseRawManufacturerMapper.getById(manufacturerId).getName())
                    .setReceiveDate(purchaseReportRecord.getReceiveDate());

            commonBatchNumberDTO.setDetails(head);

        }

        pageBean.setList(pages);
        pageBean.setTotal(sum);

        return pageBean;
    }

    /**
     * 发布
     *
     * @param batchNumberId 批号id
     * @return
     */
    @Override
    public String release(Integer batchNumberId) {
        CommonBatchNumber batchNumber = commonBatchNumberMapper.byId(batchNumberId);

        Assert.notNull(batchNumber, "发布的数据不存在");

        batchNumber.setIsPublished(1);

        commonBatchNumberMapper.update(batchNumber);

        return String.format("%s批次数据发布成功", batchNumber.getBatchNumber());
    }


}
