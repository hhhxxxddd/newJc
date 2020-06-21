package com.jinchi.common.dto.anode;

/**
 * @author: LiuTaoYi
 * @time: 2020/6/20 23:42
 * @description:
 */
public class AnodeCoefficientRateDTO {

    private Integer code;

    private Integer productionCode;

    private Float precursors;

    private Float lithiumCarbonate;

    private Float premix;

    private Float production;

    private Float coeA;

    private Float coeB;

    private Float coeC;

    private Float coeD;

    private Float coeE;

    private Float coeF;

    private Float coeG;

    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(Integer productionCode) {
        this.productionCode = productionCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrecursors() {
        return precursors;
    }

    public void setPrecursors(Float precursors) {
        this.precursors = precursors;
    }

    public Float getLithiumCarbonate() {
        return lithiumCarbonate;
    }

    public void setLithiumCarbonate(Float lithiumCarbonate) {
        this.lithiumCarbonate = lithiumCarbonate;
    }

    public Float getPremix() {
        return premix;
    }

    public void setPremix(Float premix) {
        this.premix = premix;
    }

    public Float getProduction() {
        return production;
    }

    public void setProduction(Float production) {
        this.production = production;
    }

    public Float getCoeA() {
        return coeA;
    }

    public void setCoeA(Float coeA) {
        this.coeA = coeA;
    }

    public Float getCoeB() {
        return coeB;
    }

    public void setCoeB(Float coeB) {
        this.coeB = coeB;
    }

    public Float getCoeC() {
        return coeC;
    }

    public void setCoeC(Float coeC) {
        this.coeC = coeC;
    }

    public Float getCoeD() {
        return coeD;
    }

    public void setCoeD(Float coeD) {
        this.coeD = coeD;
    }

    public Float getCoeE() {
        return coeE;
    }

    public void setCoeE(Float coeE) {
        this.coeE = coeE;
    }

    public Float getCoeF() {
        return coeF;
    }

    public void setCoeF(Float coeF) {
        this.coeF = coeF;
    }

    public Float getCoeG() {
        return coeG;
    }

    public void setCoeG(Float coeG) {
        this.coeG = coeG;
    }

    public AnodeCoefficientRateDTO(Integer code, Integer productionCode, Float precursors, Float lithiumCarbonate, Float premix, Float production, Float coeA, Float coeB, Float coeC, Float coeD, Float coeE, Float coeF, Float coeG, String name) {
        this.code = code;
        this.productionCode = productionCode;
        this.precursors = precursors;
        this.lithiumCarbonate = lithiumCarbonate;
        this.premix = premix;
        this.production = production;
        this.coeA = coeA;
        this.coeB = coeB;
        this.coeC = coeC;
        this.coeD = coeD;
        this.coeE = coeE;
        this.coeF = coeF;
        this.coeG = coeG;
        this.name = name;
    }
}
