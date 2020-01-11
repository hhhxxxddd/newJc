package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorProductionLine;
import com.jinchi.common.domain.BasicInfoPrecursorProductionLineExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoPrecursorProductionLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-26 15:31
 * @description:
 **/
@Service
public class PrecursorProductionLineServiceImp implements PrecursorProductionLineService{

    @Autowired
    BasicInfoPrecursorProductionLineMapper basicInfoPrecursorProductionLineMapper;

    @Override
    public BasicInfoPrecursorProductionLine getDataById(Integer id) {
        return basicInfoPrecursorProductionLineMapper.selectByPrimaryKey(id);
    }

    @Override
    public BasicInfoPrecursorProductionLine add(BasicInfoPrecursorProductionLine basicInfoPrecursorProductionLine) {
        basicInfoPrecursorProductionLineMapper.insertSelective(basicInfoPrecursorProductionLine);
        return basicInfoPrecursorProductionLine;
    }

    @Override
    public void delete(Integer lineId) {
        basicInfoPrecursorProductionLineMapper.deleteByPrimaryKey(lineId);
    }

    @Override
    public Page page(Integer page, Integer size) {
        Page pageInfo = new Page(getAll(),page,size);
        return pageInfo;
    }

    @Override
    public List getAll() {
        BasicInfoPrecursorProductionLineExample example = new BasicInfoPrecursorProductionLineExample();
        example.createCriteria();
        return basicInfoPrecursorProductionLineMapper.selectByExample(example);
    }

    @Override
    public BasicInfoPrecursorProductionLine update(BasicInfoPrecursorProductionLine basicInfoPrecursorProductionLine) {
        basicInfoPrecursorProductionLineMapper.updateByPrimaryKeySelective(basicInfoPrecursorProductionLine);
        return basicInfoPrecursorProductionLine;
    }

    @Override
    public String getNameById(Integer id) {
        return basicInfoPrecursorProductionLineMapper.selectByPrimaryKey(id).getName();
    }
}
