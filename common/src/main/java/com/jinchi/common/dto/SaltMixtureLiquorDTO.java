package com.jinchi.common.dto;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-05 12:23
 * @description: 原料领用 补料数据 混合盐溶液DTO
 **/

public class SaltMixtureLiquorDTO {
    private Integer materialCode;
    private String materialName;
    private Integer materialTypeCode;
    private Float weights;
    private Float density;
    private Float niConcentration;
    private Float coConcentration;
    private Float mnConcentration;

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

    public Float getNiConcentration() {
        return niConcentration;
    }

    public void setNiConcentration(Float niConcentration) {
        this.niConcentration = niConcentration;
    }

    public Float getCoConcentration() {
        return coConcentration;
    }

    public void setCoConcentration(Float coConcentration) {
        this.coConcentration = coConcentration;
    }

    public Float getMnConcentration() {
        return mnConcentration;
    }

    public void setMnConcentration(Float mnConcentration) {
        this.mnConcentration = mnConcentration;
    }

    public Integer getMaterialTypeCode() {
        return materialTypeCode;
    }

    public void setMaterialTypeCode(Integer materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
    }
}
