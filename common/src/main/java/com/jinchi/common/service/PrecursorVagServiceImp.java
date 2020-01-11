package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoVgaPoint;
import com.jinchi.common.domain.BasicInfoVgaPointExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoVgaPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrecursorVagServiceImp implements PrecursorVagService {
    @Autowired
    BasicInfoVgaPointMapper pointMapper;

    @Override
    public BasicInfoVgaPoint add(BasicInfoVgaPoint basicInfoVgaPoint) {
        pointMapper.insertSelective(basicInfoVgaPoint);
        return basicInfoVgaPoint;
    }

    @Override
    public void delete(Integer id) {
        pointMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for(Integer i:ids){
            delete(i);
        }
    }

    @Override
    public List getAll(String condition) {
        BasicInfoVgaPointExample example = new BasicInfoVgaPointExample();
        example.createCriteria().andVgaNameLike(condition+"%");
        return pointMapper.selectByExample(example);
    }

    @Override
    public Page getAllByPage(String condition, Integer page, Integer size) {
        return new Page(getAll(condition),page,size);
    }

    @Override
    public BasicInfoVgaPoint update(BasicInfoVgaPoint basicInfoVgaPoint) {
        pointMapper.updateByPrimaryKeySelective(basicInfoVgaPoint);
        return basicInfoVgaPoint;
    }

    @Override
    public BasicInfoVgaPoint getById(Integer id) {
        return pointMapper.selectByPrimaryKey(id);
    }
}
