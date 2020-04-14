package com.jinchi.common.service;

import com.jinchi.common.domain.DeviceRepairType;
import com.jinchi.common.dto.Page;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @time: 2020/3/30 13:12
 * @description:
 */
public interface DeviceRepairTypeService {

    DeviceRepairType add(DeviceRepairType type);

    DeviceRepairType update(DeviceRepairType type);

    List getAll();

    Page page(String condition, Integer page, Integer size);

    void deleteByIds(Integer[] ids);
}
