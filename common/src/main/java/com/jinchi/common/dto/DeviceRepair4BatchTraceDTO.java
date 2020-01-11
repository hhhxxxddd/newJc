package com.jinchi.common.dto;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-09 16:34
 * @description:
 **/

public class DeviceRepair4BatchTraceDTO {

    String deviceName;

    String fixedassetsCode;

    String deptName;

    List<DeviceRepairDetailDTO> dtoList;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFixedassetsCode() {
        return fixedassetsCode;
    }

    public void setFixedassetsCode(String fixedassetsCode) {
        this.fixedassetsCode = fixedassetsCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<DeviceRepairDetailDTO> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<DeviceRepairDetailDTO> dtoList) {
        this.dtoList = dtoList;
    }
}
