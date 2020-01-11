package com.jinchi.common.service;


import com.jinchi.common.dto.*;

import java.util.Date;
import java.util.List;

public interface DeviceRepairService {

    Page getPage(Integer repairStatus, Integer secondDeptId, String condition, String startTime,String endTime,Integer page, Integer size);

    List<DeviceRepairApplicationAndPeopleDTO> getAllByDeviceName(Integer repairStatus, Integer secondDeptCode, String condition,String startTime,String endTime);

    List<DeviceRepairApplicationDTO>getAllApplications(Integer status,Integer deptCode,Long deviceId);

    Page getPageByDeviceId (Integer status,Integer deptCode,Long deviceId,Integer page,Integer size);

    DeviceRepairApplicationDTO detail(Long id);

    DeviceRepairEvaluationsDTO evaluations(Long id);

    List<DeviceRepairApplicationDTO> query(Integer status, Integer deptCode, String deptName);

    //DeviceSpotcheckModelsHeadDTO detail(@ApiParam(name = "id", value = "点检模板主键") @RequestParam Long id );

                  //getByDeviceName(@ApiParam(name = "deviceName",value = "设备名称")@RequestParam  String deviceName);

    List getApplicationsByCondition(Long deviceCode, Date startTime, Date endTime);

    String export(Integer repairStatus,Integer secondDeptCode, String condition,String startTime,String endTime);

    DeviceRepair4BatchTraceDTO listRepairInfosByIdAndTime(Long deviceCode, String startTime, String endTime);
}
