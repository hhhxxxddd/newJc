package com.jinchi.common.service;

import com.jinchi.common.domain.TestReportRecord;
import com.jinchi.common.dto.SampleDeliveringRecordDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/11/20.
 */
@Service
public interface TestReportRecordService {
    TestReportRecord getBySampleDeliveringRecordId(Integer id);


    TestReportRecord insert(SampleDeliveringRecordDTO sampleDeliveringRecordDTO);

    TestReportRecord update(SampleDeliveringRecordDTO sampleDeliveringRecordDTO);

    TestReportRecord update(TestReportRecord testReportRecord);

    SampleDeliveringRecordDTO getDetailBySampleDeliverdingRecordId(Integer id);

    void updatePurchaseReportRecordId(Integer purchaseReportRecordId, Integer[] ids);
}
