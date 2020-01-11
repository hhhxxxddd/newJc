package com.jinchi.common.service;

import com.jinchi.common.domain.DeviceMainAttributelist;
import com.jinchi.common.mapper.DeviceMainAttributelistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceMainAttributelistServiceImp implements DeviceMainAttributelistService {
    @Autowired
    DeviceMainAttributelistMapper deviceMainAttributelistMapper;

    @Override
    public DeviceMainAttributelist addOne(DeviceMainAttributelist deviceMainAttributelist) {
        deviceMainAttributelistMapper.insert(deviceMainAttributelist);
        return deviceMainAttributelist;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
