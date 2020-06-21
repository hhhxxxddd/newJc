package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodeBurningLossRate;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoAnodeBurningLossRateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @time: 2020/6/21 11:17
 * @description:
 */
@Service
public class AnodeBurningLossRateServiceImp implements AnodeBurningLossRateService {

    @Autowired
    BasicInfoAnodeBurningLossRateMapper burningLossRateMapper;


    @Override
    public BasicInfoAnodeBurningLossRate add(BasicInfoAnodeBurningLossRate value) {
        burningLossRateMapper.insertSelective(value);
        return value;
    }

    @Override
    public void delete(Integer id) {
        burningLossRateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDelete(Integer[] ids) {
        for (Integer id : ids) {
            delete(id);
        }
    }

    @Override
    public BasicInfoAnodeBurningLossRate update(BasicInfoAnodeBurningLossRate value) {
        burningLossRateMapper.updateByPrimaryKeySelective(value);
        return value;
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        List list = burningLossRateMapper.selectByCondition(condition);
        return new Page(list, page, size);
    }
}
