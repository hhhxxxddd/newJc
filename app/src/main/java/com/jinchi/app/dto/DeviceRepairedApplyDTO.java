package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceRepairedApplyDTO {

    Long repairId;

    Integer flag;//flag=1待维修的接单，flag=2已接单的提交，flag=3已接单的暂存

    String faultReason;

    String partners;

    String timeConsuming;

    List<DeviceRepairAccDTO> accs;

    Integer receivePeopleId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getReceivePeopleId() {
        return receivePeopleId;
    }

    public void setReceivePeopleId(Integer receivePeopleId) {
        this.receivePeopleId = receivePeopleId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Long getRepairId() {
        return repairId;
    }

    public void setRepairId(Long repairId) {
        this.repairId = repairId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getFaultReason() {
        return faultReason;
    }

    public void setFaultReason(String faultReason) {
        this.faultReason = faultReason;
    }

    public List<DeviceRepairAccDTO> getAccs() {
        return accs;
    }

    public void setAccs(List<DeviceRepairAccDTO> accs) {
        this.accs = accs;
    }

    public String getPartners() {
        return partners;
    }

    public void setPartners(String partners) {
        this.partners = partners;
    }

    public String getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(String timeConsuming) {
        this.timeConsuming = timeConsuming;
    }
}
