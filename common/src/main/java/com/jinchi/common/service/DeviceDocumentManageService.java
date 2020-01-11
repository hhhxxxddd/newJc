package com.jinchi.common.service;

import com.jinchi.common.domain.DeviceDocumentMain;
import com.jinchi.common.domain.DeviceDocumentUnit;
import com.jinchi.common.domain.DeviceMainAccessory;
import com.jinchi.common.dto.DeviceDocumentMainDTO;
import com.jinchi.common.dto.DeviceMainManualDTO;
import com.jinchi.common.dto.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DeviceDocumentManageService {

    String getSpecificationById(Long id);

    Map<String,Integer> getDeviceNameByDeptId(Integer deptId);

    List<DeviceDocumentMainDTO> getByDeptIdByDeviceName(Integer deptId,String deviceName,String condition);

    DeviceDocumentMainDTO addOne(DeviceDocumentMainDTO deviceDocumentMainDTO);

    String deleteById(Long id);

    Page getAllByDeptIdByDeviceNameByPage(Integer deptId, String deviceName, String condition, Integer page, Integer size, String fieldName, String orderType);

    //搜索接口

    DeviceDocumentMainDTO update(DeviceDocumentMainDTO deviceDocumentMainDTO);

    DeviceMainAccessory updateMainAccessory(DeviceMainAccessory deviceMainAccessory);


    DeviceDocumentMainDTO getDetailById(Long id);

    void deleteByIds(Long[] ids);

    DeviceMainManualDTO upLoad(MultipartFile file) throws IOException;

    void cancelLoad(Long[] id);

    void duplicateDevice(Long id);

    Page getByPageByUnitName(String unitName,Integer page,Integer size);

    Page getByPageByAccName(String accName,Integer page,Integer size);

    int duplicateDeviceAcc(Long originDeviceId,Long newDeviceId);

    int duplicateDeviceUnit(Long originDeviceId,Long newUnitId,Integer flag);

    int duplicateUnitAcc(Long originUnitId,Long newUnitId);

    List<DeviceDocumentUnit> getAllUnitByDeptCodeByDeviceName(String deviceName);

    List<DeviceDocumentMain> getAllMainByDeptCodeByDeviceName(String deviceName);

    int duplicateMutipleDevice(Long deviceId,Integer cnt);

    List<DeviceDocumentMain> getAllByDeptId(Integer deptId);
}
