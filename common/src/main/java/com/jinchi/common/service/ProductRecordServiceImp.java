package com.jinchi.common.service;

import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.constant.QualitySampleStatusEnum;
import com.jinchi.common.constant.QualitySampleTypeEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.quality.product.ProductReportHeadDTO;
import com.jinchi.common.dto.quality.product.TestResultDTO;
import com.jinchi.common.mapper.*;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 说明:
 * <br>
 *
 * @author huxudong
 * @Description 成品检验
 * <br>
 * 日期: 2018/11/19
 * <br>
 * 版本: 1.0
 */
@Service
public class ProductRecordServiceImp implements ProductRecordService {
    @Autowired
    private DeliveryFactoryMapper deliveryFactoryMapper;
    @Autowired
    private SampleDeliveringRecordMapper sampleDeliveringRecordMapper;
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private TestReportRecordMapper testReportRecordMapper;
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    private TestItemMapper testItemMapper;
    @Autowired
    private TechniqueProductStandardRecordMapper techniqueProductStandardRecordMapper;
    @Autowired
    private TestItemResultRecordMapper testItemResultRecordMapper;
    @Autowired
    private TechniqueProductTestItemStandardMapper techniqueProductTestItemStandardMapper;

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    /**
     * 查询所有
     *
     * @param factory  送样工厂名称
     * @param pageBean 分页实体
     * @return
     */
    @Override
    public PageBean pagesByFactory(String factory, PageBean pageBean) {

        List<SampleDeliveringRecord> sampleDeliveringRecords = sampleDeliveringRecordMapper.getAllByFactors(QualitySampleTypeEnum.SAMPLE_ENDPRODUCT.get(),
                QualitySampleStatusEnum.SAMPLE_ACCEPTED.get(),
                factory,
                pageBean);

        if (null == sampleDeliveringRecords || sampleDeliveringRecords.isEmpty()) return null;

        Integer sum = sampleDeliveringRecordMapper.countSum(QualitySampleTypeEnum.SAMPLE_ENDPRODUCT.get(),
                QualitySampleStatusEnum.SAMPLE_ACCEPTED.get(),
                factory);

        List<ProductReportHeadDTO> productReportHeadDTOS = new ArrayList<>();

        for (SampleDeliveringRecord sampleDeliveringRecord : sampleDeliveringRecords) {
            ProductReportHeadDTO productReportHeadDTO = new ProductReportHeadDTO();

            /**
             * 样品送检能拿到的部分
             */
            Integer sampleDeliveringRecordId = sampleDeliveringRecord.getId();
            Integer deliveryFactoryId = sampleDeliveringRecord.getDeliveryFactoryId();
            Integer delivererId = sampleDeliveringRecord.getDelivererId();
            Date sampleDeliveringDate = sampleDeliveringRecord.getSampleDeliveringDate();
            String testItems = sampleDeliveringRecord.getTestItems();
            String exceptionComment = sampleDeliveringRecord.getExceptionComment();
            Integer serialNumberId = sampleDeliveringRecord.getSerialNumberId();

            StringBuffer testItemString = new StringBuffer();

            for (String id : testItems.replaceAll("[^0-9]", ",").split(",")) {
                if (id.length() > 0) {
                    int i = Integer.parseInt(id);
                    TestItem testItem = testItemMapper.find(i);
                    testItemString.append(testItem==null?"":testItem.getName() + ",");
                }
            }

            testItemString.deleteCharAt(testItemString.length() - 1);

            /**
             * 批号能拿到的部分
             */
            TestReportRecord testReportRecord = testReportRecordMapper.getBySampleDeliveringRecordId(sampleDeliveringRecordId);
            Integer batchNumberId = testReportRecord.getBatchNumberId();
            CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(batchNumberId);


            productReportHeadDTO
                    .setBatchNumberId(commonBatchNumber.getId())
                    .setDeliver(authUserMapper.byId(delivererId).getName())
                    .setDeliverFactory(deliveryFactoryMapper.findById(deliveryFactoryId).getName())
                    .setDeliveringDate(sampleDeliveringDate)
                    .setException(exceptionComment)
                    .setRepoBaseSerialNumber(repoBaseSerialNumberMapper.findById(serialNumberId))
                    .setTestItemString(testItemString.toString())
                    .setIsPublished(commonBatchNumber.getIsPublished())
                    .setStatus(commonBatchNumber.getStatus());

            productReportHeadDTOS.add(productReportHeadDTO);
        }

        pageBean.setTotal(sum);
        pageBean.setList(productReportHeadDTOS);

        return pageBean;
    }

    /**
     * 查看详情
     *
     * @param batchNumberId 批号id
     * @return
     */
    @Override
    public ProductReportHeadDTO byBatchNumberId(Integer batchNumberId) {
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(batchNumberId);
        if (null == commonBatchNumber || !commonBatchNumber.getDataType().equals(BatchTypeEnum.QUALITY_PRODUCT_TEST.typeCode()))
            return null;

        ProductReportHeadDTO productReportHeadDTO = new ProductReportHeadDTO();

        //拿到检测表
        TestReportRecord testReportRecord = testReportRecordMapper.getByBatchNumberId(batchNumberId);
        Assert.notNull(testReportRecord, "数据库数据错误,此批号无成品检测数据");

        Integer testReportRecordId = testReportRecord.getId();
        Integer sampleDeliveringRecordId = testReportRecord.getSampleDeliveringRecordId();
        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(sampleDeliveringRecordId);

        Integer serialNumberId = sampleDeliveringRecord.getSerialNumberId();
        RepoBaseSerialNumber serialNumber = repoBaseSerialNumberMapper.findById(serialNumberId);
        productReportHeadDTO.setRepoBaseSerialNumber(serialNumber)
                .setTestReportRecord(testReportRecord)
                .setDeliveringDate(sampleDeliveringRecord.getSampleDeliveringDate());

        //设置详情和标准
        List<TestItemResultRecord> results = testItemResultRecordMapper.getByTestReportId(testReportRecordId);

        TechniqueProductStandardRecord lastedStandard = techniqueProductStandardRecordMapper.lastedStandard(serialNumberId);
        Map<Integer, String> standardMap = new HashMap<>();

        if (null != lastedStandard) {
            List<TechniqueProductTestItemStandard> standardResults = techniqueProductTestItemStandardMapper.findByRecordId(lastedStandard.getId());
            standardResults.stream().forEach(e -> standardMap.put(e.getTestItemId(), e.getValue()));
        }

        List<TestResultDTO> testResultDTOS = new ArrayList<>();

        for (TestItemResultRecord testItemResultRecord : results) {
            TestResultDTO testResultDTO = new TestResultDTO();
            Integer testItemId = testItemResultRecord.getTestItemId();

            TestItem testItem = testItemMapper.find(testItemId);
            testResultDTO.setTestItemResultRecord(testItemResultRecord)
                    .setTestItem(testItem)
                    .setStandardValue(standardMap.getOrDefault(testItemId, null));

            testResultDTOS.add(testResultDTO);
        }

        productReportHeadDTO.setTestResultDTOList(testResultDTOS);
        AuthUserDTO user = authUserMapper.byId(testReportRecord.getJudger());
        String tester = user==null?"":user.getName();
        productReportHeadDTO.setTester(tester);
        return productReportHeadDTO;
    }

    /**
     * 更新值
     *
     * @param productReportHeadDTO
     * @return
     */
    @Override
    @Transactional
    public ProductReportHeadDTO updateValue(ProductReportHeadDTO productReportHeadDTO) {
        Integer batchNumberId = productReportHeadDTO.getBatchNumberId();

        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(batchNumberId);

        Assert.isTrue(null != commonBatchNumber && commonBatchNumber.getDataType().equals(BatchTypeEnum.QUALITY_PRODUCT_TEST.typeCode()));

        TestReportRecord testReportRecord = productReportHeadDTO.getTestReportRecord();


        List<TestResultDTO> testResultDTOList = productReportHeadDTO.getTestResultDTOList();

        Assert.notEmpty(testResultDTOList, "更新内容不存在");

        List<TestItemResultRecord> testItemResultRecords = new ArrayList<>();

        testResultDTOList.stream().forEach(e -> testItemResultRecords.add(e.getTestItemResultRecord()));
        Integer isQualified = testReportRecord.getIsQualified();

        for (TestItemResultRecord result : testItemResultRecords) {
            if (null == result.getIsValid()) result.setIsValid(1);
            if (result.getIsValid().equals(0)) {
                isQualified = 0;
                break;
            }
        }


        TestReportRecord oldValue = testReportRecordMapper.getByBatchNumberId(batchNumberId);
        oldValue.setJudgeDate(new Date())
                .setJudger(testReportRecord.getJudger())
                .setIsQualified(isQualified)
                .setQualityLevel(testReportRecord.getQualityLevel());
        testReportRecordMapper.update(oldValue);


        testItemResultRecordMapper.batchUpdate(testItemResultRecords);
        testItemResultRecordMapper.batchUpdateIsValid(testItemResultRecords);

        return productReportHeadDTO;
    }

    /**
     * 更新择优人
     * @param testReportRecord
     * @return
     */
    @Override
    @Transactional
    public TestReportRecord updateRate(TestReportRecord testReportRecord) {
        Integer batchNumberId = testReportRecord.getBatchNumberId();

        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(batchNumberId);

        Assert.isTrue(null!=commonBatchNumber && commonBatchNumber.getDataType().equals(BatchTypeEnum.QUALITY_PRODUCT_TEST.typeCode()));

        Assert.notNull(testReportRecord,"更新内容不存在");

        TestReportRecord oldValue = testReportRecordMapper.getByBatchNumberId(batchNumberId);
        oldValue.setQualityLevel(testReportRecord.getQualityLevel())
                .setRateDate(testReportRecord.getRateDate())
                .setRatePersonId(testReportRecord.getRatePersonId());
        testReportRecordMapper.update(oldValue);

        return oldValue;
    }

}
