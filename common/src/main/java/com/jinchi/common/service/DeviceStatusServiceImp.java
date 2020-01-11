package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoDeviceStatus;
import com.jinchi.common.domain.BasicInfoDeviceStatusExample;
import com.jinchi.common.mapper.BasicInfoDeviceStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceStatusServiceImp implements DeviceStatusService {

    @Autowired
    BasicInfoDeviceStatusMapper basicInfoDeviceStatusMapper;

    @Override
    public BasicInfoDeviceStatus addOne(BasicInfoDeviceStatus basicInfoDeviceStatus) {
        basicInfoDeviceStatusMapper.insertSelective(basicInfoDeviceStatus);
        return basicInfoDeviceStatus;
    }

    @Override
    public BasicInfoDeviceStatus update(BasicInfoDeviceStatus basicInfoDeviceStatus) {
        BasicInfoDeviceStatusExample example = new BasicInfoDeviceStatusExample();
        example.createCriteria().andCodeEqualTo(basicInfoDeviceStatus.getCode());
        basicInfoDeviceStatusMapper.updateByExampleSelective(basicInfoDeviceStatus,example);
        return basicInfoDeviceStatus;
    }

    @Override
    public void deleteById(Integer id) {
        BasicInfoDeviceStatusExample example = new BasicInfoDeviceStatusExample();
        example.createCriteria().andCodeEqualTo(id);
        basicInfoDeviceStatusMapper.deleteByExample(example);
    }

    @Override
    public BasicInfoDeviceStatus getStatusById(Integer id) {
        BasicInfoDeviceStatusExample example = new BasicInfoDeviceStatusExample();
        example.createCriteria().andCodeEqualTo(id);
        return basicInfoDeviceStatusMapper.selectByExample(example).get(0);

    }

    @Override
    public List<BasicInfoDeviceStatus> getAll() {
        return basicInfoDeviceStatusMapper.selectByExample(new BasicInfoDeviceStatusExample());
    }

    @Override
    public List<BasicInfoDeviceStatus> getBybNameLike(String name) {
        BasicInfoDeviceStatusExample example = new BasicInfoDeviceStatusExample();
        example.createCriteria().andNameLike("%"+name+"%");
        return basicInfoDeviceStatusMapper.selectByExample(example);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for(Integer id:ids)
            deleteById(id);
    }
}
