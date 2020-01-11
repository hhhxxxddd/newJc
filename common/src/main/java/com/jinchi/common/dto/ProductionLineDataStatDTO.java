package com.jinchi.common.dto;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-11 14:12
 * @description:
 **/

public class ProductionLineDataStatDTO {
    private Integer lineCode;

    private Float totals;

    private Float niValue;

    private Float coValue;

    private Float mnValue;

    public Integer getLineCode() {
        return lineCode;
    }

    public void setLineCode(Integer lineCode) {
        this.lineCode = lineCode;
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
