package com.jinchi.common.dto;

import com.jinchi.common.domain.AuxiliaryMaterialsStatisticHead;

public class AuxiliaryHeadDTO {
    AuxiliaryMaterialsStatisticHead head;

    String periodName;

    public AuxiliaryMaterialsStatisticHead getHead() {
        return head;
    }

    public void setHead(AuxiliaryMaterialsStatisticHead head) {
        this.head = head;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }
}
