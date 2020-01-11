package com.jinchi.common.dto;

import com.jinchi.common.domain.DeviceMaintenanceRecordHead;

public class DeviceMaintenanceRecordDTO extends DeviceMaintenanceRecordHead {

    String deptName;

    String maintName;

    public DeviceMaintenanceRecordDTO(DeviceMaintenanceRecordHead deviceMaintenanceRecordHead){
        this.setCode(deviceMaintenanceRecordHead.getCode());
        this.setPlanCode(deviceMaintenanceRecordHead.getPlanCode());
        this.setDeviceCode(deviceMaintenanceRecordHead.getDeviceCode());
        this.setFixedassetsCode(deviceMaintenanceRecordHead.getFixedassetsCode());
        this.setDeviceName(deviceMaintenanceRecordHead.getDeviceName());
        this.setDeptCode(deviceMaintenanceRecordHead.getDeptCode());
        this.setPlanDate(deviceMaintenanceRecordHead.getPlanDate());
        this.setReceiveDate(deviceMaintenanceRecordHead.getReceiveDate());
        this.setFinishiDate(deviceMaintenanceRecordHead.getFinishiDate());
        this.setMaintPeople(deviceMaintenanceRecordHead.getMaintPeople());
        this.setAbnormalcontent(deviceMaintenanceRecordHead.getAbnormalcontent());
        this.setEditFlag(deviceMaintenanceRecordHead.getEditFlag());
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getMaintName() {
        return maintName;
    }

    public void setMaintName(String maintName) {
        this.maintName = maintName;
    }
}
