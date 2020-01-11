package com.jinchi.common.dto.anode;

import java.util.List;

public class AnodeProcess {

    private Integer periodId;

    private String periodName;

    private List<AnodeMaterial> materials;

    /*private List<AnodeMaterial> others;

    public List<AnodeMaterial> getOthers() {
        return others;
    }

    public void setOthers(List<AnodeMaterial> others) {
        this.others = others;
    }*/

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public List<AnodeMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<AnodeMaterial> materials) {
        this.materials = materials;
    }
}
