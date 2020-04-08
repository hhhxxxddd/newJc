package com.jinchi.common.service;

import com.jinchi.common.domain.DevicePatrolPlanRecordHead;
import com.jinchi.common.dto.DevicePatrolPlanDTO;
import com.jinchi.common.dto.Page;

import java.util.Date;
import java.util.List;

public interface DevicePatrolPlanService {

    DevicePatrolPlanRecordHead add(Integer deptId, String planName,Integer checkType, String planDate,String endDate,Long modelId,Integer userId);

    DevicePatrolPlanDTO update(Long planId,String planName,String planDate);

    List<DevicePatrolPlanDTO> getAllByDeptIdByStatus(Integer deptId,Integer status,String condition);

    Page page(Integer deptId,Integer status,String condition,Integer page,Integer size);

    DevicePatrolPlanDTO detail(Long planId);

    void delete(Long id);

    void deleteByIds(Long[] ids);
}
