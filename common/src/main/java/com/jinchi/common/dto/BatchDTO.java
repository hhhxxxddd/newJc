package com.jinchi.common.dto;

import com.jinchi.common.domain.ProductionBatchInfo;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-20 17:22
 * @description:
 **/

public class BatchDTO {
    Integer number;
    ProductionBatchInfo info;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public ProductionBatchInfo getInfo() {
        return info;
    }

    public void setInfo(ProductionBatchInfo info) {
        this.info = info;
    }
}
