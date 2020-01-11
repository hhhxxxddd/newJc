package com.jinchi.common.dto;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-05 11:08
 * @description: 原料领用 补料数据 晶体DTO
 **/

public class CrystalsDTO {
    private Integer materialCode;
    private String materialName;
    private Integer materialTypeCode;
    private Float weight;
    private Float concentration;
    private Boolean niFlag;//标记位
    private Boolean coFlag;//标记位
    private Boolean mnFlag;//标记位

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

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getConcentration() {
        return concentration;
    }

    public void setConcentration(Float concentration) {
        this.concentration = concentration;
    }

    public Boolean getNiFlag() {
        return niFlag;
    }

    public void setNiFlag(Boolean niFlag) {
        this.niFlag = niFlag;
    }

    public Boolean getCoFlag() {
        return coFlag;
    }

    public void setCoFlag(Boolean coFlag) {
        this.coFlag = coFlag;
    }

    public Boolean getMnFlag() {
        return mnFlag;
    }

    public void setMnFlag(Boolean mnFlag) {
        this.mnFlag = mnFlag;
    }

    public Integer getMaterialTypeCode() {
        return materialTypeCode;
    }

    public void setMaterialTypeCode(Integer materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
    }
}
