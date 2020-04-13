package com.jinchi.common.service;

import com.jinchi.common.domain.DeviceRepairType;
import com.jinchi.common.domain.DeviceRepairTypeExample;
import com.jinchi.common.mapper.DeviceRepairTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @time: 2020/3/30 13:21
 * @description:
 */
@Service
public class DeviceRepairTypeServiceImp implements DeviceRepairTypeService {

    @Autowired
    DeviceRepairTypeMapper deviceRepairTypeMapper;

    @Override
    public DeviceRepairType add(DeviceRepairType type) {
        deviceRepairTypeMapper.insertSelective(type);
        return type;
    }

    @Override
    public DeviceRepairType update(DeviceRepairType type) {
        deviceRepairTypeMapper.updateByPrimaryKeySelective(type);
        return type;
    }

    @Override
    public List getAll() {
        return deviceRepairTypeMapper.selectByExample(new DeviceRepairTypeExample());
    }

    @Override
    public List page(Integer page, Integer size) {
        String sql = "select * from device_repair_type order by code limit "
                + size * (page - 1) + "," + size;
        return deviceRepairTypeMapper.selectBySql(sql);
    }
}
