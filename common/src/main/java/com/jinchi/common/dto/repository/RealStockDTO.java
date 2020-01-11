package com.jinchi.common.dto.repository;

public class RealStockDTO {
    private int realWeight;
    private String batch;
    private String materialName;

    public int getRealWeight() {
        return realWeight;
    }

    public void setRealWeight(int realWeight) {
        this.realWeight = realWeight;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
