package com.jinchi.common.service;

import com.jinchi.common.domain.DeviceMainManual;
import com.jinchi.common.domain.DeviceMainManualExample;
import com.jinchi.common.mapper.DeviceMainManualMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceManualServiceImp implements DeviceManualService {
    @Autowired
    DeviceMainManualMapper deviceMainManualMapper;
    @Override
    public DeviceMainManual addOne(DeviceMainManual deviceMainManual) {
        deviceMainManualMapper.insertSelective(deviceMainManual);
        return deviceMainManual;
    }

    @Override
    public void deleteById(Long code) {
        DeviceMainManualExample example = new DeviceMainManualExample();
        example.createCriteria().andCodeEqualTo(code);
        deviceMainManualMapper.deleteByExample(example);
    }

    @Override
    public DeviceMainManual getByCode(Long code) {
        DeviceMainManualExample example = new DeviceMainManualExample();
        example.createCriteria().andCodeEqualTo(code);
        return deviceMainManualMapper.selectByExample(example).get(0);
    }

    @Override
    public List<DeviceMainManual> getByMainCode(Long mainCode) {
        DeviceMainManualExample example = new DeviceMainManualExample();
        example.createCriteria().andMainCodeEqualTo(mainCode);
        return deviceMainManualMapper.selectByExample(example);
    }

    @Override
    public void update(Long code,Long mainCode) {
        DeviceMainManual deviceMainManual = new DeviceMainManual();
        deviceMainManual.setMainCode(mainCode);
        DeviceMainManualExample example = new DeviceMainManualExample();
        example.createCriteria().andCodeEqualTo(code);
        deviceMainManualMapper.updateByExampleSelective(deviceMainManual,example);
    }
}
