package com.jinchi.common.model.responsibilitypattern;

import com.jinchi.common.domain.CommonBatchNumber;

/**
 * @author：XudongHu
 * @className:ProCommonHandle
 * @description:
 * @date:16:36 2019/3/15
 * @Modifer:
 */
public class ProCommonHandle extends AbstractCommonBatch {
    @Override
    protected Object check(CommonBatchNumber batchNumber) {
        return null;
    }
}
