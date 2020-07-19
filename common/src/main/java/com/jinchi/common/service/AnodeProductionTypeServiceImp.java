package com.jinchi.common.service;


import com.jinchi.common.domain.BasicInfoAnodeProductionType;
import com.jinchi.common.domain.BasicInfoAnodeProductionTypeExample;
import com.jinchi.common.dto.Page;
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
        BasicInfoAnodeProductionTypeExample example = new BasicInfoAnodeProductionTypeExample();
        example.createCriteria().andNameEqualTo(productionType.getName());
        if (typeMapper.selectByExample(example).size() > 0) {
            return new BasicInfoAnodeProductionType();
        }
        typeMapper.insertSelective(productionType);
        return productionType;
    }

    @Override
    public BasicInfoAnodeProductionType update(BasicInfoAnodeProductionType productionType) {
        typeMapper.updateByPrimaryKeySelective(productionType);
        return productionType;
    }

    @Override
    public List getAll(String condition) {
        BasicInfoAnodeProductionTypeExample example = new BasicInfoAnodeProductionTypeExample();
        example.createCriteria().andNameLike(condition + "%");
        return typeMapper.selectByExample(example);
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        return new Page(getAll(condition), page, size);
    }

    @Override
    public void delete(Integer id) {
        typeMapper.deleteByPrimaryKey(id);
    }
}
