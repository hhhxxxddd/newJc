package com.jinchi.common.dto;

import com.jinchi.common.domain.DevicePatrolPlanRecordHead;
import com.jinchi.common.domain.DevicePatrolPlanRecordItemDetails;
import com.jinchi.common.domain.DevicePatrolPlanRecordLocationDetails;

import java.util.List;

public class DevicePatrolPlanDTO {
    DevicePatrolPlanRecordHead devicePatrolPlanRecordHead;
    List<DevicePatrolPlanRecordItemDetails> devicePatrolPlanRecordItemDetailsList;
    List<DevicePatrolPlanRecordLocationDetails> devicePatrolPlanRecordLocationDetailsList;
    String detpName;
    String modelName;
    String tabPeopleName;

    public DevicePatrolPlanRecordHead getDevicePatrolPlanRecordHead() {
        return devicePatrolPlanRecordHead;
    }

    public void setDevicePatrolPlanRecordHead(DevicePatrolPlanRecordHead devicePatrolPlanRecordHead) {
        this.devicePatrolPlanRecordHead = devicePatrolPlanRecordHead;
    }

    public List<DevicePatrolPlanRecordItemDetails> getDevicePatrolPlanRecordItemDetailsList() {
        return devicePatrolPlanRecordItemDetailsList;
    }

    public void setDevicePatrolPlanRecordItemDetailsList(List<DevicePatrolPlanRecordItemDetails> devicePatrolPlanRecordItemDetailsList) {
        this.devicePatrolPlanRecordItemDetailsList = devicePatrolPlanRecordItemDetailsList;
    }

    public List<DevicePatrolPlanRecordLocationDetails> getDevicePatrolPlanRecordLocationDetailsList() {
        return devicePatrolPlanRecordLocationDetailsList;
    }

    public void setDevicePatrolPlanRecordLocationDetailsList(List<DevicePatrolPlanRecordLocationDetails> devicePatrolPlanRecordLocationDetailsList) {
        this.devicePatrolPlanRecordLocationDetailsList = devicePatrolPlanRecordLocationDetailsList;
    }

    public String getDetpName() {
        return detpName;
    }

    public void setDetpName(String detpName) {
        this.detpName = detpName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getTabPeopleName() {
        return tabPeopleName;
    }

    public void setTabPeopleName(String tabPeopleName) {
        this.tabPeopleName = tabPeopleName;
    }

    @Override
    public String toString() {
        return "DevicePatrolPlanDTO{" +
                "devicePatrolPlanRecordHead=" + devicePatrolPlanRecordHead +
                ", devicePatrolPlanRecordItemDetailsList=" + devicePatrolPlanRecordItemDetailsList +
                ", devicePatrolPlanRecordLocationDetailsList=" + devicePatrolPlanRecordLocationDetailsList +
                ", detpName='" + detpName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", tabPeopleName='" + tabPeopleName + '\'' +
                '}';
    }
}
