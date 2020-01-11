package com.jinchi.common.service;

import com.jinchi.common.domain.ProductionProcessDeviceMap;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.ProductionProcessDeviceMapMapper;

import java.util.List;


public interface DeviceProcessService {

    Page getPageByProcessDeptId(Integer processDeptId,String condition,Integer page,Integer size);

    List getDeviceByProId(Integer processDeptId);

    void assign(Integer processDeptId,Long[] deviceIds);

    List<ProductionProcessDeviceMap> getByProcessCodeByDeptCode(Integer processCode, Integer deptCode);

    List getDeviceByDeptCode(Integer deptId);
}
