package com.jinchi.common.service;



import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.DeviceMaintenanceItems;
import com.jinchi.common.domain.DeviceMaintenanceItemsExample;
import com.jinchi.common.dto.Page;


import java.util.List;
import java.util.Set;

public interface DeviceMaintenanceItemsService {

    DeviceMaintenanceItems addOne(DeviceMaintenanceItems deviceMaintenanceItems);

    Set<String> getAllMaintenanceItems();

    void delMaintenanceDevById(Long id);

    void delMaintenanceDevByIds(Long[] ids);

    DeviceMaintenanceItems update(DeviceMaintenanceItems deviceMaintenanceItems);

    Page<DeviceMaintenanceItems> getByDeviceNameByPage(String deviceName, String condition, Integer page, Integer size, String orderField, String orderTpye);

    List<DeviceMaintenanceItems> getByDeviceName(String deviceName,String condition);

    Set<String> getAllByDeviceName(String deviceName);

    List<DeviceMaintenanceItems> getDetailById(Long id);

}
