package com.jinchi.common.dto;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-22 22:34
 * @description:
 **/

public class CompoundCellDTO {
    private Integer code;

    private Integer lineCode;

    private String lineName;

    private Integer materialCode;

    private String materialName;

    private Float volumesValue;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getLineCode() {
        return lineCode;
    }

    public void setLineCode(Integer lineCode) {
        this.lineCode = lineCode;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
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

    public Float getVolumesValue() {
        return volumesValue;
    }

    public void setVolumesValue(Float volumesValue) {
        this.volumesValue = volumesValue;
    }
}
