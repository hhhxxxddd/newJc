package com.jinchi.common.service;


import com.jinchi.common.controller.BasicInfoLocationController;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.DeviceSpotcheckModelsHeadDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.Result;
import com.jinchi.common.mapper.BasicInfoDeptMapper;
import com.jinchi.common.mapper.BasicInfoLocationMapper;
import com.jinchi.common.mapper.DevicePatrolModelsLocationDetailsMapper;
import com.jinchi.common.mapper.DevicePatrolPlanRecordLocationDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.RemoteEndpoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
@Transactional
public class BasicInfoLocationServiceImp implements BasicInfoLocationService{

    @Autowired
    BasicInfoLocationMapper basicInfoLocationMapper;
    @Autowired
    BasicInfoDeptMapper basicInfoDeptMapper;
    @Autowired
    DevicePatrolModelsLocationDetailsMapper devicePatrolModelsLocationDetailsMapper;
    @Autowired
    DevicePatrolPlanRecordLocationDetailsMapper devicePatrolPlanRecordLocationDetailsMapper;


    @Override
    public void deleteById(Integer id){
        DevicePatrolModelsLocationDetailsExample devicePatrolModelsLocationDetailsExample = new DevicePatrolModelsLocationDetailsExample();
        devicePatrolModelsLocationDetailsExample.createCriteria().andLocationCodeEqualTo(id);
        devicePatrolModelsLocationDetailsMapper.deleteByExample(devicePatrolModelsLocationDetailsExample);

        DevicePatrolPlanRecordLocationDetailsExample devicePatrolPlanRecordLocationDetailsExample = new DevicePatrolPlanRecordLocationDetailsExample();
        devicePatrolPlanRecordLocationDetailsExample.createCriteria().andLocationCodeEqualTo(id);
        devicePatrolPlanRecordLocationDetailsMapper.deleteByExample(devicePatrolPlanRecordLocationDetailsExample);


        BasicInfoLocationExample example = new BasicInfoLocationExample();
        example.createCriteria().andCodeEqualTo(id);
        basicInfoLocationMapper.deleteByExample(example);

    }

    @Override
    public void deleteByIds(Integer[] ids){
        for (Integer id : ids) {
            deleteById(id);
        }
    }

    @Override
    @Transactional
    public BasicInfoLocation add(BasicInfoLocation basicInfoLocation) {
        basicInfoLocationMapper.insertSelective(basicInfoLocation);
        return basicInfoLocation;
    }



    @Override
    @Transactional
    public BasicInfoLocation updataById(BasicInfoLocation basicInfoLocation) {
        BasicInfoLocationExample example = new BasicInfoLocationExample();
        example.createCriteria().andCodeEqualTo(basicInfoLocation.getCode());
        basicInfoLocationMapper.updateByExample(basicInfoLocation,example);
        return basicInfoLocation;
    }


    @Override
    public List<BasicInfoLocation> getAllByDeptName(Integer deptId, Integer page, Integer size) {

        BasicInfoDeptExample temp = new BasicInfoDeptExample();
        temp.createCriteria().andCodeEqualTo(deptId);
        List<BasicInfoDept> temps = basicInfoDeptMapper.selectByExample(temp);

        List<BasicInfoLocation> basicInfoLocations = new ArrayList<>();
        BasicInfoLocationExample example = new BasicInfoLocationExample();
        example.createCriteria().andDeptCodeEqualTo(temps.get(0).getCode());
        basicInfoLocations = basicInfoLocationMapper.selectByExample(example);
        return basicInfoLocations;
    }

    @Override
    public Page getByPage(Integer deptId, Integer page, Integer size) {
        Page<BasicInfoLocation> pageInfo  = new Page<>(getAllByDeptName(deptId,page,size),page,size);
        return pageInfo;
    }

    @Override
    public List<BasicInfoLocation> getLocationById (Integer id){
        BasicInfoLocationExample example = new BasicInfoLocationExample();
        example.createCriteria().andDeptCodeEqualTo(id);
        List<BasicInfoLocation> res = basicInfoLocationMapper.selectByExample(example);
        return  res;
    }


}
