package com.jinchi.common.service;


import com.jinchi.common.domain.BasicInfoDept;
import com.jinchi.common.domain.BasicInfoDeptExample;
import com.jinchi.common.domain.BasicInfoUserDeviceDeptMap;
import com.jinchi.common.domain.BasicInfoUserDeviceDeptMapExample;
import com.jinchi.common.mapper.BasicInfoDeptMapper;
import com.jinchi.common.mapper.BasicInfoUserDeviceDeptMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDeviceServiceImp implements UserDeviceService {

    @Autowired
    BasicInfoUserDeviceDeptMapMapper basicInfoUserDeviceDeptMapMapper;
    @Autowired
    BasicInfoDeptMapper basicInfoDeptMapper;

    @Override
    public List<BasicInfoDept> getDeptByAuthId(int id){
        BasicInfoUserDeviceDeptMapExample example = new BasicInfoUserDeviceDeptMapExample();
        example.createCriteria().andAuthCodeEqualTo(id);
        List<BasicInfoUserDeviceDeptMap> basicInfoUserDeviceDeptMaps = basicInfoUserDeviceDeptMapMapper.selectByExample(example);
        List<BasicInfoDept> ans = new ArrayList<>();
        for(int i=0;i<basicInfoUserDeviceDeptMaps.size();i++){
            BasicInfoDeptExample example1 = new BasicInfoDeptExample();
            example1.createCriteria().andCodeEqualTo(basicInfoUserDeviceDeptMaps.get(i).getDeptCode());
            ans.add(basicInfoDeptMapper.selectByExample(example1).get(0));
        }
         return  ans;
    }



}
