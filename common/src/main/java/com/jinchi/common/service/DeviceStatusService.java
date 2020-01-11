package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoDeviceStatus;

import java.util.List;

public interface DeviceStatusService {

    BasicInfoDeviceStatus addOne(BasicInfoDeviceStatus basicInfoDeviceStatus);

    BasicInfoDeviceStatus update(BasicInfoDeviceStatus basicInfoDeviceStatus);

    void deleteById(Integer id);

    BasicInfoDeviceStatus getStatusById(Integer id);

    List<BasicInfoDeviceStatus> getAll();

    List<BasicInfoDeviceStatus> getBybNameLike(String name);

    void deleteByIds(Integer[] ids);
}
