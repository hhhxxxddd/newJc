package com.jinchi.app.service;

import com.jinchi.app.domain.*;
import com.jinchi.app.dto.*;
import com.jinchi.app.mapper.BasicInfoLocationMapper;
import com.jinchi.app.mapper.DevicePatrolPlanRecordHeadMapper;
import com.jinchi.app.mapper.DevicePatrolPlanRecordItemDetailsMapper;
import com.jinchi.app.mapper.DevicePatrolPlanRecordLocationDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DevicePatrolServiceImp implements DevicePatrolService{
    @Autowired
    DeptService deptService;
    @Autowired
    UserDeviceService userDeviceService;
    @Autowired
    BasicInfoLocationMapper basicInfoLocationMapper;
    @Autowired
    DevicePatrolPlanRecordHeadMapper devicePatrolPlanRecordHeadMapper;
    @Autowired
    DevicePatrolPlanRecordItemDetailsMapper devicePatrolPlanRecordItemDetailsMapper;
    @Autowired
    DevicePatrolPlanRecordLocationDetailsMapper devicePatrolPlanRecordLocationDetailsMapper;

    @Override
    public List<PatrolDTO> page(PatrolPostDTO patrolPostDTO) {
        Integer userId = patrolPostDTO.getId().intValue();
        int size = patrolPostDTO.getSize()==null?5:patrolPostDTO.getSize();
        int page = patrolPostDTO.getPage()==null?1:patrolPostDTO.getPage();
        List<PatrolDTO> ans = new ArrayList<>();

        List<BasicInfoDeptDto> basicInfoDeptDtos = userDeviceService.getDeptByAuthId(userId);
        List<Integer> bids = new ArrayList<>();
        basicInfoDeptDtos.forEach(e->bids.add(e.getCode()));
        for(int i=0;i<bids.size();i++){
            String sql = "select * from device_patrol_plan_record_head where (dept_code = " + bids.get(i) + " and edit_flag = " + patrolPostDTO.getStatus() + ")";
            if(patrolPostDTO.getCondition()!=null){
                sql += " and (code like '"+patrolPostDTO.getCondition()+"%' or plan_name like '"+patrolPostDTO.getCondition()+"%')";
            }
            List<DevicePatrolPlanRecordHead> devicePatrolPlanRecordHeads = devicePatrolPlanRecordHeadMapper.selectByCondition(sql);
            for(int l=0;l<devicePatrolPlanRecordHeads.size();l++){
                DevicePatrolPlanRecordHead temp = devicePatrolPlanRecordHeads.get(l);
                PatrolDTO patrolDTO = new PatrolDTO();
                patrolDTO.setPlanId(temp.getCode());
                patrolDTO.setPlanName(temp.getPlanName());
                patrolDTO.setCheckType(temp.getCheckType()==false?0:1);
                patrolDTO.setPlanDate(temp.getPlanTime());
                patrolDTO.setEndDate(temp.getTabulatedate());
                patrolDTO.setStatus(temp.getEditFlag());
                patrolDTO.setDeptName(deptService.getById(temp.getDeptCode()).getName());
                if(patrolPostDTO.getStatus() == 2 || patrolPostDTO.getStatus() == 3){
                    patrolDTO.setReveiveDate(temp.getReceiveTime());
                    if(patrolPostDTO.getStatus() == 3) {
                        patrolDTO.setFinishDate(temp.getFinishTime());
                    }
                }
                ans.add(patrolDTO);
            }
        }
        Page pageInfo = new Page(size,page,ans);
        return pageInfo.getList();
    }

    @Override
    public PatrolDTO detail(Long id) {
        PatrolDTO ans = new PatrolDTO();

        DevicePatrolPlanRecordHeadExample example = new DevicePatrolPlanRecordHeadExample();
        example.createCriteria().andCodeEqualTo(id);
        DevicePatrolPlanRecordHead recordHead = devicePatrolPlanRecordHeadMapper.selectByExample(example).get(0);
        Integer status = recordHead.getEditFlag();

        DevicePatrolPlanRecordItemDetailsExample example1 = new DevicePatrolPlanRecordItemDetailsExample();
        example1.createCriteria().andPlanRecordCodeEqualTo(id);
        List<DevicePatrolPlanRecordItemDetails> items = devicePatrolPlanRecordItemDetailsMapper.selectByExample(example1);

        DevicePatrolPlanRecordLocationDetailsExample example2 = new DevicePatrolPlanRecordLocationDetailsExample();
        example2.createCriteria().andPlanRecordCodeEqualTo(id);
        List<DevicePatrolPlanRecordLocationDetails> locations = devicePatrolPlanRecordLocationDetailsMapper.selectByExample(example2);

        ans.setPlanId(recordHead.getCode());
        ans.setCheckType(recordHead.getCheckType()==false?0:1);
        ans.setDeptName(deptService.getById(recordHead.getDeptCode()).getName());
        ans.setPlanDate(recordHead.getPlanTime());
        ans.setEndDate(recordHead.getTabulatedate());
        ans.setPatrolComment(recordHead.getPatrolComment());

        List<PatrolItemsDTO> itemsDTOS = new ArrayList<>();
        for(int i=0;i<items.size();i++){
            DevicePatrolPlanRecordItemDetails itemDetails = items.get(i);
            PatrolItemsDTO temp = new PatrolItemsDTO();
            temp.setPatrolItemsId(itemDetails.getCode());
            temp.setContent(itemDetails.getPatrolContent());
            temp.setItem(itemDetails.getPatrolItem());
            if(status == 2 || status == 3) {
                temp.setMemo(itemDetails.getMainContent());
                temp.setResult(itemDetails.getMainValues());
            }
            itemsDTOS.add(temp);
        }
        ans.setPis(itemsDTOS);

        List<PatrolLocationDTO> locationDTOS = new ArrayList<>();
        for(int i=0;i<locations.size();i++){
            DevicePatrolPlanRecordLocationDetails locationDetails = locations.get(i);
            PatrolLocationDTO temp = new PatrolLocationDTO();
            temp.setLocation(locationDetails.getLocationName());
            temp.setPatrolLocationId(locationDetails.getCode());
            temp.setLocationId(locationDetails.getLocationCode());
            if(status == 2 || status == 3) {
                temp.setReadCardTime(locationDetails.getReadIdcardTime());
            }
            locationDTOS.add(temp);
        }
        if(status == 2 || status == 3){
            ans.setReveiveDate(recordHead.getReceiveTime());
        }
        if(status == 3){
            ans.setFinishDate(recordHead.getFinishTime());
        }
        ans.setPls(locationDTOS);
        return ans;
    }

    @Override
    public void commit(PatrolDTO patrolDTO) {
        int flag = patrolDTO.getFlag();
        Long planId = patrolDTO.getPlanId();
        DevicePatrolPlanRecordHeadExample example = new DevicePatrolPlanRecordHeadExample();
        example.createCriteria().andCodeEqualTo(planId);
        DevicePatrolPlanRecordHead devicePatrolPlanRecordHead = new DevicePatrolPlanRecordHead();
        if(flag == 2) {
            devicePatrolPlanRecordHead.setEditFlag(flag);
            devicePatrolPlanRecordHead.setReceiveTime(new Date());
            devicePatrolPlanRecordHead.setReceivePeople(patrolDTO.getPeople());
            devicePatrolPlanRecordHeadMapper.updateByExampleSelective(devicePatrolPlanRecordHead, example);
        }else{
            List<PatrolItemsDTO> itemsDTOS = patrolDTO.getPis();
            if(itemsDTOS==null){
                itemsDTOS = new ArrayList<>();
            }
            List<PatrolLocationDTO> locationDTOs = patrolDTO.getPls();
            if(locationDTOs==null){
                locationDTOs = new ArrayList<>();
            }
            DevicePatrolPlanRecordItemDetailsExample example1 = new DevicePatrolPlanRecordItemDetailsExample();
            example1.createCriteria().andPlanRecordCodeEqualTo(planId);
            devicePatrolPlanRecordItemDetailsMapper.deleteByExample(example1);

            DevicePatrolPlanRecordLocationDetailsExample example2 = new DevicePatrolPlanRecordLocationDetailsExample();
            example2.createCriteria().andPlanRecordCodeEqualTo(planId);
            devicePatrolPlanRecordLocationDetailsMapper.deleteByExample(example2);

            for(int i=0;i<itemsDTOS.size();i++){
                PatrolItemsDTO temp = itemsDTOS.get(i);
                DevicePatrolPlanRecordItemDetails item = new DevicePatrolPlanRecordItemDetails();
                item.setPlanRecordCode(planId);
                item.setMainContent(temp.getMemo());
                item.setMainValues(temp.getResult());
                item.setPatrolContent(temp.getContent());
                item.setPatrolItem(temp.getItem());
                devicePatrolPlanRecordItemDetailsMapper.insertSelective(item);
            }

            for(int i=0;i<locationDTOs.size();i++){
                PatrolLocationDTO temp = locationDTOs.get(i);
                DevicePatrolPlanRecordLocationDetails location = new DevicePatrolPlanRecordLocationDetails();
                location.setLocationName(temp.getLocation());
                location.setLocationCode(temp.getLocationId());
                location.setPlanRecordCode(planId);
                location.setReadIdcardTime(temp.getReadCardTime());
                devicePatrolPlanRecordLocationDetailsMapper.insertSelective(location);
            }
            devicePatrolPlanRecordHead.setEditFlag(flag==3?3:2);
            devicePatrolPlanRecordHead.setPatrolComment(patrolDTO.getPatrolComment());
            if(flag == 3) {
                devicePatrolPlanRecordHead.setFinishTime(new Date());
                devicePatrolPlanRecordHead.setPatrolPeople(patrolDTO.getPeople());
            }
            devicePatrolPlanRecordHeadMapper.updateByExampleSelective(devicePatrolPlanRecordHead,example);
        }

    }

    @Override
    public PatrolLocationDTO getLocationByIdCard(PatrolPostDTO patrolPostDTO) {
        BasicInfoLocationExample example = new BasicInfoLocationExample();
        example.createCriteria().andIdCodeEqualTo(patrolPostDTO.getCondition()) ;
        List<BasicInfoLocation> basicInfoLocations = basicInfoLocationMapper.selectByExample(example);
        if(basicInfoLocations.size()==0) {
            return null;
        }
        DevicePatrolPlanRecordLocationDetailsExample example1 = new DevicePatrolPlanRecordLocationDetailsExample();
        example1.createCriteria().andPlanRecordCodeEqualTo(patrolPostDTO.getId()).andLocationCodeEqualTo(basicInfoLocations.get(0).getCode());
        List<DevicePatrolPlanRecordLocationDetails> details = devicePatrolPlanRecordLocationDetailsMapper.selectByExample(example1);
        if(details.size()==0){
            return null;
        }
        PatrolLocationDTO ans = new PatrolLocationDTO();
        ans.setPatrolLocationId(details.get(0).getCode());
        ans.setReadCardTime(new Date());
        return ans;
    }
}
