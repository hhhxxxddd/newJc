package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoExceptionModels;
import com.jinchi.common.domain.BasicInfoExceptionModelsExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoExceptionModelsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechExceptionServiceImp implements TechExceptionService {
    @Autowired
    BasicInfoExceptionModelsMapper modelsMapper;

    @Override
    public BasicInfoExceptionModels add(BasicInfoExceptionModels basicInfoExceptionModels) {
        modelsMapper.insertSelective(basicInfoExceptionModels);
        return basicInfoExceptionModels;
    }

    @Override
    public void delete(Integer id) {
        modelsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for(Integer id:ids){
            delete(id);
        }
    }

    @Override
    public List getAll() {
        BasicInfoExceptionModelsExample example = new BasicInfoExceptionModelsExample();
        example.createCriteria();
        return modelsMapper.selectByExample(example);
    }

    @Override
    public Page getAllPage(Integer page, Integer size) {
        return new Page(getAll(),page,size);
    }

    @Override
    public BasicInfoExceptionModels detail(Integer id) {
        return modelsMapper.selectByPrimaryKey(id);
    }

    @Override
    public BasicInfoExceptionModels update(BasicInfoExceptionModels basicInfoExceptionModels) {
        modelsMapper.updateByPrimaryKeySelective(basicInfoExceptionModels);
        return basicInfoExceptionModels;
    }
}
