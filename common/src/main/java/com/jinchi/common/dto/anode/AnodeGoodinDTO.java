package com.jinchi.common.dto.anode;


import com.jinchi.common.domain.AnodeGoodsInProcessStatisticHead;
import com.jinchi.common.domain.BasicInfoAnodeProductionLine;
import com.jinchi.common.domain.BasicInfoAnodeProductionType;

import java.util.List;

public class AnodeGoodinDTO {

    //头表
    private AnodeGoodsInProcessStatisticHead head;

    private BasicInfoAnodeProductionLine line;

    private BasicInfoAnodeProductionType type;

    private String periodName;

    //各个工序下的详情
    private List<AnodeProcess> processes;

    public AnodeGoodsInProcessStatisticHead getHead() {
        return head;
    }

    public void setHead(AnodeGoodsInProcessStatisticHead head) {
        this.head = head;
    }

    public BasicInfoAnodeProductionLine getLine() {
        return line;
    }

    public void setLine(BasicInfoAnodeProductionLine line) {
        this.line = line;
    }

    public BasicInfoAnodeProductionType getType() {
        return type;
    }

    public void setType(BasicInfoAnodeProductionType type) {
        this.type = type;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public List<AnodeProcess> getProcesses() {
        return processes;
    }

    public void setProcesses(List<AnodeProcess> processes) {
        this.processes = processes;
    }
}
