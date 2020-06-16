package com.jinchi.app.service;


import com.jinchi.app.domain.*;
import com.jinchi.app.dto.*;
import com.jinchi.app.mapper.DeviceMaintenanceAccessoryMapper;
import com.jinchi.app.mapper.DeviceMaintenancePlansHeadMapper;
import com.jinchi.app.mapper.DeviceMaintenanceRecordDetailsMapper;
import com.jinchi.app.mapper.DeviceMaintenanceRecordHeadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-08-26 13:42
 * @description:
 **/
@Service
@Transactional
public class DeviceMaintenanceRecordServiceImp implements DeviceMaintenanceRecordService {

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

    @Override
    public List<DeviceMaintenanceGetDto> getRecords(DeviceMaintenancePostDto deviceMaintenancePostDto) {
        List<DeviceMaintenanceGetDto> deviceMaintenanceGetDtos = new ArrayList<>();
        List<DeviceMaintenanceRecordHeadDto> deviceMaintenanceRecordHeadDtos = new ArrayList<>();
        List<BasicInfoDeptDto> basicInfoDepts = userDeviceService.getDeptByAuthId(deviceMaintenancePostDto.getUserId());
        for (int i = 0; i < basicInfoDepts.size(); i++) {
            String sql = "select * from device_maintenance_record_head as a,basic_info_dept as b where (a.dept_code = b.code and a.dept_code = " + basicInfoDepts.get(i).getCode() + " and a.edit_flag = " + deviceMaintenancePostDto.getStatusId() + ")";
            if (deviceMaintenancePostDto.getCondition() != null) {
                sql += "and (a.fixedassets_code like '" + deviceMaintenancePostDto.getCondition() + "%' or a.device_name like '" + deviceMaintenancePostDto.getCondition() + "%' or b.name like '" + deviceMaintenancePostDto.getCondition() + "%')";
            }
            List<DeviceMaintenanceRecordHead> deviceMaintenanceRecordHeads = deviceMaintenanceRecordHeadMapper.selectByCondition(sql);


            for (int j = 0; j < deviceMaintenanceRecordHeads.size(); j++) {
                DeviceMaintenanceRecordHeadDto deviceMaintenanceRecordHeadDto = new DeviceMaintenanceRecordHeadDto();
                deviceMaintenanceRecordHeadDto.setCode(deviceMaintenanceRecordHeads.get(j).getCode());
                deviceMaintenanceRecordHeadDto.setFixedassetsCode("" + deviceMaintenanceRecordHeads.get(j).getFixedassetsCode());
                deviceMaintenanceRecordHeadDto.setDeviceName(deviceMaintenanceRecordHeads.get(j).getDeviceName());
                deviceMaintenanceRecordHeadDto.setDeptCode("" + deviceMaintenanceRecordHeads.get(j).getDeptCode());
                deviceMaintenanceRecordHeadDto.setPlanCode("" + deviceMaintenanceRecordHeads.get(j).getPlanCode());
                deviceMaintenanceRecordHeadDto.setPlanDate(deviceMaintenanceRecordHeads.get(j).getPlanDate());
                deviceMaintenanceRecordHeadDto.setReceiveDate(deviceMaintenanceRecordHeads.get(j).getReceiveDate());
                deviceMaintenanceRecordHeadDto.setFinishDate(deviceMaintenanceRecordHeads.get(j).getFinishiDate());
                String sqlMaintain = "SELECT * FROM `device_maintenance_plans_head` WHERE `code` = '" + deviceMaintenanceRecordHeads.get(j).getPlanCode() + "'";
                deviceMaintenanceRecordHeadDto.setMaintainFrequency(String.valueOf(deviceMaintenancePlansHeadMapper.selectByCondition(sqlMaintain).get(0).getMaintPeriod()));
                deviceMaintenanceRecordHeadDtos.add(deviceMaintenanceRecordHeadDto);
            }
        }
        for (int i = 0; i < deviceMaintenanceRecordHeadDtos.size(); i++) {
            deviceMaintenanceGetDtos.add(new DeviceMaintenanceGetDto());
            deviceMaintenanceGetDtos.get(i).setDeviceMaintenanceRecordHeadDto(deviceMaintenanceRecordHeadDtos.get(i));
            BasicInfoDeptDto basicInfoDeptDto = new BasicInfoDeptDto();
            for (int j = 0; j < basicInfoDepts.size(); j++) {
                if (String.valueOf(basicInfoDepts.get(j).getCode()).equals(deviceMaintenanceRecordHeadDtos.get(i).getDeptCode())) {
                    basicInfoDeptDto = basicInfoDepts.get(j);
                }
            }
            deviceMaintenanceGetDtos.get(i).setBasicInfoDeptDto(basicInfoDeptDto);
        }
        return deviceMaintenanceGetDtos;
    }

    @Override
    public List<DeviceMaintenanceFormatDto> getFormatRecords(List<DeviceMaintenanceGetDto> deviceMaintenanceGetDtos) {

        List<DeviceMaintenanceFormatDto> deviceMaintenanceFormatDtos = new ArrayList<>();
        for (int i = 0; i < deviceMaintenanceGetDtos.size(); i++) {
            DeviceMaintenanceFormatDto deviceMaintenanceFormatDto = new DeviceMaintenanceFormatDto();
            deviceMaintenanceFormatDto.setUnitID("" + deviceMaintenanceGetDtos.get(i).getBasicInfoDeptDto().getCode());
            deviceMaintenanceFormatDto.setName(deviceMaintenanceGetDtos.get(i).getBasicInfoDeptDto().getName());
            deviceMaintenanceFormatDto.setMaintainID("" + deviceMaintenanceGetDtos.get(i).getDeviceMaintenanceRecordHeadDto().getCode());
            deviceMaintenanceFormatDto.setFixedassetsCode(deviceMaintenanceGetDtos.get(i).getDeviceMaintenanceRecordHeadDto().getFixedassetsCode());
            deviceMaintenanceFormatDto.setDeviceName(deviceMaintenanceGetDtos.get(i).getDeviceMaintenanceRecordHeadDto().getDeviceName());
            deviceMaintenanceFormatDto.setDeptCode(deviceMaintenanceGetDtos.get(i).getDeviceMaintenanceRecordHeadDto().getDeptCode());
            deviceMaintenanceFormatDto.setPlanDate(deviceMaintenanceGetDtos.get(i).getDeviceMaintenanceRecordHeadDto().getPlanDate());
            deviceMaintenanceFormatDto.setPlanCode(deviceMaintenanceGetDtos.get(i).getDeviceMaintenanceRecordHeadDto().getPlanCode());
            deviceMaintenanceFormatDto.setReceiveDate(deviceMaintenanceGetDtos.get(i).getDeviceMaintenanceRecordHeadDto().getReceiveDate());
            deviceMaintenanceFormatDto.setFinishDate(deviceMaintenanceGetDtos.get(i).getDeviceMaintenanceRecordHeadDto().getFinishDate());
            deviceMaintenanceFormatDto.setMaintainFrequency(deviceMaintenanceGetDtos.get(i).getDeviceMaintenanceRecordHeadDto().getMaintainFrequency());
            deviceMaintenanceFormatDtos.add(deviceMaintenanceFormatDto);
        }
        return deviceMaintenanceFormatDtos;
    }

    @Override
    public List<DeviceMaintenanceFormatDto> getByIdStatusCondition(DeviceMaintenancePostDto deviceMaintenancePostDto) {
        int size;
        int page;
        if (deviceMaintenancePostDto.getSize() != null && deviceMaintenancePostDto.getPage() != null) {
            size = deviceMaintenancePostDto.getSize();
            page = deviceMaintenancePostDto.getPage();
        } else {
            size = 5;
            page = 1;
        }
        List<DeviceMaintenanceGetDto> deviceMaintenanceGetDtos = getRecords(deviceMaintenancePostDto);
        Page<DeviceMaintenanceFormatDto> pageInfo = new Page<>(size, page, getFormatRecords(deviceMaintenanceGetDtos));
        return pageInfo.getList();
    }

    @Override
    public DeviceMaintenanceDetailGetDto getRecordDetail(DeviceMaintenanceDetailPostDto deviceMaintenanceDetailPostDto) {
        Long id = Long.parseLong(deviceMaintenanceDetailPostDto.getId());
        DeviceMaintenanceDetailGetDto deviceMaintenanceDetailGetDto = new DeviceMaintenanceDetailGetDto();
        DeviceMaintenanceRecordHeadExample example = new DeviceMaintenanceRecordHeadExample();
        example.createCriteria().andCodeEqualTo(id);
        DeviceMaintenanceRecordHead deviceMaintenanceRecordHead = deviceMaintenanceRecordHeadMapper.selectByExample(example).get(0);
        DeviceMaintenanceRecordDetailHeadDto deviceMaintenanceRecordDetailHeadDto = new DeviceMaintenanceRecordDetailHeadDto();
        deviceMaintenanceRecordDetailHeadDto.setHeadId(deviceMaintenanceRecordHead.getCode());
        deviceMaintenanceRecordDetailHeadDto.setPlanCode(deviceMaintenanceRecordHead.getPlanCode());
        deviceMaintenanceRecordDetailHeadDto.setAbnormalcontent(deviceMaintenanceRecordHead.getAbnormalcontent());
        deviceMaintenanceRecordDetailHeadDto.setDeptCode(deviceMaintenanceRecordHead.getDeptCode());
        deviceMaintenanceRecordDetailHeadDto.setDeviceCode(deviceMaintenanceRecordHead.getDeviceCode());
        deviceMaintenanceRecordDetailHeadDto.setDeviceName(deviceMaintenanceRecordHead.getDeviceName());
        deviceMaintenanceRecordDetailHeadDto.setEditFlag(deviceMaintenanceRecordHead.getEditFlag());
        deviceMaintenanceRecordDetailHeadDto.setMaintPeople(deviceMaintenanceRecordHead.getMaintPeople());
        deviceMaintenanceRecordDetailHeadDto.setPlanDate(deviceMaintenanceRecordHead.getPlanDate());
        deviceMaintenanceRecordDetailHeadDto.setReceiveDate(deviceMaintenanceRecordHead.getReceiveDate());
        deviceMaintenanceRecordDetailHeadDto.setFinishiDate(deviceMaintenanceRecordHead.getFinishiDate());
        deviceMaintenanceRecordDetailHeadDto.setFixedassetsCode(deviceMaintenanceRecordHead.getFixedassetsCode());
        deviceMaintenanceDetailGetDto.setHead(deviceMaintenanceRecordDetailHeadDto);
        DeviceMaintenanceRecordDetailsExample example1 = new DeviceMaintenanceRecordDetailsExample();
        example1.createCriteria().andPlanCodeEqualTo(id);
        deviceMaintenanceDetailGetDto.setDetails(deviceMaintenanceRecordDetailsMapper.selectByExample(example1));
        DeviceMaintenanceAccessoryExample example2 = new DeviceMaintenanceAccessoryExample();
        example2.createCriteria().andPlanCodeEqualTo(id);
        List<DeviceMaintenanceAccessory> deviceMaintenanceAccessories = deviceMaintenanceAccessoryMapper.selectByExample(example2);
        List<DeviceMaintenanceAccessoryDto> deviceMaintenanceAccessoryDtos = new ArrayList<>();
        for (DeviceMaintenanceAccessory d : deviceMaintenanceAccessories) {
            DeviceMaintenanceAccessoryDto dto = new DeviceMaintenanceAccessoryDto();
            dto.setCode("" + d.getCode());
            if (d.getCounts() != null) {
                dto.setCounts("" + d.getCounts());
            } else {
                dto.setCounts("");
            }
            if (d.getName() != null) {
                dto.setName(d.getName());
            } else {
                dto.setName("");
            }
            if (d.getSpecification() != null) {
                dto.setSpecification(d.getSpecification());
            } else {
                dto.setSpecification("");
            }
            dto.setPlanCode("" + d.getPlanCode());
            if (d.getUnits() != null) {
                if (d.getUnits() == 0) {
                    dto.setUnits("个");
                }
                if (d.getUnits() == 1) {
                    dto.setUnits("台");
                }
                if (d.getUnits() == 2) {
                    dto.setUnits("套");
                }
                if (d.getUnits() == 3) {
                    dto.setUnits("米");
                }
            } else {
                dto.setUnits("");
            }
            deviceMaintenanceAccessoryDtos.add(dto);
        }
        deviceMaintenanceDetailGetDto.setAccessorys(deviceMaintenanceAccessoryDtos);
        return deviceMaintenanceDetailGetDto;
    }

    @Override
    @Transactional
    public DeviceMaintenanceRecordDetailUpdateDto updateById(DeviceMaintenanceRecordDetailUpdateDto updateDto) {
        DeviceMaintenanceRecordHeadUpdateDto hud = updateDto.getHead();
        List<DeviceMaintenanceRecordDetails> deviceMaintenanceRecordDetails = updateDto.getDetails();
        List<DeviceMaintenanceAccessoryDto> deviceMaintenanceAccessoryDto = updateDto.getAccessory();
        // 如果是从待保养到已保养不变
        // 从已保养到已完成 要重新更改数据，判断的条件是editflag == 2
        long headCode = hud.getHeadId();
        DeviceMaintenanceRecordHeadExample deviceMaintenanceRecordHeadExample = new DeviceMaintenanceRecordHeadExample();
        deviceMaintenanceRecordHeadExample.createCriteria().andCodeEqualTo(headCode);
        DeviceMaintenanceRecordHead deviceMaintenanceRecordHead =
                deviceMaintenanceRecordHeadMapper.selectByExample(deviceMaintenanceRecordHeadExample).get(0);
        int flag = hud.getFlag();
        if (deviceMaintenanceRecordHead.getEditFlag().equals(2)) {
            DeviceMaintenanceRecordHead head = new DeviceMaintenanceRecordHead();
            head.setAbnormalcontent(hud.getNote());
            deviceMaintenanceRecordHeadMapper.updateByExampleSelective(head, deviceMaintenanceRecordHeadExample);
            if (deviceMaintenanceAccessoryDto != null) {
                DeviceMaintenanceAccessoryExample deviceMaintenanceAccessoryExample = new DeviceMaintenanceAccessoryExample();
                deviceMaintenanceAccessoryExample.createCriteria().andPlanCodeEqualTo(headCode);
                List<DeviceMaintenanceAccessory> deviceMaintenanceAccessories =
                        deviceMaintenanceAccessoryMapper.selectByExample(deviceMaintenanceAccessoryExample);
                if (deviceMaintenanceAccessories.size() > 0) {
                    System.out.println("删除原始配件信息:   " + deviceMaintenanceAccessoryMapper.deleteByExample(deviceMaintenanceAccessoryExample));
                }
                for (DeviceMaintenanceAccessoryDto dto : deviceMaintenanceAccessoryDto) {
                    DeviceMaintenanceAccessory accessory = new DeviceMaintenanceAccessory();
                    if (!dto.getUnits().equals("")) {
                        if (dto.getUnits().equals("个")) {
                            accessory.setUnits(Byte.parseByte("0"));
                        }
                        if (dto.getUnits().equals("台")) {
                            accessory.setUnits(Byte.parseByte("1"));
                        }
                        if (dto.getUnits().equals("套")) {
                            accessory.setUnits(Byte.parseByte("2"));
                        }
                        if (dto.getUnits().equals("米")) {
                            accessory.setUnits(Byte.parseByte("3"));
                        }
                    }
                    if (!dto.getName().equals("")) {
                        accessory.setName(dto.getName());
                    }
                    accessory.setPlanCode(Long.parseLong(dto.getPlanCode()));
                    if (!dto.getCounts().equals("")) {
                        accessory.setCounts(Integer.parseInt(dto.getCounts()));
                    }
                    if (!dto.getSpecification().equals("")) {
                        accessory.setSpecification(dto.getSpecification());
                    }
                    deviceMaintenanceAccessoryMapper.insert(accessory);
                }
            }

            if (deviceMaintenanceRecordDetails != null) {
                for (DeviceMaintenanceRecordDetails d : deviceMaintenanceRecordDetails) {
                    long code = d.getCode();
                    DeviceMaintenanceRecordDetailsExample deviceMaintenanceRecordDetailsExample = new DeviceMaintenanceRecordDetailsExample();
                    deviceMaintenanceRecordDetailsExample.createCriteria().andCodeEqualTo(code);
                    deviceMaintenanceRecordDetailsMapper.updateByExampleSelective(d, deviceMaintenanceRecordDetailsExample);
                }
            }

        }
        if (flag == 1) {
            if (deviceMaintenanceRecordHead.getEditFlag().equals(2) && deviceMaintenanceRecordDetails != null) {
                DeviceMaintenancePlansHeadExample deviceMaintenancePlansHeadExample = new DeviceMaintenancePlansHeadExample();
                deviceMaintenancePlansHeadExample.createCriteria().andCodeEqualTo(deviceMaintenanceRecordHead.getPlanCode());
                List<DeviceMaintenancePlansHead> deviceMaintenancePlansHead = deviceMaintenancePlansHeadMapper.selectByExample(deviceMaintenancePlansHeadExample);
                for (DeviceMaintenancePlansHead d : deviceMaintenancePlansHead) {
                    long period = d.getMaintPeriod();
                    Date temp = d.getPlanDate();
                    long time = temp.getTime();
                    period = (period * 24 * 60 * 60 * 1000);
                    time += period;
                    Date newDate = new Date(time);
                    d.setPlanDate(newDate);
                    //再加一次
                    time += period;
                    Date next_date = new Date(time);
                    d.setNextDate(next_date);
                    deviceMaintenancePlansHeadMapper.updateByExampleSelective(d, deviceMaintenancePlansHeadExample);
                    //如果这个是2  换为3
                    deviceMaintenanceRecordHead.setEditFlag(3);
                    //添加完成时间
                    deviceMaintenanceRecordHead.setFinishiDate(new Date());
                    deviceMaintenanceRecordHeadMapper.updateByExampleSelective(deviceMaintenanceRecordHead, deviceMaintenanceRecordHeadExample);
                    //eff-flag 为零的时候   新增一条记录
                    if (d.getEffFlag() == 0) {
                        DeviceMaintenanceRecordHead insert = deviceMaintenanceRecordHead;
                        insert.setEditFlag(1);// flag 设置为一
                        insert.setCode(null);
                        insert.setFinishiDate(null);
                        insert.setReceiveDate(null);
                        insert.setAbnormalcontent(null);
                        insert.setPlanDate(newDate);
                        deviceMaintenanceRecordHeadMapper.insertSelective(insert);
                        DeviceMaintenanceRecordDetailsExample example = new DeviceMaintenanceRecordDetailsExample();
                        example.createCriteria().andPlanCodeEqualTo(headCode);
                        List<DeviceMaintenanceRecordDetails> details = deviceMaintenanceRecordDetailsMapper.selectByExample(example);
                        for (DeviceMaintenanceRecordDetails d1 : details) {
                            DeviceMaintenanceRecordDetails d2 = new DeviceMaintenanceRecordDetails();
                            d2.setPlanCode(insert.getCode());
                            d2.setMaintenanceItems(d1.getMaintenanceItems());
                            d2.setMaintenanceContent(d1.getMaintenanceContent());
                            d2.setOptType(d1.getOptType());
                            d2.setItemsCode(d1.getItemsCode());
                            deviceMaintenanceRecordDetailsMapper.insertSelective(d2);
                        }
                    }
                }
            }
        }

        return updateDto;
    }

    @Override
    public String receiveRecordById(IdDto idDto) {
        Long id = idDto.getId();
        DeviceMaintenanceRecordHeadExample deviceMaintenanceRecordHeadExample = new DeviceMaintenanceRecordHeadExample();
        deviceMaintenanceRecordHeadExample.createCriteria().andCodeEqualTo(id);
        DeviceMaintenanceRecordHead deviceMaintenanceRecordHead = new DeviceMaintenanceRecordHead();
        deviceMaintenanceRecordHead.setEditFlag(2);
        deviceMaintenanceRecordHead.setMaintPeople(idDto.getUserId());
        deviceMaintenanceRecordHead.setReceiveDate(new Date());
        if (deviceMaintenanceRecordHeadMapper.updateByExampleSelective(deviceMaintenanceRecordHead, deviceMaintenanceRecordHeadExample) > 0) {
            return "接单成功";
        } else {
            return "接单失败！请再次接单";
        }
    }
}
