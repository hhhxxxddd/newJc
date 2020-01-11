package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodeProcessName;
import com.jinchi.common.domain.BasicInfoAnodeProcessNameExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoAnodeProcessNameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 16:47
 * @description:
 **/
@Service
public class AnodeProcessNameServiceImp implements AnodeProcessNameService {

    @Autowired
    BasicInfoAnodeProcessNameMapper nameMapper;

    @Override
    public BasicInfoAnodeProcessName add(BasicInfoAnodeProcessName processName) {
        nameMapper.insertSelective(processName);
        return processName;
    }

    @Override
    public BasicInfoAnodeProcessName update(BasicInfoAnodeProcessName processName) {
        nameMapper.updateByPrimaryKeySelective(processName);
        return processName;
    }

    @Override
    public void delete(Integer id) {
        nameMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List getAll() {
        BasicInfoAnodeProcessNameExample example = new BasicInfoAnodeProcessNameExample();
        example.createCriteria();
        return nameMapper.selectByExample(example);
    }

    @Override
    public Page page(Integer page, Integer size) {
        return new Page(getAll(), page, size);
    }
}
