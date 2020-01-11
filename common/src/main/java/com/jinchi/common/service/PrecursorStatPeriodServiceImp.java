package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorPeriod;
import com.jinchi.common.domain.BasicInfoPrecursorPeriodExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoPrecursorPeriodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-26 13:01
 * @description:
 **/
@Service
public class PrecursorStatPeriodServiceImp implements PrecursorStatPeriodService{
    @Autowired
    BasicInfoPrecursorPeriodMapper basicInfoPrecursorPeriodMapper;

    @Override
    public BasicInfoPrecursorPeriod getDataById(Integer id) {
        return basicInfoPrecursorPeriodMapper.selectByPrimaryKey(id);
    }

    @Override
    public BasicInfoPrecursorPeriod add(BasicInfoPrecursorPeriod basicInfoPrecursorPeriod) {
        basicInfoPrecursorPeriodMapper.insertSelective(basicInfoPrecursorPeriod);
        return basicInfoPrecursorPeriod;
    }

    @Override
    public void delete(Integer id) {
        basicInfoPrecursorPeriodMapper.deleteByPrimaryKey(id);
    }

    @Override
    public BasicInfoPrecursorPeriod update(BasicInfoPrecursorPeriod basicInfoPrecursorPeriod) {
        basicInfoPrecursorPeriodMapper.updateByPrimaryKeySelective(basicInfoPrecursorPeriod);
        return basicInfoPrecursorPeriod;
    }

    @Override
    public Page page(Integer page, Integer size) {
        Page pageInfo = new Page(getAll(),page,size);
        return pageInfo;
    }

    @Override
    public List getAll() {
        BasicInfoPrecursorPeriodExample example = new BasicInfoPrecursorPeriodExample();
        example.createCriteria();
        return basicInfoPrecursorPeriodMapper.selectByExample(example);
    }
}
