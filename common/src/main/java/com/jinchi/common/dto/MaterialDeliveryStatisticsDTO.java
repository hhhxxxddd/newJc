package com.jinchi.common.dto;

import com.jinchi.common.domain.MaterialDeliveryStatisticDataList;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-07 13:38
 * @description:
 **/

public class MaterialDeliveryStatisticsDTO {
    MaterialDeliveryStatisticHeadDTO headDTO;
    MaterialDeliveryStatisticDataList data;

    public MaterialDeliveryStatisticHeadDTO getHeadDTO() {
        return headDTO;
    }

    public void setHeadDTO(MaterialDeliveryStatisticHeadDTO headDTO) {
        this.headDTO = headDTO;
    }

    public MaterialDeliveryStatisticDataList getData() {
        return data;
    }

    public void setData(MaterialDeliveryStatisticDataList data) {
        this.data = data;
    }
}
