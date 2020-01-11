package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoMaterialType;
import com.jinchi.common.domain.BasicInfoMaterialTypeExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoMaterialTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-28 16:50
 * @description:
 **/
@Service
public class PrecursorMaterialTypeServiceImp implements PrecursorMaterialTypeService {

    @Autowired
    BasicInfoMaterialTypeMapper materialTypeMapper;

    @Override
    public List getAll() {
        BasicInfoMaterialTypeExample example = new BasicInfoMaterialTypeExample();
        example.createCriteria();
        return materialTypeMapper.selectByExample(example);
    }

    @Override
    public BasicInfoMaterialType add(BasicInfoMaterialType basicInfoMaterialType) {
        materialTypeMapper.insertSelective(basicInfoMaterialType);
        return basicInfoMaterialType;
    }

    @Override
    public BasicInfoMaterialType getByCode(Integer id) {
        return materialTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        materialTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
            deleteById(id);
        }
    }

    @Override
    public BasicInfoMaterialType update(BasicInfoMaterialType basicInfoMaterialType) {
        BasicInfoMaterialTypeExample example = new BasicInfoMaterialTypeExample();
        example.createCriteria().andCodeEqualTo(basicInfoMaterialType.getCode());
        materialTypeMapper.updateByExampleSelective(basicInfoMaterialType, example);
        return basicInfoMaterialType;
    }

    @Override
    public Page page(Integer page, Integer size) {
        Page pageInfo = new Page(getAll(), page, size);
        return pageInfo;
    }

    @Override
    public List getRecordsByTypes(Byte dataType) {
        BasicInfoMaterialTypeExample example = new BasicInfoMaterialTypeExample();
        example.createCriteria().andDataTypeEqualTo(dataType);
        return materialTypeMapper.selectByExample(example);
    }
}
