package com.jinchi.common.model.responsibilitypattern;

import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.domain.CommonBatchNumber;
import com.jinchi.common.service.MiddleProductionDetectionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author：XudongHu
 * @className:MedCommonHandle
 * @description:
 * @date:16:36 2019/3/15
 * @Modifer:
 */
public class MedCommonHandle extends AbstractCommonBatch {
    @Autowired
    private MiddleProductionDetectionService middleProductionDetectionService;

    protected Integer batchType = BatchTypeEnum.QUALITY_INTERMEDIATE_TEST.typeCode();

    @Override
    protected Object check(CommonBatchNumber batchNumber) {
        return middleProductionDetectionService.findDetailsByBatchNumberId(batchNumber.getId());
    }
}
