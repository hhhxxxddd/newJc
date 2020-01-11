package com.jinchi.common.dto.anode;

import com.jinchi.common.domain.BasicInfoAnodeMaterialTypes;
import com.jinchi.common.domain.BasicInfoMaterialType;

public class AnodeMaterial {

    private Integer code;

    private String materialName;

    //领料量
    private Float receive = 0f;

    //下方数据
    private Float value = 0f;

    //已混量
    private Float mix = 0f;

    //结存量
    private Float balance = 0f;

    //进料量
    private Float inMat = 0f;

    //入炉排数
    private Integer intoFurnace = 0;

    //出炉排数
    private Integer outFurnace = 0;

    //上面的数，下面的数 true 表格值，false下方值
    private Boolean flag;

    private Byte dateType;

    //详情中的消耗量
    private Float consum = 0f;

    public Float getConsum() {
        return consum;
    }

    public void setConsum(Float consum) {
        this.consum = consum;
    }

    public AnodeMaterial(){

    }

    public AnodeMaterial(BasicInfoAnodeMaterialTypes materialType){
        this.code = materialType.getCode();
        this.materialName = materialType.getMaterialName();
        this.flag = materialType.getFlag();
        this.dateType = materialType.getDataType();
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

    public Float getReceive() {
        return receive;
    }

    public void setReceive(Float receive) {
        this.receive = receive;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Float getMix() {
        return mix;
    }

    public void setMix(Float mix) {
        this.mix = mix;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getInMat() {
        return inMat;
    }

    public void setInMat(Float inMat) {
        this.inMat = inMat;
    }

    public Integer getIntoFurnace() {
        return intoFurnace;
    }

    public void setIntoFurnace(Integer intoFurnace) {
        this.intoFurnace = intoFurnace;
    }

    public Integer getOutFurnace() {
        return outFurnace;
    }

    public void setOutFurnace(Integer outFurnace) {
        this.outFurnace = outFurnace;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Byte getDateType() {
        return dateType;
    }

    public void setDateType(Byte dateType) {
        this.dateType = dateType;
    }
}
