package com.jinchi.common.dto;

import com.jinchi.common.domain.DeviceMaintenanceAccessory;
import com.jinchi.common.domain.DeviceMaintenanceRecordDetails;
import com.jinchi.common.domain.DeviceMaintenanceRecordHead;

public class DeviceMaintenanceRecordHeadDTO {

    DeviceMaintenanceRecordHead deviceMaintenanceRecordHead;
    DeviceMaintenanceRecordDetails deviceMaintenanceRecordDetails;
    DeviceMaintenanceAccessory deviceMaintenanceAccessory;

    public DeviceMaintenanceAccessory getDeviceMaintenanceAccessory() {
        return deviceMaintenanceAccessory;
    }

    public void setDeviceMaintenanceAccessory(DeviceMaintenanceAccessory deviceMaintenanceAccessory) {
        this.deviceMaintenanceAccessory = deviceMaintenanceAccessory;
    }

    public DeviceMaintenanceRecordHead getDeviceMaintenanceRecordHead() {
        return deviceMaintenanceRecordHead;
    }

    public void setDeviceMaintenanceRecordHead(DeviceMaintenanceRecordHead deviceMaintenanceRecordHead) {
        this.deviceMaintenanceRecordHead = deviceMaintenanceRecordHead;
    }

    public DeviceMaintenanceRecordDetails getDeviceMaintenancePlansDetails() {
        return deviceMaintenanceRecordDetails;
    }

    public void setDeviceMaintenancePlansDetails(DeviceMaintenanceRecordDetails deviceMaintenanceRecordDetails) {
        this.deviceMaintenanceRecordDetails = deviceMaintenanceRecordDetails;
    }

    @Override
    public String toString() {
        return "DeviceMaintenanceRecordHeadDTO{" +
                "deviceMaintenanceRecordHead=" + deviceMaintenanceRecordHead +
                ", deviceMaintenanceRecordDetails=" + deviceMaintenanceRecordDetails +
                ", deviceMaintenanceAccessory=" + deviceMaintenanceAccessory +
                '}';
    }
}
