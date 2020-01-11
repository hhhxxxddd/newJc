package com.jinchi.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinchi.common.dto.RawMaterialSulfateBase;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MaterialDeliveryStatisticNickelSulfate extends RawMaterialSulfateBase {
    public MaterialDeliveryStatisticNickelSulfate(Long code, Long statisticCode, Integer materialTypeCode, Integer materialCode, String materialName, String encoder, Date deliveryTime, Float weights, Float niConcentration, Float coConcentration, Float mnConcentration, Float niMetallicity, Float coMetallicity, Float mnMetallicity) {
        super(code, statisticCode, materialTypeCode, materialCode, materialName, encoder, deliveryTime, weights, niConcentration, coConcentration, mnConcentration, niMetallicity, coMetallicity, mnMetallicity);
    }

    public MaterialDeliveryStatisticNickelSulfate() {
    }
}