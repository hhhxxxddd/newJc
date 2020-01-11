package com.jinchi.common.service;

import com.jinchi.common.domain.DeviceDocumentMain;
import com.jinchi.common.dto.DeviceMaintenancePlansHeadDTO;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface DeviceMaintenancePlanService {
    DeviceMaintenancePlansHeadDTO add(DeviceMaintenancePlansHeadDTO deviceMaintenancePlansHeadDTO);

    DeviceMaintenancePlansHeadDTO update(DeviceMaintenancePlansHeadDTO deviceMaintenancePlansHeadDTO);

    DeviceMaintenancePlansHeadDTO detail(Long id);

    void delete(Long id);

    List<DeviceMaintenancePlansHeadDTO> getAllByCondition(Integer deptId,Integer statusId,String condition);

    List<DeviceDocumentMain>  getMainDeviceByDeptCode(int code);

    Page getByPage(Integer deptId,Integer statusId,String condition,Integer page,Integer size);

    DeviceMaintenancePlansHeadDTO generatorMaint(DeviceMaintenancePlansHeadDTO deviceMaintenancePlansHeadDTO);
}
