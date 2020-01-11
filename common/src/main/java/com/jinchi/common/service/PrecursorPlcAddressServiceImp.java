package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorPlcAddress;
import com.jinchi.common.domain.BasicInfoPrecursorPlcAddressExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoPrecursorPlcAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrecursorPlcAddressServiceImp implements PrecursorPlcAddressService {
    @Autowired
    BasicInfoPrecursorPlcAddressMapper addressMapper;

    @Override
    public BasicInfoPrecursorPlcAddress add(BasicInfoPrecursorPlcAddress basicInfoPrecursorPlcAddress) {
        addressMapper.insertSelective(basicInfoPrecursorPlcAddress);
        return basicInfoPrecursorPlcAddress;
    }

    @Override
    public void delete(Integer id) {
        addressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List getAll(String condition) {
        BasicInfoPrecursorPlcAddressExample example = new BasicInfoPrecursorPlcAddressExample();
        example.createCriteria().andAddressLike(condition+"%");
        return addressMapper.selectByExample(example);
    }

    @Override
    public Page getByPage(String condition, Integer page, Integer size) {
        List<BasicInfoPrecursorPlcAddress> info = getAll(condition);
        Page pageInfo = new Page(info,page,size);
        return pageInfo;
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for(Integer i:ids){
            delete(i);
        }
    }

    @Override
    public BasicInfoPrecursorPlcAddress update(BasicInfoPrecursorPlcAddress basicInfoPrecursorPlcAddress) {
        addressMapper.updateByPrimaryKeySelective(basicInfoPrecursorPlcAddress);
        return basicInfoPrecursorPlcAddress;
    }

    @Override
    public BasicInfoPrecursorPlcAddress getById(Integer id) {
        return addressMapper.selectByPrimaryKey(id);
    }
}
