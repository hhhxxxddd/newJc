package com.jinchi.app.dto;

import com.jinchi.app.domain.DeviceMaintenanceAccessory;
import com.jinchi.app.domain.DeviceMaintenanceRecordDetails;
import com.jinchi.app.domain.DeviceMaintenanceRecordHead;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-08-26 16:09
 * @description:
 **/

public class DeviceMaintenanceRecordDetailsDto {
    private DeviceMaintenanceRecordHead deviceMaintenanceRecordHead;
    private List<DeviceMaintenanceRecordDetails> deviceMaintenanceRecordDetails;

    private  List<DeviceMaintenanceAccessory> deviceMaintenanceAccessory;

    public DeviceMaintenanceRecordDetailsDto() {
    }

    public DeviceMaintenanceRecordHead getDeviceMaintenanceRecordHead() {
        return deviceMaintenanceRecordHead;
    }

    public void setDeviceMaintenanceRecordHead(DeviceMaintenanceRecordHead deviceMaintenanceRecordHead) {
        this.deviceMaintenanceRecordHead = deviceMaintenanceRecordHead;
    }

    public List<DeviceMaintenanceRecordDetails> getDeviceMaintenanceRecordDetails() {
        return deviceMaintenanceRecordDetails;
    }

    public void setDeviceMaintenanceRecordDetails(List<DeviceMaintenanceRecordDetails> deviceMaintenanceRecordDetails) {
        this.deviceMaintenanceRecordDetails = deviceMaintenanceRecordDetails;
    }

    public List<DeviceMaintenanceAccessory> getDeviceMaintenanceAccessory() {
        return deviceMaintenanceAccessory;
    }

    public void setDeviceMaintenanceAccessory(List<DeviceMaintenanceAccessory> deviceMaintenanceAccessory) {
        this.deviceMaintenanceAccessory = deviceMaintenanceAccessory;
    }
}
