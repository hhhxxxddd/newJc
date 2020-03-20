package com.jinchi.app.service;

import com.jinchi.app.domain.*;
import com.jinchi.app.dto.*;
import com.jinchi.app.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-14 10:12
 * @description:
 **/
@Service
public class DeviceIDCardManageServiceImp implements DeviceIDCardManageService {

    @Autowired
    BasicInfoDeptMapper deptMapper;
    @Autowired
    BasicInfoDeviceProcessMapper processMapper;
    @Autowired
    ProductionProcessDeviceMapMapper mapMapper;
    @Autowired
    BasicInfoDeviceStatusMapper deviceStatusMapper;
    @Autowired
    DeviceDocumentMainMapper mainMapper;

    @Override
    public List getDataByCondition(DeviceConditionDTO conditionDTO) {
        BasicInfoDeptExample example = new BasicInfoDeptExample();
        example.createCriteria().andParentCodeIsNotNull();
        int defaultDeptCode = deptMapper.selectByExample(example).get(0).getCode();

        BasicInfoDeviceProcessExample example1 = new BasicInfoDeviceProcessExample();
        example1.createCriteria().andDeptCodeEqualTo(defaultDeptCode);
        short defaultProcessCode = processMapper.selectByExample(example1).get(0).getCode();

        int deptCode = conditionDTO.getDeptCode() == null ? defaultDeptCode : conditionDTO.getDeptCode();
        short processCode = conditionDTO.getProcessCode() == null ? defaultProcessCode : conditionDTO.getProcessCode();

        ProductionProcessDeviceMapExample example2 = new ProductionProcessDeviceMapExample();
        example2.createCriteria().andDeptCodeEqualTo(deptCode).andProcessCodeEqualTo(processCode);
        List<ProductionProcessDeviceMap> mapList = mapMapper.selectByExample(example2);
        List<DeviceIDCardDTO> deviceIDCardDTOS = new ArrayList<>();
        for (ProductionProcessDeviceMap map : mapList) {
            DeviceIDCardDTO dto = new DeviceIDCardDTO();
            DeviceDocumentMainExample examplee = new DeviceDocumentMainExample();
            examplee.createCriteria().andCodeEqualTo(map.getDeviceCode());
            List<DeviceDocumentMain> mains = mainMapper.selectByExample(examplee);
            if(mains.size() == 0)
                continue;
            DeviceDocumentMain main = mains.get(0);
            ProductionProcessDeviceMap temp = map;
            temp.transfer(main);
            dto.setDeviceCode(temp.getDeviceCode());
            dto.setDeviceName(temp.getDeviceName());
            dto.setFixedassetsCode(temp.getFixedassetsCode());
            dto.setIdNum(temp.getIdCode());
            dto.setDeviceStatus(deviceStatusMapper.selectByPrimaryKey(temp.getStatusCode()).getName());
            deviceIDCardDTOS.add(dto);
        }
        int page = conditionDTO.getPage() == null ? 1 : conditionDTO.getPage();
        int size = conditionDTO.getSize() == null ? 5 : conditionDTO.getSize();
        return new Page(size, page, deviceIDCardDTOS).getList();
    }

    @Override
    public List getDataByCondition1(DeviceConditionDTO conditionDTO) {
        List<DeviceIDCardDTO> deviceIDCardDTOS = new ArrayList<>();

        if (conditionDTO.getDeptCode() != null && conditionDTO.getProcessCode() == null) {
            DeviceDocumentMainExample example = new DeviceDocumentMainExample();
            example.createCriteria().andDeptCodeEqualTo(conditionDTO.getDeptCode());
            List<DeviceDocumentMain> deviceDocumentMains = mainMapper.selectByExample(example);
            for (DeviceDocumentMain map : deviceDocumentMains) {
                DeviceIDCardDTO dto = new DeviceIDCardDTO();
                dto.setDeviceCode(map.getCode());
                dto.setDeviceName(map.getDeviceName());
                dto.setFixedassetsCode(map.getFixedassetsCode());
                dto.setIdNum(map.getIdCode());
                dto.setDeviceStatus(deviceStatusMapper.selectByPrimaryKey(map.getStatusCode()).getName());
                deviceIDCardDTOS.add(dto);
            }
            return deviceIDCardDTOS;
        }
        if (conditionDTO.getDeptCode() != null && conditionDTO.getProcessCode() != null) {
            ProductionProcessDeviceMapExample example2 = new ProductionProcessDeviceMapExample();
            example2.createCriteria().andDeptCodeEqualTo(conditionDTO.getDeptCode()).andProcessCodeEqualTo(conditionDTO.getProcessCode());
            List<ProductionProcessDeviceMap> mapList = mapMapper.selectByExample(example2);
            for (ProductionProcessDeviceMap map : mapList) {
                DeviceIDCardDTO dto = new DeviceIDCardDTO();
                DeviceDocumentMainExample examplee = new DeviceDocumentMainExample();
                examplee.createCriteria().andCodeEqualTo(map.getDeviceCode());
                List<DeviceDocumentMain> mains = mainMapper.selectByExample(examplee);
                if(mains.size() == 0)
                    continue;
                DeviceDocumentMain main = mains.get(0);
                ProductionProcessDeviceMap temp = map;
                temp.transfer(main);
                dto.setDeviceCode(temp.getDeviceCode());
                dto.setDeviceName(temp.getDeviceName());
                dto.setFixedassetsCode(temp.getFixedassetsCode());
                dto.setIdNum(temp.getIdCode());
                dto.setDeviceStatus(deviceStatusMapper.selectByPrimaryKey(temp.getStatusCode()).getName());
                deviceIDCardDTOS.add(dto);
            }
            return deviceIDCardDTOS;
        }
        return new ArrayList();
    }

    @Override
    public DeviceIDCardDTO getDetailByCode(DeviceConditionDTO conditionDTO) {
        long deviceCode = conditionDTO.getDeviceCode();

        ProductionProcessDeviceMapExample example = new ProductionProcessDeviceMapExample();
        example.createCriteria().andDeviceCodeEqualTo(deviceCode);
        List<ProductionProcessDeviceMap> maps = mapMapper.selectByExample(example);
        DeviceIDCardDTO dto = new DeviceIDCardDTO();
        if (maps.size() > 0) {
            ProductionProcessDeviceMap map = maps.get(0);
            dto.setDeviceCode(map.getDeviceCode());
            dto.setDeptName(deptMapper.selectByPrimaryKey(map.getDeptCode()).getName());
            dto.setProcessName(map.getProcessName());
            dto.setDeviceName(map.getDeviceName());
            dto.setFixedassetsCode(map.getFixedassetsCode());
            dto.setDeviceStatus(deviceStatusMapper.selectByPrimaryKey(map.getStatusCode()).getName());
            dto.setIdNum(map.getIdCode());
        } else {
            DeviceDocumentMainExample example1 = new DeviceDocumentMainExample();
            example1.createCriteria().andCodeEqualTo(deviceCode);
            DeviceDocumentMain map = mainMapper.selectByExample(example1).get(0);
            dto.setDeviceCode(map.getCode());
            dto.setDeptName(deptMapper.selectByPrimaryKey(map.getDeptCode()).getName());
            dto.setProcessName("");
            dto.setDeviceName(map.getDeviceName());
            dto.setFixedassetsCode(map.getFixedassetsCode());
            dto.setDeviceStatus(deviceStatusMapper.selectByPrimaryKey(map.getStatusCode()).getName());
            dto.setIdNum(map.getIdCode());
        }
        return dto;
    }

    @Override
    public DeviceIDCardDTO updateByCode(DeviceIDCardDTO dto) {
        ProductionProcessDeviceMapExample example = new ProductionProcessDeviceMapExample();
        example.createCriteria().andDeviceCodeEqualTo(dto.getDeviceCode());
        ProductionProcessDeviceMap map = new ProductionProcessDeviceMap();
        map.setIdCode(dto.getIdNum());
        mapMapper.updateByExampleSelective(map, example);

        //更新主设备表id卡信息
        DeviceDocumentMainExample example1 = new DeviceDocumentMainExample();
        example1.createCriteria().andCodeEqualTo(dto.getDeviceCode());
        DeviceDocumentMain main = new DeviceDocumentMain();
        main.setIdCode(dto.getIdNum());
        mainMapper.updateByExampleSelective(main, example1);

        return dto;
    }

    @Override
    public List getProcessByDept(DeviceConditionDTO conditionDTO) {
        BasicInfoDeviceProcessExample example = new BasicInfoDeviceProcessExample();
        example.createCriteria().andDeptCodeEqualTo(conditionDTO.getDeptCode());
        return processMapper.selectByExample(example);
    }

    @Override
    public List<DeptCata> getDeptCata() {
        BasicInfoDeptExample example = new BasicInfoDeptExample();
        example.createCriteria().andParentCodeIsNull();
        List<BasicInfoDept> parent = deptMapper.selectByExample(example);
        List<DeptCata> ans = new ArrayList<>();
        for (int i = 0; i < parent.size(); i++) {
            DeptCata deptCata = new DeptCata();
            deptCata.setParentId(parent.get(i).getCode());
            deptCata.setParentName(parent.get(i).getName());
            example.clear();
            example.createCriteria().andParentCodeEqualTo(parent.get(i).getCode());
            List<BasicInfoDept> son = deptMapper.selectByExample(example);
            List<SonDept> sonDepts = new ArrayList<>();
            for (int j = 0; j < son.size(); j++) {
                SonDept sonDept = new SonDept();
                sonDept.setDeptId(son.get(j).getCode());
                sonDept.setDeptName(son.get(j).getName());
                sonDepts.add(sonDept);
            }
            if (sonDepts.size() != 0) {
                deptCata.setSons(sonDepts);
            }
            if (sonDepts.size() != 0) {
                if (deptCata.getSons() == null || deptCata.getSons().size() == 0) {
                    deptCata.setSons(new ArrayList<>());
                }
                ans.add(deptCata);
            }
        }
        return ans;
    }
}
