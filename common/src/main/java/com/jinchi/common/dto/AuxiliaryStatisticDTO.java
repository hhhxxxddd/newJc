package com.jinchi.common.dto;

import com.jinchi.common.domain.AuxiliaryMaterialsStatisticHead;

public class AuxiliaryStatisticDTO<T> {

    AuxiliaryMaterialsStatisticHead head;

    T process;

    String processName;

    String lineName;

    String periodName;

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
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

    public T getProcess() {
        return process;
    }

    public void setProcess(T process) {
        this.process = process;
    }
}
