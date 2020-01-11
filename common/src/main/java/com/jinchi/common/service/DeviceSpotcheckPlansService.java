package com.jinchi.common.service;


import com.jinchi.common.domain.DeviceSpotcheckPlans;
import com.jinchi.common.dto.DeviceSpotcheckMainDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.SpotCheckPlanDTO;


import java.util.List;
import java.util.Map;


public interface DeviceSpotcheckPlansService {

    Map<String, Integer> getDeviceNameByDeptId(Integer deptId);

    List<DeviceSpotcheckMainDTO> getAddMsg(int deptCode, String deviceName);

    Boolean deleteByCode(long code);

    Boolean addOne(long DeviceCode, String deviceName,long modelId);

    List<SpotCheckPlanDTO> getPlanByConditions(Integer deptId,String deviceName,Integer status,String condition);

    Page getPlanByConditionsByPage(Integer deptId,String deviceName,Integer status,String condition,Integer page,Integer size);

    void update(Long planId);
}
