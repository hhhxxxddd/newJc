package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoProcessBaseValues;
import com.jinchi.common.domain.BasicInfoProcessBaseValuesExample;
import com.jinchi.common.mapper.BasicInfoProcessBaseValuesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @time: 2020/3/1 11:25
 * @description:
 */
@Service
public class BasicInfoProcessBaseValuesServiceImp implements BasicInfoProcessBaseValuesService {

    @Autowired
    BasicInfoProcessBaseValuesMapper valuesMapper;

    @Override
    public BasicInfoProcessBaseValues update(BasicInfoProcessBaseValues values) {
        valuesMapper.updateByPrimaryKeySelective(values);
        return values;
    }

    @Override
    public List getAll() {
        return valuesMapper.selectByExample(new BasicInfoProcessBaseValuesExample());
    }
}
