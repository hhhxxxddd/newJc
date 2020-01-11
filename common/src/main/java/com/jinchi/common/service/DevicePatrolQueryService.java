package com.jinchi.common.service;

import com.jinchi.common.dto.DevicePatrolPlanDTO;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface DevicePatrolQueryService {

    List<DevicePatrolPlanDTO> getAll(Integer deptId, Integer status, String startDate, String endDate, String condition);

    Page getAllByPage(Integer deptId, Integer status, String startDate, String endDate, String condition, Integer page, Integer size);

    DevicePatrolPlanDTO detail(Long id);
}
