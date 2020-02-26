package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoProcessInitValues;
import com.jinchi.common.domain.BasicInfoProcessInitValuesExample;
import com.jinchi.common.mapper.BasicInfoProcessInitValuesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @time: 2020/2/26 17:59
 * @description:
 */
@Service
public class BasicInfoProcessInitValuesServiceImp implements BasicInfoProcessInitValuesService {

    @Autowired
    BasicInfoProcessInitValuesMapper basicInfoProcessInitValuesMapper;

    @Override
    public List getAll() {
        return basicInfoProcessInitValuesMapper.selectByExample(new BasicInfoProcessInitValuesExample());
    }

    @Override
    public BasicInfoProcessInitValues update(BasicInfoProcessInitValues basicInfoProcessInitValues) {
        basicInfoProcessInitValuesMapper.updateByPrimaryKeySelective(basicInfoProcessInitValues);
        return basicInfoProcessInitValues;
    }
}
