package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.DevicePatrolPlanDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.DevicePatrolModelsHeadMapper;
import com.jinchi.common.mapper.DevicePatrolPlanRecordHeadMapper;
import com.jinchi.common.mapper.DevicePatrolPlanRecordItemDetailsMapper;
import com.jinchi.common.mapper.DevicePatrolPlanRecordLocationDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DevicePatrolQueryServiceImp implements DevicePatrolQueryService {

    @Autowired
    DevicePatrolPlanRecordHeadMapper devicePatrolPlanRecordHeadMapper;
    @Autowired
    DevicePatrolPlanRecordItemDetailsMapper devicePatrolPlanRecordItemDetailsMapper;
    @Autowired
    DevicePatrolPlanRecordLocationDetailsMapper devicePatrolPlanRecordLocationDetailsMapper;
    @Autowired
    DevicePatrolModelsHeadMapper devicePatrolModelsHeadMapper;
    @Autowired
    DeptManageService deptManageService;
    @Autowired
    AuthUserService authUserService;
    @Autowired
    DevicePatrolModelService devicePatrolModelService;

    @Override
    public List<DevicePatrolPlanDTO> getAll(Integer deptId, Integer status, String startDate, String endDate, String condition) {
        List<DevicePatrolPlanDTO> ans = new ArrayList<>();
        String sql = "select * from device_patrol_plan_record_head where (dept_code = " + deptId + " and plan_name like '"+condition+"%')";
        if(status!=-1){
            sql += " and (edit_flag = "+status+")";
        }
        if (!startDate.equals("") && !endDate.equals("")) {
            sql += "and (plan_time <= '" + endDate + " 23:59:59' and plan_time >= '" + startDate + " 00:00:00')";
        }
        List<DevicePatrolPlanRecordHead> devicePatrolPlanRecordHeads = devicePatrolPlanRecordHeadMapper.selectByCondition(sql);
        for(int i=0;i<devicePatrolPlanRecordHeads.size();i++){
            DevicePatrolPlanDTO devicePatrolPlanDTO = new DevicePatrolPlanDTO();
            devicePatrolPlanDTO.setDevicePatrolPlanRecordHead(devicePatrolPlanRecordHeads.get(i));
            DevicePatrolModelsHeadExample example = new DevicePatrolModelsHeadExample();
            example.createCriteria().andCodeEqualTo(devicePatrolPlanRecordHeads.get(i).getModelCode());
            devicePatrolPlanDTO.setModelName(devicePatrolModelsHeadMapper.selectByExample(example).get(0).getPatrolName());
            ans.add(devicePatrolPlanDTO);
        }
        return ans;
    }

    @Override
    public Page getAllByPage(Integer deptId, Integer status, String startDate, String endDate, String condition, Integer page, Integer size) {
        Page pageInfo = new Page(getAll(deptId,status,startDate,endDate,condition),page,size);
        return pageInfo;
    }

    @Override
    public DevicePatrolPlanDTO detail(Long id) {
        DevicePatrolPlanDTO devicePatrolPlanDTO = new DevicePatrolPlanDTO();
        DevicePatrolPlanRecordHeadExample example = new DevicePatrolPlanRecordHeadExample();
        example.createCriteria().andCodeEqualTo(id);
        devicePatrolPlanDTO.setDevicePatrolPlanRecordHead(devicePatrolPlanRecordHeadMapper.selectByExample(example).get(0));

        DevicePatrolPlanRecordItemDetailsExample example1 = new DevicePatrolPlanRecordItemDetailsExample();
        example1.createCriteria().andPlanRecordCodeEqualTo(id);
        devicePatrolPlanDTO.setDevicePatrolPlanRecordItemDetailsList(devicePatrolPlanRecordItemDetailsMapper.selectByExample(example1));

        DevicePatrolPlanRecordLocationDetailsExample example2 = new DevicePatrolPlanRecordLocationDetailsExample();
        example2.createCriteria().andPlanRecordCodeEqualTo(id);
        devicePatrolPlanDTO.setDevicePatrolPlanRecordLocationDetailsList(devicePatrolPlanRecordLocationDetailsMapper.selectByExample(example2));

        DevicePatrolModelsHeadExample example3 = new DevicePatrolModelsHeadExample();
        example3.createCriteria().andCodeEqualTo(devicePatrolPlanDTO.getDevicePatrolPlanRecordHead().getModelCode());
        DevicePatrolModelsHead modelsHead = devicePatrolModelsHeadMapper.selectByExample(example3).get(0);

        DevicePatrolPlanRecordHead head = devicePatrolPlanDTO.getDevicePatrolPlanRecordHead();
        devicePatrolPlanDTO.setDetpName(deptManageService.getDetailById(head.getDeptCode()).getName());
        devicePatrolPlanDTO.setModelName(modelsHead.getPatrolName());
        AuthUserDTO authUserDTO = authUserService.findById(head.getSetPeople());
        devicePatrolPlanDTO.setTabPeopleName(authUserDTO == null ? "" : authUserDTO.getName());
        return devicePatrolPlanDTO;
    }
}
