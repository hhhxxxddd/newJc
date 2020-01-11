package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodeProductionType;
import com.jinchi.common.domain.BasicInfoAnodeProductionTypeExample;
import com.jinchi.common.mapper.BasicInfoAnodeProductionTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-04 10:53
 * @description:
 **/
@Service
public class AnodeProductionTypeServiceImp implements AnodeProductionTypeService {

    @Autowired
    BasicInfoAnodeProductionTypeMapper typeMapper;

    @Override
    public BasicInfoAnodeProductionType add(BasicInfoAnodeProductionType productionType) {
        typeMapper.insertSelective(productionType);
        return productionType;
    }

    @Override
    public BasicInfoAnodeProductionType update(BasicInfoAnodeProductionType productionType) {
        typeMapper.updateByPrimaryKeySelective(productionType);
        return productionType;
    }

    @Override
    public List getAll() {
        BasicInfoAnodeProductionTypeExample example = new BasicInfoAnodeProductionTypeExample();
        example.createCriteria();
        return typeMapper.selectByExample(example);
    }

    @Override
    public void delete(Integer id) {
        typeMapper.deleteByPrimaryKey(id);
    }
}
