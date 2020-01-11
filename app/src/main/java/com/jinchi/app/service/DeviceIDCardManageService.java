package com.jinchi.app.service;

import com.jinchi.app.dto.DeptCata;
import com.jinchi.app.dto.DeviceConditionDTO;
import com.jinchi.app.dto.DeviceIDCardDTO;

import java.util.List;

public interface DeviceIDCardManageService {

    List getDataByCondition(DeviceConditionDTO conditionDTO);

    List getDataByCondition1(DeviceConditionDTO conditionDTO);

    DeviceIDCardDTO getDetailByCode(DeviceConditionDTO conditionDTO);

    DeviceIDCardDTO updateByCode(DeviceIDCardDTO dto);

    List getProcessByDept(DeviceConditionDTO conditionDTO);

    List<DeptCata> getDeptCata();
}
