package com.jinchi.common.model.responsibilitypattern;

import com.jinchi.common.domain.CommonBatchNumber;
import io.jsonwebtoken.lang.Assert;

/**
 * @author：XudongHu
 * @className:AbstractCommonBatch
 * @description:
 * @date:11:15 2019/3/15
 * @Modifer:
 */
public abstract class AbstractCommonBatch {
    protected Integer batchType;
    protected AbstractCommonBatch nextHandle;

    //设置责任链中的下一个责任类
    public AbstractCommonBatch nextHandle(AbstractCommonBatch nextHandle) {
        this.nextHandle = nextHandle;
        return this;
    }

    //判断是否由当前责任链处理
    public void deal(CommonBatchNumber batchNumber) {
        Assert.notNull(batchNumber.getDataType(), String.format(String.format("责任链异常=>id为%d的批号类型设置为空",batchNumber.getId())));
        if (batchNumber.getDataType().equals(batchType)) {
            check(batchNumber);
        }
        nextHandle.deal(batchNumber);
    }

    abstract protected Object check(CommonBatchNumber batchNumber);
}
