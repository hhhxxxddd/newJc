package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoDeviceProcess;
import com.jinchi.common.domain.BasicInfoDeviceProcessExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoDeviceProcessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptProcessServiceImp implements DeptProcessService {

    @Autowired
    BasicInfoDeviceProcessMapper mapper;

    @Override
    public BasicInfoDeviceProcess add(BasicInfoDeviceProcess basicInfoDeviceProcess) {
        mapper.insertSelective(basicInfoDeviceProcess);
        return basicInfoDeviceProcess;
    }

    @Override
    public void delete(Integer id) {
        mapper.deleteByPrimaryKey(id.shortValue());
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for(Integer id:ids){
            delete(id);
        }
    }

    @Override
    public BasicInfoDeviceProcess detail(Integer id) {
        return mapper.selectByPrimaryKey(id.shortValue());
    }

    @Override
    public List getAll(Integer deptId) {
        BasicInfoDeviceProcessExample example = new BasicInfoDeviceProcessExample();
        example.createCriteria().andDeptCodeEqualTo(deptId);
        return mapper.selectByExample(example);
    }

    @Override
    public Page page(Integer deptId, Integer page, Integer size) {
        return new Page(getAll(deptId),page,size);
    }

    @Override
    public BasicInfoDeviceProcess update(BasicInfoDeviceProcess basicInfoDeviceProcess) {
        mapper.updateByPrimaryKeySelective(basicInfoDeviceProcess);
        return basicInfoDeviceProcess;
    }
}
