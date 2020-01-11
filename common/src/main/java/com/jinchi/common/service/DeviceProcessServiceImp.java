package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.DeviceDocumentMainDTO;
import com.jinchi.common.dto.DeviceProcessDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.ProcessAssignDTO;
import com.jinchi.common.mapper.BasicInfoDeviceProcessMapper;
import com.jinchi.common.mapper.DeviceDocumentMainMapper;
import com.jinchi.common.mapper.ProductionProcessDeviceMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceProcessServiceImp implements DeviceProcessService {

    @Autowired
    BasicInfoDeviceProcessMapper deviceProcessMapper;
    @Autowired
    ProductionProcessDeviceMapMapper processDeviceMapMapper;
    @Autowired
    DeviceDocumentManageService deviceDocumentManageService;
    @Autowired
    DeviceStatusService deviceStatusService;
    @Autowired
    DeviceDocumentMainMapper mainMapper;

    @Override
    public Page getPageByProcessDeptId(Integer processDeptId, String condition, Integer page, Integer size) {
        String sql = "select d.* from basic_info_device_process as p,production_process_device_map as d";
        sql +=  " where p.code = d.process_code and p.code = '" + processDeptId + "'";
        sql += " and (d.device_name like '" + condition + "%' or d.fixedassets_code like '" + condition + "%')";

        List<ProductionProcessDeviceMap> map = processDeviceMapMapper.selectByCondition(sql);
        List<DeviceProcessDTO> ans = new ArrayList<>();
        for(int i=0;i<map.size();i++){
            DeviceProcessDTO processDTO = new DeviceProcessDTO();
            processDTO.setDeviceMap(map.get(i));
            processDTO.setStatus(deviceStatusService.getStatusById(map.get(i).getStatusCode()));
            ans.add(processDTO);
        }
        Page pageInfo = new Page(ans,page,size);
        return pageInfo;
    }

    @Override
    public List getDeviceByProId(Integer processDeptId) {
        List<ProcessAssignDTO> ans = new ArrayList<>();
        BasicInfoDeviceProcess deviceProcess = deviceProcessMapper.selectByPrimaryKey(processDeptId.shortValue());
        List<DeviceDocumentMain> mains = deviceDocumentManageService.getAllByDeptId(deviceProcess.getDeptCode());

        for(int i=0;i<mains.size();i++){
            ProcessAssignDTO assignDTO = new ProcessAssignDTO();
            ProductionProcessDeviceMapExample example = new ProductionProcessDeviceMapExample();
            example.createCriteria().andDeviceCodeEqualTo(mains.get(i).getCode()).andProcessCodeEqualTo(processDeptId.shortValue());
            if(processDeviceMapMapper.selectByExample(example).size() != 0){
                assignDTO.setChosen(true);
                assignDTO.setDeviceCode(mains.get(i).getCode());
                assignDTO.setDeviceName(mains.get(i).getDeviceName());
                assignDTO.setFixedassetsCode(mains.get(i).getFixedassetsCode());
                assignDTO.setSpecification(mains.get(i).getSpecification());
                ans.add(assignDTO);
                continue;
            }else{
                example.clear();
                example.createCriteria().andDeviceCodeEqualTo(mains.get(i).getCode());
                if(processDeviceMapMapper.selectByExample(example).size() == 0){
                    assignDTO.setChosen(false);
                    assignDTO.setDeviceCode(mains.get(i).getCode());
                    assignDTO.setDeviceName(mains.get(i).getDeviceName());
                    assignDTO.setFixedassetsCode(mains.get(i).getFixedassetsCode());
                    assignDTO.setSpecification(mains.get(i).getSpecification());
                    ans.add(assignDTO);
                    continue;
                }
            }
        }
        return ans;
    }

    @Override
    public void assign(Integer processDeptId, Long[] deviceIds) {
        ProductionProcessDeviceMapExample example = new ProductionProcessDeviceMapExample();
        example.createCriteria().andProcessCodeEqualTo(processDeptId.shortValue());
        processDeviceMapMapper.deleteByExample(example);

        BasicInfoDeviceProcess deviceProcess = deviceProcessMapper.selectByPrimaryKey(processDeptId.shortValue());

        for(Long i:deviceIds){
            ProductionProcessDeviceMap map = new ProductionProcessDeviceMap();
            DeviceDocumentMainDTO deviceDocumentMainDTO = deviceDocumentManageService.getDetailById(i);
            DeviceDocumentMain main = deviceDocumentMainDTO.getDeviceDocumentMain();
            map.setProcessCode(processDeptId.shortValue());
            map.setStartDate(main.getStartdate());
            map.setProcessName(deviceProcess.getProcessName());
            map.setDeptCode(main.getDeptCode());
            map.setDeviceCode(main.getCode());
            map.setFixedassetsCode(main.getFixedassetsCode());
            map.setDeviceName(main.getDeviceName());
            map.setSpecification(main.getSpecification());
            map.setIdCode(main.getIdCode());
            map.setStatusCode(main.getStatusCode());
            processDeviceMapMapper.insertSelective(map);
        }
    }

    @Override
    public List<ProductionProcessDeviceMap> getByProcessCodeByDeptCode(Integer processCode, Integer deptCode) {
        ProductionProcessDeviceMapExample example = new ProductionProcessDeviceMapExample();
        example.createCriteria().andDeptCodeEqualTo(deptCode).andProcessCodeEqualTo(processCode.shortValue());
        return processDeviceMapMapper.selectByExample(example);
    }

    @Override
    public List getDeviceByDeptCode(Integer deptId) {
        DeviceDocumentMainExample example = new DeviceDocumentMainExample();
        example.createCriteria().andDeptCodeEqualTo(deptId);
        return mainMapper.selectByExample(example);
    }
}