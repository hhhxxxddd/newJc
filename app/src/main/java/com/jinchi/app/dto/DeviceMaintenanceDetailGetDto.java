package com.jinchi.app.dto;

import com.jinchi.app.domain.DeviceMaintenanceAccessory;
import com.jinchi.app.domain.DeviceMaintenanceRecordDetails;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-08-27 16:58
 * @description:
 **/

public class DeviceMaintenanceDetailGetDto {
    private DeviceMaintenanceRecordDetailHeadDto head;
    private List<DeviceMaintenanceRecordDetails> details;

    private  List<DeviceMaintenanceAccessoryDto> accessorys;

    public DeviceMaintenanceDetailGetDto() {
    }

    public DeviceMaintenanceRecordDetailHeadDto getHead() {
        return head;
    }

    public void setHead(DeviceMaintenanceRecordDetailHeadDto head) {
        this.head = head;
    }

    public List<DeviceMaintenanceRecordDetails> getDetails() {
        return details;
    }

    public void setDetails(List<DeviceMaintenanceRecordDetails> details) {
        this.details = details;
    }

    public List<DeviceMaintenanceAccessoryDto> getAccessorys() {
        return accessorys;
    }

    public void setAccessorys(List<DeviceMaintenanceAccessoryDto> accessorys) {
        this.accessorys = accessorys;
    }
}
