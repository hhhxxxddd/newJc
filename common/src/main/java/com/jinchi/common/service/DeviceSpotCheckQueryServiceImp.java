package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.DeviceSpotCheckRecordHeadDTO;
import com.jinchi.common.dto.DeviceSpotcheckModelsHeadDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.DeviceSpotcheckModelsDetailsMapper;
import com.jinchi.common.mapper.DeviceSpotcheckModelsHeadMapper;
import com.jinchi.common.mapper.DeviceSpotcheckRecordDetailsMapper;
import com.jinchi.common.mapper.DeviceSpotcheckRecordHeadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceSpotCheckQueryServiceImp implements DeviceSpotCheckQueryService {
    @Autowired
    DeviceSpotcheckRecordHeadMapper deviceSpotcheckRecordHeadMapper;
    @Autowired
    DeviceSpotcheckRecordDetailsMapper deviceSpotcheckRecordDetailsMapper;
    @Autowired
    AuthUserService authUserService;

    @Override
    public Page deviceDetailPage(Long id,Integer page,Integer size) {
        DeviceSpotcheckRecordHeadExample example = new DeviceSpotcheckRecordHeadExample();
        example.createCriteria().andDeviceCodeEqualTo(id);
        List<DeviceSpotcheckRecordHead> deviceSpotcheckRecordHeads = deviceSpotcheckRecordHeadMapper.selectByExample(example);
        List<DeviceSpotCheckRecordHeadDTO> deviceSpotCheckRecordHeadDTOS = new ArrayList<>();
        for(int i=0;i<deviceSpotcheckRecordHeads.size();i++){
            DeviceSpotcheckRecordHead temp = deviceSpotcheckRecordHeads.get(i);
            deviceSpotCheckRecordHeadDTOS.add(new DeviceSpotCheckRecordHeadDTO());
            deviceSpotCheckRecordHeadDTOS.get(i).setDeviceSpotcheckRecordHead(temp);
            AuthUserDTO spotPeople = authUserService.findById(temp.getSpotcheckPeople());
            AuthUserDTO confimPeople = authUserService.findById(temp.getConfirmPeople());
            deviceSpotCheckRecordHeadDTOS.get(i).setSpotPeople(spotPeople==null?"":spotPeople.getName());
            deviceSpotCheckRecordHeadDTOS.get(i).setConfirmPeople(confimPeople==null?"":confimPeople.getName());
        }
        Page pageInfo = new Page(deviceSpotCheckRecordHeadDTOS,page,size);
        return pageInfo;
    }
    @Override
    public DeviceSpotCheckRecordHeadDTO deviceRecordDetail(Long id) {
        DeviceSpotCheckRecordHeadDTO ans = new DeviceSpotCheckRecordHeadDTO();
        DeviceSpotcheckRecordHeadExample example = new DeviceSpotcheckRecordHeadExample();
        example.createCriteria().andCodeEqualTo(id);
        DeviceSpotcheckRecordHead deviceSpotCheckRecordHead = deviceSpotcheckRecordHeadMapper.selectByExample(example).get(0);
        ans.setDeviceSpotcheckRecordHead(deviceSpotCheckRecordHead);
        AuthUserDTO authUserDTO = authUserService.findById(deviceSpotCheckRecordHead.getSpotcheckPeople());
        ans.setSpotPeople(authUserDTO == null ? "" : authUserDTO.getName());
        AuthUserDTO authUserDTO1 = authUserService.findById(deviceSpotCheckRecordHead.getConfirmPeople());
        ans.setConfirmPeople(authUserDTO1 == null ? "" : authUserDTO1.getName());
        DeviceSpotcheckRecordDetailsExample example1 = new DeviceSpotcheckRecordDetailsExample();
        example1.createCriteria().andRecordCodeEqualTo(id);
        List<DeviceSpotcheckRecordDetails> deviceSpotcheckRecordDetails = deviceSpotcheckRecordDetailsMapper.selectByExample(example1);
        ans.setDeviceSpotcheckRecordDetailsList(deviceSpotcheckRecordDetails);
        return ans;
    }
}
