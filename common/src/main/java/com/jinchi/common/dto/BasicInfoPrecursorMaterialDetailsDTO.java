package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jinchi.common.domain.BasicInfoPrecursorMaterialDetails;

//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BasicInfoPrecursorMaterialDetailsDTO extends BasicInfoPrecursorMaterialDetails {
    //单晶体浓度,辅料统计的密度
    Float monPotency = 0f;
    //Ni浓度
    Float niPotency = 0f;
    //Co浓度
    Float coPotency = 0f;
    //Mn浓度
    Float mnPotency = 0f;
    //含固量
    Float solidContent = 0f;
    //重量 辅料统计的重量，入库，辅料消耗量
    Float weight = 0f;
    //体积 辅料统计的体积
    Float volume = 0f;
    //重量或体积
    Float weiOrVol = 0f;
    //氨浓度
    Float ammPotency = 0f;
    //碱浓度
    Float alkPotency = 0f;
    //氨重量
    Float ammValue = 0f;
    //碱重量
    Float alkValue = 0f;

    public BasicInfoPrecursorMaterialDetailsDTO(){

    }

    public BasicInfoPrecursorMaterialDetailsDTO(BasicInfoPrecursorMaterialDetails basicInfoPrecursorMaterialDetails){
        this.setCode(basicInfoPrecursorMaterialDetails.getCode());
        this.setMaterialName(basicInfoPrecursorMaterialDetails.getMaterialName());
        this.setDataType(basicInfoPrecursorMaterialDetails.getDataType());
        this.setValueType(basicInfoPrecursorMaterialDetails.getValueType());
        this.setTypes(basicInfoPrecursorMaterialDetails.getTypes());
        this.setProcessCode(basicInfoPrecursorMaterialDetails.getProcessCode());
        this.setMn(basicInfoPrecursorMaterialDetails.getMn());
        this.setNi(basicInfoPrecursorMaterialDetails.getNi());
        this.setCo(basicInfoPrecursorMaterialDetails.getCo());
        this.setAmmoniaFlag(basicInfoPrecursorMaterialDetails.getAmmoniaFlag());
        this.setAlkaliFlag(basicInfoPrecursorMaterialDetails.getAlkaliFlag());
    }

    public Float getMonPotency() {
        return monPotency;
    }

    public void setMonPotency(Float monPotency) {
        this.monPotency = monPotency;
    }

    public Float getNiPotency() {
        return niPotency;
    }

    public void setNiPotency(Float niPotency) {
        this.niPotency = niPotency;
    }

    public Float getCoPotency() {
        return coPotency;
    }

    public void setCoPotency(Float coPotency) {
        this.coPotency = coPotency;
    }

    public Float getMnPotency() {
        return mnPotency;
    }

    public void setMnPotency(Float mnPotency) {
        this.mnPotency = mnPotency;
    }

    public Float getSolidContent() {
        return solidContent;
    }

    public void setSolidContent(Float solidContent) {
        this.solidContent = solidContent;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Float getWeiOrVol() {
        return weiOrVol;
    }

    public void setWeiOrVol(Float weiOrVol) {
        this.weiOrVol = weiOrVol;
    }

    public Float getAmmPotency() {
        return ammPotency;
    }

    public void setAmmPotency(Float ammPotency) {
        this.ammPotency = ammPotency;
    }

    public Float getAlkPotency() {
        return alkPotency;
    }

    public void setAlkPotency(Float alkPotency) {
        this.alkPotency = alkPotency;
    }

    public Float getAmmValue() {
        return ammValue;
    }

    public void setAmmValue(Float ammValue) {
        this.ammValue = ammValue;
    }

    public Float getAlkValue() {
        return alkValue;
    }

    public void setAlkValue(Float alkValue) {
        this.alkValue = alkValue;
    }
}
