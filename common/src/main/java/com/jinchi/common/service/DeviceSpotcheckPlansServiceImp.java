package com.jinchi.common.service;


import com.jinchi.common.domain.*;
import com.jinchi.common.dto.DeviceDocumentMainDTO;
import com.jinchi.common.dto.DeviceSpotcheckMainDTO;
import com.jinchi.common.mapper.*;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.SpotCheckPlanDTO;
import com.jinchi.common.mapper.DeviceDocumentMainMapper;
import com.jinchi.common.mapper.DeviceSpotcheckModelsHeadMapper;
import com.jinchi.common.mapper.DeviceSpotcheckPlansMapper;
import com.jinchi.common.mapper.DeviceSpotcheckRecordHeadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DeviceSpotcheckPlansServiceImp implements DeviceSpotcheckPlansService {
    @Autowired
    DeviceSpotcheckPlansMapper deviceSpotcheckPlansMapper;
    @Autowired
    DeviceSpotcheckModelsHeadMapper deviceSpotcheckModelsHeadMapper;
    @Autowired
    DeviceSpotcheckRecordHeadMapper deviceSpotcheckRecordHeadMapper;
    @Autowired
    DeviceDocumentMainMapper deviceDocumentMainMapper;
    @Autowired
    DeviceDocumentManageService deviceDocumentManageService;
    @Autowired
    DeviceSpotcheckModelsDetailsMapper deviceSpotcheckModelsDetailsMapper;
    @Autowired
    DeviceSpotcheckRecordDetailsMapper deviceSpotcheckRecordDetailsMapper;
    @Autowired
    BasicInfoDeptMapper basicInfoDeptMapper;

    @Override
    @Transactional
    public Boolean addOne(long deviceCode, String deviceName,long modelId) {
        //获取  fixCode  device_name deptCode
        DeviceDocumentMainExample deviceDocumentMainExample = new DeviceDocumentMainExample();
        deviceDocumentMainExample.createCriteria().andCodeEqualTo(deviceCode);
        List<DeviceDocumentMain> deviceDocumentMain = deviceDocumentMainMapper.selectByExample(deviceDocumentMainExample);
        //此处没有考虑不存在的情况，数组会越界
        String fixedassetsCode = deviceDocumentMain.get(0).getFixedassetsCode();
        Integer deptCode = deviceDocumentMain.get(0).getDeptCode();
        String id_code = deviceDocumentMain.get(0).getIdCode();
        String detailDeviceName = deviceDocumentMain.get(0).getDeviceName();
        int parentCode = deptCode;
        BasicInfoDeptExample example = new BasicInfoDeptExample();
        example.createCriteria().andCodeEqualTo(deptCode);
        BasicInfoDept b = basicInfoDeptMapper.selectByExample(example).get(0);
        if(b.getParentCode()!=null)
            parentCode = b.getParentCode();
        //获取modelcode
        DeviceSpotcheckModelsHeadExample deviceSpotcheckModelsHeadExample = new DeviceSpotcheckModelsHeadExample();
        //通过statusCode的 状态， 和   设备名称  和    部门的id选出模板
        deviceSpotcheckModelsHeadExample.createCriteria().andDeviceNameEqualTo(deviceName).andModelStatusEqualTo(false).andDeptCodeEqualTo(parentCode);
        List<DeviceSpotcheckModelsHead> deviceSpotcheckModelsHead = deviceSpotcheckModelsHeadMapper.selectByExample(deviceSpotcheckModelsHeadExample);
        long modelCode = deviceSpotcheckModelsHead.get(0).getCode();
        DeviceSpotcheckPlans deviceSpotcheckPlans = new DeviceSpotcheckPlans();
        deviceSpotcheckPlans.setDeptCode(deptCode);
        deviceSpotcheckPlans.setDeviceName(detailDeviceName);
        deviceSpotcheckPlans.setFixedassetsCode(fixedassetsCode);
        deviceSpotcheckPlans.setModelCode(modelId);
        deviceSpotcheckPlans.setDeviceCode(deviceCode);
        deviceSpotcheckPlans.setEffFlag(false);
        deviceSpotcheckPlansMapper.insertSelective(deviceSpotcheckPlans);
        //reorde_head 中需要插入的数据
        // planCode detailsdeviceCode  fixedassetsCode  deviceName  deptcode   id_code(devicedocumentmain)
//        DeviceSpotcheckPlansExample deviceSpotcheckPlansExample = new DeviceSpotcheckPlansExample();
//        //一个主设备只有一个点检计划
//        deviceSpotcheckPlansExample.createCriteria().andDeviceCodeEqualTo(deviceCode);
//        List<DeviceSpotcheckPlans> forPlanCode = deviceSpotcheckPlansMapper.selectByExample(deviceSpotcheckPlansExample);
//        long planCode = forPlanCode.get(0).getCode();

        /*DeviceSpotcheckRecordHead deviceSpotcheckRecordHead = new DeviceSpotcheckRecordHead();
        deviceSpotcheckRecordHead.setPlanCode(deviceSpotcheckPlans.getCode());
        deviceSpotcheckRecordHead.setDeviceName(detailDeviceName);
        deviceSpotcheckRecordHead.setFixedassetsCode(fixedassetsCode);
        deviceSpotcheckRecordHead.setDeviceCode(deviceCode);
        deviceSpotcheckRecordHead.setDeptCode(deptCode);
        deviceSpotcheckRecordHead.setIdCode(id_code);
         deviceSpotcheckRecordHeadMapper.insertSelective(deviceSpotcheckRecordHead);

        //record detail中需要插入的数据
        //recordCode   modelDetailCode  spotcheckItems   spotcheckContent
//        DeviceSpotcheckRecordHeadExample deviceSpotcheckRecordHeadExample = new DeviceSpotcheckRecordHeadExample();
//        deviceSpotcheckRecordHeadExample.createCriteria().andDeviceCodeEqualTo(deviceCode);
//        List<DeviceSpotcheckRecordHead> forRecordCode = deviceSpotcheckRecordHeadMapper.selectByExample(deviceSpotcheckRecordHeadExample);
//        //一个点检计划只有一条
//        long recordCode = forRecordCode.get(0).getCode();
        long recordCode = deviceSpotcheckRecordHead.getCode();
         //       System.out.println();
        DeviceSpotcheckModelsDetailsExample deviceSpotcheckModelsDetailsExample = new DeviceSpotcheckModelsDetailsExample();
        deviceSpotcheckModelsDetailsExample.createCriteria().andModelCodeEqualTo(modelCode);
        List<DeviceSpotcheckModelsDetails> deviceSpotcheckModelsDetails = deviceSpotcheckModelsDetailsMapper.selectByExample(deviceSpotcheckModelsDetailsExample);


        //detail可能有多条 全插入
        for (DeviceSpotcheckModelsDetails d : deviceSpotcheckModelsDetails) {
            long modelDetailCode = d.getCode();
            String spotcheckItems = d.getSpotcheckItems();
            String spotcheckContent = d.getSpotcheckContent();
            DeviceSpotcheckRecordDetails temp = new DeviceSpotcheckRecordDetails();
            temp.setRecordCode(recordCode);
            temp.setModelDetailCode(modelDetailCode);
            temp.setSpotcheckItems(spotcheckItems);
            temp.setSpotcheckContent(spotcheckContent);
            deviceSpotcheckRecordDetailsMapper.insertSelective(temp);
        }*/
        return true;
    }


    @Override
    public Boolean deleteByCode(long code) {

        DeviceSpotcheckRecordHeadExample deviceSpotcheckRecordHeadExample = new DeviceSpotcheckRecordHeadExample();
        deviceSpotcheckRecordHeadExample.createCriteria().andPlanCodeEqualTo(code);
        List<DeviceSpotcheckRecordHead> deviceSpotcheckRecordHead = deviceSpotcheckRecordHeadMapper.selectByExample(deviceSpotcheckRecordHeadExample);
        //存在点检记录返回  null
        if (!deviceSpotcheckRecordHead.isEmpty()) {
            return false;
        }

        DeviceSpotcheckPlansExample deviceSpotcheckPlansExample = new DeviceSpotcheckPlansExample();
        deviceSpotcheckPlansExample.createCriteria().andCodeEqualTo(code);
        deviceSpotcheckPlansMapper.deleteByExample(deviceSpotcheckPlansExample);
        return true;
    }


    @Override
    public List<DeviceSpotcheckMainDTO> getAddMsg(int deptCode, String deviceName) {
        int parentCode = deptCode;
        BasicInfoDeptExample example = new BasicInfoDeptExample();
        example.createCriteria().andCodeEqualTo(deptCode);
        BasicInfoDept b = basicInfoDeptMapper.selectByExample(example).get(0);
        if(b.getParentCode()!=null)
            parentCode = b.getParentCode();
        //  deptCode 是部门的code   device 的 名称是一个类名。
        //首先去获到详细的信息
        List<DeviceSpotcheckMainDTO> deviceSpotcheckMainDTO = new ArrayList<>();
        List<DeviceDocumentMainDTO> deviceDocumentMainDTO = deviceDocumentManageService.getByDeptIdByDeviceName(deptCode, deviceName, "");
        int flag = 0;
        //首先通过类名查询是否有点检的模板， 如果没有的的话全部都给flag 0
        DeviceSpotcheckModelsHeadExample deviceSpotcheckModelsHeadExample = new DeviceSpotcheckModelsHeadExample();
        deviceSpotcheckModelsHeadExample.createCriteria().andDeptCodeEqualTo(parentCode).andDeviceNameEqualTo(deviceName);
        List<DeviceSpotcheckModelsHead> deviceSpotcheckModelsHeads = deviceSpotcheckModelsHeadMapper.selectByExample(deviceSpotcheckModelsHeadExample);
        List<DeviceSpotcheckModelsHead> effects = new ArrayList<>();
        if (deviceSpotcheckModelsHeads.isEmpty()) {
            flag = 0; //无点检模板

        } else {
            //如果能找到一个有效的
            for (DeviceSpotcheckModelsHead d : deviceSpotcheckModelsHeads) {
                if (d.getModelStatus() == false) {
                    flag = -1;
                    effects.add(d);
                }
            }
        }
        List<Long> effectIds = new ArrayList<>();
        for(int i=0;i<effects.size();i++){
            effectIds.add(effects.get(i).getCode());
        }
        System.out.println("flag:  " + flag + "dto: " + deviceDocumentMainDTO.size() + "模板： " + deviceSpotcheckModelsHeads.size());
        //对于每一个的信息，判断他的条件
        if (flag == 0) {
            for (DeviceDocumentMainDTO d : deviceDocumentMainDTO) {
                DeviceSpotcheckMainDTO temp = new DeviceSpotcheckMainDTO();
                temp.setDeviceDocumentMain(d.getDeviceDocumentMain());
                temp.setFlag(0);//无点检模板
                deviceSpotcheckMainDTO.add(temp);
            }
        }
        else {
            for (DeviceDocumentMainDTO d : deviceDocumentMainDTO) {
                DeviceSpotcheckPlansExample deviceSpotcheckPlansExample = new DeviceSpotcheckPlansExample();
                //这个麻烦， 设备名称是全称  ，所以要  从 DTO 里面获取
                deviceSpotcheckPlansExample.createCriteria().andModelCodeIn(effectIds).andDeviceCodeEqualTo(d.getDeviceDocumentMain().getCode());
                List<DeviceSpotcheckPlans> deviceSpotcheckPlans = deviceSpotcheckPlansMapper.selectByExample(deviceSpotcheckPlansExample);
                DeviceSpotcheckMainDTO temp = new DeviceSpotcheckMainDTO();
                temp.setDeviceDocumentMain(d.getDeviceDocumentMain());
                if (deviceSpotcheckPlans.isEmpty()) {
                    temp.setFlag(2);//新增点检模板
                } else {
                    temp.setFlag(1);//计划已制定
                }
                deviceSpotcheckMainDTO.add(temp);
            }
        }
        return deviceSpotcheckMainDTO;
    }

    @Override
    public List<SpotCheckPlanDTO> getPlanByConditions(Integer deptId, String deviceName, Integer status, String condition) {
        List<SpotCheckPlanDTO> spotCheckPlanDTOS = new ArrayList<>();
        String sql = "select * from device_spotcheck_plans where (dept_code = " + deptId + " and (device_name = '"+deviceName + "' or device_name like '"+deviceName+"-%'))";
        if(status!=-1){
            sql += " and (eff_flag = " + status + ")";
        }
        if(!condition.equals("")){
            sql += " and (code like '" + condition + "%' or fixedassets_code like '" + condition + "%' or code like '"+condition + "%')";
        }
        sql += " order by code desc";
        System.out.println(sql);
        List<DeviceSpotcheckPlans> deviceSpotcheckPlans = deviceSpotcheckPlansMapper.selectByCondition(sql);
        DeviceSpotcheckRecordHeadExample example1 = new DeviceSpotcheckRecordHeadExample();
        for(int i=0;i<deviceSpotcheckPlans.size();i++){
            example1.createCriteria().andPlanCodeEqualTo(deviceSpotcheckPlans.get(i).getCode());
            SpotCheckPlanDTO spotCheckPlanDTO = new SpotCheckPlanDTO();
            spotCheckPlanDTO.setDeviceSpotcheckPlans(deviceSpotcheckPlans.get(i));
            spotCheckPlanDTO.setDetailNum(deviceSpotcheckRecordHeadMapper.countByExample(example1));
            example1.clear();
            spotCheckPlanDTOS.add(spotCheckPlanDTO);
        }
        return spotCheckPlanDTOS;
    }

    @Override
    public Page getPlanByConditionsByPage(Integer deptId, String deviceName, Integer status, String condition, Integer page, Integer size) {
        Page pageInfo = new Page(getPlanByConditions(deptId,deviceName,status,condition),page,size);
        return pageInfo;
    }

    @Override
    public Map<String, Integer> getDeviceNameByDeptId(Integer deptId) {
        DeviceSpotcheckPlansExample example = new DeviceSpotcheckPlansExample();
        example.createCriteria().andDeptCodeEqualTo(deptId);
        List<DeviceSpotcheckPlans> l = deviceSpotcheckPlansMapper.selectByExample(example);
        Map<String, Integer> ans = new HashMap<>();
        for (DeviceSpotcheckPlans d : l) {
            String name = d.getDeviceName().split("-")[0];
            if (!ans.containsKey(name)) {
                ans.put(name, 1);
            } else {
                int num = ans.get(name);
                ans.put(name, num + 1);
            }
        }
        return ans;
    }

    @Override
    public void update(Long planId) {
        DeviceSpotcheckPlansExample example = new DeviceSpotcheckPlansExample();
        example.createCriteria().andCodeEqualTo(planId);
        DeviceSpotcheckPlans plans = deviceSpotcheckPlansMapper.selectByExample(example).get(0);
        plans.setEffFlag(plans.getEffFlag()?false:true);
        deviceSpotcheckPlansMapper.updateByExampleSelective(plans,example);
    }
}
