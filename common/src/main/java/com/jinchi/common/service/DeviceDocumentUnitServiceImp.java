package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.DeviceDocumentAddUnitDTO;
import com.jinchi.common.dto.DeviceDocumentMainDTO;
import com.jinchi.common.dto.DeviceDocumentUnitDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.DeviceDocumentUnitMapper;
import com.jinchi.common.mapper.DeviceUnitAttributelistMapper;
import com.jinchi.common.mapper.DeviceUnitAccessoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class DeviceDocumentUnitServiceImp implements DeviceDocumentUnitService {

    @Autowired
    DeviceDocumentUnitMapper deviceDocumentUnitMapper;

    @Autowired
    DeviceDocumentManageService deviceDocumentManageService;//这里要写实现类的话，不该后面的名字了

    @Autowired
    DeptManageServiceImp deptManageService;
    @Autowired
    DeviceUnitAttributelistMapper deviceUnitAttributelistMapper;
    @Autowired
    DeviceUnitAccessoryMapper deviceUnitAccessoryMapper;

    @Override
    public List<DeviceDocumentUnit> getUnitByDeptIdByDeviceId(Integer deptId, Long deviceId, String condition) {
        DeviceDocumentUnitExample example = new DeviceDocumentUnitExample();
        example.createCriteria().andDeptCodeEqualTo(deptId).andMainCodeEqualTo(deviceId).andDeviceNameLike("%" + condition + "%");
        DeviceDocumentUnitExample.Criteria c2 = example.createCriteria();
        c2.andDeptCodeEqualTo(deptId).andMainCodeEqualTo(deviceId).andFixedassetsCodeLike("%" + condition + "%");
        example.or(c2);
        return deviceDocumentUnitMapper.selectByExample(example);
    }

    @Override
    public Page getUnitByDeptIdByDeviceIdByPage(Integer deptId, Long deviceId, String condition, Integer page, Integer size, String fieldName, String orderType) {
        List<DeviceDocumentUnit> deviceDocumentUnits = getUnitByDeptIdByDeviceId(deptId, deviceId, condition);
        Page<DeviceDocumentUnit> pageInfo = new Page<>(deviceDocumentUnits, page, size);
        return pageInfo;
    }

    @Override
    public String deleteById(Long id) {
        DeviceUnitAccessoryExample example1 = new DeviceUnitAccessoryExample();
        example1.createCriteria().andUnitCodeEqualTo(id);
        if(deviceUnitAccessoryMapper.countByExample(example1)!=0){
            return "存在配件，不允许删除！";
        }
        DeviceDocumentUnitExample example = new DeviceDocumentUnitExample();
        example.createCriteria().andCodeEqualTo(id);
        deviceDocumentUnitMapper.deleteByExample(example);
        return "操作成功！";
    }

    @Override
    public DeviceDocumentAddUnitDTO addOne(DeviceDocumentAddUnitDTO deviceDocumentAddUnitDTO) {
        deviceDocumentUnitMapper.insertSelective(deviceDocumentAddUnitDTO.getDeviceDocumentUnit());
        if (deviceDocumentAddUnitDTO.getArrName() != null) {
            for (int i = 0; i < deviceDocumentAddUnitDTO.getArrName().size(); i++) {
                DeviceUnitAttributelist d = new DeviceUnitAttributelist();
                d.setName(deviceDocumentAddUnitDTO.getArrName().get(i));
                d.setAttValues(deviceDocumentAddUnitDTO.getArrValue().get(i));
                d.setUnitCode(deviceDocumentAddUnitDTO.getDeviceDocumentUnit().getCode());
                deviceUnitAttributelistMapper.insert(d);
            }
        }
        return deviceDocumentAddUnitDTO;
    }

    @Override
    public DeviceDocumentAddUnitDTO updateUnit(DeviceDocumentAddUnitDTO deviceDocumentAddUnitDTO) {
        //首先先更新DeviceDocumentAddUnit
        long code = deviceDocumentAddUnitDTO.getDeviceDocumentUnit().getCode();
        DeviceDocumentUnitExample deviceDocumentUnitExample = new DeviceDocumentUnitExample();
        deviceDocumentUnitExample.createCriteria().andCodeEqualTo(code);
        deviceDocumentUnitMapper.updateByExampleSelective(deviceDocumentAddUnitDTO.getDeviceDocumentUnit(), deviceDocumentUnitExample);
        //先删除然后插入新属性值
        DeviceUnitAttributelistExample deviceUnitAttributelistExample = new DeviceUnitAttributelistExample();
        deviceUnitAttributelistExample.createCriteria().andUnitCodeEqualTo(code);
        deviceUnitAttributelistMapper.deleteByExample(deviceUnitAttributelistExample);

        List<String> first = deviceDocumentAddUnitDTO.getArrName();
        List<String> secoend = deviceDocumentAddUnitDTO.getArrValue();
        //如果为空不会进入循环
        for (int i = 0; i < first.size(); i++) {
            DeviceUnitAttributelist deviceUnitAttributelist = new DeviceUnitAttributelist();
            deviceUnitAttributelist.setUnitCode(code);
            deviceUnitAttributelist.setName(first.get(i));
            deviceUnitAttributelist.setAttValues(secoend.get(i));
            deviceUnitAttributelistMapper.insertSelective(deviceUnitAttributelist);
        }
        return deviceDocumentAddUnitDTO;
    }

    @Override
    public DeviceUnitAccessory updateUnitAccessory(DeviceUnitAccessory deviceUnitAccessory) {

        DeviceUnitAccessoryExample deviceUnitAccessoryExample = new DeviceUnitAccessoryExample();
        deviceUnitAccessoryExample.createCriteria().andCodeEqualTo(deviceUnitAccessory.getCode());
        deviceUnitAccessoryMapper.updateByExampleSelective(deviceUnitAccessory, deviceUnitAccessoryExample);
        deviceUnitAccessoryMapper.updateByExampleSelective(deviceUnitAccessory, deviceUnitAccessoryExample);
        return deviceUnitAccessory;
    }


    @Override
    @Transactional
    public void deleteByIds(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public DeviceDocumentUnitDTO getDetailById(long id) {
        DeviceDocumentUnitDTO ans = new DeviceDocumentUnitDTO();
        //获取  Unit
        DeviceDocumentUnitExample example = new DeviceDocumentUnitExample();
        example.createCriteria().andCodeEqualTo(id);
        List<DeviceDocumentUnit> Temp = deviceDocumentUnitMapper.selectByExample(example);
        DeviceDocumentUnit ddu = Temp.get(0); //获取第一个， 不考虑没有的情况和 重复
        //加入 UNIT
        ans.setDeviceDocumentUnit(ddu);
        //获取  dept 的 id
        Integer deptId = ddu.getDeptCode();
        BasicInfoDept basicInfoDept = deptManageService.getDetailById(deptId);
        ans.setBasicInfoDept(basicInfoDept);
        //获取 main的 信息
        Long mainId = ddu.getMainCode();
        //通过main的id找到， main的所有信息
        DeviceDocumentMainDTO deviceDocumentMainDTO = deviceDocumentManageService.getDetailById(mainId);
        ans.setDeviceDocumentMainDTO(deviceDocumentMainDTO);
        List<String> first = new ArrayList<>();
        List<String> second = new ArrayList<>();
        DeviceUnitAttributelistExample deviceUnitAttributelistExample = new DeviceUnitAttributelistExample();
        deviceUnitAttributelistExample.createCriteria().andUnitCodeEqualTo(id);
        List<DeviceUnitAttributelist> deviceUnitAttributelist = deviceUnitAttributelistMapper.selectByExample(deviceUnitAttributelistExample);
        if (deviceUnitAttributelist != null) {
            for (DeviceUnitAttributelist list : deviceUnitAttributelist) {
                first.add(list.getName());
                second.add(list.getAttValues());
            }
        }
        ans.setArrName(first);
        ans.setArrValue(second);
        return ans;
    }
}
