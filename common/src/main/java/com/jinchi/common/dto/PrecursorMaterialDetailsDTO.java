package com.jinchi.common.dto;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-13 11:29
 * @description:
 **/

public class PrecursorMaterialDetailsDTO {
    private Integer code;
    private String materialName;
    private Byte dataType;
    private Byte valueType;
    private Byte types;
    private Integer processCode;
    private String processName;
    private Byte mn;
    private Byte co;
    private Byte ni;
    private Byte amm;
    private Byte alk;

    public Byte getValueType() {
        return valueType;
    }

    public void setValueType(Byte valueType) {
        this.valueType = valueType;
    }

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

    public Byte getMn() {
        return mn;
    }

    public void setMn(Byte mn) {
        this.mn = mn;
    }

    public Byte getCo() {
        return co;
    }

    public void setCo(Byte co) {
        this.co = co;
    }

    public Byte getNi() {
        return ni;
    }

    public void setNi(Byte ni) {
        this.ni = ni;
    }

    public Byte getAmm() {
        return amm;
    }

    public void setAmm(Byte amm) {
        this.amm = amm;
    }

    public Byte getAlk() {
        return alk;
    }

    public void setAlk(Byte alk) {
        this.alk = alk;
    }
}
