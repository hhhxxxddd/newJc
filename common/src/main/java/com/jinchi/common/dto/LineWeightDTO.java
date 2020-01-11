package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-11 16:21
 * @description:
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineWeightDTO {
    Integer code;
    Integer materialCode;
    String materialName;
    Byte types;
    Integer processCode;
    String processName;
    Boolean flag;

    List<WeightDTO> weightDTOS;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

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

    public Byte getTypes() {
        return types;
    }

    public void setTypes(Byte types) {
        this.types = types;
    }

    public Integer getProcessCode() {
        return processCode;
    }

    public void setProcessCode(Integer processCode) {
        this.processCode = processCode;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public List<WeightDTO> getWeightDTOS() {
        return weightDTOS;
    }

    public void setWeightDTOS(List<WeightDTO> weightDTOS) {
        this.weightDTOS = weightDTOS;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
