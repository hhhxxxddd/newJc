package com.jinchi.common.service;

import com.jinchi.common.domain.DeviceDocumentUnit;
import com.jinchi.common.domain.DeviceUnitAccessory;
import com.jinchi.common.dto.DeviceDocumentAddUnitDTO;
import com.jinchi.common.dto.DeviceDocumentUnitDTO;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface DeviceDocumentUnitService {
    List<DeviceDocumentUnit> getUnitByDeptIdByDeviceId(Integer deptId,Long deviceId,String condition);

    Page getUnitByDeptIdByDeviceIdByPage(Integer deptId, Long deviceId, String condition, Integer page, Integer size, String fieldName, String orderType);

    DeviceDocumentAddUnitDTO addOne(DeviceDocumentAddUnitDTO deviceDocumentAddUnitDTO);

    String deleteById(Long id);

    void deleteByIds(Long[] id);

    DeviceDocumentAddUnitDTO updateUnit(DeviceDocumentAddUnitDTO deviceDocumentAddUnitDTO);

    public DeviceUnitAccessory updateUnitAccessory(DeviceUnitAccessory deviceUnitAccessory);

    DeviceDocumentUnitDTO getDetailById(long id);
}
