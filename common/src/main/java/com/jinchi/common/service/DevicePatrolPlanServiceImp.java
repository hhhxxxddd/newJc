package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.DevicePatrolModelDTO;
import com.jinchi.common.dto.DevicePatrolPlanDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.DevicePatrolPlanRecordHeadMapper;
import com.jinchi.common.mapper.DevicePatrolPlanRecordItemDetailsMapper;
import com.jinchi.common.mapper.DevicePatrolPlanRecordLocationDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DevicePatrolPlanServiceImp implements DevicePatrolPlanService {
    @Autowired
    DevicePatrolPlanRecordHeadMapper devicePatrolPlanRecordHeadMapper;
    @Autowired
    DevicePatrolPlanRecordItemDetailsMapper devicePatrolPlanRecordItemDetailsMapper;
    @Autowired
    DevicePatrolPlanRecordLocationDetailsMapper devicePatrolPlanRecordLocationDetailsMapper;
    @Autowired
    DevicePatrolModelService devicePatrolModelService;
    @Autowired
    DeptManageService deptManageService;
    @Autowired
    AuthUserService authUserService;

    @Override
    @Transactional
    public DevicePatrolPlanRecordHead add(Integer deptId, String planName,Integer checkType, String planDate, String endDate,Long modelId,Integer userId){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Date end = new Date();
        try {
            date = df.parse(planDate);
            end = df.parse(endDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        DevicePatrolPlanRecordHead devicePatrolPlanRecordHead = new DevicePatrolPlanRecordHead();
        devicePatrolPlanRecordHead.setDeptCode(deptId);
        devicePatrolPlanRecordHead.setPlanName(planName);
        devicePatrolPlanRecordHead.setCheckType(checkType==0?false:true);
        devicePatrolPlanRecordHead.setPlanTime(date);
        devicePatrolPlanRecordHead.setModelCode(modelId);
        devicePatrolPlanRecordHead.setSetPeople(userId);
        devicePatrolPlanRecordHead.setTabulatedate(end);
        devicePatrolPlanRecordHeadMapper.insertSelective(devicePatrolPlanRecordHead);
        Long planId = devicePatrolPlanRecordHead.getCode();
        DevicePatrolModelDTO devicePatrolModelDTO = devicePatrolModelService.detail(modelId);
        for(int i=0;i<devicePatrolModelDTO.getDevicePatrolModelsItemDetailsList().size();i++){
            DevicePatrolPlanRecordItemDetails devicePatrolPlanRecordItemDetails = new DevicePatrolPlanRecordItemDetails();
            devicePatrolPlanRecordItemDetails.setPlanRecordCode(planId);
            devicePatrolPlanRecordItemDetails.setModelDetailCode(devicePatrolModelDTO.getDevicePatrolModelsItemDetailsList().get(i).getCode());
            devicePatrolPlanRecordItemDetails.setPatrolContent(devicePatrolModelDTO.getDevicePatrolModelsItemDetailsList().get(i).getPatrolContent());
            devicePatrolPlanRecordItemDetails.setPatrolItem(devicePatrolModelDTO.getDevicePatrolModelsItemDetailsList().get(i).getPatrolItem());
            devicePatrolPlanRecordItemDetailsMapper.insertSelective(devicePatrolPlanRecordItemDetails);
        }
        for(int i=0;i<devicePatrolModelDTO.getDevicePatrolModelsLocationDetails().size();i++){
            DevicePatrolPlanRecordLocationDetails devicePatrolPlanRecordLocationDetails = new DevicePatrolPlanRecordLocationDetails();
            devicePatrolPlanRecordLocationDetails.setModelDetailCode(devicePatrolModelDTO.getDevicePatrolModelsLocationDetails().get(i).getCode());
            devicePatrolPlanRecordLocationDetails.setPlanRecordCode(planId);
            devicePatrolPlanRecordLocationDetails.setLocationCode(devicePatrolModelDTO.getDevicePatrolModelsLocationDetails().get(i).getLocationCode());
            devicePatrolPlanRecordLocationDetails.setLocationName(devicePatrolModelDTO.getDevicePatrolModelsLocationDetails().get(i).getLocationName());
            devicePatrolPlanRecordLocationDetailsMapper.insertSelective(devicePatrolPlanRecordLocationDetails);
        }
        return devicePatrolPlanRecordHead;
    }

    @Override
    @Transactional
    public DevicePatrolPlanDTO update(Long planId, String planName, String planDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try{
            date = df.parse(planDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        DevicePatrolPlanRecordHead devicePatrolPlanRecordHead = new DevicePatrolPlanRecordHead();
        devicePatrolPlanRecordHead.setPlanTime(date);
        devicePatrolPlanRecordHead.setPlanName(planName);
        DevicePatrolPlanRecordHeadExample example = new DevicePatrolPlanRecordHeadExample();
        example.createCriteria().andCodeEqualTo(planId);
        devicePatrolPlanRecordHeadMapper.updateByExampleSelective(devicePatrolPlanRecordHead,example);
        return detail(planId);
    }

    @Override
    public List<DevicePatrolPlanDTO> getAllByDeptIdByStatus(Integer deptId, Integer status, String condition) {
        String sql = "select * from device_patrol_plan_record_head where (dept_code = " + deptId + ")";
        if(status!=-1){
            if (status == 1) {
                sql += "and (edit_flag = 1 or edit_flag = 0 )";
            } else {
                sql += " and (edit_flag = " + status + ")";
            }
        }
        if(!condition.equals("")){
            sql += " and (plan_name like '"+ condition +"%')";
        }
        List<DevicePatrolPlanRecordHead> devicePatrolPlanRecordHeads = devicePatrolPlanRecordHeadMapper.selectByCondition(sql);
        List<DevicePatrolPlanDTO> ans = new ArrayList<>();
        for(int i=0;i<devicePatrolPlanRecordHeads.size();i++){
            DevicePatrolPlanDTO devicePatrolPlanDTO = new DevicePatrolPlanDTO();
            devicePatrolPlanDTO.setDevicePatrolPlanRecordHead(devicePatrolPlanRecordHeads.get(i));
            Long modelId = devicePatrolPlanRecordHeads.get(i).getModelCode();
            devicePatrolPlanDTO.setModelName(devicePatrolModelService.detail(modelId).getDevicePatrolModelsHead().getPatrolName());
            ans.add(devicePatrolPlanDTO);
        }
        return ans;
    }

    @Override
    public Page page(Integer deptId, Integer status, String condition, Integer page, Integer size) {
        Page pageInfo = new Page(getAllByDeptIdByStatus(deptId,status,condition),page,size);
        return pageInfo;
    }

    @Override
    public DevicePatrolPlanDTO detail(Long planId) {
        DevicePatrolPlanDTO ans = new DevicePatrolPlanDTO();
        DevicePatrolPlanRecordHeadExample example = new DevicePatrolPlanRecordHeadExample();
        example.createCriteria().andCodeEqualTo(planId);
        DevicePatrolPlanRecordHead devicePatrolPlanRecordHead = devicePatrolPlanRecordHeadMapper.selectByExample(example).get(0);
        ans.setDevicePatrolPlanRecordHead(devicePatrolPlanRecordHead);
        ans.setDetpName(deptManageService.getDetailById(devicePatrolPlanRecordHead.getDeptCode()).getName());
        ans.setTabPeopleName(devicePatrolPlanRecordHead.getSetPeople()==null?"":authUserService.findById(devicePatrolPlanRecordHead.getSetPeople()).getName());
        ans.setModelName(devicePatrolModelService.detail(devicePatrolPlanRecordHead.getModelCode()).getDevicePatrolModelsHead().getPatrolName());

        DevicePatrolPlanRecordItemDetailsExample example1 = new DevicePatrolPlanRecordItemDetailsExample();
        example1.createCriteria().andPlanRecordCodeEqualTo(planId);
        List<DevicePatrolPlanRecordItemDetails> devicePatrolPlanRecordItemDetails = devicePatrolPlanRecordItemDetailsMapper.selectByExample(example1);
        ans.setDevicePatrolPlanRecordItemDetailsList(devicePatrolPlanRecordItemDetails);

        DevicePatrolPlanRecordLocationDetailsExample example2 = new DevicePatrolPlanRecordLocationDetailsExample();
        example2.createCriteria().andPlanRecordCodeEqualTo(planId);
        List<DevicePatrolPlanRecordLocationDetails> devicePatrolPlanRecordLocationDetails = devicePatrolPlanRecordLocationDetailsMapper.selectByExample(example2);
        ans.setDevicePatrolPlanRecordLocationDetailsList(devicePatrolPlanRecordLocationDetails);
        return ans;
    }

    @Override
    public void delete(Long id) {
        DevicePatrolPlanRecordItemDetailsExample example = new DevicePatrolPlanRecordItemDetailsExample();
        example.createCriteria().andPlanRecordCodeEqualTo(id);
        devicePatrolPlanRecordItemDetailsMapper.deleteByExample(example);

        DevicePatrolPlanRecordLocationDetailsExample example1 = new DevicePatrolPlanRecordLocationDetailsExample();
        example1.createCriteria().andPlanRecordCodeEqualTo(id);
        devicePatrolPlanRecordLocationDetailsMapper.deleteByExample(example1);

        DevicePatrolPlanRecordHeadExample example2 = new DevicePatrolPlanRecordHeadExample();
        example2.createCriteria().andCodeEqualTo(id);
        devicePatrolPlanRecordHeadMapper.deleteByExample(example2);
    }

    @Override
    @Transactional
    public void deleteByIds(Long[] ids) {
        for(Long id:ids)
            delete(id);
    }
}
