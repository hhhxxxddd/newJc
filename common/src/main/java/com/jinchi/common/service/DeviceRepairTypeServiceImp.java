package com.jinchi.common.service;

import com.jinchi.common.domain.DeviceRepairType;
import com.jinchi.common.domain.DeviceRepairTypeExample;
import com.jinchi.common.dto.Page;
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
    public Page page(String condition, Integer page, Integer size) {
        DeviceRepairTypeExample example = new DeviceRepairTypeExample();
        example.createCriteria().andTypeNameLike(condition + "%");

        List<DeviceRepairType> typeList = deviceRepairTypeMapper.selectByExample(example);

        return new Page(typeList, page, size);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
            deviceRepairTypeMapper.deleteByPrimaryKey(id);
        }
    }
}
