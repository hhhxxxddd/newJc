package com.jinchi.common.dto;

import com.jinchi.common.domain.*;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-07 13:50
 * @description:
 **/

public class UncommittedDetailDTO {
    MaterialDeliveryStatisticHead statisticHead;
    List<MaterialDeliveryStatisticSaltMixtureLiquor> saltMixtureLiquors;
    List<MaterialDeliveryStatisticCrystals> crystals;
    List<MaterialDeliveryStatisticSingleCrystalLiquor> singleCrystalLiquors;
    List<RawMaterialSulfateBase> sulfates;
    MaterialDeliveryStatisticSulfateConcentration sulfateConcentration;

    public MaterialDeliveryStatisticHead getStatisticHead() {
        return statisticHead;
    }

    public void setStatisticHead(MaterialDeliveryStatisticHead statisticHead) {
        this.statisticHead = statisticHead;
    }

    public List<MaterialDeliveryStatisticSaltMixtureLiquor> getSaltMixtureLiquors() {
        return saltMixtureLiquors;
    }

    public void setSaltMixtureLiquors(List<MaterialDeliveryStatisticSaltMixtureLiquor> saltMixtureLiquors) {
        this.saltMixtureLiquors = saltMixtureLiquors;
    }

    public List<MaterialDeliveryStatisticCrystals> getCrystals() {
        return crystals;
    }

    public void setCrystals(List<MaterialDeliveryStatisticCrystals> crystals) {
        this.crystals = crystals;
    }

    public List<MaterialDeliveryStatisticSingleCrystalLiquor> getSingleCrystalLiquors() {
        return singleCrystalLiquors;
    }

    public void setSingleCrystalLiquors(List<MaterialDeliveryStatisticSingleCrystalLiquor> singleCrystalLiquors) {
        this.singleCrystalLiquors = singleCrystalLiquors;
    }

    public MaterialDeliveryStatisticSulfateConcentration getSulfateConcentration() {
        return sulfateConcentration;
    }

    public void setSulfateConcentration(MaterialDeliveryStatisticSulfateConcentration sulfateConcentration) {
        this.sulfateConcentration = sulfateConcentration;
    }

    public List<RawMaterialSulfateBase> getSulfates() {
        return sulfates;
    }

    public void setSulfates(List<RawMaterialSulfateBase> sulfates) {
        this.sulfates = sulfates;
    }
}
