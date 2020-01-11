package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodeCalculateCoefficient;
import com.jinchi.common.domain.BasicInfoAnodeCalculateCoefficientExample;
import com.jinchi.common.mapper.BasicInfoAnodeCalculateCoefficientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 23:25
 * @description:
 **/
@Service
public class AnodeCalculateCoefficientServiceImp implements AnodeCalculateCoefficientService {

    @Autowired
    BasicInfoAnodeCalculateCoefficientMapper coefficientMapper;

    @Override
    public BasicInfoAnodeCalculateCoefficient add(BasicInfoAnodeCalculateCoefficient record) {
        coefficientMapper.insertSelective(record);
        return record;
    }

    @Override
    public BasicInfoAnodeCalculateCoefficient update(BasicInfoAnodeCalculateCoefficient record) {
        coefficientMapper.updateByPrimaryKeySelective(record);
        return record;
    }

    @Override
    public BasicInfoAnodeCalculateCoefficient getOne() {
        BasicInfoAnodeCalculateCoefficientExample example = new BasicInfoAnodeCalculateCoefficientExample();
        example.createCriteria();
        List<BasicInfoAnodeCalculateCoefficient> coefficients = coefficientMapper.selectByExample(example);
        if (coefficients.size() > 0) {
            return coefficients.get(0);
        } else {
            return null;
        }
    }
}
