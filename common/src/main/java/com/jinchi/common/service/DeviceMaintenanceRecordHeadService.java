package com.jinchi.common.service;


import com.jinchi.common.dto.DeviceMaintenanceDetailsDTO;
import com.jinchi.common.dto.DeviceRepair4BatchTraceDTO;
import com.jinchi.common.dto.Page;

import java.util.Date;
import java.util.List;

public interface DeviceMaintenanceRecordHeadService {

    List<DeviceMaintenanceDetailsDTO> getByIdStatusCondition(Integer id, Integer status, String condition);

    List getByDeptBystatus(Integer deptId, Integer status, String condition, String startDate, String endDate);

    List getRecordsByCondition(Long deviceCode, Date startTime, Date endTime);

    DeviceRepair4BatchTraceDTO listRecordsByIdAndTime(Long deviceCode, String startTime, String endTime);

    Page getByDeptBystatusByPage(Integer deptId,Integer status,String condition,String startDate,String endDate,Integer page,Integer size);

    DeviceMaintenanceDetailsDTO detail(Long id);

    DeviceMaintenanceDetailsDTO updateById(DeviceMaintenanceDetailsDTO deviceMaintenanceDetailsDTO);

    void delete(Long id);
}
