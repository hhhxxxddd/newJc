package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorProcessType;
import com.jinchi.common.domain.BasicInfoPrecursorProcessTypeExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoPrecursorProcessTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-26 17:28
 * @description:
 **/
@Service
public class PrecursorProcessTypeServiceImp implements PrecursorProcessTypeService{

    @Autowired
    BasicInfoPrecursorProcessTypeMapper basicInfoPrecursorProcessTypeMapper;

    @Override
    public BasicInfoPrecursorProcessType add(BasicInfoPrecursorProcessType basicInfoPrecursorProcessType) {
        basicInfoPrecursorProcessTypeMapper.insertSelective(basicInfoPrecursorProcessType);
        return basicInfoPrecursorProcessType;
    }

    @Override
    public void delete(Integer id) {
        basicInfoPrecursorProcessTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public BasicInfoPrecursorProcessType update(BasicInfoPrecursorProcessType basicInfoPrecursorProcessType) {
        basicInfoPrecursorProcessTypeMapper.updateByPrimaryKeySelective(basicInfoPrecursorProcessType);
        return basicInfoPrecursorProcessType;
    }

    @Override
    public Page page(Integer page, Integer size) {
        Page pageInfo = new Page(getAll(),page,size);
        return pageInfo;
    }

    @Override
    public List getAll() {
        BasicInfoPrecursorProcessTypeExample example = new BasicInfoPrecursorProcessTypeExample();
        example.createCriteria();
        return basicInfoPrecursorProcessTypeMapper.selectByExample(example);
    }

    @Override
    public List getDataByTypes(Byte types) {
        BasicInfoPrecursorProcessTypeExample example = new BasicInfoPrecursorProcessTypeExample();
        example.createCriteria().andTypesEqualTo(types);
        return basicInfoPrecursorProcessTypeMapper.selectByExample(example);
    }

    @Override
    public String getProcessNameById(Integer id) {
        return basicInfoPrecursorProcessTypeMapper.selectByPrimaryKey(id).getProcessName();
    }

    @Override
    public List getByType(Integer flag) {
        BasicInfoPrecursorProcessTypeExample example = new BasicInfoPrecursorProcessTypeExample();
        example.createCriteria().andTypesEqualTo(flag.byteValue());
        return basicInfoPrecursorProcessTypeMapper.selectByExample(example);
    }
}
