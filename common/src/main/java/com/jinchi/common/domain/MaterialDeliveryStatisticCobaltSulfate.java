package com.jinchi.common.domain;

import com.jinchi.common.dto.RawMaterialSulfateBase;

import java.util.Date;

public class MaterialDeliveryStatisticCobaltSulfate extends RawMaterialSulfateBase {
    public MaterialDeliveryStatisticCobaltSulfate(Long code, Long statisticCode, Integer materialTypeCode, Integer materialCode, String materialName, String encoder, Date deliveryTime, Float weights, Float niConcentration, Float coConcentration, Float mnConcentration, Float niMetallicity, Float coMetallicity, Float mnMetallicity) {
        super(code, statisticCode, materialTypeCode, materialCode, materialName, encoder, deliveryTime, weights, niConcentration, coConcentration, mnConcentration, niMetallicity, coMetallicity, mnMetallicity);
    }

    public MaterialDeliveryStatisticCobaltSulfate() {
    }
}