package com.jinchi.common.model.responsibilitypattern;

import com.jinchi.common.domain.CommonBatchNumber;

/**
 * @author：XudongHu
 * @className:RawCommon
 * @description:
 * @date:16:35 2019/3/15
 * @Modifer:
 */
public class RawCommonHandle extends AbstractCommonBatch {
    @Override
    protected Object check(CommonBatchNumber batchNumber) {
        return null;
    }
}
