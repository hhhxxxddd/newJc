package com.jinchi.common.service;


import com.jinchi.common.constant.AddressEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.*;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.ComUtil;
import com.jinchi.common.utils.ExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class DeviceRepairServiceImp implements DeviceRepairService {
    @Autowired
    DeviceRepairAccessoryMapper deviceRepairAccessoryMapper;

    @Autowired
    DeviceRepairEvaluationsMapper deviceRepairEvaluationsMapper;

    @Autowired
    BasicInfoDeptMapper basicInfoDeptMapper;

    @Autowired
    DeviceRepairApplicationMapper deviceRepairApplicationMapper;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    DeptManageService deptManageService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    DeviceRepairHelperMapper helperMapper;

    @Override
    public List<DeviceRepairApplicationAndPeopleDTO> getAllByDeviceName(Integer repairStatus,Integer secondDeptCode, String condition,String startTime,String endTime) {
        List<DeviceRepairApplicationAndPeopleDTO> res = new ArrayList<>();
      
        String sql = "select * from device_repair_application  where (dept_code  = " + secondDeptCode + " and repair_status = " + repairStatus + ")";
        if(!condition.equals("")){
            sql += " and (device_name like '" +condition+ "%')";
        }
        if(!startTime.equals("")&&!endTime.equals("")){
            sql += " and (report_time >= '" + startTime + " 00:00:00' and report_time <= '" + endTime + " 23:59:59')";
        }
        sql += " order by report_time desc";
        List<DeviceRepairApplication> deviceRepairApplications = deviceRepairApplicationMapper.selectBycondition(sql);
        for (int i=0;i<deviceRepairApplications.size();i++)
        {
            DeviceRepairApplicationAndPeopleDTO deviceRepairApplicationAndPeopleDTO = new DeviceRepairApplicationAndPeopleDTO();
            deviceRepairApplicationAndPeopleDTO.setDeviceRepairApplication(deviceRepairApplications.get(i));
            AuthUserDTO authUserDTO = authUserService.findById(deviceRepairApplications.get(i).getReceivePeople());
            deviceRepairApplicationAndPeopleDTO.setReceivePeople(authUserDTO==null?null:authUserDTO.getName());
            AuthUserDTO authUserDTO1 = authUserService.findById(deviceRepairApplications.get(i).getReportPeople());
            deviceRepairApplicationAndPeopleDTO.setReportPeople(authUserDTO1==null?null:authUserDTO1.getName());
            deviceRepairApplicationAndPeopleDTO.getDeviceRepairApplication().setReportPhone(authUserDTO1==null?null:authUserDTO1.getPhone());
            deviceRepairApplicationAndPeopleDTO.getDeviceRepairApplication().setReceivePhone(authUserDTO==null?null:authUserDTO.getPhone());
            deviceRepairApplicationAndPeopleDTO.setDeptName(deptManageService.getDeptById(deviceRepairApplications.get(i).getDeptCode()).getName());
            res.add(deviceRepairApplicationAndPeopleDTO);
        }
        return res;
    }

    @Override
    public Page getPage(Integer repairStatus, Integer secondDeptCode, String condition, String startTime,String endTime,Integer page, Integer size) {
        List<DeviceRepairApplicationAndPeopleDTO> res = new ArrayList<>();

        String sql = "select * from device_repair_application  where (dept_code  = " + secondDeptCode + " and repair_status = " + repairStatus + ")";
        if(!condition.equals("")){
            sql += " and (device_name like '" +condition+ "%')";
        }
        if(!startTime.equals("")&&!endTime.equals("")){
            sql += " and (report_time >= '" + startTime + " 00:00:00' and report_time <= '" + endTime + " 23:59:59')";
        }
        sql += " order by report_time desc";
        Integer total = deviceRepairApplicationMapper.count(sql.replaceAll("\\*","count(*)"));
        sql += " limit "+(page-1)*size+","+size;
        List<DeviceRepairApplication> deviceRepairApplications = deviceRepairApplicationMapper.selectBycondition(sql);
        for (int i=0;i<deviceRepairApplications.size();i++)
        {
            String key = "device_re_app"+deviceRepairApplications.get(i).getCode();
            DeviceRepairApplicationAndPeopleDTO deviceRepairApplicationAndPeopleDTO = new DeviceRepairApplicationAndPeopleDTO();
            deviceRepairApplicationAndPeopleDTO.setDeviceRepairApplication(deviceRepairApplications.get(i));
            AuthUserDTO authUserDTO = authUserService.findById(deviceRepairApplications.get(i).getReceivePeople());
            deviceRepairApplicationAndPeopleDTO.setReceivePeople(authUserDTO==null?null:authUserDTO.getName());
            AuthUserDTO authUserDTO1 = authUserService.findById(deviceRepairApplications.get(i).getReportPeople());
            deviceRepairApplicationAndPeopleDTO.setReportPeople(authUserDTO1==null?null:authUserDTO1.getName());
            deviceRepairApplicationAndPeopleDTO.getDeviceRepairApplication().setReportPhone(authUserDTO1==null?null:authUserDTO1.getPhone());
            deviceRepairApplicationAndPeopleDTO.getDeviceRepairApplication().setReceivePhone(authUserDTO==null?null:authUserDTO.getPhone());
            deviceRepairApplicationAndPeopleDTO.setDeptName(deptManageService.getDeptById(deviceRepairApplications.get(i).getDeptCode()).getName());
            res.add(deviceRepairApplicationAndPeopleDTO);
        }
        Page pageInfo = new Page(res,1,size);
        pageInfo.setPage(page);
        pageInfo.setTotal(total);
        return pageInfo;
    }


    @Override
    public DeviceRepairApplicationDTO detail (Long id){
        DeviceRepairApplicationDTO res = new DeviceRepairApplicationDTO();
        DeviceRepairApplicationExample example = new DeviceRepairApplicationExample();
        example.createCriteria().andCodeEqualTo(id);
        DeviceRepairApplication deviceRepairApplication = deviceRepairApplicationMapper.selectByExample(example).get(0);
        res.setDeviceRepairApplication(deviceRepairApplication);
        res.getDeviceRepairApplication().setReceivePhone(authUserService.findById(deviceRepairApplication.getReceivePeople()).getPhone());
        res.getDeviceRepairApplication().setReportPhone(authUserService.findById(deviceRepairApplication.getReportPeople()).getPhone());

        DeviceRepairAccessoryExample example1 = new DeviceRepairAccessoryExample();
        example1.createCriteria().andRepairCodeEqualTo(id);
        List<DeviceRepairAccessory> deviceRepairAccessory = deviceRepairAccessoryMapper.selectByExample(example1);
        res.setDeviceRepairAccessory(deviceRepairAccessory);
        res.setReceivePeople(authUserService.findById(deviceRepairApplication.getReceivePeople()).getName());
        res.setReportPeople(authUserService.findById(deviceRepairApplication.getReportPeople()).getName());

        DeviceRepairHelperExample example2 = new DeviceRepairHelperExample();
        example2.createCriteria().andRepairCodeEqualTo(id);
        List<DeviceRepairHelper> helpers = helperMapper.selectByExample(example2);

        if(helpers.size()>0)
            res.setHelpPeoples(helpers.get(0).getUsers());

        return res;

    }

    @Override
    public DeviceRepairEvaluationsDTO evaluations(Long id){
        DeviceRepairEvaluationsDTO deviceRepairEvaluationsDTO= new DeviceRepairEvaluationsDTO();

        DeviceRepairEvaluationsExample example = new DeviceRepairEvaluationsExample();
        example.createCriteria().andRepairCodeEqualTo(id);
        List<DeviceRepairEvaluations> deviceRepairEvaluations = deviceRepairEvaluationsMapper.selectByExample(example);
        deviceRepairEvaluationsDTO.setDeviceRepairEvaluations(deviceRepairEvaluations);

        DeviceRepairApplication deviceRepairApplication = new DeviceRepairApplication();
        DeviceRepairApplicationExample example1 = new DeviceRepairApplicationExample();
        example1.createCriteria().andCodeEqualTo(id);
        deviceRepairApplication = deviceRepairApplicationMapper.selectByExample(example1).get(0);
        deviceRepairEvaluationsDTO.setEvaluationsResult(deviceRepairApplication.getEvaluationResult());
        return deviceRepairEvaluationsDTO;

    }

    @Override
    public List<DeviceRepairApplicationDTO> query (Integer status,Integer deptCode,String deviceName) {
        List<DeviceRepairApplicationDTO> res2 = new ArrayList<>();
        DeviceRepairApplicationExample example = new DeviceRepairApplicationExample();
        example.createCriteria().andDeptCodeEqualTo(deptCode).andRepairStatusEqualTo(status);
        List<DeviceRepairApplication> res = deviceRepairApplicationMapper.selectByExample(example);
        for (int i = 0; i < res.size(); i++) {
            if(res.get(i).getDeviceName().contains(deviceName)) {
                DeviceRepairAccessoryExample example1 = new DeviceRepairAccessoryExample();
                example1.createCriteria().andRepairCodeEqualTo(res.get(i).getCode());
                List<DeviceRepairAccessory> deviceRepairAccessories = deviceRepairAccessoryMapper.selectByExample(example1);
                DeviceRepairApplicationDTO deviceRepairApplicationDTO = new DeviceRepairApplicationDTO();
                deviceRepairApplicationDTO.setDeviceRepairApplication(res.get(i));
                deviceRepairApplicationDTO.setDeviceRepairAccessory(deviceRepairAccessories);
                deviceRepairApplicationDTO.setReportPeople(authUserService.findById(res.get(i).getReportPeople()).getName());
                deviceRepairApplicationDTO.setReceivePeople(authUserService.findById(res.get(i).getReceivePeople()).getName());
                res2.add(deviceRepairApplicationDTO);
            }
        }
        return res2;
    }

    @Override
    public  List<DeviceRepairApplicationDTO> getAllApplications(Integer status,Integer deptId,Long deviceId){
        List<DeviceRepairApplicationDTO> res = new ArrayList<>();
        DeviceRepairApplicationExample deviceRepairApplicationExample = new DeviceRepairApplicationExample();
        deviceRepairApplicationExample.createCriteria().andRepairStatusEqualTo(status).andDeptCodeEqualTo(deptId).andDeviceCodeEqualTo(deviceId);
        List<DeviceRepairApplication> deviceRepairApplications = deviceRepairApplicationMapper.selectByExample(deviceRepairApplicationExample);
        for(int i=0;i<deviceRepairApplications.size();i++){
            DeviceRepairApplicationDTO deviceRepairApplicationDTO = new DeviceRepairApplicationDTO();
            deviceRepairApplicationDTO.setDeviceRepairApplication(deviceRepairApplications.get(i));
            deviceRepairApplicationDTO.setReceivePeople(authUserService.findById(deviceRepairApplications.get(i).getReceivePeople()).getName());
            deviceRepairApplicationDTO.setReportPeople(authUserService.findById(deviceRepairApplications.get(i).getReportPeople()).getName());
            res.add(deviceRepairApplicationDTO);
        }
        return res;
    }


    @Override
    public Page getPageByDeviceId (Integer status, Integer deptCode, Long deviceId , Integer page, Integer size) {
        Page<DeviceRepairApplicationDTO> pageInfo = new Page<>(getAllApplications(status,deptCode,deviceId), page, size);
        return pageInfo;
    }

    @Override
    public List getApplicationsByCondition(Long deviceCode, Date startTime, Date endTime) {
        DeviceRepairApplicationExample example = new DeviceRepairApplicationExample();
        example.createCriteria().andDeviceCodeEqualTo(deviceCode).andFinishTimeBetween(startTime, endTime);
        return deviceRepairApplicationMapper.selectByExample(example);
    }

    @Override
    public String export(Integer repairStatus,Integer secondDeptCode, String condition,String startTime,String endTime) {
        Set<String> dateSet = new HashSet<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateSet.add("评价时间");
        dateSet.add("报修时间");
        dateSet.add("完成时间");
        dateSet.add("接单时间");
        if (!startTime.equals("")) {
            startTime += " 00:00:00";
            endTime += " 23:59:59";
        } else {
            startTime = null;
            endTime = null;
        }
        condition += "%";
        List<Map<String, Object>> doc = deviceRepairApplicationMapper.getDoneJson(repairStatus, secondDeptCode, condition, startTime, endTime);

        if (doc.size() == 0) {
            return "该时间区间内没有维修记录,请重新选择时间";
        }
        List<Map<String, Object>> afterHandler = new ArrayList<>();
        for (int i = 0; i < doc.size(); i++) {
            Map<String, Object> map = doc.get(i);
            Map<String, Object> handleMap = new HashMap<>();

            //处理维修配件
            Long repairId = Long.parseLong(map.get("维修编号").toString());
            DeviceRepairAccessoryExample example = new DeviceRepairAccessoryExample();
            example.createCriteria().andRepairCodeEqualTo(repairId);
            List<DeviceRepairAccessory> accessories = deviceRepairAccessoryMapper.selectByExample(example);
            String ans = "";
            for(int l=0;l<accessories.size();l++){
                DeviceRepairAccessory accessory = accessories.get(l);
                String temp = "" + (l+1) + "." + accessory.getName() + "_" + accessory.getSpecification() + ":" + accessory.getCounts();
                if(accessory.getUnits() == 0){
                    temp += "个";
                }
                if(accessory.getUnits() == 1){
                    temp += "台";
                }
                if(accessory.getUnits() == 2){
                    temp += "套";
                }
                if(accessory.getUnits() == 3){
                    temp += "米";
                }
                ans += temp;
                if(l != accessories.size() - 1) {
                    ans += "\n";
                }
            }
            map.put("维修配件",ans);
            for(String key:map.keySet()){
                if("紧急程度".equals(key)){
                    System.out.println(map.get(key).toString());
                    if("1".equals(map.get(key).toString())){
                        handleMap.put(key,"紧急");
                    }
                    else{
                        handleMap.put(key,"一般");
                    }
                }else if("接单人".equals(key)||"报修人".equals(key)){
                    Integer userId = Integer.parseInt(map.get(key).toString());
                    String userName = authUserService.findById(userId).getName();
                    handleMap.put(key,userName);
                }else if(dateSet.contains(key)){
                    String dateStr = map.get(key).toString();
                    dateStr = dateStr.substring(0,dateStr.length()-2);
                    handleMap.put(key,dateStr);
                }else{
                    handleMap.put(key,map.get(key).toString());
                }
            }
            afterHandler.add(handleMap);
        }
        List<Map<String,Object>> fin = new ArrayList<>();
        for(Map map:afterHandler){
            Map<String,Object> temp = new LinkedHashMap<>();
            temp.put("维修编号",map.get("维修编号"));
            temp.put("部门",map.get("部门"));
            temp.put("设备名",map.get("设备名"));
            temp.put("固定资产编码",map.get("固定资产编码"));
            temp.put("报修人",map.get("报修人"));
            temp.put("报修时间",map.get("报修时间"));
            temp.put("紧急程度",map.get("紧急程度"));
            temp.put("故障描述",map.get("故障描述"));
            temp.put("故障原因",map.get("故障原因"));
            temp.put("维修配件",map.get("维修配件"));
            temp.put("接单人",map.get("接单人"));
            temp.put("接单时间",map.get("接单时间"));
            temp.put("完成时间",map.get("完成时间"));
            temp.put("评价时间",map.get("评价时间"));
            temp.put("评价结果",map.get("评价结果"));
            fin.add(temp);
        }
        return AddressEnum.EXCEL_REPAIR.getPath() + ExportUtil.exportExcel(fin, AddressEnum.getCurrentPath(AddressEnum.EXCEL_REPAIR.getCode()));
    }

    @Override
    public DeviceRepair4BatchTraceDTO listRepairInfosByIdAndTime(Long deviceCode, String startTime, String endTime) {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";

        DeviceRepair4BatchTraceDTO dto = new DeviceRepair4BatchTraceDTO();

        DeviceRepairApplicationExample example = new DeviceRepairApplicationExample();
        example.createCriteria().andDeviceCodeEqualTo(deviceCode).andFinishTimeBetween(ComUtil.getDate(startTime, dateFormat), ComUtil.getDate(endTime, dateFormat));
        List<DeviceRepairApplication> applications = deviceRepairApplicationMapper.selectByExample(example);

        if (applications.size() == 0) {
            return null;
        }
        dto.setDeviceName(applications.get(0).getDeviceName());
        dto.setFixedassetsCode(applications.get(0).getFixedassetsCode());
        dto.setDeptName(deptManageService.getDeptById(applications.get(0).getDeptCode()).getName());

        List<DeviceRepairDetailDTO> detailDTOS = new ArrayList<>();

        for (DeviceRepairApplication application : applications) {
            DeviceRepairDetailDTO detailDTO = new DeviceRepairDetailDTO();
            detailDTO.setRepairCode(application.getCode());
            detailDTO.setEmergeStatus(application.getEmergeStatus());
            detailDTO.setReportTime(application.getReportTime());
            detailDTO.setReportPeople(authUserService.findById(application.getReportPeople()).getName());
            detailDTO.setReceiveTime(application.getReceiveTime());
            detailDTO.setReceivePeople(authUserService.findById(application.getReceivePeople()).getName());
            detailDTO.setFinishTime(application.getFinishTime());
            detailDTOS.add(detailDTO);
        }
        dto.setDtoList(detailDTOS);
        return dto;
    }
}
