package com.jinchi.common.dto;

public class GoodInStatisticProcessDTO<T> {

    T process;//6个工序对应的表的实体类

    String materialName;

    public T getProcess() {
        return process;
    }

    public void setProcess(T process) {
        this.process = process;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
