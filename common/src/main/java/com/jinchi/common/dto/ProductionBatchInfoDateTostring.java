package com.jinchi.common.dto;

import com.jinchi.common.domain.ProductionBatchInfo;

import java.util.Date;

public class ProductionBatchInfoDateTostring {

    private ProductionBatchInfo productionBatchInfo;
    private String setTime;

    private String modifyTime;

    private String startTime;
    private String endTime;
    public ProductionBatchInfoDateTostring() {

    }

    public ProductionBatchInfoDateTostring(ProductionBatchInfo productionBatchInfo, String setTime, String modifyTime, String startTime, String endTime) {
        this.productionBatchInfo = productionBatchInfo;
        this.setTime = setTime;
        this.modifyTime = modifyTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ProductionBatchInfo getProductionBatchInfo() {
        return productionBatchInfo;
    }

    public void setProductionBatchInfo(ProductionBatchInfo productionBatchInfo) {
        this.productionBatchInfo = productionBatchInfo;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}