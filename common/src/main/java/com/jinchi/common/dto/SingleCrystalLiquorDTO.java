package com.jinchi.common.dto;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-05 14:06
 * @description: 原料领用 补料数据 单晶体溶液DTO
 **/

public class SingleCrystalLiquorDTO {
    private Integer materialCode;
    private String materialName;
    private Integer materialTypeCode;
    private Float weights;
    private Float density;
    private Float concentration;
    private Boolean niFlag;
    private Boolean coFlag;
    private Boolean mnFlag;

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

    public Float getWeights() {
        return weights;
    }

    public void setWeights(Float weights) {
        this.weights = weights;
    }

    public Float getDensity() {
        return density;
    }

    public void setDensity(Float density) {
        this.density = density;
    }

    public Float getConcentration() {
        return concentration;
    }

    public void setConcentration(Float concentration) {
        this.concentration = concentration;
    }

    public Integer getMaterialTypeCode() {
        return materialTypeCode;
    }

    public void setMaterialTypeCode(Integer materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
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
}
