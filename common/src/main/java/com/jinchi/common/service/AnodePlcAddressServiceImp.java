package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodePlcAddress;
import com.jinchi.common.domain.BasicInfoAnodePlcAddressExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoAnodePlcAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 21:53
 * @description:
 **/
@Service
public class AnodePlcAddressServiceImp implements AnodePlcAddressService {

    @Autowired
    BasicInfoAnodePlcAddressMapper addressMapper;

    @Override
    public BasicInfoAnodePlcAddress add(BasicInfoAnodePlcAddress anodePlcAddress) {
        addressMapper.insertSelective(anodePlcAddress);
        return anodePlcAddress;
    }

    @Override
    public BasicInfoAnodePlcAddress update(BasicInfoAnodePlcAddress anodePlcAddress) {
        addressMapper.updateByPrimaryKeySelective(anodePlcAddress);
        return anodePlcAddress;
    }

    @Override
    public void deleteById(Integer id) {
        addressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List getAll(String condition) {
        BasicInfoAnodePlcAddressExample example = new BasicInfoAnodePlcAddressExample();
        example.createCriteria().andPlcAddressLike(condition + "%");
        return addressMapper.selectByExample(example);
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        return new Page(getAll(condition), page, size);
    }

    @Override
    public BasicInfoAnodePlcAddress getById(Integer id) {
        return addressMapper.selectByPrimaryKey(id);
    }
}
