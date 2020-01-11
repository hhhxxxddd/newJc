package com.jinchi.common.dto;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-11 16:31
 * @description:
 **/

public class WeightDTO {
    Integer lineCode;
    String lineName;
    Float weightValue;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Float getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(Float weightValue) {
        this.weightValue = weightValue;
    }

    public Integer getLineCode() {
        return lineCode;
    }

    public void setLineCode(Integer lineCode) {
        this.lineCode = lineCode;
    }
}
