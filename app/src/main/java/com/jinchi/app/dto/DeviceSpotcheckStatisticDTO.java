package com.jinchi.app.dto;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-12 14:34
 * @description:
 **/

public class DeviceSpotcheckStatisticDTO {
    private List<DeviceSpotcheckStatisticDetailDTO> detailDTOList;

    public List<DeviceSpotcheckStatisticDetailDTO> getDetailDTOList() {
        return detailDTOList;
    }

    public void setDetailDTOList(List<DeviceSpotcheckStatisticDetailDTO> detailDTOList) {
        this.detailDTOList = detailDTOList;
    }
}
