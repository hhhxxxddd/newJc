package com.jinchi.common.service;

import com.jinchi.common.dto.DevicePatrolModelDTO;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface DevicePatrolModelService {

    DevicePatrolModelDTO add(DevicePatrolModelDTO devicePatrolModelDTO);

    DevicePatrolModelDTO update(DevicePatrolModelDTO devicePatrolModelDTO);

    void deleteById(Long id);

    void deleteByIds(Long[] ids);

    DevicePatrolModelDTO detail(Long id);

    List<DevicePatrolModelDTO> getAll(Integer deptCode,Integer status,String condition);

    Page page(Integer deptCode,Integer status,String condition,Integer page,Integer size);

}
