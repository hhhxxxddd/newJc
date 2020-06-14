package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.mapper.AuxiliaryMaterialsStatisticHeadMapper;
import com.jinchi.common.mapper.GoodsInProcessStatisticHeadMapper;
import com.jinchi.common.mapper.MaterialDeliveryStatisticHeadMapper;
import com.jinchi.common.mapper.ProductStorageStatisticHeadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: LiuTaoYi
 * @time: 2020/6/12 17:18
 * @description:
 */
@Service
public class PrecursorHeadTableOperationServiceImp implements PrecursorHeadTableOperationService {

    @Autowired
    MaterialDeliveryStatisticHeadMapper matHeadMapper;

    @Autowired
    GoodsInProcessStatisticHeadMapper goodsHeadMapper;

    @Autowired
    ProductStorageStatisticHeadMapper psHeadMapper;

    @Autowired
    AuxiliaryMaterialsStatisticHeadMapper amsHeadMapper;

    @Override
    public Boolean checkDate(Integer periodId, Integer periods) {

        MaterialDeliveryStatisticHeadExample matExample = new MaterialDeliveryStatisticHeadExample();
        matExample.createCriteria().andPeriodCodeEqualTo(periodId).andLineNameEqualTo(periods).andFlagEqualTo((byte) 1);
        int matSize = matHeadMapper.selectByExample(matExample).size();

        GoodsInProcessStatisticHeadExample goodExample = new GoodsInProcessStatisticHeadExample();
        goodExample.createCriteria().andPeriodCodeEqualTo(periodId).andLineNameEqualTo(periods).andFlagEqualTo((byte) 1);
        int goodSize = goodsHeadMapper.selectByExample(goodExample).size();

        ProductStorageStatisticHeadExample psExample = new ProductStorageStatisticHeadExample();
        psExample.createCriteria().andPeriodCodeEqualTo(periodId).andLineNameEqualTo(periods).andFlagEqualTo((byte) 1);
        int psSize = psHeadMapper.selectByExample(psExample).size();

        AuxiliaryMaterialsStatisticHeadExample amsExample = new AuxiliaryMaterialsStatisticHeadExample();
        amsExample.createCriteria().andPeriodCodeEqualTo(periodId).andPeriodsEqualTo(periods).andFlagEqualTo(true);
        int amsSize = amsHeadMapper.selectByExample(amsExample).size();

        // 不存在已统计，返回true
        return matSize == 0 && goodSize == 0 && psSize == 0 && amsSize == 0;
    }

    @Override
    public void updateAllEndTime(Integer periodId, Integer periods, Date endTime) {

        MaterialDeliveryStatisticHeadExample matExample = new MaterialDeliveryStatisticHeadExample();
        matExample.createCriteria().andPeriodCodeEqualTo(periodId).andLineNameEqualTo(periods);
        MaterialDeliveryStatisticHead matHead = new MaterialDeliveryStatisticHead();
        matHead.setEndTime(endTime);
        matHeadMapper.updateByExampleSelective(matHead, matExample);

        GoodsInProcessStatisticHeadExample goodExample = new GoodsInProcessStatisticHeadExample();
        goodExample.createCriteria().andPeriodCodeEqualTo(periodId).andLineNameEqualTo(periods);
        GoodsInProcessStatisticHead goodHead = new GoodsInProcessStatisticHead();
        goodHead.setEndTime(endTime);
        goodsHeadMapper.updateByExampleSelective(goodHead, goodExample);

        ProductStorageStatisticHeadExample psExample = new ProductStorageStatisticHeadExample();
        psExample.createCriteria().andPeriodCodeEqualTo(periodId).andLineNameEqualTo(periods);
        ProductStorageStatisticHead psHead = new ProductStorageStatisticHead();
        psHead.setEndTime(endTime);
        psHeadMapper.updateByExampleSelective(psHead, psExample);

        AuxiliaryMaterialsStatisticHeadExample amsExample = new AuxiliaryMaterialsStatisticHeadExample();
        amsExample.createCriteria().andPeriodCodeEqualTo(periodId).andPeriodsEqualTo(periods);
        AuxiliaryMaterialsStatisticHead amsHead = new AuxiliaryMaterialsStatisticHead();
        amsHead.setEndTime(endTime);
        amsHeadMapper.updateByExampleSelective(amsHead, amsExample);
    }
}
