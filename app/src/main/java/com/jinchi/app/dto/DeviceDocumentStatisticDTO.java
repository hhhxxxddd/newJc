package com.jinchi.app.dto;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-12 10:29
 * @description: 设备档案统计 返回参数DTO
 **/

public class DeviceDocumentStatisticDTO {
    private DeviceDocumentStatisticHeadDTO head;
    private List<DeviceDocumentStatisticDetailDTO> detailDTOS;

    public DeviceDocumentStatisticHeadDTO getHead() {
        return head;
    }

    public void setHead(DeviceDocumentStatisticHeadDTO head) {
        this.head = head;
    }

    public List<DeviceDocumentStatisticDetailDTO> getDetailDTOS() {
        return detailDTOS;
    }

    public void setDetailDTOS(List<DeviceDocumentStatisticDetailDTO> detailDTOS) {
        this.detailDTOS = detailDTOS;
    }
}
