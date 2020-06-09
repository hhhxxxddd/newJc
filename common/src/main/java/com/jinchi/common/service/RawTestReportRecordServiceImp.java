package com.jinchi.common.service;


import com.alibaba.druid.util.MySqlUtils;
import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.constant.QualitySampleStatusEnum;
import com.jinchi.common.constant.QualitySampleTypeEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.RawTestReportDTO;
import com.jinchi.common.dto.TestDTO;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.ComUtil;
import com.jinchi.common.utils.TestItemUtil;
import com.sun.javafx.binding.StringFormatter;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author：YuboWu
 */
@Service
public class RawTestReportRecordServiceImp implements RawTestReportRecordService {
    private final static Integer BATCH_QUALITY_RAW_TEST = BatchTypeEnum.QUALITY_RAW_TEST.typeCode();

    @Autowired
    private TestReportRecordMapper testReportRecordMapper;
    @Autowired
    private TestItemResultRecordMapper testItemResultRecordMapper;
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private SampleDeliveringRecordMapper sampleDeliveringRecordMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private DeliveryFactoryMapper deliveryFactoryMapper;
    @Autowired
    private TestItemMapper testItemMapper;
    @Autowired
    private TestItemUtil testItemUtil;
    @Autowired
    private QualitySampleDeliveringRecordRawMapper qualitySampleDeliveringRecordRawMapper;
    @Autowired
    private TechniqueRawTestItemStandardMapper techniqueRawTestItemStandardMapper;
    @Autowired
    private TechniqueRawStandardRecordMapper rawStandardRecordMapper;
    @Autowired
    private TechniqueBaseRawMaterialMapper rawMaterialMapper;

    /**
     * 查询所有原材料检测项目
     *
     * @return
     */
    @Override
    public List<RawTestReportDTO> findAll() {
        List<SampleDeliveringRecord> sampleDeliveringRecords = sampleDeliveringRecordMapper.getAllByFactors(QualitySampleTypeEnum.SAMPLE_RAWMATERIAL.get(), QualitySampleStatusEnum.SAMPLE_ACCEPTED.get(), null, null);

        if (null == sampleDeliveringRecords || 0 == sampleDeliveringRecords.size()) return null;

        List<RawTestReportDTO> dtoList = new ArrayList<>(sampleDeliveringRecords.size());

        for (SampleDeliveringRecord record : sampleDeliveringRecords) {
            RawTestReportDTO dto = new RawTestReportDTO();

            TestReportRecord testReportRecord = testReportRecordMapper.getBySampleDeliveringRecordId(record.getId());
            Assert.notNull(testReportRecord, "检测结果不存在");
            CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(testReportRecord.getBatchNumberId());
            AuthUserDTO authUserDTO1 = authUserMapper.byId(record.getDelivererId());
            DeliveryFactory deliveryFactory = deliveryFactoryMapper.findById(record.getDeliveryFactoryId());
            RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(record.getSerialNumberId());

            StringBuffer testIdsString = new StringBuffer(record.getTestItems());
            String itemNames = testItemUtil.convertItemNames(testIdsString.toString());

            dto.setTestReportRecord(testReportRecord);
            dto.setSampleDeliveringRecord(record);
            dto.setCommonBatchNumber(commonBatchNumber);
            dto.setDeliverer(authUserDTO1.getName());
            dto.setDeliveryFactoryName(deliveryFactory==null?"":deliveryFactory.getName());
            dto.setSerialNumber(repoBaseSerialNumber==null?"":repoBaseSerialNumber.getSerialNumber());
            dto.setTestItemString(itemNames);

            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * 详情（根据样品送检id查）
     */
    @Override
    public RawTestReportDTO findDetailsById(Integer id) {
        RawTestReportDTO rawTestReportDTO = new RawTestReportDTO();
        List<TestDTO> testDTOS = new ArrayList<>();

        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(id);
        if (null == sampleDeliveringRecord || sampleDeliveringRecord.getType() != 1 || sampleDeliveringRecord.getAcceptStatus() != 2)
            return null;
        //RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(sampleDeliveringRecord.getSerialNumberId());
        TestReportRecord testReportRecord = testReportRecordMapper.getBySampleDeliveringRecordId(sampleDeliveringRecord.getId());
        AuthUserDTO authUserDTO = authUserMapper.byId(testReportRecord.getJudger());
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(testReportRecord.getBatchNumberId());
        if (commonBatchNumber == null || commonBatchNumber.getDataType() != BatchTypeEnum.QUALITY_RAW_TEST.typeCode())
            return null;

        List<TestItemResultRecord> testItemResultRecords = testItemResultRecordMapper.getByTestReportId(testReportRecord.getId());
        QualitySampleDeliveringRecordRawExample example = new QualitySampleDeliveringRecordRawExample();
        example.createCriteria().andSampleIdEqualTo(id);
        QualitySampleDeliveringRecordRaw qualitySampleDeliveringRecordRaw = (QualitySampleDeliveringRecordRaw)ComUtil.getOne(qualitySampleDeliveringRecordRawMapper.selectByExample(example));
        Integer standardId = qualitySampleDeliveringRecordRaw==null?null:qualitySampleDeliveringRecordRaw.getStandardId();
        for (TestItemResultRecord record : testItemResultRecords) {
            TestDTO testDTO = new TestDTO();
            TestItem testItem = testItemMapper.find(record.getTestItemId());
            String testItemName = testItem==null?"":testItem.getName();
            testDTO.setName(testItemName);
            testDTO.setTestItemResultRecord(record);
            testDTO.setUnit(testItem == null ? "" : testItem.getUnit());
            String value = techniqueRawTestItemStandardMapper.getValueByStandardIdByTestItemsId(standardId, testItem.getId());
            testDTO.setValue(value);
            testDTOS.add(testDTO);
        }

        TechniqueRawStandardRecord rawStandardRecord = rawStandardRecordMapper.findById(sampleDeliveringRecord.getSerialNumberId());
        TechniqueBaseRawMaterial material = rawMaterialMapper.getById(rawStandardRecord.getRawMaterialId());
        Assert.notNull(material, String.format("不存在id为%s原材料",rawStandardRecord.getRawMaterialId()));

        rawTestReportDTO.setTestDTOS(testDTOS);
        rawTestReportDTO.setSampleDeliveringRecord(sampleDeliveringRecord);
        rawTestReportDTO.setMaterialName(material.getName());
        //rawTestReportDTO.setSerialNumber(repoBaseSerialNumber.getSerialNumber());
        rawTestReportDTO.setTestReportRecord(testReportRecord);
        rawTestReportDTO.setCommonBatchNumber(commonBatchNumber);
        if (commonBatchNumber.getStatus() == BatchStatusEnum.SAVE.status()) {
            return rawTestReportDTO;
        } else {
            rawTestReportDTO.setTester(authUserDTO.getName());
        }
        return rawTestReportDTO;
    }


    /**
     * 更新结果
     *
     * @param rawTestReportDTO
     * @return RawTestReportDTO
     */
    @Override
    @Transactional
    public RawTestReportDTO updateResults(RawTestReportDTO rawTestReportDTO) {

        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(rawTestReportDTO.getSampleDeliveringRecord().getId());
        Assert.isTrue(null != sampleDeliveringRecord
                && sampleDeliveringRecord.getType().equals(QualitySampleTypeEnum.SAMPLE_RAWMATERIAL.get())
                && sampleDeliveringRecord.getAcceptStatus().equals(QualitySampleStatusEnum.SAMPLE_ACCEPTED.get()), "录检失败,送检数据异常");

        //更新审批记录
        TestReportRecord testReportRecord = testReportRecordMapper.getBySampleDeliveringRecordId(sampleDeliveringRecord.getId());
        //TODO 默认都为合格 待设计

        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(testReportRecord.getBatchNumberId());
        Assert.isTrue(null != commonBatchNumber
                && BATCH_QUALITY_RAW_TEST.equals(commonBatchNumber.getDataType())
                && BatchStatusEnum.SAVE.status() == commonBatchNumber.getStatus(), "原材料录检数据不存在或为不可编辑的状态");

        TestReportRecord newTestReport = rawTestReportDTO.getTestReportRecord();
        Integer judger = newTestReport.getJudger();
        Assert.isTrue(null != judger && null != authUserMapper.byId(judger), "请输入检验人id");

        testReportRecord.setIsQualified(1);
        testReportRecord.setJudger(judger);
        testReportRecord.setJudgeDate(new Date());
        testReportRecordMapper.update(testReportRecord);

        //更新检测结果

        List<TestDTO> testDTOS = rawTestReportDTO.getTestDTOS();
        Assert.notNull(testDTOS, "检测结果不存在");
        for (TestDTO dto : testDTOS) {
            testItemResultRecordMapper.update(dto.getTestItemResultRecord());
        }

        //得到批号id
        rawTestReportDTO.setCommonBatchNumber(commonBatchNumber);

        return rawTestReportDTO;
    }

    /**
     * 根据送样工厂名模糊查询所有-分页
     */
    @Override
    public PageBean<RawTestReportDTO> findByFactoryNameByPage(String factoryName, PageBean pageBean) {
        List<SampleDeliveringRecord> sampleDeliveringRecords = sampleDeliveringRecordMapper.getAllByFactors(QualitySampleTypeEnum.SAMPLE_RAWMATERIAL.get(), QualitySampleStatusEnum.SAMPLE_ACCEPTED.get(), factoryName, pageBean);
        if (null == sampleDeliveringRecords || 0 == sampleDeliveringRecords.size()) return null;
        List<RawTestReportDTO> dtoList = new ArrayList<>(sampleDeliveringRecords.size());

        for (SampleDeliveringRecord record : sampleDeliveringRecords) {
            RawTestReportDTO dto = new RawTestReportDTO();

            TestReportRecord testReportRecord = testReportRecordMapper.getBySampleDeliveringRecordId(record.getId());
            Assert.notNull(testReportRecord, "检测结果不存在");
            CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(testReportRecord.getBatchNumberId());
            AuthUserDTO authUserDTO1 = authUserMapper.byId(record.getDelivererId());
            DeliveryFactory deliveryFactory = deliveryFactoryMapper.findById(record.getDeliveryFactoryId());
            RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(record.getSerialNumberId());

            String itemNames = testItemUtil.convertItemNames((record.getTestItems()));

            dto.setTestReportRecord(testReportRecord);
            dto.setSampleDeliveringRecord(record);
            dto.setCommonBatchNumber(commonBatchNumber);
            dto.setDeliverer(authUserDTO1.getName());
            dto.setDeliveryFactoryName(deliveryFactory==null?"":deliveryFactory.getName());
            dto.setSerialNumber(repoBaseSerialNumber==null?"":repoBaseSerialNumber.getMaterialName());
            dto.setTestItemString(itemNames);

            dtoList.add(dto);
        }

        pageBean.setList(dtoList);
        pageBean.setTotal(sampleDeliveringRecordMapper.countSum(QualitySampleTypeEnum.SAMPLE_RAWMATERIAL.get(), QualitySampleStatusEnum.SAMPLE_ACCEPTED.get(), factoryName));
        return pageBean;
    }


    /**
     * 详情（根据批号id查）
     */
    @Override
    public RawTestReportDTO Details(Integer id) {
        RawTestReportDTO rawTestReportDTO = new RawTestReportDTO();
        List<TestDTO> testDTOS = new ArrayList<>();

        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(id);
        if (commonBatchNumber == null || commonBatchNumber.getDataType() != BatchTypeEnum.QUALITY_RAW_TEST.typeCode())
            return null;

        TestReportRecord testReportRecord = testReportRecordMapper.getByBatchNumberId(id);
        if (testReportRecord == null) return null;

        SampleDeliveringRecord sampleDeliveringRecord = sampleDeliveringRecordMapper.getById(testReportRecord.getSampleDeliveringRecordId());

        if (null == sampleDeliveringRecord || sampleDeliveringRecord.getType() != QualitySampleTypeEnum.SAMPLE_RAWMATERIAL.get() || sampleDeliveringRecord.getAcceptStatus() != QualitySampleStatusEnum.SAMPLE_ACCEPTED.get())
            return null;
        RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(sampleDeliveringRecord.getSerialNumberId());
        AuthUserDTO authUserDTO = authUserMapper.byId(testReportRecord.getJudger());

        List<TestItemResultRecord> testItemResultRecords = testItemResultRecordMapper.getByTestReportId(testReportRecord.getId());
        for (TestItemResultRecord record : testItemResultRecords) {
            TestDTO testDTO = new TestDTO();
            TestItem testItem = testItemMapper.find(record.getTestItemId());
            testDTO.setName(testItem==null?"":testItem.getName());
            testDTO.setTestItemResultRecord(record);
            testDTO.setUnit(testItem == null ? "" : testItem.getUnit());
            testDTOS.add(testDTO);
        }

        rawTestReportDTO
                .setTestDTOS(testDTOS)
                .setSampleDeliveringRecord(sampleDeliveringRecord)
                .setMaterialName(repoBaseSerialNumber.getMaterialName())
                .setSerialNumber(repoBaseSerialNumber.getSerialNumber())
                .setTestReportRecord(testReportRecord)
                .setCommonBatchNumber(commonBatchNumber);

        if (commonBatchNumber.getStatus() == BatchStatusEnum.SAVE.status()) {
            return rawTestReportDTO;
        } else {
            rawTestReportDTO.setTester(authUserDTO.getName());
        }
        return rawTestReportDTO;
    }

}