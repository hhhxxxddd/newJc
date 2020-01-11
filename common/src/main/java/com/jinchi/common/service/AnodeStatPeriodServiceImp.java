package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodePeriod;
import com.jinchi.common.domain.BasicInfoAnodePeriodExample;
import com.jinchi.common.mapper.BasicInfoAnodePeriodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 15:31
 * @description:
 **/
@Service
public class AnodeStatPeriodServiceImp implements AnodeStatPeriodService {

    @Autowired
    BasicInfoAnodePeriodMapper periodMapper;

    @Override
    public BasicInfoAnodePeriod add(BasicInfoAnodePeriod anodePeriod) {
        periodMapper.insertSelective(anodePeriod);
        return anodePeriod;
    }

    @Override
    public void delete(Integer id) {
        periodMapper.deleteByPrimaryKey(id);
    }

    @Override
    public BasicInfoAnodePeriod update(BasicInfoAnodePeriod anodePeriod) {
        periodMapper.updateByPrimaryKeySelective(anodePeriod);
        return anodePeriod;
    }

    @Override
    public List getAll() {
        BasicInfoAnodePeriodExample example = new BasicInfoAnodePeriodExample();
        example.createCriteria();
        return periodMapper.selectByExample(example);
    }
}
