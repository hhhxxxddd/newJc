package com.jinchi.common.dto;

import com.jinchi.common.domain.AuxiliaryMaterialsStatisticDataList;
import com.jinchi.common.domain.AuxiliaryMaterialsStatisticHead;

import java.util.List;

public class AuxiliaryDetailDTO {

    AuxiliaryMaterialsStatisticHead head;

    AuxiliaryMaterialsStatisticDataList processTotal;

    String processName;

    List processes;

    String periodName;

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public List getProcesses() {
        return processes;
    }

    public void setProcesses(List processes) {
        this.processes = processes;
    }

    public AuxiliaryMaterialsStatisticDataList getProcessTotal() {
        return processTotal;
    }

    public void setProcessTotal(AuxiliaryMaterialsStatisticDataList processTotal) {
        this.processTotal = processTotal;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public AuxiliaryMaterialsStatisticHead getHead() {
        return head;
    }

    public void setHead(AuxiliaryMaterialsStatisticHead head) {
        this.head = head;
    }
}
