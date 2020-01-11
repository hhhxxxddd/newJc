package com.jinchi.common.dto;

public class AuxiliaryProcessDTO<T> {

    T process;

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
