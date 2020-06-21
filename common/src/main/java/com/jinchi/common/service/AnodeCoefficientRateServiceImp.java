package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodeCoefficientRate;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoAnodeCoefficientRateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @time: 2020/6/20 19:08
 * @description:
 */
@Service
public class AnodeCoefficientRateServiceImp implements AnodeCoefficientRateService {

    @Autowired
    BasicInfoAnodeCoefficientRateMapper rateMapper;

    @Override
    public BasicInfoAnodeCoefficientRate add(BasicInfoAnodeCoefficientRate anodeCoefficientRate) {
        rateMapper.insertSelective(anodeCoefficientRate);
        return anodeCoefficientRate;
    }

    @Override
    public BasicInfoAnodeCoefficientRate update(BasicInfoAnodeCoefficientRate anodeCoefficientRate) {
        rateMapper.updateByPrimaryKeySelective(anodeCoefficientRate);
        return anodeCoefficientRate;
    }

    @Override
    public void delete(Integer id) {
        rateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page page(Integer page, Integer size) {
        List list = rateMapper.mySelect();
        return new Page(list, page, size);
    }
}
