package com.jinchi.app.dto;

import com.jinchi.app.domain.DeviceMaintenanceAccessory;
import com.jinchi.app.domain.DeviceMaintenanceRecordDetails;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-08-28 10:46
 * @description: 保养单暂存和提交接口数据传输模型
 **/

public class DeviceMaintenanceRecordDetailUpdateDto {
    private DeviceMaintenanceRecordHeadUpdateDto head;
    private List<DeviceMaintenanceRecordDetails> details;

    private  List<DeviceMaintenanceAccessoryDto> accessory;

    public DeviceMaintenanceRecordDetailUpdateDto() {
    }

    public DeviceMaintenanceRecordHeadUpdateDto getHead() {
        return head;
    }

    public void setHead(DeviceMaintenanceRecordHeadUpdateDto head) {
        this.head = head;
    }

    public List<DeviceMaintenanceRecordDetails> getDetails() {
        return details;
    }

    public void setDetails(List<DeviceMaintenanceRecordDetails> details) {
        this.details = details;
    }

    public List<DeviceMaintenanceAccessoryDto> getAccessory() {
        return accessory;
    }

    public void setAccessory(List<DeviceMaintenanceAccessoryDto> accessory) {
        this.accessory = accessory;
    }
}

