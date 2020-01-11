package com.jinchi.common.dto;

import com.jinchi.common.domain.ProductStorageStatisticByLineDetail;
import com.jinchi.common.domain.ProductStorageStatisticHead;

public class ProductStorageStatisticDTO {

    private ProductStorageStatisticHead head;

    private ProductStorageStatisticByLineDetail lineDetail;

    private String periodName;

    private String lName;

    public ProductStorageStatisticHead getHead() {
        return head;
    }

    public void setHead(ProductStorageStatisticHead head) {
        this.head = head;
    }

    public ProductStorageStatisticByLineDetail getLineDetail() {
        return lineDetail;
    }

    public void setLineDetail(ProductStorageStatisticByLineDetail lineDetail) {
        this.lineDetail = lineDetail;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
