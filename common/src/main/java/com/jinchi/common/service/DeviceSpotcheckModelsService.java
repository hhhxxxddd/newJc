package com.jinchi.common.service;


import com.jinchi.common.domain.BasicInfoDept;
import com.jinchi.common.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DeviceSpotcheckModelsService {

    DeviceSpotcheckModelsHeadDTO add (DeviceSpotcheckModelsHeadDTO DeviceSpotcheckModelsDTO);

    void deleteById(Long id);

    void deleteByIds(Long[] ids);

    DeviceSpotcheckModelsHeadDTO updataById (DeviceSpotcheckModelsHeadDTO deviceMaintenancePlansHeadDTO);

    DeviceSpotcheckModelsHeadDTO detail (Long id);

    DeviceSpotcheckModelsDTO getDevicesNameByMainCode (int id);

    List<BasicInfoDept> getChildDeptByCode(int id);

    List<String> getDeviceByCode(int id);

    List<DeviceSpotcheckModelsDTO> getAllDevices();

    Page getByPage(String deviceName,Integer deptId,Integer page,Integer size);

    List<DeviceSpotcheckModelsHeadDTO> getAllByDeviceName(String deviceName,Integer deptId ,Integer page, Integer size);

    List<DeviceSpotcheckModelsHeadDTO> getModelsByDeptId(Integer deptId);

    List<DeviceSpotcheckModelsDTO> getByDeviceName(String deviceName);
    //左侧模糊查询

    void deleteDetailById(Long headCode,Long id);

    String upload(MultipartFile file) throws IOException;

    void cancelLoad(String path);

    List getModelByDeviceName(Integer deptCode,String deviceName);

}
