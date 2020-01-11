package com.jinchi.common.dto;

import com.jinchi.common.domain.BasicInfoPrecursorMaterialDetails;

import java.util.List;

public class GoodInProcessDTO {

    Integer processId;

    String processName;

    List<BasicInfoPrecursorMaterialDetailsDTO> materialDetails;

    List<GoodInLineProDTO> lineProDTOS;

    public List<GoodInLineProDTO> getLineProDTOS() {
        return lineProDTOS;
    }

    public void setLineProDTOS(List<GoodInLineProDTO> lineProDTOS) {
        this.lineProDTOS = lineProDTOS;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public List<BasicInfoPrecursorMaterialDetailsDTO> getMaterialDetails() {
        return materialDetails;
    }

    public void setMaterialDetails(List<BasicInfoPrecursorMaterialDetailsDTO> materialDetails) {
        this.materialDetails = materialDetails;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }
}
