package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.DeviceMaintenancePlansHeadDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceMaintenancePlanServiceImp implements DeviceMaintenancePlanService {
    @Autowired
    DeviceMaintenancePlansHeadMapper deviceMaintenancePlansHeadMapper;
    @Autowired
    DeviceMaintenancePlansDetailsMapper deviceMaintenancePlansDetailsMapper;
    @Autowired
    DeviceMaintenanceItemsMapper deviceMaintenanceItemsMapper;
    @Autowired
    DeviceMaintenanceRecordHeadMapper deviceMaintenanceRecordHeadMapper;
    @Autowired
    DeviceMaintenanceRecordDetailsMapper deviceMaintenanceRecordDetailsMapper;
    @Autowired
    DeviceDocumentMainMapper deviceDocumentMainMapper;
    @Autowired
    AuthUserService authUserService;
    @Autowired
    DeviceMaintenanceItemsService deviceMaintenanceItemsService;

    @Override
    @Transactional
    public DeviceMaintenancePlansHeadDTO add(DeviceMaintenancePlansHeadDTO deviceMaintenancePlansHeadDTO) {
        deviceMaintenancePlansHeadMapper.insertSelective(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead());
        if(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getEffFlag()!=1) {
            DeviceMaintenanceRecordHead deviceMaintenanceRecordHead = new DeviceMaintenanceRecordHead();
            deviceMaintenanceRecordHead.setPlanCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getCode());
            deviceMaintenanceRecordHead.setFixedassetsCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getFixedassetsCode());
            //LiuTaoYi 2019-12-06 add deviceCode
            deviceMaintenanceRecordHead.setDeviceCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getDeviceCode());
            deviceMaintenanceRecordHead.setDeviceName(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getDeviceName());
            deviceMaintenanceRecordHead.setDeptCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getDeptCode());
            deviceMaintenanceRecordHead.setPlanDate(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getPlanDate());
            deviceMaintenanceRecordHead.setMaintPeople(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getSetPeople());
            deviceMaintenanceRecordHead.setEditFlag(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getEditFlag());
            deviceMaintenanceRecordHeadMapper.insertSelective(deviceMaintenanceRecordHead);
            for (int i = 0; i < deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().size(); i++) {
                System.out.println(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getCode());
                DeviceMaintenancePlansDetails deviceMaintenancePlansDetails = new DeviceMaintenancePlansDetails();
                deviceMaintenancePlansDetails.setMaintenanceContent(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getMaintenanceContent());
                deviceMaintenancePlansDetails.setMaintenanceItems(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getMaintenanceItems());
                deviceMaintenancePlansDetails.setItemsCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getCode());
                deviceMaintenancePlansDetails.setPlanCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getCode());
                DeviceMaintenanceRecordDetails deviceMaintenanceRecordDetails = new DeviceMaintenanceRecordDetails();
                deviceMaintenanceRecordDetails.setMaintenanceContent(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getMaintenanceContent());
                deviceMaintenanceRecordDetails.setMaintenanceItems(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getMaintenanceItems());
                deviceMaintenanceRecordDetails.setItemsCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getCode());
                deviceMaintenanceRecordDetails.setOptType(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getOptType());
                deviceMaintenanceRecordDetails.setPlanCode(deviceMaintenanceRecordHead.getCode());
                deviceMaintenanceRecordDetails.setMainValues(-1);
                deviceMaintenancePlansDetailsMapper.insertSelective(deviceMaintenancePlansDetails);
                deviceMaintenanceRecordDetailsMapper.insertSelective(deviceMaintenanceRecordDetails);
            }
        }
        return deviceMaintenancePlansHeadDTO;
    }

    @Override
    @Transactional
    public DeviceMaintenancePlansHeadDTO update(DeviceMaintenancePlansHeadDTO deviceMaintenancePlansHeadDTO) {
        DeviceMaintenancePlansHeadExample example = new DeviceMaintenancePlansHeadExample();
        example.createCriteria().andCodeEqualTo(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getCode());
        deviceMaintenancePlansHeadMapper.updateByExampleSelective(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead(),example);

        DeviceMaintenancePlansDetailsExample example1 = new DeviceMaintenancePlansDetailsExample();
        example1.createCriteria().andPlanCodeEqualTo(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getCode());
        deviceMaintenancePlansDetailsMapper.deleteByExample(example1);

        DeviceMaintenanceRecordDetailsExample example2 = new DeviceMaintenanceRecordDetailsExample();
        example2.createCriteria().andPlanCodeEqualTo(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getCode());
        deviceMaintenanceRecordDetailsMapper.deleteByExample(example2);

        for(int i=0;i<deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansDetails().size();i++){
            DeviceMaintenancePlansDetails deviceMaintenancePlansDetails = new DeviceMaintenancePlansDetails();
            deviceMaintenancePlansDetails.setMaintenanceContent(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansDetails().get(i).getMaintenanceContent());
            deviceMaintenancePlansDetails.setMaintenanceItems(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansDetails().get(i).getMaintenanceItems());
            deviceMaintenancePlansDetails.setItemsCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansDetails().get(i).getItemsCode());
            deviceMaintenancePlansDetails.setPlanCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getCode());
            DeviceMaintenanceRecordDetails deviceMaintenanceRecordDetails = new DeviceMaintenanceRecordDetails();
            deviceMaintenanceRecordDetails.setMaintenanceContent(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansDetails().get(i).getMaintenanceContent());
            deviceMaintenanceRecordDetails.setMaintenanceItems(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansDetails().get(i).getMaintenanceItems());
            deviceMaintenanceRecordDetails.setItemsCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansDetails().get(i).getItemsCode());
            deviceMaintenanceRecordDetails.setOptType(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansDetails().get(i).getOptType());
            deviceMaintenanceRecordDetails.setPlanCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getCode());
            deviceMaintenancePlansDetailsMapper.insertSelective(deviceMaintenancePlansDetails);
            deviceMaintenanceRecordDetailsMapper.insertSelective(deviceMaintenanceRecordDetails);
        }
        return deviceMaintenancePlansHeadDTO;
    }

    @Override
    public DeviceMaintenancePlansHeadDTO detail(Long id) {
        DeviceMaintenancePlansHeadDTO deviceMaintenancePlansHeadDTO = new DeviceMaintenancePlansHeadDTO();
        DeviceMaintenancePlansHeadExample example = new DeviceMaintenancePlansHeadExample();
        example.createCriteria().andCodeEqualTo(id);
        deviceMaintenancePlansHeadDTO.setDeviceMaintenancePlansHead(deviceMaintenancePlansHeadMapper.selectByExample(example).get(0));

        DeviceMaintenancePlansDetailsExample example1 = new DeviceMaintenancePlansDetailsExample();
        example1.createCriteria().andPlanCodeEqualTo(id);
        List<DeviceMaintenancePlansDetails> details = deviceMaintenancePlansDetailsMapper.selectByExample(example1);
       // System.out.println(details.size());
        deviceMaintenancePlansHeadDTO.setDeviceMaintenancePlansDetails(details);

        List<DeviceMaintenanceItems> items = deviceMaintenanceItemsService.getByDeviceName(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getDeviceName(),"");
        deviceMaintenancePlansHeadDTO.setDeviceMaintenanceItems(items);

        DeviceMaintenanceRecordHeadExample example2 = new DeviceMaintenanceRecordHeadExample();
        example2.createCriteria().andPlanCodeEqualTo(id);
        deviceMaintenancePlansHeadDTO.setDetailNum(deviceMaintenanceRecordHeadMapper.countByExample(example2));

        example2.clear();
        example2.createCriteria().andPlanCodeEqualTo(id).andPlanDateEqualTo(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getPlanDate());
        deviceMaintenancePlansHeadDTO.setGenerateMaint(deviceMaintenanceRecordHeadMapper.countByExample(example2)>0?false:true);

        return deviceMaintenancePlansHeadDTO;
    }

    @Override
    public void delete(Long id) {
        DeviceMaintenancePlansDetailsExample deviceMaintenancePlansDetailsExample = new DeviceMaintenancePlansDetailsExample();
        deviceMaintenancePlansDetailsExample.createCriteria().andPlanCodeEqualTo(id);
        deviceMaintenancePlansDetailsMapper.deleteByExample(deviceMaintenancePlansDetailsExample);
        
        DeviceMaintenancePlansHeadExample example = new DeviceMaintenancePlansHeadExample();
        example.createCriteria().andCodeEqualTo(id);
        deviceMaintenancePlansHeadMapper.deleteByExample(example);
    }


    @Override
    public List<DeviceDocumentMain>  getMainDeviceByDeptCode(int code) {
        DeviceDocumentMainExample deviceDocumentMainExample= new DeviceDocumentMainExample();
        deviceDocumentMainExample.createCriteria().andDeptCodeEqualTo(code);
        return   deviceDocumentMainMapper.selectByExample(deviceDocumentMainExample);
    }


    @Override
    public List<DeviceMaintenancePlansHeadDTO> getAllByCondition(Integer deptId,Integer statusId,String condition) {
        List<DeviceMaintenancePlansHeadDTO> deviceMaintenancePlansHeadDTOS = new ArrayList<>();
        String sql = "select * from device_maintenance_plans_head where (dept_code = " + deptId + ")";
        if(statusId!=-1)
            sql += " and (eff_flag = " + statusId + ")";
        if(!condition.equals(""))
            sql += " and (device_name like '" + condition + "%' or fixedassets_code like '" + condition + "%')";
        List<DeviceMaintenancePlansHead> deviceMaintenancePlansHeads = deviceMaintenancePlansHeadMapper.selectByCondition(sql);
        for(int i=0;i<deviceMaintenancePlansHeads.size();i++){
            deviceMaintenancePlansHeadDTOS.add(new DeviceMaintenancePlansHeadDTO());
            deviceMaintenancePlansHeadDTOS.get(i).setDeviceMaintenancePlansHead(deviceMaintenancePlansHeads.get(i));
            DeviceMaintenanceRecordHeadExample example = new DeviceMaintenanceRecordHeadExample();
            example.createCriteria().andPlanCodeEqualTo(deviceMaintenancePlansHeads.get(i).getCode());
            deviceMaintenancePlansHeadDTOS.get(i).setDetailNum(deviceMaintenanceRecordHeadMapper.countByExample(example));
            deviceMaintenancePlansHeadDTOS.get(i).setSetPeopleName(authUserService.findById(deviceMaintenancePlansHeads.get(i).getSetPeople()).getName());
        }
        return deviceMaintenancePlansHeadDTOS;
    }

    @Override
    public Page getByPage(Integer deptId, Integer statusId, String condition, Integer page, Integer size) {
        Page<DeviceMaintenancePlansHeadDTO> pageInfo  = new Page<>(getAllByCondition(deptId,statusId,condition),page,size);
        return pageInfo;
    }

    @Override
    @Transactional
    public DeviceMaintenancePlansHeadDTO generatorMaint(DeviceMaintenancePlansHeadDTO deviceMaintenancePlansHeadDTO) {
        if(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getEffFlag()!=1) {
            DeviceMaintenanceRecordHead deviceMaintenanceRecordHead = new DeviceMaintenanceRecordHead();
            deviceMaintenanceRecordHead.setPlanCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getCode());
            deviceMaintenanceRecordHead.setFixedassetsCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getFixedassetsCode());
            deviceMaintenanceRecordHead.setDeviceName(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getDeviceName());
            deviceMaintenanceRecordHead.setDeptCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getDeptCode());
            deviceMaintenanceRecordHead.setPlanDate(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getPlanDate());
            deviceMaintenanceRecordHead.setMaintPeople(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getSetPeople());
            deviceMaintenanceRecordHead.setEditFlag(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getEditFlag());
            deviceMaintenanceRecordHeadMapper.insertSelective(deviceMaintenanceRecordHead);
            for (int i = 0; i < deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().size(); i++) {
                DeviceMaintenancePlansDetails deviceMaintenancePlansDetails = new DeviceMaintenancePlansDetails();
                deviceMaintenancePlansDetails.setMaintenanceContent(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getMaintenanceContent());
                deviceMaintenancePlansDetails.setItemsCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getCode());
                deviceMaintenancePlansDetails.setMaintenanceItems(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getMaintenanceItems());
                deviceMaintenancePlansDetails.setPlanCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenancePlansHead().getCode());
                DeviceMaintenanceRecordDetails deviceMaintenanceRecordDetails = new DeviceMaintenanceRecordDetails();
                deviceMaintenanceRecordDetails.setMaintenanceContent(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getMaintenanceContent());
                deviceMaintenanceRecordDetails.setMaintenanceItems(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getMaintenanceItems());
                deviceMaintenanceRecordDetails.setOptType(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getOptType());
                deviceMaintenanceRecordDetails.setItemsCode(deviceMaintenancePlansHeadDTO.getDeviceMaintenanceItems().get(i).getCode());
                deviceMaintenanceRecordDetails.setPlanCode(deviceMaintenanceRecordHead.getCode());
                deviceMaintenanceRecordDetails.setMainValues(-1);
                deviceMaintenancePlansDetailsMapper.insertSelective(deviceMaintenancePlansDetails);
                deviceMaintenanceRecordDetailsMapper.insertSelective(deviceMaintenanceRecordDetails);
            }
        }
        return deviceMaintenancePlansHeadDTO;
    }
}
