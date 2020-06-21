package com.jinchi.common.dto.anode;

/**
 * @author: LiuTaoYi
 * @time: 2020/6/21 9:34
 * @description:
 */
public class AnodeBurningLossRateDTO {

    private Integer code;

    private Integer productionCode;

    private Float lossrate1;

    private Float lossrate2;

    private Float lossrate3;

    private Float lossrate4;

    private Float lossrate5;

    private Float lossrate6;

    private Float lossrate7;

    private Float lossrate8;

    private String name;

    public AnodeBurningLossRateDTO(Integer code, Integer productionCode, Float lossrate1, Float lossrate2, Float lossrate3, Float lossrate4, Float lossrate5, Float lossrate6, Float lossrate7, Float lossrate8, String name) {
        this.code = code;
        this.productionCode = productionCode;
        this.lossrate1 = lossrate1;
        this.lossrate2 = lossrate2;
        this.lossrate3 = lossrate3;
        this.lossrate4 = lossrate4;
        this.lossrate5 = lossrate5;
        this.lossrate6 = lossrate6;
        this.lossrate7 = lossrate7;
        this.lossrate8 = lossrate8;
        this.name = name;
    }

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

    public Float getLossrate1() {
        return lossrate1;
    }

    public void setLossrate1(Float lossrate1) {
        this.lossrate1 = lossrate1;
    }

    public Float getLossrate2() {
        return lossrate2;
    }

    public void setLossrate2(Float lossrate2) {
        this.lossrate2 = lossrate2;
    }

    public Float getLossrate3() {
        return lossrate3;
    }

    public void setLossrate3(Float lossrate3) {
        this.lossrate3 = lossrate3;
    }

    public Float getLossrate4() {
        return lossrate4;
    }

    public void setLossrate4(Float lossrate4) {
        this.lossrate4 = lossrate4;
    }

    public Float getLossrate5() {
        return lossrate5;
    }

    public void setLossrate5(Float lossrate5) {
        this.lossrate5 = lossrate5;
    }

    public Float getLossrate6() {
        return lossrate6;
    }

    public void setLossrate6(Float lossrate6) {
        this.lossrate6 = lossrate6;
    }

    public Float getLossrate7() {
        return lossrate7;
    }

    public void setLossrate7(Float lossrate7) {
        this.lossrate7 = lossrate7;
    }

    public Float getLossrate8() {
        return lossrate8;
    }

    public void setLossrate8(Float lossrate8) {
        this.lossrate8 = lossrate8;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
