package com.jinchi.common.service;


import com.jinchi.common.domain.DeviceDocumentMain;
import com.jinchi.common.domain.DeviceDocumentMainExample;
import com.jinchi.common.domain.DeviceMaintenanceItems;
import com.jinchi.common.domain.DeviceMaintenanceItemsExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.DeviceDocumentMainMapper;
import com.jinchi.common.mapper.DeviceMaintenanceItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Service
public class DeviceMaintenanceItemsServiceImp implements DeviceMaintenanceItemsService {
    @Autowired
    DeviceMaintenanceItemsMapper deviceMaintenanceItemsMapper;
    @Autowired
    DeviceDocumentMainMapper deviceDocumentMainMapper;

    @Override
    public DeviceMaintenanceItems addOne(DeviceMaintenanceItems deviceMaintenanceItems) {
        deviceMaintenanceItemsMapper.insert(deviceMaintenanceItems);
        return deviceMaintenanceItems;
    }

    @Override
    public Set<String> getAllMaintenanceItems() {
        DeviceDocumentMainExample deviceDocumentMainExample = new DeviceDocumentMainExample();
        deviceDocumentMainExample.createCriteria().andDeptCodeGreaterThan(0);
        List<DeviceDocumentMain> list = deviceDocumentMainMapper.selectByExample(deviceDocumentMainExample);
        Set<String> set = new HashSet<>();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            DeviceDocumentMain temp = (DeviceDocumentMain) it.next();
            set.add(temp.getDeviceName().split("-")[0]);
        }
        return set;
    }

    @Override
    public void delMaintenanceDevById(Long id) {
        DeviceMaintenanceItemsExample example = new DeviceMaintenanceItemsExample();
        example.createCriteria().andCodeEqualTo(id);
        deviceMaintenanceItemsMapper.deleteByExample(example);
    }

    @Override
    public void delMaintenanceDevByIds(Long[] ids) {
        for (Long id : ids) {
            delMaintenanceDevById(id);
        }
    }

    @Override
    public DeviceMaintenanceItems update(DeviceMaintenanceItems deviceMaintenanceItems) {
        DeviceMaintenanceItemsExample example = new DeviceMaintenanceItemsExample();
        Long code = deviceMaintenanceItems.getCode();
        example.createCriteria().andCodeEqualTo(code);
        deviceMaintenanceItemsMapper.updateByExampleSelective(deviceMaintenanceItems, example);
        return deviceMaintenanceItems;
    }

    @Override
    public Page<DeviceMaintenanceItems> getByDeviceNameByPage(String deviceName, String condition, Integer page, Integer size, String orderFiled, String orderType) {
        Page<DeviceMaintenanceItems> pageInfo = new Page<>(getByDeviceName(deviceName, condition), page, size);
        return pageInfo;
    }

    @Override
    public List<DeviceMaintenanceItems> getByDeviceName(String deviceName, String condition) {
        String sql = "select * from device_maintenance_items where (device_name = '" + deviceName + "')";
        if (!condition.equals("")) {
            sql += " and (maintenance_items like '" + condition + "%' or maintenance_content like '" + condition + "%')";
        }
        return deviceMaintenanceItemsMapper.selectByCondition(sql);
    }

    @Override
    public Set<String> getAllByDeviceName(String deviceName) {
        Set<String> temp = getAllMaintenanceItems();
        Set<String> ans = new HashSet<>();
        for (String s : temp) {
            if (s.contains(deviceName))
                ans.add(s);
        }
        return ans;
    }
    @Override
    public List<DeviceMaintenanceItems> getDetailById(Long id) {
        DeviceMaintenanceItemsExample example = new DeviceMaintenanceItemsExample();
        example.createCriteria().andCodeEqualTo(id);
        return deviceMaintenanceItemsMapper.selectByExample(example);
    }
}
