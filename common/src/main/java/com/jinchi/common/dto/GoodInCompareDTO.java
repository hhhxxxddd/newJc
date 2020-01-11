package com.jinchi.common.dto;

public class GoodInCompareDTO {

    Integer periodNum;

    String lineName;

    Float ni;

    Float co;

    Float mn;

    Float amm;

    Float alk;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Float getAmm() {
        return amm;
    }

    public void setAmm(Float amm) {
        this.amm = amm;
    }

    public Float getAlk() {
        return alk;
    }

    public void setAlk(Float alk) {
        this.alk = alk;
    }

    public Integer getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(Integer periodNum) {
        this.periodNum = periodNum;
    }

    public Float getNi() {
        return ni;
    }

    public void setNi(Float ni) {
        this.ni = ni;
    }

    public Float getCo() {
        return co;
    }

    public void setCo(Float co) {
        this.co = co;
    }

    public Float getMn() {
        return mn;
    }

    public void setMn(Float mn) {
        this.mn = mn;
    }
}
