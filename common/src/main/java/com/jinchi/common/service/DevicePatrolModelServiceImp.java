package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.DevicePatrolModelDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.DevicePatrolModelsHeadMapper;
import com.jinchi.common.mapper.DevicePatrolModelsItemDetailsMapper;
import com.jinchi.common.mapper.DevicePatrolModelsLocationDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DevicePatrolModelServiceImp implements DevicePatrolModelService {
    @Autowired
    DevicePatrolModelsHeadMapper devicePatrolModelsHeadMapper;
    @Autowired
    DevicePatrolModelsItemDetailsMapper devicePatrolModelsItemDetailsMapper;
    @Autowired
    DevicePatrolModelsLocationDetailsMapper devicePatrolModelsLocationDetailsMapper;
    @Autowired
    AuthUserService authUserService;

    @Override
    @Transactional
    public DevicePatrolModelDTO add(DevicePatrolModelDTO devicePatrolModelDTO) {
        devicePatrolModelsHeadMapper.insertSelective(devicePatrolModelDTO.getDevicePatrolModelsHead());
        Long code = devicePatrolModelDTO.getDevicePatrolModelsHead().getCode();
        for(int i=0;i<devicePatrolModelDTO.getDevicePatrolModelsItemDetailsList().size();i++){
            devicePatrolModelDTO.getDevicePatrolModelsItemDetailsList().get(i).setModelCode(code);
            devicePatrolModelsItemDetailsMapper.insertSelective(devicePatrolModelDTO.getDevicePatrolModelsItemDetailsList().get(i));
        }
        for(int i=0;i<devicePatrolModelDTO.getDevicePatrolModelsLocationDetails().size();i++){
            devicePatrolModelDTO.getDevicePatrolModelsLocationDetails().get(i).setModelCode(code);
            devicePatrolModelsLocationDetailsMapper.insertSelective(devicePatrolModelDTO.getDevicePatrolModelsLocationDetails().get(i));
        }
        return devicePatrolModelDTO;
    }

    @Override
    @Transactional
    public DevicePatrolModelDTO update(DevicePatrolModelDTO devicePatrolModelDTO) {
        Long modelId = devicePatrolModelDTO.getDevicePatrolModelsHead().getCode();
        DevicePatrolModelsHeadExample example = new DevicePatrolModelsHeadExample();
        example.createCriteria().andCodeEqualTo(modelId);
        devicePatrolModelsHeadMapper.updateByExampleSelective(devicePatrolModelDTO.getDevicePatrolModelsHead(),example);

        DevicePatrolModelsItemDetailsExample example1 = new DevicePatrolModelsItemDetailsExample();
        example1.createCriteria().andModelCodeEqualTo(modelId);
        devicePatrolModelsItemDetailsMapper.deleteByExample(example1);

        DevicePatrolModelsLocationDetailsExample example2 = new DevicePatrolModelsLocationDetailsExample();
        example2.createCriteria().andModelCodeEqualTo(modelId);
        devicePatrolModelsLocationDetailsMapper.deleteByExample(example2);

        for(int i=0;i<devicePatrolModelDTO.getDevicePatrolModelsItemDetailsList().size();i++){
            devicePatrolModelDTO.getDevicePatrolModelsItemDetailsList().get(i).setModelCode(modelId);
            devicePatrolModelsItemDetailsMapper.insertSelective(devicePatrolModelDTO.getDevicePatrolModelsItemDetailsList().get(i));
        }

        for(int i=0;i<devicePatrolModelDTO.getDevicePatrolModelsLocationDetails().size();i++){
            devicePatrolModelDTO.getDevicePatrolModelsLocationDetails().get(i).setModelCode(modelId);
            devicePatrolModelsLocationDetailsMapper.insertSelective(devicePatrolModelDTO.getDevicePatrolModelsLocationDetails().get(i));
        }

        return devicePatrolModelDTO;
    }

    @Override
    public void deleteById(Long id) {
        //删除items和locations
        DevicePatrolModelsItemDetailsExample example1 = new DevicePatrolModelsItemDetailsExample();
        example1.createCriteria().andModelCodeEqualTo(id);
        devicePatrolModelsItemDetailsMapper.deleteByExample(example1);

        DevicePatrolModelsLocationDetailsExample example2 = new DevicePatrolModelsLocationDetailsExample();
        example2.createCriteria().andModelCodeEqualTo(id);
        devicePatrolModelsLocationDetailsMapper.deleteByExample(example2);

        DevicePatrolModelsHeadExample example = new DevicePatrolModelsHeadExample();
        example.createCriteria().andCodeEqualTo(id);
        devicePatrolModelsHeadMapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public void deleteByIds(Long[] ids) {
        for(Long id:ids)
            deleteById(id);
    }

    @Override
    public DevicePatrolModelDTO detail(Long id) {
        DevicePatrolModelDTO ans = new DevicePatrolModelDTO();
        DevicePatrolModelsHeadExample example = new DevicePatrolModelsHeadExample();
        example.createCriteria().andCodeEqualTo(id);
        DevicePatrolModelsHead devicePatrolModelsHead = devicePatrolModelsHeadMapper.selectByExample(example).get(0);
        DevicePatrolModelsItemDetailsExample example1 = new DevicePatrolModelsItemDetailsExample();
        example1.createCriteria().andModelCodeEqualTo(id);
        List<DevicePatrolModelsItemDetails> devicePatrolModelsItemDetails = devicePatrolModelsItemDetailsMapper.selectByExample(example1);
        DevicePatrolModelsLocationDetailsExample example2 = new DevicePatrolModelsLocationDetailsExample();
        example2.createCriteria().andModelCodeEqualTo(id);
        List<DevicePatrolModelsLocationDetails> devicePatrolModelsLocationDetails = devicePatrolModelsLocationDetailsMapper.selectByExample(example2);
        ans.setDevicePatrolModelsHead(devicePatrolModelsHead);
        ans.setDevicePatrolModelsItemDetailsList(devicePatrolModelsItemDetails);
        ans.setDevicePatrolModelsLocationDetails(devicePatrolModelsLocationDetails);
        ans.setSetPeople(authUserService.findById(devicePatrolModelsHead.getSetPeople()).getName());
        return ans;
    }

    @Override
    public List<DevicePatrolModelDTO> getAll(Integer deptCode, Integer status, String condition) {
        List<DevicePatrolModelDTO> ans = new ArrayList<>();
        String sql = "select * from device_patrol_models_head where (dept_code = " + deptCode + ")";
        if(status!=-1){
            sql += " and (check_type = " + status + ")";
        }
        if(!condition.equals("")){
            sql += " and (patrol_name like '" + condition + "%')";
        }
        List<DevicePatrolModelsHead> devicePatrolModelsHeads = devicePatrolModelsHeadMapper.selectByCondition(sql);
        for(int i=0;i<devicePatrolModelsHeads.size();i++){
            DevicePatrolModelDTO devicePatrolModelDTO = new DevicePatrolModelDTO();
            devicePatrolModelDTO.setDevicePatrolModelsHead(devicePatrolModelsHeads.get(i));
            devicePatrolModelDTO.setSetPeople(devicePatrolModelsHeads.get(i).getSetPeople()==null?null:authUserService.findById(devicePatrolModelsHeads.get(i).getSetPeople()).getName());
            ans.add(devicePatrolModelDTO);
        }
        return ans;
    }

    @Override
    public Page page(Integer deptCode, Integer status, String condition, Integer page, Integer size) {
        Page pageInfo = new Page(getAll(deptCode,status,condition),page,size);
        return pageInfo;
    }
}
