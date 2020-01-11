package com.jinchi.common.dto;

import com.jinchi.common.domain.ProductStorageStatisticHead;

public class ProductStorageHeadDTO {

    private ProductStorageStatisticHead head;

    String periodName;

    public ProductStorageStatisticHead getHead() {
        return head;
    }

    public void setHead(ProductStorageStatisticHead head) {
        this.head = head;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }
}
