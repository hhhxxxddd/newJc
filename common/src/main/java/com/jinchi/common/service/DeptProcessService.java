package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoDeviceProcess;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface DeptProcessService {

    BasicInfoDeviceProcess add(BasicInfoDeviceProcess basicInfoDeviceProcess);

    void delete(Integer id);

    void deleteByIds(Integer[] ids);

    BasicInfoDeviceProcess detail(Integer id);

    List getAll(Integer deptId);

    Page page(Integer deptId,Integer page,Integer size);

    BasicInfoDeviceProcess update(BasicInfoDeviceProcess basicInfoDeviceProcess);
}
