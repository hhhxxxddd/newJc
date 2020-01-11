package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodeProductionLine;
import com.jinchi.common.domain.BasicInfoAnodeProductionLineExample;
import com.jinchi.common.mapper.BasicInfoAnodeProductionLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 15:59
 * @description:
 **/
@Service
public class AnodeProductionLineServiceImp implements AnodeProductionLineService {

    @Autowired
    BasicInfoAnodeProductionLineMapper lineMapper;

    @Override
    public BasicInfoAnodeProductionLine add(BasicInfoAnodeProductionLine line) {
        lineMapper.insertSelective(line);
        return line;
    }

    @Override
    public void delete(Integer id) {
        lineMapper.deleteByPrimaryKey(id);
    }

    @Override
    public BasicInfoAnodeProductionLine update(BasicInfoAnodeProductionLine line) {
        lineMapper.updateByPrimaryKeySelective(line);
        return line;
    }

    @Override
    public List getAll() {
        BasicInfoAnodeProductionLineExample example = new BasicInfoAnodeProductionLineExample();
        example.createCriteria();
        return lineMapper.selectByExample(example);
    }

    @Override
    public List getByFlag(Boolean flag) {
        BasicInfoAnodeProductionLineExample example = new BasicInfoAnodeProductionLineExample();
        example.createCriteria().andFlagEqualTo(flag);
        return lineMapper.selectByExample(example);
    }
}
