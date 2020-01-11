package com.jinchi.common.dto;

import com.jinchi.common.domain.MaterialDeliveryStatisticByLineTotals;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-12 14:47
 * @description: 原料领用统计 按产线统计DTO
 **/

public class ListByProductionLineDTO {

    List<ProductionLineStatDTO> lineStatDTOS;

    MaterialDeliveryStatisticByLineTotals byLineTotals;

    public List<ProductionLineStatDTO> getLineStatDTOS() {
        return lineStatDTOS;
    }

    public void setLineStatDTOS(List<ProductionLineStatDTO> lineStatDTOS) {
        this.lineStatDTOS = lineStatDTOS;
    }

    public MaterialDeliveryStatisticByLineTotals getByLineTotals() {
        return byLineTotals;
    }

    public void setByLineTotals(MaterialDeliveryStatisticByLineTotals byLineTotals) {
        this.byLineTotals = byLineTotals;
    }
}
