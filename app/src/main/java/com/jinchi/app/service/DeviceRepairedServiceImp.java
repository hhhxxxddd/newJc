package com.jinchi.app.service;

import com.jinchi.app.domain.*;
import com.jinchi.app.dto.DeviceRepairAccDTO;
import com.jinchi.app.dto.DeviceRepairDetail;
import com.jinchi.app.dto.DeviceRepairedApplyDTO;
import com.jinchi.app.dto.EvaluItem;
import com.jinchi.app.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeviceRepairedServiceImp implements DeviceRepairedService {
    @Autowired
    DeviceRepairApplicationMapper deviceRepairApplicationMapper;
    @Autowired
    DeviceRepairAccessoryMapper deviceRepairAccessoryMapper;
    @Autowired
    DeviceDocumentMainService deviceDocumentMainService;
    @Autowired
    DeptService deptService;
    @Autowired
    AuthUserService authUserService;
    @Autowired
    DeviceRepairEvaluationsMapper deviceRepairEvaluationsMapper;
    @Autowired
    UserDeviceService userDeviceService;
    @Autowired
    BasicInfoUserDeviceDeptMapMapper deviceDeptMapMapper;
    @Autowired
    BasicInfoAppUserAuthorityMapMapper appUserAuthorityMapMapper;
    @Autowired
    DeviceRepairHelperMapper deviceRepairHelperMapper;

    @Override
    public DeviceRepairDetail detail(Long id) {
        DeviceRepairApplicationExample example = new DeviceRepairApplicationExample();
        example.createCriteria().andCodeEqualTo(id);
        DeviceRepairApplication deviceRepairApplication = deviceRepairApplicationMapper.selectByExample(example).get(0);
        Integer status = deviceRepairApplication.getRepairStatus();

        DeviceRepairDetail ans = new DeviceRepairDetail();
        ans.setRepairStatuts(deviceRepairApplication.getRepairStatus());
        ans.setRepaidId(deviceRepairApplication.getCode());
        ans.setDeviceName(deviceDocumentMainService.getById(deviceRepairApplication.getDeviceCode()).getDeviceName());
        ans.setFixedassets(deviceDocumentMainService.getById(deviceRepairApplication.getDeviceCode()).getFixedassetsCode());
        ans.setDeptName(deptService.getById(deviceRepairApplication.getDeptCode()).getName());
        ans.setReportTime(deviceRepairApplication.getReportTime());
        ans.setRepairPeople(authUserService.findById(deviceRepairApplication.getReportPeople()).getName());
        ans.setPhone(authUserService.findById(deviceRepairApplication.getReportPeople()).getPhone());
        ans.setFaultComment(deviceRepairApplication.getFaultContent());
        ans.setEmergeStatus(deviceRepairApplication.getEmergeStatus());

        if (status == 2 || status == 3 || status == 4) {
            ans.setReveiveTime(deviceRepairApplication.getReceiveTime());
            ans.setFaultReason(deviceRepairApplication.getFaultReason());
            DeviceRepairHelperExample helperExample = new DeviceRepairHelperExample();
            helperExample.createCriteria().andRepairCodeEqualTo(id);
            List<DeviceRepairHelper> helpers = deviceRepairHelperMapper.selectByExample(helperExample);
            ans.setPartners(helpers.size()==0?"":helpers.get(0).getUsers());
            List<DeviceRepairAccDTO> accs = new ArrayList<>();
            DeviceRepairAccessoryExample example1 = new DeviceRepairAccessoryExample();
            example1.createCriteria().andRepairCodeEqualTo(id);
            List<DeviceRepairAccessory> deviceRepairAccessories = deviceRepairAccessoryMapper.selectByExample(example1);
            for (int i = 0; i < deviceRepairAccessories.size(); i++) {
                DeviceRepairAccDTO deviceRepairAccDTO = new DeviceRepairAccDTO();
                deviceRepairAccDTO.setSpecification(deviceRepairAccessories.get(i).getSpecification());
                deviceRepairAccDTO.setCount(deviceRepairAccessories.get(i).getCounts());
                deviceRepairAccDTO.setAccName(deviceRepairAccessories.get(i).getName());
                String unitString = null;
                int unit = deviceRepairAccessories.get(i).getUnits().intValue();
                if (unit == 0)
                    unitString = "个";
                if (unit == 1)
                    unitString = "台";
                if (unit == 2)
                    unitString = "套";
                deviceRepairAccDTO.setUnit(unitString);
                accs.add(deviceRepairAccDTO);
            }
            ans.setAccs(accs);
        }
        if (status == 3 || status == 4) {
            ans.setEvaluResult(deviceRepairApplication.getEvaluationResult());
            ans.setFinishiTime(deviceRepairApplication.getFinishTime());
            if (status == 4) {
                DeviceRepairEvaluationsExample example1 = new DeviceRepairEvaluationsExample();
                example1.createCriteria().andRepairCodeEqualTo(id);
                List<DeviceRepairEvaluations> deviceRepairEvaluations = deviceRepairEvaluationsMapper.selectByExample(example1);
                List<EvaluItem> evaluItems = new ArrayList<>();
                for (int i = 0; i < deviceRepairEvaluations.size(); i++) {
                    EvaluItem evaluItem = new EvaluItem();
                    evaluItem.setItemName(deviceRepairEvaluations.get(i).getItemName());
                    evaluItem.setLevel(deviceRepairEvaluations.get(i).getLevels());
                    evaluItems.add(evaluItem);
                }
                ans.setEis(evaluItems);
            }
        }
        return ans;
    }

    @Override
    @Transactional
    public void commit(DeviceRepairedApplyDTO deviceRepairedApplyDTO) {
        Long repairId = deviceRepairedApplyDTO.getRepairId();
        Integer flag = deviceRepairedApplyDTO.getFlag();
        List<DeviceRepairAccDTO> accs = deviceRepairedApplyDTO.getAccs();
        if (accs == null)
            accs = new ArrayList<>();
        DeviceRepairApplicationExample example = new DeviceRepairApplicationExample();
        example.createCriteria().andCodeEqualTo(repairId);
        if (flag == 1) {//flag=1待维修的接单
            DeviceRepairApplication deviceRepairApplication = new DeviceRepairApplication();
            deviceRepairApplication.setRepairStatus(2);
            deviceRepairApplication.setReceiveTime(new Date());
            deviceRepairApplication.setReceivePeople(deviceRepairedApplyDTO.getReceivePeopleId());
            deviceRepairApplicationMapper.updateByExampleSelective(deviceRepairApplication, example);
        }
        if (flag == 2) {//flag=2已接单的提交
            DeviceRepairHelperExample example2 = new DeviceRepairHelperExample();
            example2.createCriteria().andRepairCodeEqualTo(repairId);
            deviceRepairHelperMapper.deleteByExample(example2);

            DeviceRepairHelper deviceRepairHelper = new DeviceRepairHelper();
            deviceRepairHelper.setUsers(deviceRepairedApplyDTO.getPartners());
            deviceRepairHelper.setRepairCode(repairId);
            deviceRepairHelperMapper.insertSelective(deviceRepairHelper);//合作人员

            DeviceRepairApplication deviceRepairApplication = new DeviceRepairApplication();
            deviceRepairApplication.setRepairStatus(3);
            deviceRepairApplication.setFinishTime(new Date());
            deviceRepairApplication.setFaultReason(deviceRepairedApplyDTO.getFaultReason());
            deviceRepairApplication.setReceivePeople(deviceRepairedApplyDTO.getReceivePeopleId());
            deviceRepairApplicationMapper.updateByExampleSelective(deviceRepairApplication, example);
            DeviceRepairAccessoryExample example1 = new DeviceRepairAccessoryExample();
            example1.createCriteria().andRepairCodeEqualTo(repairId);
            deviceRepairAccessoryMapper.deleteByExample(example1);
            for (int i = 0; i < accs.size(); i++) {
                DeviceRepairAccessory deviceRepairAccessory = new DeviceRepairAccessory();
                deviceRepairAccessory.setCounts(accs.get(i).getCount());
                deviceRepairAccessory.setName(accs.get(i).getAccName());
                deviceRepairAccessory.setSpecification(accs.get(i).getSpecification());
                Integer unit = 0;
                String unitString = accs.get(i).getUnit();
                if (unitString.equals("个")) {
                    unit = 0;
                }
                if (unitString.equals("台")) {
                    unit = 1;
                }
                if (unitString.equals("套")) {
                    unit = 2;
                }
                deviceRepairAccessory.setUnits(unit.byteValue());
                deviceRepairAccessory.setRepairCode(repairId);
                deviceRepairAccessoryMapper.insertSelective(deviceRepairAccessory);
            }

        }
        if (flag == 3) {//flag=3已接单的暂存
            DeviceRepairHelperExample example2 = new DeviceRepairHelperExample();
            example2.createCriteria().andRepairCodeEqualTo(repairId);
            deviceRepairHelperMapper.deleteByExample(example2);

            DeviceRepairHelper deviceRepairHelper = new DeviceRepairHelper();
            deviceRepairHelper.setUsers(deviceRepairedApplyDTO.getPartners());
            deviceRepairHelper.setRepairCode(repairId);
            deviceRepairHelperMapper.insertSelective(deviceRepairHelper);//合作人员

            DeviceRepairApplication deviceRepairApplication = new DeviceRepairApplication();
            // deviceRepairApplication.setFinishTime(new Date());
            deviceRepairApplication.setFaultReason(deviceRepairedApplyDTO.getFaultReason());
            deviceRepairApplication.setReceivePeople(deviceRepairedApplyDTO.getReceivePeopleId());
            deviceRepairApplicationMapper.updateByExampleSelective(deviceRepairApplication, example);
            DeviceRepairAccessoryExample example1 = new DeviceRepairAccessoryExample();
            example1.createCriteria().andRepairCodeEqualTo(repairId);
            deviceRepairAccessoryMapper.deleteByExample(example1);
            for (int i = 0; i < accs.size(); i++) {
                DeviceRepairAccessory deviceRepairAccessory = new DeviceRepairAccessory();
                deviceRepairAccessory.setCounts(accs.get(i).getCount());
                deviceRepairAccessory.setName(accs.get(i).getAccName());
                deviceRepairAccessory.setSpecification(accs.get(i).getSpecification());
                Integer unit = null;
                String unitString = accs.get(i).getUnit();
                if (unitString.equals("个")) {
                    unit = 0;
                }
                if (unitString.equals("台")) {
                    unit = 1;
                }
                if (unitString.equals("套")) {
                    unit = 2;
                }
                deviceRepairAccessory.setUnits(unit == null ? null : unit.byteValue());
                deviceRepairAccessory.setRepairCode(repairId);
                deviceRepairAccessoryMapper.insertSelective(deviceRepairAccessory);
            }
        }
    }

//    @Override
//    public List<RepairPageData> page(RepairPostDTO repairPostDTO) {
//        int page = repairPostDTO.getPage()==null?1:repairPostDTO.getPage();
//        int size = repairPostDTO.getSize()==null?5:repairPostDTO.getSize();
//        List<BasicInfoDeptDto> deps = userDeviceService.getDeptByAuthId(repairPostDTO.getId().intValue());
//        List<RepairPageData> ans = new ArrayList<>();
//        for(int i=0;i<deps.size();i++){
//            String sql = null;
//            if(repairPostDTO.getStatus() != 3) {
//                sql = "select r.* from device_repair_application as r,basic_info_dept as d where r.repair_status = " + repairPostDTO.getStatus() + " and r.dept_code=d.`code` and d.`code` = " + deps.get(i).getCode();
//            }
//            else{
//                sql = "select r.* from device_repair_application as r,basic_info_dept as d where (r.repair_status = 3 or r.repair_status = 4) and r.dept_code=d.`code` and d.`code` = " + deps.get(i).getCode();
//            }
//            if(repairPostDTO.getCondition()!=null){
//                sql += " and (r.device_name like '"+ repairPostDTO.getCondition() + "%' or r.fixedassets_code like '" + repairPostDTO.getCondition() + "%' or d.name like '"+repairPostDTO.getCondition()+"%')";
//            }
//            List<DeviceRepairApplication> repairApplications = deviceRepairApplicationMapper.selectBycondition(sql);
//            for(int l=0;l<repairApplications.size();l++){
//                RepairPageData data = new RepairPageData();
//                data.setDeptName(deps.get(i).getName());
//                data.setDeviceName(repairApplications.get(l).getDeviceName());
//                data.setEmergeFlag(repairApplications.get(l).getEmergeStatus());
//                data.setFixedassets(repairApplications.get(l).getFixedassetsCode());
//                data.setRepairId(repairApplications.get(l).getCode());
//                data.setReportDate(repairApplications.get(l).getReportTime());
//                data.setDeptId(repairApplications.get(l).getDeptCode());
//                data.setDeviceId(repairApplications.get(l).getDeviceCode());
//                data.setFaultComment(repairApplications.get(l).getFaultContent());
//                data.setRepairStatus(repairApplications.get(l).getRepairStatus());
//                ans.add(data);
//            }
//        }
//        Page pageInfo = new Page(size,page,ans);
//        return pageInfo.getList();
//    }


//    @Override
//    public List getPartnerById(Integer id) {
//        //获取这个用户所属的所有设备部门
//        BasicInfoUserDeviceDeptMapExample deviceDeptMapExample = new BasicInfoUserDeviceDeptMapExample();
//        deviceDeptMapExample.createCriteria().andAuthCodeEqualTo(id);
//        List<BasicInfoUserDeviceDeptMap> deviceDeptMaps = deviceDeptMapMapper.selectByExample(deviceDeptMapExample);
//        List<Integer> deptCodeList = new ArrayList<>();
//        deviceDeptMaps.forEach(ddm->deptCodeList.add(ddm.getDeptCode()));
//
//        //找到这些设备部门的其他人
//        BasicInfoUserDeviceDeptMapExample deviceDeptMapExample1 = new BasicInfoUserDeviceDeptMapExample();
//        deviceDeptMapExample1.createCriteria().andDeptCodeIn(deptCodeList);
//        List<BasicInfoUserDeviceDeptMap> deviceDeptMaps1 = deviceDeptMapMapper.selectByExample(deviceDeptMapExample1);
//        List<Integer> userIds = new ArrayList<>();
//        deviceDeptMaps1.forEach(ddm->userIds.add(ddm.getAuthCode()));
//
//        //这些用户还必须有设备维修的权限
//        BasicInfoAppUserAuthorityMapExample example = new BasicInfoAppUserAuthorityMapExample();
//        example.createCriteria().andUserCodeIn(userIds).andAuthCodeEqualTo((byte) 2);
//        List<BasicInfoAppUserAuthorityMap> userAuthorityMaps = appUserAuthorityMapMapper.selectByExample(example);
//        userIds.clear();
//        for (BasicInfoAppUserAuthorityMap userAuthorityMap : userAuthorityMaps){
//            userIds.add(userAuthorityMap.getUserCode());
//        }
//        List<LoginAuthDTO> dtos = new ArrayList<>();
//        for (int uid : userIds){
//            if (uid != id){
//                LoginAuthDTO loginAuthDTO = new LoginAuthDTO();
//                loginAuthDTO.setId(uid);
//                loginAuthDTO.setUsername(authUserService.findById(uid).getName());
//                dtos.add(loginAuthDTO);
//            }
//        }
//        return dtos;
//    }
//
//    @Override
//    public void cooperation(DeviceRepairHelperDTO repairHelperDTO) {
//        List<Integer> userIds = repairHelperDTO.getPartnerList();
//        long repairId = repairHelperDTO.getRepairId();
//        for (Integer id : userIds){
//            DeviceRepairHelper deviceRepairHelper = new DeviceRepairHelper();
//            deviceRepairHelper.setRepairCode(repairId);
//            deviceRepairHelper.setUserCode(id);
//            deviceRepairHelperMapper.insertSelective(deviceRepairHelper);
//        }
//    }
}
