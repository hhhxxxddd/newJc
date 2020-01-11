package com.jinchi.common.dto;

import com.jinchi.common.domain.*;

import java.util.List;

public class ProductStorageDetailDTO {

    private ProductStorageStatisticHead head;

    private String periodName;

    private ProductStorageStatisticDataList list;

    ///

    private List<ProductStorageStatisticDataDetails> dataDetails;

    private ProductStorageStatisticByLineTotals totals;

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

    public ProductStorageStatisticDataList getList() {
        return list;
    }

    public void setList(ProductStorageStatisticDataList list) {
        this.list = list;
    }

    public List<ProductStorageStatisticDataDetails> getDataDetails() {
        return dataDetails;
    }

    public void setDataDetails(List<ProductStorageStatisticDataDetails> dataDetails) {
        this.dataDetails = dataDetails;
    }

    public ProductStorageStatisticByLineTotals getTotals() {
        return totals;
    }

    public void setTotals(ProductStorageStatisticByLineTotals totals) {
        this.totals = totals;
    }
}
