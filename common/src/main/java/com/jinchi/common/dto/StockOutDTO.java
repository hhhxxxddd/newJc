package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-05 17:04
 * @description: 出库数据记录DTO
 **/

public class StockOutDTO {

    private Integer materialCode;
    private String materialName;
    private Integer materialTypeCode;

    private String materialBatch;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date outStockTime;
    private String weight;
    private String callMaterialPoint;//叫料点

    public Integer getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(Integer materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getMaterialTypeCode() {
        return materialTypeCode;
    }

    public void setMaterialTypeCode(Integer materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
    }

    public Date getOutStockTime() {
        return outStockTime;
    }

    public void setOutStockTime(Date outStockTime) {
        this.outStockTime = outStockTime;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCallMaterialPoint() {
        return callMaterialPoint;
    }

    public void setCallMaterialPoint(String callMaterialPoint) {
        this.callMaterialPoint = callMaterialPoint;
    }

    public String getMaterialBatch() {
        return materialBatch;
    }

    public void setMaterialBatch(String materialBatch) {
        this.materialBatch = materialBatch;
    }
}
