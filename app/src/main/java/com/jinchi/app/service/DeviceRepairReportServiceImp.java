package com.jinchi.app.service;


import com.jinchi.app.domain.*;
import com.jinchi.app.dto.*;
import com.jinchi.app.mapper.*;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeviceRepairReportServiceImp implements DeviceRepairReportService {
    @Autowired
    DeviceRepairApplicationMapper repairApplicationMapper;
    @Autowired
    UserDeviceService userDeviceService;
    @Autowired
    DeviceDocumentMainMapper deviceDocumentMainMapper;
    @Autowired
    DeviceDocumentMainService deviceDocumentMainService;
    @Autowired
    DeptService deptService;
    @Autowired
    AuthUserService authUserService;
    @Autowired
    BasicInfoQuickPhrasesMapper basicInfoQuickPhrasesMapper;
    @Autowired
    BasicInfoDeptMapper basicInfoDeptMapper;
    @Autowired
    BasicInfoEvaluationsItemMapper basicInfoEvaluationsItemMapper;
    @Autowired
    DeviceRepairEvaluationsMapper deviceRepairEvaluationsMapper;
    @Autowired
    DeviceRepairAccessoryMapper deviceRepairAccessoryMapper;

    @Override
    public RepairPageDataDTO page(RepairPostDTO repairPostDTO) {
        int page = repairPostDTO.getPage() == null ? 1 : repairPostDTO.getPage();
        int size = repairPostDTO.getSize() == null ? 5 : repairPostDTO.getSize();
        List<BasicInfoDeptDto> deps = userDeviceService.getDeptByAuthId(repairPostDTO.getId().intValue());
        List<RepairPageData> ans = new ArrayList<>();
        RepairPageDataDTO dto = new RepairPageDataDTO();
        int numApply = 0;
        int numWait = 0;
        int numConfirm = 0;

        List<DeviceRepairApplication> total = new ArrayList<>();
        if (deps.size() == 0) {
            return new RepairPageDataDTO();
        }

        String sql = null;
        String sqlApply = "select count(*) from device_repair_application as r,basic_info_dept as d where r.repair_status = 1 and r.dept_code=d.`code` and d.`code` in (SELECT dept_code FROM basic_info_user_device_dept_map WHERE auth_code = '" + repairPostDTO.getId().intValue() + "')";
        String sqlWait = "select count(*) from device_repair_application as r,basic_info_dept as d where r.repair_status = 2 and r.dept_code=d.`code` and d.`code` in (SELECT dept_code FROM basic_info_user_device_dept_map WHERE auth_code = '" + repairPostDTO.getId().intValue() + "')";
        String sqlConfirm = "select COUNT(*) from device_repair_application as r,basic_info_dept as d where (r.repair_status = 3 or r.repair_status = 4) and r.dept_code=d.`code` and d.`code` in (SELECT dept_code FROM basic_info_user_device_dept_map WHERE auth_code = '" + repairPostDTO.getId().intValue() + "')";
        numApply += repairApplicationMapper.countByCondition(sqlApply);
        numWait += repairApplicationMapper.countByCondition(sqlWait);
        numConfirm += repairApplicationMapper.countByCondition(sqlConfirm);
        if (repairPostDTO.getStatus() != 3) {
            sql = "select r.* from device_repair_application as r,basic_info_dept as d where r.repair_status = " + repairPostDTO.getStatus() + " and r.dept_code=d.`code` and d.`code` in (SELECT dept_code FROM basic_info_user_device_dept_map WHERE auth_code = '" + repairPostDTO.getId().intValue() + "')";
        } else {
            sql = "select r.* from device_repair_application as r,basic_info_dept as d where (r.repair_status = 3 or r.repair_status = 4) and r.dept_code=d.`code` and d.`code` in (SELECT dept_code FROM basic_info_user_device_dept_map WHERE auth_code = '" + repairPostDTO.getId().intValue() + "')";
        }
        if (repairPostDTO.getStartTime() != null && repairPostDTO.getEndTime() != null) {
            sql += " and (r.finish_time > '" + repairPostDTO.getStartTime() + " 00:00:00' and r.finish_time < '" + repairPostDTO.getEndTime() + " 23:59:59')";
        }
        if (repairPostDTO.getCondition() != null) {
            sql += " and (r.device_name like '" + repairPostDTO.getCondition() + "%' or r.fixedassets_code like '" + repairPostDTO.getCondition() + "%' or d.name like '" + repairPostDTO.getCondition() + "%')";
        }
        if (repairPostDTO.getRepairTypeId() != null) {
            sql += " and repair_type_id = '" + repairPostDTO.getRepairTypeId() + "'";
        }
        if (repairPostDTO.getStatus() == 1 || repairPostDTO.getStatus() == 2) {
            sql += " order by report_time desc";
        }
        if (repairPostDTO.getStatus() == 4 || repairPostDTO.getStatus() == 3) {
            sql += " order by finish_time desc, emerge_status desc";
        }
        int start = (page - 1) * size;
        sql += " limit " + start + "," + size;
        List<DeviceRepairApplication> repairApplications = repairApplicationMapper.selectBycondition(sql);
        total.addAll(repairApplications);


        for (int l = 0; l < total.size(); l++) {
            RepairPageData data = new RepairPageData();
            data.setDeptName(deptService.getById(total.get(l).getDeptCode()).getName());
            data.setDeviceName(total.get(l).getDeviceName());
            data.setEmergeFlag(total.get(l).getEmergeStatus());
            data.setFixedassets(total.get(l).getFixedassetsCode());
            data.setRepairId(total.get(l).getCode());
            data.setReportDate(total.get(l).getReportTime());
            data.setDeptId(total.get(l).getDeptCode());
            data.setDeviceId(total.get(l).getDeviceCode());
            data.setFaultComment(total.get(l).getFaultContent());
            data.setRepairStatus(total.get(l).getRepairStatus());
            //ata.setFixedassets(total.get(l).getFinishTime().toString());
            ans.add(data);
        }
        dto.setApply(numApply);
        dto.setWait(numWait);
        dto.setConfirm(numConfirm);
        dto.setList(ans);
        return dto;
    }

    @Override
    public void delete(Long id) {
        DeviceRepairApplicationExample example = new DeviceRepairApplicationExample();
        example.createCriteria().andCodeEqualTo(id);
        repairApplicationMapper.deleteByExample(example);
    }

    @Override
    public DeviceRepairDetail detail(Long id, Integer userId) {
        DeviceRepairApplicationExample example = new DeviceRepairApplicationExample();
        example.createCriteria().andCodeEqualTo(id);
        DeviceRepairApplication deviceRepairApplication = repairApplicationMapper.selectByExample(example).get(0);
        DeviceRepairDetail ans = new DeviceRepairDetail();
        ans.setRepairStatuts(deviceRepairApplication.getRepairStatus());
        int status = deviceRepairApplication.getRepairStatus();
        if (status == 0) {
            DeviceDocumentMain deviceDocumentMain = deviceDocumentMainService.getById(deviceRepairApplication.getDeviceCode());
            ans.setDeviceName(deviceDocumentMain.getDeviceName());
            ans.setFixedassets(deviceDocumentMain.getFixedassetsCode());
            ans.setFaultComment(deviceRepairApplication.getFaultContent());
            ans.setEmergeStatus(deviceRepairApplication.getEmergeStatus());
        }
        if (status == 1) {
            ans.setIsCancel(userId == deviceRepairApplication.getReportPeople().intValue() ? 1 : 0);
            ans.setDeptName(deptService.getById(deviceRepairApplication.getDeptCode()).getName());
            ans.setDeviceName(deviceDocumentMainService.getById(deviceRepairApplication.getDeviceCode()).getDeviceName());
            ans.setFixedassets(deviceDocumentMainService.getById(deviceRepairApplication.getDeviceCode()).getFixedassetsCode());
            ans.setReportTime(deviceRepairApplication.getReportTime());
            ans.setFaultComment(deviceRepairApplication.getFaultContent());
            ans.setRepaidId(deviceRepairApplication.getCode());
            ans.setEmergeStatus(deviceRepairApplication.getEmergeStatus());
        }
        if (status == 2) {
            ans.setDeptName(deptService.getById(deviceRepairApplication.getDeptCode()).getName());
            DeviceDocumentMain deviceDocumentMain = deviceDocumentMainService.getById(deviceRepairApplication.getDeviceCode());
            ans.setDeviceName(deviceDocumentMain.getDeviceName());
            ans.setFixedassets(deviceDocumentMain.getFixedassetsCode());
            ans.setReportTime(deviceRepairApplication.getReportTime());
            ans.setReveiveTime(deviceRepairApplication.getReceiveTime());
            AuthUserDTO authUserDTO = authUserService.findById(deviceRepairApplication.getReceivePeople());
            ans.setRepairPeople(authUserDTO.getName());
            ans.setPhone(authUserDTO.getPhone());
            ans.setFaultComment(deviceRepairApplication.getFaultContent());
            ans.setRepaidId(deviceRepairApplication.getCode());
            ans.setEmergeStatus(deviceRepairApplication.getEmergeStatus());
        }
        if (status == 3 || status == 4) {
            ans.setDeptName(deptService.getById(deviceRepairApplication.getDeptCode()).getName());
            DeviceDocumentMain deviceDocumentMain = deviceDocumentMainService.getById(deviceRepairApplication.getDeviceCode());
            ans.setDeviceName(deviceDocumentMain.getDeviceName());
            ans.setFixedassets(deviceDocumentMain.getFixedassetsCode());
            ans.setReportTime(deviceRepairApplication.getReportTime());
            ans.setReveiveTime(deviceRepairApplication.getReceiveTime());
            AuthUserDTO authUserDTO = authUserService.findById(deviceRepairApplication.getReceivePeople());
            ans.setRepairPeople(authUserDTO.getName());
            ans.setPhone(authUserDTO.getPhone());
            ans.setFaultComment(deviceRepairApplication.getFaultContent());
            ans.setEmergeStatus(deviceRepairApplication.getEmergeStatus());
            ans.setRepaidId(deviceRepairApplication.getCode());
            ans.setFinishiTime(deviceRepairApplication.getFinishTime());
            ans.setFaultReason(deviceRepairApplication.getFaultReason());
            DeviceRepairAccessoryExample example1 = new DeviceRepairAccessoryExample();
            example1.createCriteria().andRepairCodeEqualTo(deviceRepairApplication.getCode());
            List<DeviceRepairAccessory> deviceRepairAccessories = deviceRepairAccessoryMapper.selectByExample(example1);
            List<DeviceRepairAccDTO> accs = new ArrayList<>();
            for (int i = 0; i < deviceRepairAccessories.size(); i++) {
                DeviceRepairAccDTO deviceRepairAccDTO = new DeviceRepairAccDTO();
                deviceRepairAccDTO.setAccName(deviceRepairAccessories.get(i).getName());
                deviceRepairAccDTO.setCount(deviceRepairAccessories.get(i).getCounts());
                deviceRepairAccDTO.setSpecification(deviceRepairAccessories.get(i).getSpecification());
                String unitString = null;
                int unit = deviceRepairAccessories.get(i).getUnits().intValue();
                if (unit == 0)
                    unitString = "个";
                if (unit == 1)
                    unitString = "台";
                if (unit == 2)
                    unitString = "套";
                if(unit == 3)
                    unitString = "米";
                deviceRepairAccDTO.setUnit(unitString);
                accs.add(deviceRepairAccDTO);
            }
            ans.setAccs(accs);
            if (status == 4) {
                //加入评价信息
                ans.setEvaluResult(deviceRepairApplication.getEvaluationResult());
                DeviceRepairEvaluationsExample example2 = new DeviceRepairEvaluationsExample();
                example2.createCriteria().andRepairCodeEqualTo(id);
                List<DeviceRepairEvaluations> deviceRepairEvaluations = deviceRepairEvaluationsMapper.selectByExample(example2);
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
    public void commit(DeviceRepairApplyDTO deviceRepairApplyDTO) {
        DeviceRepairApplication deviceRepairApplication = new DeviceRepairApplication();
        deviceRepairApplication.setDeptCode(deviceRepairApplyDTO.getDeptCode());
        deviceRepairApplication.setDeviceCode(deviceRepairApplyDTO.getDeviceCode());
        deviceRepairApplication.setDeviceName(deviceRepairApplyDTO.getDeviceName());
        deviceRepairApplication.setEmergeStatus(deviceRepairApplyDTO.getEmergeStatus());
        deviceRepairApplication.setFaultContent(deviceRepairApplyDTO.getFaultComment());
        deviceRepairApplication.setFixedassetsCode(deviceRepairApplyDTO.getFixedassets());
        deviceRepairApplication.setReportTime(new Date());
        deviceRepairApplication.setReportPeople(deviceRepairApplyDTO.getUserId());
        deviceRepairApplication.setRepairStatus(deviceRepairApplyDTO.getFlag());
        // 2020/03/30 维修申请前选择维修类型
        deviceRepairApplication.setRepairTypeId(deviceRepairApplyDTO.getRepairTypeId());

        if (deviceRepairApplyDTO.getRepairId() == null) {
            repairApplicationMapper.insertSelective(deviceRepairApplication);
        } else {
            DeviceRepairApplicationExample example = new DeviceRepairApplicationExample();
            example.createCriteria().andCodeEqualTo(deviceRepairApplyDTO.getRepairId());
            repairApplicationMapper.updateByExampleSelective(deviceRepairApplication, example);
        }
    }

    @Override
    public List<BasicDeviceDTO> getDeviceByDeptId(RepairPostDTO repairPostDTO) {
        List<BasicDeviceDTO> ans = new ArrayList<>();
        Integer size = repairPostDTO.getSize() == null ? 5 : repairPostDTO.getSize();
        Integer page = repairPostDTO.getPage() == null ? 1 : repairPostDTO.getPage();
        String sql = "select * from device_document_main where dept_code = " + repairPostDTO.getId();
        if (repairPostDTO.getCondition() != null) {
            sql += " and (device_name like '" + repairPostDTO.getCondition() + "%' or fixedassets_code like '" + repairPostDTO.getCondition() + "%')";
        }
        List<DeviceDocumentMain> deviceDocumentMains = deviceDocumentMainMapper.selectByConditions(sql);
        for (int i = 0; i < deviceDocumentMains.size(); i++) {
            BasicDeviceDTO temp = new BasicDeviceDTO();
            temp.setDeviceId(deviceDocumentMains.get(i).getCode());
            temp.setDeviceName(deviceDocumentMains.get(i).getDeviceName());
            temp.setFixedassets(deviceDocumentMains.get(i).getFixedassetsCode());
            ans.add(temp);
        }
        Page pageInfo = new Page(size, page, ans);
        return pageInfo.getList();
    }

    @Override
    public List<BasicInfoQuickPhrases> getQuickPhase() {
        BasicInfoQuickPhrasesExample example = new BasicInfoQuickPhrasesExample();
        example.createCriteria();
        return basicInfoQuickPhrasesMapper.selectByExample(example);
    }

    @Override
    public List<DeptCata> getDeptCata(Integer userId) {
        List<BasicInfoDeptDto> basicInfoDeptDtos = userDeviceService.getDeptByAuthId(userId);
        List<Integer> deptIds = new ArrayList<>();
        for (int i = 0; i < basicInfoDeptDtos.size(); i++) {
            deptIds.add(basicInfoDeptDtos.get(i).getCode());
        }
        BasicInfoDeptExample example = new BasicInfoDeptExample();
        example.createCriteria().andParentCodeIsNull();
        List<BasicInfoDept> parent = basicInfoDeptMapper.selectByExample(example);
        List<DeptCata> ans = new ArrayList<>();
        for (int i = 0; i < parent.size(); i++) {
            DeptCata deptCata = new DeptCata();
            deptCata.setParentId(parent.get(i).getCode());
            deptCata.setParentName(parent.get(i).getName());
            example.clear();
            example.createCriteria().andParentCodeEqualTo(parent.get(i).getCode()).andCodeIn(deptIds);
            List<BasicInfoDept> son = basicInfoDeptMapper.selectByExample(example);
            List<SonDept> sonDepts = new ArrayList<>();
            for (int j = 0; j < son.size(); j++) {
                SonDept sonDept = new SonDept();
                sonDept.setDeptId(son.get(j).getCode());
                sonDept.setDeptName(son.get(j).getName());
                sonDepts.add(sonDept);
            }
            if (sonDepts.size() != 0) {
                deptCata.setSons(sonDepts);
            }
            if (sonDepts.size() != 0 || (deptIds.size() != 0 && parent.get(i).getCode() == deptIds.get(0))) {
                if (deptCata.getSons() == null || deptCata.getSons().size() == 0) {
                    deptCata.setSons(new ArrayList<>());
                }
                ans.add(deptCata);
            }
        }
        return ans;
    }

    @Override
    public List<BasicInfoEvaluationsItem> evaluation() {
        BasicInfoEvaluationsItemExample example = new BasicInfoEvaluationsItemExample();
        example.createCriteria();
        return basicInfoEvaluationsItemMapper.selectByExample(example);
    }

    @Override
    @Transactional
    public DeviceRepairEvaluationDTO comfirmEvalution(DeviceRepairEvaluationDTO deviceRepairEvaluationDTO) {
        List<EvaluItem> items = deviceRepairEvaluationDTO.getEvaluItems();
        for (int i = 0; i < items.size(); i++) {
            DeviceRepairEvaluations deviceRepairEvaluations = new DeviceRepairEvaluations();
            deviceRepairEvaluations.setItemCode(items.get(i).getItemId());
            deviceRepairEvaluations.setLevels(items.get(i).getLevel());
            deviceRepairEvaluations.setRepairCode(deviceRepairEvaluationDTO.getRepairId());
            deviceRepairEvaluations.setItemName(items.get(i).getItemName());
            deviceRepairEvaluationsMapper.insertSelective(deviceRepairEvaluations);
        }
        DeviceRepairApplication deviceRepairApplication = new DeviceRepairApplication();
        deviceRepairApplication.setEvaluationResult(deviceRepairEvaluationDTO.getComment());
        deviceRepairApplication.setEvaluationTime(new Date());
        deviceRepairApplication.setRepairStatus(4);
        DeviceRepairApplicationExample example = new DeviceRepairApplicationExample();
        example.createCriteria().andCodeEqualTo(deviceRepairEvaluationDTO.getRepairId());
        repairApplicationMapper.updateByExampleSelective(deviceRepairApplication, example);
        return deviceRepairEvaluationDTO;
    }

    @Override
    public DeviceDTO getDeviceByIdCard(RepairPostDTO repairPostDTO) {
        DeviceDTO deviceDTO = new DeviceDTO();
        String idCard = repairPostDTO.getCondition();
        Integer userId = repairPostDTO.getId().intValue();
        List<BasicInfoDeptDto> basicInfoDeptDtos = userDeviceService.getDeptByAuthId(userId);
        List<Integer> bIds = new ArrayList<>();
        for (BasicInfoDeptDto b : basicInfoDeptDtos) {
            bIds.add(b.getCode());
        }
        DeviceDocumentMainExample example = new DeviceDocumentMainExample();
        example.createCriteria().andIdCodeEqualTo(idCard).andDeptCodeIn(bIds);
        List<DeviceDocumentMain> deviceDocumentMains = deviceDocumentMainMapper.selectByExample(example);
        DeviceDocumentMain deviceDocumentMain = deviceDocumentMains.size() == 0 ? null : deviceDocumentMains.get(0);
        if (deviceDocumentMain == null)
            return null;
        deviceDTO.setDeptId(deviceDocumentMain.getDeptCode());
        deviceDTO.setDeviceId(deviceDocumentMain.getCode());
        deviceDTO.setDeviceName(deviceDocumentMain.getDeviceName());
        deviceDTO.setDeptName(deptService.getById(deviceDTO.getDeptId()).getName());
        deviceDTO.setFixedassets(deviceDocumentMain.getFixedassetsCode());
        deviceDTO.setIdCard(deviceDocumentMain.getIdCode());
        return deviceDTO;
    }

    @Override
    public void rescind(Long id) {
        DeviceRepairApplicationExample example = new DeviceRepairApplicationExample();
        example.createCriteria().andCodeEqualTo(id);
        List<DeviceRepairApplication> applications = repairApplicationMapper.selectByExample(example);
        DeviceRepairApplication application = applications.size() == 0 ? null : applications.get(0);
        Assert.notNull(application, "不存在这条维修单");
        application.setRepairStatus(0);
        repairApplicationMapper.updateByExampleSelective(application, example);
    }
}
