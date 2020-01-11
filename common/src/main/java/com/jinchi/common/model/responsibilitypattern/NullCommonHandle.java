package com.jinchi.common.model.responsibilitypattern;

import com.jinchi.common.domain.CommonBatchNumber;

/**
 * @author：XudongHu
 * @className:NullCommonHandle
 * @description: 无批号责任类
 * @date:16:37 2019/3/15
 * @Modifer:
 */
public class NullCommonHandle extends AbstractCommonBatch {
    @Override
    protected Object check(CommonBatchNumber batchNumber) {
        return null;
    }
}
