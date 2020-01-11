package com.jinchi.common.dto;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-31 19:23
 * @description:
 **/

public class RawmaterialLineWeightDTO {
    private Integer materialCode;
    private String materialName;
    private Byte dataType;//物料来源
    private Integer materialTypeCode;//材料类别
    private String materialTypeName;
    List<WeightDTO> weightDTOS;

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

    public Byte getDataType() {
        return dataType;
    }

    public void setDataType(Byte dataType) {
        this.dataType = dataType;
    }

    public Integer getMaterialTypeCode() {
        return materialTypeCode;
    }

    public void setMaterialTypeCode(Integer materialTypeCode) {
        this.materialTypeCode = materialTypeCode;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public List<WeightDTO> getWeightDTOS() {
        return weightDTOS;
    }

    public void setWeightDTOS(List<WeightDTO> weightDTOS) {
        this.weightDTOS = weightDTOS;
    }
}
