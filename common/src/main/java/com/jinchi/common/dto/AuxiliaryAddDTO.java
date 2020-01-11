package com.jinchi.common.dto;

import com.jinchi.common.domain.AuxiliaryMaterialsStatisticHead;

import java.util.List;

public class AuxiliaryAddDTO {

    private AuxiliaryMaterialsStatisticHead head;

    private List<GoodInProcessDTO> processDTOS;

    public AuxiliaryMaterialsStatisticHead getHead() {
        return head;
    }

    public void setHead(AuxiliaryMaterialsStatisticHead head) {
        this.head = head;
    }

    public List<GoodInProcessDTO> getProcessDTOS() {
        return processDTOS;
    }

    public void setProcessDTOS(List<GoodInProcessDTO> processDTOS) {
        this.processDTOS = processDTOS;
    }
}
