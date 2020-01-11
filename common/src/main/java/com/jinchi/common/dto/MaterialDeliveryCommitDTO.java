package com.jinchi.common.dto;

import com.jinchi.common.domain.MaterialDeliveryStatisticHead;

import java.time.Period;
import java.util.Date;
import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-08 11:25
 * @description: 原料领用提交/保存DTO
 **/

public class MaterialDeliveryCommitDTO {
    private MaterialDeliveryStatisticHead head;
    private String periodName;
    private Long statisticCode;
    private Byte flag;//0保存 1提交

    private Integer periods;

    //硫酸盐溶液浓度
    private Float niConcentration;
    private Float coConcentration;
    private Float mnConcentration;

    //出库数据
    List<StockOutDTO> stockOutDTOS;

    //补料 晶体
    List<CrystalsDTO> crystalsDTOS;

    //补料 混合盐溶液
    List<SaltMixtureLiquorDTO> saltMixtureLiquorDTOS;

    //补料 单晶体溶液
    List<SingleCrystalLiquorDTO> singleCrystalLiquorDTOS;

    public Long getStatisticCode() {
        return statisticCode;
    }

    public void setStatisticCode(Long statisticCode) {
        this.statisticCode = statisticCode;
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }


    public Float getNiConcentration() {
        return niConcentration;
    }

    public void setNiConcentration(Float niConcentration) {
        this.niConcentration = niConcentration;
    }

    public Float getCoConcentration() {
        return coConcentration;
    }

    public void setCoConcentration(Float coConcentration) {
        this.coConcentration = coConcentration;
    }

    public Float getMnConcentration() {
        return mnConcentration;
    }

    public void setMnConcentration(Float mnConcentration) {
        this.mnConcentration = mnConcentration;
    }

    public List<StockOutDTO> getStockOutDTOS() {
        return stockOutDTOS;
    }

    public void setStockOutDTOS(List<StockOutDTO> stockOutDTOS) {
        this.stockOutDTOS = stockOutDTOS;
    }

    public List<CrystalsDTO> getCrystalsDTOS() {
        return crystalsDTOS;
    }

    public void setCrystalsDTOS(List<CrystalsDTO> crystalsDTOS) {
        this.crystalsDTOS = crystalsDTOS;
    }

    public List<SaltMixtureLiquorDTO> getSaltMixtureLiquorDTOS() {
        return saltMixtureLiquorDTOS;
    }

    public void setSaltMixtureLiquorDTOS(List<SaltMixtureLiquorDTO> saltMixtureLiquorDTOS) {
        this.saltMixtureLiquorDTOS = saltMixtureLiquorDTOS;
    }

    public List<SingleCrystalLiquorDTO> getSingleCrystalLiquorDTOS() {
        return singleCrystalLiquorDTOS;
    }

    public void setSingleCrystalLiquorDTOS(List<SingleCrystalLiquorDTO> singleCrystalLiquorDTOS) {
        this.singleCrystalLiquorDTOS = singleCrystalLiquorDTOS;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public MaterialDeliveryStatisticHead getHead() {
        return head;
    }

    public void setHead(MaterialDeliveryStatisticHead head) {
        this.head = head;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }
}
