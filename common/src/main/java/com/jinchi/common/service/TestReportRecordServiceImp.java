package com.jinchi.common.service;

import com.jinchi.common.domain.TestReportRecord;
import com.jinchi.common.dto.SampleDeliveringRecordDTO;
import com.jinchi.common.mapper.SampleDeliveringRecordMapper;
import com.jinchi.common.mapper.TestItemResultRecordMapper;
import com.jinchi.common.mapper.TestReportRecordMapper;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/11/20.
 */
@Service
public class TestReportRecordServiceImp implements TestReportRecordService {
    @Autowired
    private TestReportRecordMapper testReportRecordMapper;
    @Autowired
    private TestItemResultRecordMapper testItemResultRecordMapper;
    @Autowired
    private SampleDeliveringRecordMapper sampleDeliveringRecordMapper;

    @Override
    public TestReportRecord getBySampleDeliveringRecordId(Integer id) {
        return testReportRecordMapper.getBySampleDeliveringRecordId(id);
    }


    @Override
    public TestReportRecord insert(SampleDeliveringRecordDTO sampleDeliveringRecordDTO) {
        return null;
    }

    @Override
    public TestReportRecord update(SampleDeliveringRecordDTO sampleDeliveringRecordDTO) {
        return null;
    }

    @Override
    public TestReportRecord update(TestReportRecord testReportRecord) {
        testReportRecordMapper.update(testReportRecord);
        return testReportRecord;
    }

    @Override
    public SampleDeliveringRecordDTO getDetailBySampleDeliverdingRecordId(Integer id) {
        return null;
    }

    @Override
    public void updatePurchaseReportRecordId(Integer purchaseReportRecordId, Integer[] ids) {
        Assert.isTrue(ids.length == 0, "参数数组为空");
        testReportRecordMapper.updatePurchaseReportRecordId(purchaseReportRecordId, ids);
    }

}
