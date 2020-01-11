package com.jinchi.common.dto;

public class MatPlcDTO {
    Integer code;

    Integer materialId;

    String material;

    Integer plcId;

    String plcAddress;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getPlcId() {
        return plcId;
    }

    public void setPlcId(Integer plcId) {
        this.plcId = plcId;
    }

    public String getPlcAddress() {
        return plcAddress;
    }

    public void setPlcAddress(String plcAddress) {
        this.plcAddress = plcAddress;
    }
}
