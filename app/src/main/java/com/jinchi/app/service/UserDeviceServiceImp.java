package com.jinchi.app.service;


import com.jinchi.app.domain.BasicInfoDept;
import com.jinchi.app.domain.BasicInfoDeptExample;
import com.jinchi.app.domain.BasicInfoUserDeviceDeptMap;
import com.jinchi.app.domain.BasicInfoUserDeviceDeptMapExample;
import com.jinchi.app.dto.BasicInfoDeptDto;
import com.jinchi.app.mapper.BasicInfoDeptMapper;
import com.jinchi.app.mapper.BasicInfoUserDeviceDeptMapMapper;
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
    public List<BasicInfoDeptDto> getDeptByAuthId(int id){
        BasicInfoUserDeviceDeptMapExample example = new BasicInfoUserDeviceDeptMapExample();
        example.createCriteria().andAuthCodeEqualTo(id);
        List<BasicInfoUserDeviceDeptMap> basicInfoUserDeviceDeptMaps = basicInfoUserDeviceDeptMapMapper.selectByExample(example);
        List<BasicInfoDeptDto> ans = new ArrayList<>();
        for(int i=0;i<basicInfoUserDeviceDeptMaps.size();i++){
            BasicInfoDeptExample example1 = new BasicInfoDeptExample();
            example1.createCriteria().andCodeEqualTo(basicInfoUserDeviceDeptMaps.get(i).getDeptCode());
            BasicInfoDept basicInfoDept = basicInfoDeptMapper.selectByExample(example1).get(0);
            BasicInfoDeptDto basicInfoDeptDto = new BasicInfoDeptDto();
            basicInfoDeptDto.setCode(basicInfoDept.getCode());
            basicInfoDeptDto.setName(basicInfoDept.getName());
            ans.add(basicInfoDeptDto);
        }
         return ans;
    }
}
