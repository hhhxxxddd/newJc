package com.jinchi.common.dto;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 20:33
 * @description:
 **/

public class RawmaterialsDTO {

    private Integer code;

    private String materialName;

    private Byte dataType;

    private Byte phaseType;

    private Byte pickingType;

    private Integer typesCode;

    private Boolean niFlag;

    private Boolean coFlag;

    private Boolean mnFlag;

    private String typeName;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public Byte getPhaseType() {
        return phaseType;
    }

    public void setPhaseType(Byte phaseType) {
        this.phaseType = phaseType;
    }

    public Byte getPickingType() {
        return pickingType;
    }

    public void setPickingType(Byte pickingType) {
        this.pickingType = pickingType;
    }

    public Integer getTypesCode() {
        return typesCode;
    }

    public void setTypesCode(Integer typesCode) {
        this.typesCode = typesCode;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
