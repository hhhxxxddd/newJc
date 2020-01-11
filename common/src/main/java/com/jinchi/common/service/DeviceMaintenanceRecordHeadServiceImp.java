package com.jinchi.common.service;


import com.jinchi.common.domain.*;
import com.jinchi.common.dto.*;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DeviceMaintenanceRecordHeadServiceImp implements DeviceMaintenanceRecordHeadService {

    @Autowired
    UserDeviceService userDeviceService;
    @Autowired
    DeviceMaintenanceRecordHeadMapper deviceMaintenanceRecordHeadMapper;
    @Autowired
    DeviceMaintenanceRecordDetailsMapper deviceMaintenanceRecordDetailsMapper;
    @Autowired
    DeviceMaintenanceAccessoryMapper deviceMaintenanceAccessoryMapper;
    @Autowired
    DeviceMaintenancePlansHeadMapper deviceMaintenancePlansHeadMapper;
    @Autowired
    AuthUserMapper authUserMapper;
    @Autowired
    DeptManageService deptManageService;
    @Autowired
    AuthUserService authUserService;

    @Override
    public List<DeviceMaintenanceDetailsDTO> getByIdStatusCondition(Integer id, Integer status, String condition) {
/*
        List<DeviceMaintenanceDetailsDTO> list = new ArrayList<>();
        //部门只有一个先获取到
        BasicInfoDept basicInfoDept = userDeviceService.getDeptByAuthId(id);
         //用一个example把所有的head先找出来
        DeviceMaintenanceRecordHeadExample deviceMaintenanceRecordHeadExample = new DeviceMaintenanceRecordHeadExample();
        deviceMaintenanceRecordHeadExample.createCriteria().andEditFlagEqualTo(status);
        List<DeviceMaintenanceRecordHead>  deviceMaintenanceRecordHead= deviceMaintenanceRecordHeadMapper.selectByExample(deviceMaintenanceRecordHeadExample);
        long code;
        Boolean flag;
            if(deviceMaintenanceRecordHead != null){
                flag =  condition == null ? true : false;
                    for (DeviceMaintenanceRecordHead d : deviceMaintenanceRecordHead){
                      code = -1;
                        if((d.getFixedassetsCode().contains(condition) || d.getDeviceName().contains(condition) || basicInfoDept.getName().contains(condition)) ){
                            code = d.getCode();
                        }
                        //如果condition不为空， 又没有搜索到跳过
                       if(!flag && code ==-1){
                           continue;
                       }
                        code = d.getCode();
                        DeviceMaintenanceRecordDetailsExample deviceMaintenanceRecordDetailsExample = new DeviceMaintenanceRecordDetailsExample();
                        deviceMaintenanceRecordDetailsExample.createCriteria().andPlanCodeEqualTo(code);//这里待解决
                        List<DeviceMaintenanceRecordDetails> deviceMaintenanceRecordDetails =
                                deviceMaintenanceRecordDetailsMapper.selectByExample(deviceMaintenanceRecordDetailsExample);
                        DeviceMaintenanceAccessoryExample deviceMaintenanceAccessoryExample = new DeviceMaintenanceAccessoryExample();
                        deviceMaintenanceAccessoryExample.createCriteria().andPlanCodeEqualTo(code);
                        List<DeviceMaintenanceAccessory> deviceMaintenanceAccessory = deviceMaintenanceAccessoryMapper.selectByExample(deviceMaintenanceAccessoryExample);
                       //打包dto
                        DeviceMaintenanceDetailsDTO deviceMaintenanceDetailsDTO = new DeviceMaintenanceDetailsDTO();
                        deviceMaintenanceDetailsDTO.setBasicInfoDept(basicInfoDept);
                        deviceMaintenanceDetailsDTO.setDeviceMaintenanceRecordHead(d);
                        deviceMaintenanceDetailsDTO.setDeviceMaintenanceRecordDetails(deviceMaintenanceRecordDetails);
                        deviceMaintenanceDetailsDTO.setDeviceMaintenanceAccessory(deviceMaintenanceAccessory);
                        list.add(deviceMaintenanceDetailsDTO);
                    }
        }
        return list;*/
        List<DeviceMaintenanceDetailsDTO> deviceMaintenanceDetailsDTOs = new ArrayList<>();
        List<DeviceMaintenanceRecordHead> deviceMaintenanceRecordHeads = new ArrayList<>();
        List<BasicInfoDept> basicInfoDepts = userDeviceService.getDeptByAuthId(id);
        for (int i = 0; i < basicInfoDepts.size(); i++) {
            String sql = "select * from device_maintenance_record_head as a,basic_info_dept as b where (a.dept_code = b.code and a.dept_code = " + basicInfoDepts.get(i).getCode() + " and a.edit_flag = " + status + ")";
            if (!condition.equals("")) {
                sql += "and (a.fixedassets_code like '" + condition + "%' or a.device_name like '" + condition + "%' or b.name like '" + condition + "%')";
            }
            deviceMaintenanceRecordHeads.addAll(deviceMaintenanceRecordHeadMapper.selectByCondition(sql));
        }
        for (int i = 0; i < deviceMaintenanceRecordHeads.size(); i++) {
            deviceMaintenanceDetailsDTOs.add(new DeviceMaintenanceDetailsDTO());
            deviceMaintenanceDetailsDTOs.get(i).setDeviceMaintenanceRecordHead(deviceMaintenanceRecordHeads.get(i));
            BasicInfoDept basicInfoDept = new BasicInfoDept();
            for (int j = 0; j < basicInfoDepts.size(); j++) {
                if (basicInfoDepts.get(j).getCode() == deviceMaintenanceRecordHeads.get(i).getDeptCode())
                    basicInfoDept = basicInfoDepts.get(j);
            }
            deviceMaintenanceDetailsDTOs.get(i).setBasicInfoDept(basicInfoDept);
        }
        return deviceMaintenanceDetailsDTOs;
    }

    @Override
    @Transactional
    public DeviceMaintenanceDetailsDTO updateById(DeviceMaintenanceDetailsDTO deviceMaintenanceDetailsDTO) {
        DeviceMaintenanceRecordHead deviceMaintenanceRecordHead = deviceMaintenanceDetailsDTO.getDeviceMaintenanceRecordHead();
        List<DeviceMaintenanceRecordDetails> deviceMaintenanceRecordDetails = deviceMaintenanceDetailsDTO.getDeviceMaintenanceRecordDetails();
        List<DeviceMaintenanceAccessory> deviceMaintenanceAccessory = deviceMaintenanceDetailsDTO.getDeviceMaintenanceAccessory();
        // 如果是从待保养到已保养不变
        // 从已保养到已完成 要重新更改数据，判断的条件是editflag == 2

        long headCode = deviceMaintenanceRecordHead.getCode();
        DeviceMaintenanceRecordHeadExample deviceMaintenanceRecordHeadExample = new DeviceMaintenanceRecordHeadExample();
        deviceMaintenanceRecordHeadExample.createCriteria().andCodeEqualTo(headCode);
        deviceMaintenanceRecordHeadMapper.updateByExampleSelective(deviceMaintenanceRecordHead, deviceMaintenanceRecordHeadExample);

        DeviceMaintenanceAccessoryExample deviceMaintenanceAccessoryExample = new DeviceMaintenanceAccessoryExample();
        deviceMaintenanceAccessoryExample.createCriteria().andPlanCodeEqualTo(headCode);
        deviceMaintenanceAccessoryMapper.deleteByExample(deviceMaintenanceAccessoryExample);
        for (DeviceMaintenanceAccessory d : deviceMaintenanceAccessory) {
            deviceMaintenanceAccessoryMapper.insert(d);
        }
        for (DeviceMaintenanceRecordDetails d : deviceMaintenanceRecordDetails) {
            long code = d.getCode();
            DeviceMaintenanceRecordDetailsExample deviceMaintenanceRecordDetailsExample = new DeviceMaintenanceRecordDetailsExample();
            deviceMaintenanceRecordDetailsExample.createCriteria().andCodeEqualTo(code);
            deviceMaintenanceRecordDetailsMapper.updateByExampleSelective(d, deviceMaintenanceRecordDetailsExample);
        }

        if(deviceMaintenanceRecordHead.getEditFlag().equals(2)){
            DeviceMaintenancePlansHeadExample deviceMaintenancePlansHeadExample = new DeviceMaintenancePlansHeadExample();
            deviceMaintenancePlansHeadExample.createCriteria().andCodeEqualTo(deviceMaintenanceRecordHead.getPlanCode());
            List<DeviceMaintenancePlansHead>  deviceMaintenancePlansHead = deviceMaintenancePlansHeadMapper.selectByExample(deviceMaintenancePlansHeadExample);
            for(  DeviceMaintenancePlansHead d:deviceMaintenancePlansHead){
                long period = d.getMaintPeriod();
                Date temp  = d.getPlanDate();
                 long time = temp.getTime();
                period = (period * 24 * 60 * 60 * 1000);
                time += period;
                Date newDate = new Date(time);
                d.setPlanDate(newDate);
                  //再加一次
                time += period;
                Date next_date = new Date(time);
                d.setNextDate(next_date);
                deviceMaintenancePlansHeadMapper.updateByExampleSelective(d,deviceMaintenancePlansHeadExample);
                //如果这个是2  换为3
                deviceMaintenanceRecordHead.setEditFlag(3);
                deviceMaintenanceRecordHeadMapper.updateByExample(deviceMaintenanceRecordHead,deviceMaintenanceRecordHeadExample);
                    //eff-flag 为零的时候   新增一条记录
                if(d.getEffFlag() == 0){
                    DeviceMaintenanceRecordHead insert =deviceMaintenanceRecordHead;
                    insert.setEditFlag(1);// flag 设置为一
                    insert.setCode(null);
                    insert.setFinishiDate(null);
                    insert.setReceiveDate(null);
                    insert.setPlanDate(newDate);
                    deviceMaintenanceRecordHeadMapper.insertSelective(insert);
                }
            }
        }
        return deviceMaintenanceDetailsDTO;
    }


    @Override
    public List getByDeptBystatus(Integer deptId, Integer status, String condition, String startDate, String endDate) {
        String sql = "select * from device_maintenance_record_head where (dept_code = " + deptId + " and edit_flag = " + status + ")";
        if (!condition.equals("")) {
            sql += "and (fixedassets_code like '" + condition + "%' or device_name like '" + condition + "%' or code like '" + condition + "%')";
        }
        if (!startDate.equals("") && !endDate.equals("")) {
            sql += "and (finishi_date <= '" + endDate + " 23:59:59' and finishi_date >= '" + startDate + " 00:00:00')";
        }
        List<DeviceMaintenanceRecordHead> heads = deviceMaintenanceRecordHeadMapper.selectByCondition(sql);
        List<DeviceMaintenanceRecordDTO> ans = new ArrayList<>();
        for(int i=0;i<heads.size();i++){
            DeviceMaintenanceRecordDTO temp = new DeviceMaintenanceRecordDTO(heads.get(i));
            temp.setMaintName(authUserMapper.byId(temp.getMaintPeople()).getName());
            temp.setDeptName(deptManageService.getDeptById(temp.getDeptCode()).getName());
            ans.add(temp);
        }
        return ans;
    }

    @Override
    public Page getByDeptBystatusByPage(Integer deptId, Integer status, String condition, String startDate, String endDate, Integer page, Integer size) {
        Page<DeviceMaintenanceRecordHead> pageInfo = new Page<>(getByDeptBystatus(deptId, status, condition, startDate, endDate), page, size);
        return pageInfo;
    }

    @Override
    public List getRecordsByCondition(Long deviceCode, Date startTime, Date endTime) {
        DeviceMaintenanceRecordHeadExample example = new DeviceMaintenanceRecordHeadExample();
        example.createCriteria().andDeviceCodeEqualTo(deviceCode).andFinishiDateBetween(startTime, endTime);
        return deviceMaintenanceRecordHeadMapper.selectByExample(example);
    }

    @Override
    public DeviceRepair4BatchTraceDTO listRecordsByIdAndTime(Long deviceCode, String startTime, String endTime) {
        String df = "yyyy-MM-dd HH:mm:ss";
        Date date1 = ComUtil.getDate(startTime, df);
        Date date2 = ComUtil.getDate(endTime, df);

        DeviceRepair4BatchTraceDTO dto = new DeviceRepair4BatchTraceDTO();

        DeviceMaintenanceRecordHeadExample example = new DeviceMaintenanceRecordHeadExample();
        example.createCriteria().andDeviceCodeEqualTo(deviceCode).andFinishiDateBetween(date1, date2);

        List<DeviceMaintenanceRecordHead> heads = deviceMaintenanceRecordHeadMapper.selectByExample(example);

        if (heads.size() == 0) {
            return null;
        }

        dto.setDeviceName(heads.get(0).getDeviceName());
        dto.setFixedassetsCode(heads.get(0).getFixedassetsCode());
        dto.setDeptName(deptManageService.getDeptById(heads.get(0).getDeptCode()).getName());


        List<DeviceRepairDetailDTO> dtos = new ArrayList<>();
        for (DeviceMaintenanceRecordHead head : heads) {
            DeviceRepairDetailDTO ans = new DeviceRepairDetailDTO();
            ans.setMaintCode(head.getCode());
            ans.setPlanDate(head.getPlanDate());
            ans.setReceiveTime(head.getReceiveDate());
            ans.setFinishDate(head.getFinishiDate());
            ans.setMaintPeople(authUserService.findById(head.getMaintPeople()).getName());
            dtos.add(ans);
        }
        dto.setDtoList(dtos);

        return dto;
    }

    @Override
    public DeviceMaintenanceDetailsDTO detail(Long id) {
        DeviceMaintenanceDetailsDTO deviceMaintenanceDetailsDTO = new DeviceMaintenanceDetailsDTO();
        DeviceMaintenanceRecordHeadExample example = new DeviceMaintenanceRecordHeadExample();
        example.createCriteria().andCodeEqualTo(id);
        DeviceMaintenanceRecordHead head = deviceMaintenanceRecordHeadMapper.selectByExample(example).get(0);
        deviceMaintenanceDetailsDTO.setDeviceMaintenanceRecordHead(head);
        DeviceMaintenanceRecordDetailsExample example1 = new DeviceMaintenanceRecordDetailsExample();
        example1.createCriteria().andPlanCodeEqualTo(id);
        deviceMaintenanceDetailsDTO.setDeviceMaintenanceRecordDetails(deviceMaintenanceRecordDetailsMapper.selectByExample(example1));
        DeviceMaintenanceAccessoryExample example2 = new DeviceMaintenanceAccessoryExample();
        example2.createCriteria().andPlanCodeEqualTo(id);
        deviceMaintenanceDetailsDTO.setBasicInfoDept(deptManageService.getDeptById(head.getDeptCode()));
        deviceMaintenanceDetailsDTO.setDeviceMaintenanceAccessory(deviceMaintenanceAccessoryMapper.selectByExample(example2));
        deviceMaintenanceDetailsDTO.setSetPeople(authUserMapper.byId(deviceMaintenanceDetailsDTO.getDeviceMaintenanceRecordHead().getMaintPeople()).getName());
        return deviceMaintenanceDetailsDTO;
    }

    @Override
    public void delete(Long id) {
        //删除他的detail
        DeviceMaintenanceRecordDetailsExample example1 = new DeviceMaintenanceRecordDetailsExample();
        example1.createCriteria().andPlanCodeEqualTo(id);
        deviceMaintenanceRecordDetailsMapper.deleteByExample(example1);

        //删除他的配件使用信息
        DeviceMaintenanceAccessoryExample example2 = new DeviceMaintenanceAccessoryExample();
        example1.createCriteria().andPlanCodeEqualTo(id);
        deviceMaintenanceAccessoryMapper.deleteByExample(example2);

        //删除表头
        DeviceMaintenanceRecordHeadExample example = new DeviceMaintenanceRecordHeadExample();
        example.createCriteria().andCodeEqualTo(id);
        deviceMaintenanceRecordHeadMapper.deleteByExample(example);
    }
}
