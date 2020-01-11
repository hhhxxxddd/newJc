package com.jinchi.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.DeviceMainAccessory;
import com.jinchi.common.domain.DeviceMainAccessoryExample;
import com.jinchi.common.domain.DeviceUnitAccessory;
import com.jinchi.common.domain.DeviceUnitAccessoryExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.DeviceMainAccessoryMapper;
import com.jinchi.common.mapper.DeviceUnitAccessoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceDocumentAccServiceImp implements DeviceDocumentAccService {

    @Autowired
    DeviceMainAccessoryMapper deviceMainAccessoryMapper;
    @Autowired
    DeviceUnitAccessoryMapper deviceUnitAccessoryMapper;

    @Override
    public List<DeviceMainAccessory> getMainAccByDeviceId(Long deviceId) {
        DeviceMainAccessoryExample example = new DeviceMainAccessoryExample();
        example.createCriteria().andMainCodeEqualTo(deviceId);
        return deviceMainAccessoryMapper.selectByExample(example);
    }

    //firelfy 7.21
    @Override
    public DeviceMainAccessory updateMainAccById(DeviceMainAccessory deviceMainAccessory){
        DeviceMainAccessoryExample  deviceMainAccessoryExample = new DeviceMainAccessoryExample();
        deviceMainAccessoryExample.createCriteria().andCodeEqualTo(deviceMainAccessory.getCode());
        deviceMainAccessoryMapper.updateByExampleSelective(deviceMainAccessory,deviceMainAccessoryExample);
        return deviceMainAccessory;
    }
    @Override
    public DeviceUnitAccessory updateUnitAccById(DeviceUnitAccessory deviceUnitAccessory){
        DeviceUnitAccessoryExample  deviceUnitAccessoryExample = new DeviceUnitAccessoryExample();
        deviceUnitAccessoryExample.createCriteria().andCodeEqualTo(deviceUnitAccessory.getCode());
        deviceUnitAccessoryMapper.updateByExampleSelective(deviceUnitAccessory,deviceUnitAccessoryExample);
        return deviceUnitAccessory;
    }


    @Override
    public Page getMainAccByDeviceIdByPage(Long deviceId, Integer page, Integer size, String fieldName, String orderType) {
        List<DeviceMainAccessory> deviceMainAccessories = getMainAccByDeviceId(deviceId);
        Page<DeviceMainAccessory> pageInfo = new Page<>(deviceMainAccessories,page,size);
        return pageInfo;
    }

    @Override
    public List<DeviceUnitAccessory> getUnitAccByUnitId(Long unitId) {
        DeviceUnitAccessoryExample example = new DeviceUnitAccessoryExample();
        example.createCriteria().andUnitCodeEqualTo(unitId);
        return deviceUnitAccessoryMapper.selectByExample(example);
    }

    @Override
    public Page getUnitAccByUnitIdByPage(Long unitId, Integer page, Integer size, String fieldName, String orderType) {
        List<DeviceUnitAccessory> deviceUnitAccessories = getUnitAccByUnitId(unitId);
        Page<DeviceUnitAccessory> pageInfo = new Page<>(deviceUnitAccessories,page,size);
        return pageInfo;
    }

    @Override
    public DeviceMainAccessory add(DeviceMainAccessory deviceMainAccessory){
        deviceMainAccessoryMapper.insertSelective(deviceMainAccessory);
        return deviceMainAccessory;
    }

    @Override
    public void deleteMainAccById(long id){
        DeviceMainAccessoryExample example = new DeviceMainAccessoryExample();
        example.createCriteria().andCodeEqualTo(id);
        deviceMainAccessoryMapper.deleteByExample(example);

    }
    @Override
    public void deleteUnitAccById(long id){
        DeviceUnitAccessoryExample example = new DeviceUnitAccessoryExample();
        example.createCriteria().andCodeEqualTo(id);
        deviceUnitAccessoryMapper.deleteByExample(example);

    }

    @Override
    public DeviceUnitAccessory add(DeviceUnitAccessory deviceUnitAccessory){
        deviceUnitAccessoryMapper.insertSelective(deviceUnitAccessory);
        return deviceUnitAccessory;
    }
}
