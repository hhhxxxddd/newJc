package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class GoodInTableDTO {

    Integer periodId;

    String period;

    Integer lineName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    Date endTime;

    List<GoodInProcessDTO> goodInProcessDTOS;

    List<BasicInfoPrecursorMaterialDetailsDTO> otherMaterials;

    public List<BasicInfoPrecursorMaterialDetailsDTO> getOtherMaterials() {
        return otherMaterials;
    }

    public void setOtherMaterials(List<BasicInfoPrecursorMaterialDetailsDTO> otherMaterials) {
        this.otherMaterials = otherMaterials;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getLineName() {
        return lineName;
    }

    public void setLineName(Integer lineName) {
        this.lineName = lineName;
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

    public List<GoodInProcessDTO> getGoodInProcessDTOS() {
        return goodInProcessDTOS;
    }

    public void setGoodInProcessDTOS(List<GoodInProcessDTO> goodInProcessDTOS) {
        this.goodInProcessDTOS = goodInProcessDTOS;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    @Override
    public String toString() {
        return "GoodInTableDTO{" +
                "periodId=" + periodId +
                ", period='" + period + '\'' +
                ", lineName=" + lineName +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", goodInProcessDTOS=" + goodInProcessDTOS +
                '}';
    }
}
