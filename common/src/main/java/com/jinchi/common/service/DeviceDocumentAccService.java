package com.jinchi.common.service;

import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.DeviceMainAccessory;
import com.jinchi.common.domain.DeviceUnitAccessory;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface DeviceDocumentAccService {
    DeviceMainAccessory add(DeviceMainAccessory deviceMainAccessory);

    void deleteMainAccById(long id);

    void deleteUnitAccById(long id );

    DeviceUnitAccessory add(DeviceUnitAccessory deviceUnitAccessory);

    List<DeviceMainAccessory> getMainAccByDeviceId(Long deviceId);

    Page getMainAccByDeviceIdByPage(Long deviceId, Integer page, Integer size, String fieldName, String orderType);

    List<DeviceUnitAccessory> getUnitAccByUnitId(Long unitId);

   DeviceMainAccessory updateMainAccById(DeviceMainAccessory deviceMainAccessory);

   DeviceUnitAccessory updateUnitAccById(DeviceUnitAccessory deviceUnitAccessory);

    Page getUnitAccByUnitIdByPage(Long unitId, Integer page, Integer size, String fieldName, String orderType);
}
