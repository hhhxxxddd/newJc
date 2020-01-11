package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-12 14:50
 * @description:
 **/

public class ProductionLineStatDTO {

    private String periodType;

    private Integer periodNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    private String productionLine;

    private Float totals;

    private Float niValue;

    private Float coValue;

    private Float mnValue;

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public Integer getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(Integer periodNum) {
        this.periodNum = periodNum;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProductionLine() {
        return productionLine;
    }

    public void setProductionLine(String productionLine) {
        this.productionLine = productionLine;
    }

    public Float getTotals() {
        return totals;
    }

    public void setTotals(Float totals) {
        this.totals = totals;
    }

    public Float getNiValue() {
        return niValue;
    }

    public void setNiValue(Float niValue) {
        this.niValue = niValue;
    }

    public Float getCoValue() {
        return coValue;
    }

    public void setCoValue(Float coValue) {
        this.coValue = coValue;
    }

    public Float getMnValue() {
        return mnValue;
    }

    public void setMnValue(Float mnValue) {
        this.mnValue = mnValue;
    }
}
