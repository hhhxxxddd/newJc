package com.jinchi.common.dto;

import com.jinchi.common.domain.ProductStorageStatisticHead;

import java.util.List;

public class ProductStorageEditDTO {

    private ProductStorageStatisticHead head;

    String periodName;

    List pageInfo;

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

    public List getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(List pageInfo) {
        this.pageInfo = pageInfo;
    }
}
