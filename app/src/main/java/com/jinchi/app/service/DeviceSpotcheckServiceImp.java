package com.jinchi.app.service;

import com.jinchi.app.constant.AddressEnum;
import com.jinchi.app.domain.*;
import com.jinchi.app.dto.*;
import com.jinchi.app.mapper.*;
import com.jinchi.app.utils.UnifyTransform;
import com.jinchi.app.utils.UploadUtil;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-03 11:07
 * @description:
 **/

@Service
@Transactional
public class DeviceSpotcheckServiceImp implements DeviceSpotcheckService {

    @Autowired
    UserDeviceService userDeviceService;

    @Autowired
    DeviceSpotcheckPlansMapper deviceSpotcheckPlansMapper;

    @Autowired
    DeviceSpotcheckRecordHeadMapper deviceSpotcheckRecordHeadMapper;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    DeviceSpotcheckModelsDetailsMapper deviceSpotcheckModelsDetailsMapper;

    @Autowired
    DeviceSpotcheckRecordDetailsMapper deviceSpotcheckRecordDetailsMapper;

    @Autowired
    DeviceSpotcheckModelsHeadMapper deviceSpotcheckModelsHeadMapper;

    @Autowired
    DeviceDocumentMainMapper deviceDocumentMainMapper;

    @Autowired
    ProductionProcessDeviceMapMapper mapMapper;

    @Autowired
    DeviceSpotcheckRecordHeadMapper headMapper;

    @Autowired
    BasicInfoUserDeviceProcessMapMapper userProcessMapper;

    @Autowired
    BasicInfoDeviceProcessMapper processMapper;

    @Autowired
    BasicInfoDeviceStatusMapper statusMapper;

    @Override
    public List<DeviceSpotcheckPlansGetDTO> getSpotCheckPlans(DeviceSpotcheckPlansPostDTO deviceSpotcheckPlansPostDTO) {
        UnifyTransform unifyTransform = new UnifyTransform();
        List<BasicInfoDeptDto> basicInfoDeptDtos = userDeviceService.getDeptByAuthId(deviceSpotcheckPlansPostDTO.getUserId());
        List<DeviceSpotcheckPlansGetDTO> deviceSpotcheckPlansGetDTOS = new ArrayList<>();
        DeviceSpotcheckModelsHeadExample example = new DeviceSpotcheckModelsHeadExample();
        for (int i = 0; i < basicInfoDeptDtos.size(); i++) {
            String sql = "select * from device_spotcheck_plans as a,basic_info_dept as b,device_spotcheck_models_head as c where (a.dept_code = b.code and a.model_code = c.code and a.dept_code = " + basicInfoDeptDtos.get(i).getCode() + ")";
            if (deviceSpotcheckPlansPostDTO.getCondition() != null && !deviceSpotcheckPlansPostDTO.getCondition().equals("")) {
                sql += "and (a.code like '%" + deviceSpotcheckPlansPostDTO.getCondition() + "%' or a.device_name like '%" + deviceSpotcheckPlansPostDTO.getCondition() + "%' or b.name like '%" + deviceSpotcheckPlansPostDTO.getCondition() + "%' or a.device_code like '%" + deviceSpotcheckPlansPostDTO.getCondition() + "%'or c.model_name like '%" + deviceSpotcheckPlansPostDTO.getCondition() + "%')";
            }
            List<DeviceSpotcheckPlans> deviceSpotcheckPlans = deviceSpotcheckPlansMapper.selectByCondition(sql);

            for (int j = 0; j < deviceSpotcheckPlans.size(); j++) {
                long modelCode = deviceSpotcheckPlans.get(j).getModelCode();//获取这个计划的模板id
                example.createCriteria().andCodeEqualTo(modelCode);
                DeviceSpotcheckModelsHead mHead = deviceSpotcheckModelsHeadMapper.selectByExample(example).get(0);
                example.clear();
                DeviceSpotcheckPlansGetDTO plansDTO = new DeviceSpotcheckPlansGetDTO();
                plansDTO.setPlanCode(deviceSpotcheckPlans.get(j).getCode());//计划编号
                plansDTO.setPlanName(mHead.getModelName());
                plansDTO.setDeviceCode(deviceSpotcheckPlans.get(j).getDeviceCode());//主设备编号
                plansDTO.setDeviceName(deviceSpotcheckPlans.get(j).getDeviceName());//设备名称
                plansDTO.setFixedassetsCode(deviceSpotcheckPlans.get(j).getFixedassetsCode());//固定资产编号
                plansDTO.setDeptName(basicInfoDeptDtos.get(i).getName());//部门名称
                plansDTO.setDeptCode(deviceSpotcheckPlans.get(j).getDeptCode());//部门编号
                plansDTO.setEffFlag(unifyTransform.realBoolean(deviceSpotcheckPlans.get(j).getEffFlag()));//有效性标志位
                deviceSpotcheckPlansGetDTOS.add(plansDTO);
            }
        }
        int size = 5;
        int page = 1;
        if (deviceSpotcheckPlansPostDTO.getSize() != null) {
            size = deviceSpotcheckPlansPostDTO.getSize();
        }
        if (deviceSpotcheckPlansPostDTO.getPage() != null) {
            page = deviceSpotcheckPlansPostDTO.getPage();
        }
        Page<DeviceSpotcheckPlansGetDTO> pageInfo = new Page<>(size, page, deviceSpotcheckPlansGetDTOS);
        return pageInfo.getList();
    }

    @Override
    public List<DeviceSpotcheckPlansGetDTO> getSpotCheckPlansV1(DeviceSpotcheckPlansPostDTO deviceSpotcheckPlansPostDTO) {
        UnifyTransform unifyTransform = new UnifyTransform();
        List<BasicInfoDeptDto> basicInfoDeptDtos = userDeviceService.getDeptByAuthId(deviceSpotcheckPlansPostDTO.getUserId());
        List<DeviceSpotcheckPlansGetDTO> deviceSpotcheckPlansGetDTOS = new ArrayList<>();
//        DeviceSpotcheckModelsHeadExample example = new DeviceSpotcheckModelsHeadExample();
        for (int i = 0; i < basicInfoDeptDtos.size(); i++) {
            String sql = "select * from device_spotcheck_plans as a,basic_info_dept as b,device_spotcheck_models_head as c,production_process_device_map as d where (a.dept_code = b.code and a.dept_code = d.dept_code and a.device_code = d.device_code and d.process_code in (SELECT process_code FROM basic_info_user_device_process_map where user_code = '" + deviceSpotcheckPlansPostDTO.getUserId() + "') and a.model_code = c.code and a.dept_code = " + basicInfoDeptDtos.get(i).getCode() + ")";
            if (deviceSpotcheckPlansPostDTO.getCondition() != null && !deviceSpotcheckPlansPostDTO.getCondition().equals("")) {
                sql += "and (a.code like '%" + deviceSpotcheckPlansPostDTO.getCondition() + "%' or a.device_name like '%" + deviceSpotcheckPlansPostDTO.getCondition() + "%' or b.name like '%" + deviceSpotcheckPlansPostDTO.getCondition() + "%' or a.device_code like '%" + deviceSpotcheckPlansPostDTO.getCondition() + "%'or c.model_name like '%" + deviceSpotcheckPlansPostDTO.getCondition() + "%')";
            }
            sql += "ORDER BY (a.fixedassets_code+0)";
            List<DeviceSpotcheckPlans> deviceSpotcheckPlans = deviceSpotcheckPlansMapper.selectByCondition(sql);

            for (int j = 0; j < deviceSpotcheckPlans.size(); j++) {
//                long modelCode = deviceSpotcheckPlans.get(j).getModelCode();//获取这个计划的模板id
//                example.createCriteria().andCodeEqualTo(modelCode);
//                DeviceSpotcheckModelsHead mHead = deviceSpotcheckModelsHeadMapper.selectByExample(example).get(0);
//                example.clear();
                //统计这个用户对当天对这个设备的点检次数
                Date st = unifyTransform.get0PointOfDay();
                Date et = unifyTransform.get24PointOfDay();
                DeviceSpotcheckRecordHeadExample headExample = new DeviceSpotcheckRecordHeadExample();
                headExample.createCriteria().andSpotcheckPeopleEqualTo(deviceSpotcheckPlansPostDTO.getUserId())
                        .andPlanCodeEqualTo(deviceSpotcheckPlans.get(j).getCode()).andDeviceCodeEqualTo(deviceSpotcheckPlans.get(j).getDeviceCode())
                        .andFinishTimeBetween(st, et);
                int num = headMapper.countByExample(headExample);
                DeviceSpotcheckPlansGetDTO plansDTO = new DeviceSpotcheckPlansGetDTO();
                plansDTO.setPlanCode(deviceSpotcheckPlans.get(j).getCode());//计划编号
//                plansDTO.setPlanName(mHead.getModelName());
                ProductionProcessDeviceMapExample example1 = new ProductionProcessDeviceMapExample();
                example1.createCriteria().andDeviceCodeEqualTo(deviceSpotcheckPlans.get(j).getDeviceCode());
                ProductionProcessDeviceMap map = mapMapper.selectByExample(example1).get(0);
                plansDTO.setProcessCode(map.getProcessCode());//所属工序id
                plansDTO.setProcessName(map.getProcessName());//所属工序名称
                plansDTO.setDeviceCode(deviceSpotcheckPlans.get(j).getDeviceCode());//主设备编号
                plansDTO.setDeviceName(deviceSpotcheckPlans.get(j).getDeviceName());//设备名称
                plansDTO.setFixedassetsCode(deviceSpotcheckPlans.get(j).getFixedassetsCode());//固定资产编号
                plansDTO.setDeptName(basicInfoDeptDtos.get(i).getName());//部门名称
                plansDTO.setDeptCode(deviceSpotcheckPlans.get(j).getDeptCode());//部门编号
                plansDTO.setNum(num);
                plansDTO.setEffFlag(unifyTransform.realBoolean(deviceSpotcheckPlans.get(j).getEffFlag()));//有效性标志位
                deviceSpotcheckPlansGetDTOS.add(plansDTO);
            }
        }
        int size = 5;
        int page = 1;
        if (deviceSpotcheckPlansPostDTO.getSize() != null) {
            size = deviceSpotcheckPlansPostDTO.getSize();
        }
        if (deviceSpotcheckPlansPostDTO.getPage() != null) {
            page = deviceSpotcheckPlansPostDTO.getPage();
        }
        Page<DeviceSpotcheckPlansGetDTO> pageInfo = new Page<>(size, page, deviceSpotcheckPlansGetDTOS);
        return pageInfo.getList();
    }

    @Override
    public DeviceSpotcheckRecordDetailDTO generateRecord(SpotcheckRecordDTO spotcheckRecordDTO) {
        long planCode = spotcheckRecordDTO.getPlanCode();
        int userId = spotcheckRecordDTO.getUserId();
        //根据点检计划单号 查询点检计划的基础数据
        DeviceSpotcheckPlansExample example = new DeviceSpotcheckPlansExample();
        example.createCriteria().andCodeEqualTo(planCode);
        List<DeviceSpotcheckPlans> plans = deviceSpotcheckPlansMapper.selectByExample(example);
        DeviceSpotcheckPlans plan = plans.size() == 0 ? null : plans.get(0);
        Assert.notNull(plan, "不存在这条记录");
        //拿到点检计划的基础数据 生成一条点检记录 插入数据库
//        DeviceSpotcheckRecordHead deviceSpotcheckRecordHead = new DeviceSpotcheckRecordHead();
//        deviceSpotcheckRecordHead.setPlanCode(plan.getCode());
//        deviceSpotcheckRecordHead.setDeviceCode(plan.getDeviceCode());
//        deviceSpotcheckRecordHead.setFixedassetsCode(plan.getFixedassetsCode());
//        deviceSpotcheckRecordHead.setDeviceName(plan.getDeviceName());
//        deviceSpotcheckRecordHead.setDeptCode(plan.getDeptCode());
//        deviceSpotcheckRecordHead.setEditFlag(0);//edit_flag 置为 0
//        deviceSpotcheckRecordHead.setReceiveTime(new Date());//接单时间为当前时间
//        deviceSpotcheckRecordHead.setReceivePeople(userId);//接单人
//        deviceSpotcheckRecordHead.setSpotcheckPeople(userId);//点检人
//        deviceSpotcheckRecordHeadMapper.insertSelective(deviceSpotcheckRecordHead);

//        long recordCode = deviceSpotcheckRecordHead.getCode();

        DeviceSpotcheckModelsDetailsExample example1 = new DeviceSpotcheckModelsDetailsExample();
        example1.createCriteria().andModelCodeEqualTo(plan.getModelCode());
        List<DeviceSpotcheckModelsDetails> deviceSpotcheckModelsDetails = deviceSpotcheckModelsDetailsMapper.selectByExample(example1);

        List<SpotcheckItemDetailDTO> detailDTOList = new ArrayList<>();

        for (DeviceSpotcheckModelsDetails d : deviceSpotcheckModelsDetails) {
//            long modelDetailCode = d.getCode();
            String spotcheckItems = d.getSpotcheckItems();
            String spotcheckContent = d.getSpotcheckContent();
            String spotcheckAddress = d.getSpotcheckAddress() == null ? "" : d.getSpotcheckAddress();
            //http://img.ivsky.com/img/tupian/pre/201901/18/shengdanjie-003.jpg
//            DeviceSpotcheckRecordDetails temp = new DeviceSpotcheckRecordDetails();
//            temp.setRecordCode(recordCode);
//            temp.setModelDetailCode(modelDetailCode);
//            temp.setSpotcheckItems(spotcheckItems);
//            temp.setSpotcheckContent(spotcheckContent);
//            deviceSpotcheckRecordDetailsMapper.insertSelective(temp);
            SpotcheckItemDetailDTO sidDTO = new SpotcheckItemDetailDTO();
//            sidDTO.setItemId(temp.getCode());
            sidDTO.setSpotcheckItem(spotcheckItems);
            sidDTO.setSpotcheckContent(spotcheckContent);
            sidDTO.setSpotcheckAddress(spotcheckAddress);
            sidDTO.setMainValues(-1);
            sidDTO.setMainContent(null);
            detailDTOList.add(sidDTO);
        }

        long deviceCode = plan.getDeviceCode();
        DeviceDocumentMainExample deviceDocumentMainExample = new DeviceDocumentMainExample();
        deviceDocumentMainExample.createCriteria().andCodeEqualTo(deviceCode);
        Integer statusCode = deviceDocumentMainMapper.selectByExample(deviceDocumentMainExample).get(0).getStatusCode();

        DeviceSpotcheckRecordDetailDTO dto = new DeviceSpotcheckRecordDetailDTO();
        dto.setDeviceName(plan.getDeviceName());
        dto.setFixedassetsCode(plan.getFixedassetsCode());
        dto.setPlanCode(plan.getCode());
        dto.setDeviceCode(plan.getDeviceCode());
//        dto.setRecordId(recordCode);
        dto.setDeviceStatus(statusMapper.selectByPrimaryKey(statusCode).getName());
        long modelCode = plan.getModelCode();
        DeviceSpotcheckModelsHeadExample example2 = new DeviceSpotcheckModelsHeadExample();
        example2.createCriteria().andCodeEqualTo(modelCode);
        List<DeviceSpotcheckModelsHead> modelsHeads = deviceSpotcheckModelsHeadMapper.selectByExample(example2);
        DeviceSpotcheckModelsHead head = modelsHeads.size() == 0 ? null : modelsHeads.get(0);
        Assert.notNull(head, "不存在这条记录");
        dto.setPlanName(head.getModelName());
        List<BasicInfoDeptDto> basicInfoDeptDtos = userDeviceService.getDeptByAuthId(userId);
        for (int i = 0; i < basicInfoDeptDtos.size(); i++) {
            if (basicInfoDeptDtos.get(i).getCode() == plan.getDeptCode()) {
                dto.setDeptName(basicInfoDeptDtos.get(i).getName());
            }
        }
        dto.setDetailDTOList(detailDTOList);
        return dto;
    }

    @Override
    public List<DeviceSpotcheckRecordHistoryGetDTO> today(DeviceSpotcheckRecordHistoryPostDTO dto) {
        List<DeviceSpotcheckRecordHistoryGetDTO> deviceSpotcheckRecordHistoryGetDTOS = new ArrayList<>();
        int userId = dto.getUserId();
        String time0 = DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00");
        String time1 = DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59");
        String sql = "SELECT * FROM device_spotcheck_record_head WHERE spotcheck_people = '" + userId + "' and (finish_time BETWEEN '" + time0 + "' AND '" + time1 + "' or confirm_time BETWEEN '" + time0 + "' AND '" + time1 + "') ORDER BY finish_time DESC,(fixedassets_code+0) ASC";
        List<DeviceSpotcheckRecordHead> heads = headMapper.selectByCondition(sql);
        for (DeviceSpotcheckRecordHead head : heads) {
            DeviceSpotcheckRecordHistoryGetDTO dto1 = new DeviceSpotcheckRecordHistoryGetDTO();
            dto1.setRecordId(head.getCode());
            dto1.setDeviceName(head.getDeviceName());
            dto1.setFixedassetsCode(head.getFixedassetsCode());
            dto1.setFinishTime(head.getFinishTime());
//            dto1.setSpotcheckPeople();
            dto1.setConfirmTime(head.getConfirmTime());
//            dto1.setConfirmPeople();
            dto1.setFlag(head.getEditFlag());
            deviceSpotcheckRecordHistoryGetDTOS.add(dto1);
        }
        int size = dto.getSize() == null ? 5 : dto.getSize();
        int page = dto.getPage() == null ? 1 : dto.getPage();
        return new Page(size, page, deviceSpotcheckRecordHistoryGetDTOS).getList();
    }

    @Override
    public List<DeviceSpotcheckRecordHistoryGetDTO> historyV1(DeviceSpotcheckRecordHistoryPostDTO dto) {
        List<DeviceSpotcheckRecordHistoryGetDTO> deviceSpotcheckRecordHistoryGetDTOS = new ArrayList<>();
        int userId = dto.getUserId();

        BasicInfoUserDeviceProcessMapExample mapExample = new BasicInfoUserDeviceProcessMapExample();
        mapExample.createCriteria().andUserCodeEqualTo(userId);
        List<BasicInfoUserDeviceProcessMap> maps = userProcessMapper.selectByExample(mapExample);
        BasicInfoUserDeviceProcessMap map = maps.size() == 0 ? null : maps.get(0);
        Assert.notNull(map, "此用户还未分配工序");

        short defaultProcessCode = map.getProcessCode();
        short processCode = dto.getProcessCode() == null ? defaultProcessCode : dto.getProcessCode();

        //默认起止日期是近一个月
        Date currentTime = new Date();
        long k = 3600;
        Date startTime = new Date(currentTime.getTime() - k * 30 * 24 * 1000);
        if (dto.getStartTime() != null) {
            startTime = dto.getStartTime();
        }
        Date endTime = new Date(currentTime.getTime() + 1 * 24 * 3600 * 1000);
        if (dto.getEndTime() != null) {
//            endTime = dto.getEndTime();
            endTime = new Date(dto.getEndTime().getTime() + 1 * 24 * 3600 * 1000);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sql = "SELECT * FROM `device_spotcheck_record_head` WHERE ((finish_time BETWEEN '" + sdf.format(startTime) + "' AND '" + sdf.format(endTime) + "' ) OR (confirm_time BETWEEN '" + sdf.format(startTime) + "' AND '" + sdf.format(endTime) + "')) AND receive_people = '" + userId + "' AND spotcheck_people = '" + userId + "' AND process_code = '" + processCode + "' ORDER BY finish_time DESC,(fixedassets_code+0)";
        List<DeviceSpotcheckRecordHead> heads1 = deviceSpotcheckRecordHeadMapper.selectByCondition(sql);
        for (DeviceSpotcheckRecordHead head : heads1) {
            DeviceSpotcheckRecordHistoryGetDTO dto1 = new DeviceSpotcheckRecordHistoryGetDTO();
            dto1.setRecordId(head.getCode());
            dto1.setDeviceName(head.getDeviceName());
            dto1.setFixedassetsCode(head.getFixedassetsCode());
            dto1.setFinishTime(head.getFinishTime());
//            dto1.setSpotcheckPeople();
            dto1.setConfirmTime(head.getConfirmTime());
//            dto1.setConfirmPeople();
            dto1.setFlag(head.getEditFlag());
            deviceSpotcheckRecordHistoryGetDTOS.add(dto1);
        }

        int size = 5;
        int page = 1;
        if (dto.getPage() != null) {
            page = dto.getPage();
        }
        if (dto.getSize() != null) {
            size = dto.getSize();
        }
        Page<DeviceSpotcheckRecordHistoryGetDTO> pageInfo =
                new Page<>(size, page, deviceSpotcheckRecordHistoryGetDTOS);
        return pageInfo.getList();
    }

    @Override
    public List<DeviceSpotcheckRecordHistoryGetDTO> history(DeviceSpotcheckRecordHistoryPostDTO dto) {
        List<DeviceSpotcheckRecordHistoryGetDTO> deviceSpotcheckRecordHistoryGetDTOS = new ArrayList<>();
        int userId = dto.getUserId();
        long deviceCode = dto.getDeviceCode();
        int statusId = dto.getStatusId();//待确认 1 已确认 2
        //查询当前用户 当前设备 历史点检记录 默认起止日期是近一个月
        Date currentTime = new Date();
        long k = 3600;
        Date startTime = new Date(currentTime.getTime() - k * 30 * 24 * 1000);
        if (dto.getStartTime() != null) {
            startTime = dto.getStartTime();
        }
        Date endTime = new Date(currentTime.getTime() + 1 * 24 * 3600 * 1000);
        if (dto.getEndTime() != null) {
//            endTime = dto.getEndTime();
            endTime = new Date(dto.getEndTime().getTime() + 1 * 24 * 3600 * 1000);
        }
        DeviceSpotcheckRecordHeadExample example = new DeviceSpotcheckRecordHeadExample();
        if (statusId == 1) {
            example.createCriteria().andFinishTimeBetween(startTime, endTime).andReceivePeopleEqualTo(userId).andSpotcheckPeopleEqualTo(userId).andEditFlagEqualTo(statusId).andDeviceCodeEqualTo(deviceCode);
        } else {
            example.createCriteria().andConfirmTimeBetween(startTime, endTime).andReceivePeopleEqualTo(userId).andSpotcheckPeopleEqualTo(userId).andEditFlagEqualTo(statusId).andDeviceCodeEqualTo(deviceCode);
        }
        List<DeviceSpotcheckRecordHead> heads = deviceSpotcheckRecordHeadMapper.selectByExample(example);
        for (int i = 0; i < heads.size(); i++) {
            DeviceSpotcheckRecordHistoryGetDTO spDTO = new DeviceSpotcheckRecordHistoryGetDTO();
            spDTO.setRecordId(heads.get(i).getCode());
            spDTO.setDeviceName(heads.get(i).getDeviceName());
            spDTO.setFixedassetsCode(heads.get(i).getFixedassetsCode());
            spDTO.setFinishTime(heads.get(i).getFinishTime());
            spDTO.setConfirmTime(heads.get(i).getConfirmTime());
            if (heads.get(i).getConfirmPeople() != null) {
                AuthUserDTO authUserDTO = authUserService.findById(heads.get(i).getConfirmPeople());
                spDTO.setConfirmPeople(authUserDTO.getName());
            }
            deviceSpotcheckRecordHistoryGetDTOS.add(spDTO);
        }
        int size = 5;
        int page = 1;
        if (dto.getPage() != null) {
            page = dto.getPage();
        }
        if (dto.getSize() != null) {
            size = dto.getSize();
        }
        Page<DeviceSpotcheckRecordHistoryGetDTO> pageInfo =
                new Page<>(size, page, deviceSpotcheckRecordHistoryGetDTOS);
        return pageInfo.getList();
    }

    @Override
    public DeviceSpotcheckRecordDetailDTO detail(IdDto idDto) {
        DeviceSpotcheckRecordDetailDTO dto = new DeviceSpotcheckRecordDetailDTO();
        long recordId = idDto.getId();
        DeviceSpotcheckRecordHeadExample example = new DeviceSpotcheckRecordHeadExample();
        example.createCriteria().andCodeEqualTo(recordId);
        DeviceSpotcheckRecordHead head = deviceSpotcheckRecordHeadMapper.selectByExample(example).get(0);
        long planCode = head.getPlanCode();

        long deviceCode = head.getDeviceCode();
        DeviceDocumentMainExample deviceDocumentMainExample = new DeviceDocumentMainExample();
        deviceDocumentMainExample.createCriteria().andCodeEqualTo(deviceCode);
        Integer statusCode = deviceDocumentMainMapper.selectByExample(deviceDocumentMainExample).get(0).getStatusCode();

        DeviceSpotcheckPlansExample example1 = new DeviceSpotcheckPlansExample();
        example1.createCriteria().andCodeEqualTo(planCode);
        DeviceSpotcheckPlans plan = deviceSpotcheckPlansMapper.selectByExample(example1).get(0);
        long modelCode = plan.getModelCode();
        DeviceSpotcheckModelsHeadExample example2 = new DeviceSpotcheckModelsHeadExample();
        example2.createCriteria().andCodeEqualTo(modelCode);
        DeviceSpotcheckModelsHead modelsHead = deviceSpotcheckModelsHeadMapper.selectByExample(example2).get(0);
        String modelName = modelsHead.getModelName();
        dto.setDeviceName(head.getDeviceName());//设备名称
        dto.setFixedassetsCode(head.getFixedassetsCode());//固定资产编码
        dto.setPlanCode(planCode);//计划编号
        dto.setPlanName(modelName);//点检名称
        dto.setDeviceStatus(statusMapper.selectByPrimaryKey(statusCode).getName());
        int userId = idDto.getUserId();
        List<BasicInfoDeptDto> basicInfoDeptDtos = userDeviceService.getDeptByAuthId(userId);
        for (int i = 0; i < basicInfoDeptDtos.size(); i++) {
            if (basicInfoDeptDtos.get(i).getCode() == head.getDeptCode()) {
                dto.setDeptName(basicInfoDeptDtos.get(i).getName());
            }
        }
        dto.setFinishTime(head.getFinishTime());
        if (head.getSpotcheckPeople() != null) {
            AuthUserDTO authUserDTO = authUserService.findById(head.getSpotcheckPeople());
            dto.setSpotcheckPeople(authUserDTO.getName());
        }
        dto.setConfirmTime(head.getConfirmTime());
        if (head.getConfirmPeople() != null) {
            AuthUserDTO authUserDTO = authUserService.findById(head.getConfirmPeople());
            dto.setConfirmPeople(authUserDTO.getName());
        }
        dto.setSpotcheckComment(head.getSpotcheckComment());
        dto.setScanTime(head.getScanTime());

        List<SpotcheckItemDetailDTO> detailDTOList = new ArrayList<>();
        DeviceSpotcheckRecordDetailsExample example3 = new DeviceSpotcheckRecordDetailsExample();
        example3.createCriteria().andRecordCodeEqualTo(recordId);
        List<DeviceSpotcheckRecordDetails> details = deviceSpotcheckRecordDetailsMapper.selectByExample(example3);
        for (int u = 0; u < details.size(); u++) {
            SpotcheckItemDetailDTO itemDetailDTO = new SpotcheckItemDetailDTO();
            itemDetailDTO.setItemId(details.get(u).getCode());
            itemDetailDTO.setSpotcheckItem(details.get(u).getSpotcheckItems());
            itemDetailDTO.setSpotcheckContent(details.get(u).getSpotcheckContent());
            itemDetailDTO.setMainValues(details.get(u).getMainValues());
            itemDetailDTO.setMainContent(details.get(u).getMainContent());
            long modelDetailCode = details.get(u).getModelDetailCode();
            DeviceSpotcheckModelsDetailsExample hello = new DeviceSpotcheckModelsDetailsExample();
            hello.createCriteria().andCodeEqualTo(modelDetailCode);
            DeviceSpotcheckModelsDetails world = deviceSpotcheckModelsDetailsMapper.selectByExample(hello).get(0);
            itemDetailDTO.setSpotcheckAddress(world.getSpotcheckAddress());
            detailDTOList.add(itemDetailDTO);
        }
        dto.setDetailDTOList(detailDTOList);
        return dto;
    }

    @Override
    public int updateByFlag(DeviceSpotcheckUpdateDTO dto) {
        //第一步 在头表 生成一条点检记录
        long planCode = dto.getPlanCode();
        int userId = dto.getUserId();

        //2019-12-03 更新设备基础数据表的设备状态
        int statusCode = dto.getStatusCode();
        DeviceDocumentMainExample mainExample = new DeviceDocumentMainExample();
        mainExample.createCriteria().andCodeEqualTo(dto.getDeviceCode());
        DeviceDocumentMain deviceDocumentMain = new DeviceDocumentMain();
        deviceDocumentMain.setStatusCode(statusCode);
        deviceDocumentMainMapper.updateByExampleSelective(deviceDocumentMain, mainExample);

        //根据点检计划单号 查询点检计划的基础数据
        DeviceSpotcheckPlansExample example = new DeviceSpotcheckPlansExample();
        example.createCriteria().andCodeEqualTo(planCode);
        List<DeviceSpotcheckPlans> plans = deviceSpotcheckPlansMapper.selectByExample(example);
        DeviceSpotcheckPlans plan = plans.size() == 0 ? null : plans.get(0);
        Assert.notNull(plan, "不存在这条记录");

        //拿到点检计划的基础数据 生成一条点检记录 添加数据 插入数据库
        DeviceSpotcheckRecordHead deviceSpotcheckRecordHead = new DeviceSpotcheckRecordHead();
        deviceSpotcheckRecordHead.setPlanCode(plan.getCode());
        deviceSpotcheckRecordHead.setDeviceCode(plan.getDeviceCode());
        deviceSpotcheckRecordHead.setFixedassetsCode(plan.getFixedassetsCode());
        deviceSpotcheckRecordHead.setDeviceName(plan.getDeviceName());
        deviceSpotcheckRecordHead.setDeptCode(plan.getDeptCode());
        ProductionProcessDeviceMapExample mapExample = new ProductionProcessDeviceMapExample();
        mapExample.createCriteria().andDeviceCodeEqualTo(plan.getDeviceCode());
        deviceSpotcheckRecordHead.setProcessCode(mapMapper.selectByExample(mapExample).get(0).getProcessCode());
//        deviceSpotcheckRecordHead.setEditFlag(0);//edit_flag 置为 0
        deviceSpotcheckRecordHead.setReceiveTime(new Date());//接单时间为当前时间
        deviceSpotcheckRecordHead.setReceivePeople(userId);//接单人
        deviceSpotcheckRecordHead.setSpotcheckPeople(userId);//点检人
        deviceSpotcheckRecordHead.setScanIdCode(dto.getScanIdCode());
        deviceSpotcheckRecordHead.setScanTime(dto.getScanTime());
        deviceSpotcheckRecordHead.setSpotcheckComment(dto.getSpotcheckComment());
        deviceSpotcheckRecordHead.setFinishTime(new Date());
        deviceSpotcheckRecordHead.setEditFlag(1);
        deviceSpotcheckRecordHeadMapper.insertSelective(deviceSpotcheckRecordHead);

        //从模板获取详情数据 插入数据库
        DeviceSpotcheckModelsDetailsExample example1 = new DeviceSpotcheckModelsDetailsExample();
        example1.createCriteria().andModelCodeEqualTo(plan.getModelCode());
        List<DeviceSpotcheckModelsDetails> deviceSpotcheckModelsDetails = deviceSpotcheckModelsDetailsMapper.selectByExample(example1);

        long recordCode = deviceSpotcheckRecordHead.getCode();

        for (DeviceSpotcheckModelsDetails d : deviceSpotcheckModelsDetails) {
            DeviceSpotcheckRecordDetails temp = new DeviceSpotcheckRecordDetails();
            temp.setRecordCode(recordCode);
            temp.setModelDetailCode(d.getCode());
            temp.setSpotcheckItems(d.getSpotcheckItems());
            temp.setSpotcheckContent(d.getSpotcheckContent());
            deviceSpotcheckRecordDetailsMapper.insertSelective(temp);
        }

        //点检结果更新到数据库
        List<SpotcheckItemDetailDTO> itemDTOs = dto.getItemDTOs();

        for (int i = 0; i < itemDTOs.size(); i++) {
            DeviceSpotcheckRecordDetailsExample example2 = new DeviceSpotcheckRecordDetailsExample();
            example2.createCriteria().andRecordCodeEqualTo(recordCode).andModelDetailCodeEqualTo(deviceSpotcheckModelsDetails.get(i).getCode());
            DeviceSpotcheckRecordDetails details = new DeviceSpotcheckRecordDetails();
            details.setMainValues(itemDTOs.get(i).getMainValues());
            details.setMainContent(itemDTOs.get(i).getMainContent());
            deviceSpotcheckRecordDetailsMapper.updateByExampleSelective(details, example2);
        }
        return 0;
    }

    @Override
    public List<DeviceSpotcheckRecordHistoryGetDTO> page(DeviceSpotcheckRecordHistoryPostDTO dto) {
        List<DeviceSpotcheckRecordHistoryGetDTO> deviceSpotcheckRecordHistoryGetDTOS = new ArrayList<>();
        int userId = dto.getUserId();
        List<BasicInfoDeptDto> basicInfoDeptDtos = userDeviceService.getDeptByAuthId(userId);
        int statusId = dto.getStatusId();//待确认 1 已确认 2

        Date currentTime = new Date();
        long k = 3600;
        Date startTime = new Date(currentTime.getTime() - k * 30 * 24 * 1000);
        if (dto.getStartTime() != null) {
            startTime = dto.getStartTime();
        }
        Date endTime = new Date(currentTime.getTime() + 1 * 24 * 3600 * 1000);
        if (dto.getEndTime() != null) {
//            endTime = dto.getEndTime();
            endTime = new Date(dto.getEndTime().getTime() + 1 * 24 * 3600 * 1000);
        }
        DeviceSpotcheckRecordHeadExample example = new DeviceSpotcheckRecordHeadExample();
        for (int f = 0; f < basicInfoDeptDtos.size(); f++) {
            int deptCode = basicInfoDeptDtos.get(f).getCode();
            System.out.println(deptCode);
            if (statusId == 1) {
                example.createCriteria().andFinishTimeBetween(startTime, endTime).andEditFlagEqualTo(statusId).andDeptCodeEqualTo(deptCode);
            } else {
                example.createCriteria().andConfirmTimeBetween(startTime, endTime).andEditFlagEqualTo(statusId).andDeptCodeEqualTo(deptCode);
            }
            List<DeviceSpotcheckRecordHead> heads = deviceSpotcheckRecordHeadMapper.selectByExample(example);
            example.clear();
            for (int i = 0; i < heads.size(); i++) {
                DeviceSpotcheckRecordHistoryGetDTO spDTO = new DeviceSpotcheckRecordHistoryGetDTO();
                spDTO.setRecordId(heads.get(i).getCode());
                spDTO.setDeviceName(heads.get(i).getDeviceName());
                spDTO.setFixedassetsCode(heads.get(i).getFixedassetsCode());
                spDTO.setFinishTime(heads.get(i).getFinishTime());
                spDTO.setConfirmTime(heads.get(i).getConfirmTime());
                if (heads.get(i).getSpotcheckPeople() != null) {
                    AuthUserDTO authUserDTO = authUserService.findById(heads.get(i).getSpotcheckPeople());
                    spDTO.setSpotcheckPeople(authUserDTO.getName());
                }
                if (heads.get(i).getConfirmPeople() != null) {
                    AuthUserDTO authUserDTO = authUserService.findById(heads.get(i).getConfirmPeople());
                    spDTO.setConfirmPeople(authUserDTO.getName());
                }
                deviceSpotcheckRecordHistoryGetDTOS.add(spDTO);
            }
        }

        int size = 5;
        int page = 1;
        if (dto.getPage() != null) {
            page = dto.getPage();
        }
        if (dto.getSize() != null) {
            size = dto.getSize();
        }
        Page<DeviceSpotcheckRecordHistoryGetDTO> pageInfo =
                new Page<>(size, page, deviceSpotcheckRecordHistoryGetDTOS);
        return pageInfo.getList();
    }

    @Override
    public int managerConfirm(IdDto idDto) {
        long recordId = idDto.getId();
        int userId = idDto.getUserId();

        DeviceSpotcheckRecordHeadExample example = new DeviceSpotcheckRecordHeadExample();
        example.createCriteria().andCodeEqualTo(recordId);
        DeviceSpotcheckRecordHead head = new DeviceSpotcheckRecordHead();
        head.setConfirmPeople(userId);
        head.setConfirmTime(new Date());
        head.setEditFlag(2);

        return deviceSpotcheckRecordHeadMapper.updateByExampleSelective(head, example);
    }

    @Override
    public SpotcheckNFCResultDTO getDataByNfc(SpotcheckNFCDTO dto) {
        SpotcheckNFCResultDTO resultDTO = new SpotcheckNFCResultDTO();
        int userId = dto.getUserId();
        String icCardCode = dto.getIcCardCode();

        List<BasicInfoDeptDto> basicInfoDeptDtos = userDeviceService.getDeptByAuthId(userId);
        List<Integer> bIds = new ArrayList<>();
        for (BasicInfoDeptDto b : basicInfoDeptDtos) {
            bIds.add(b.getCode());
        }
        //根据卡号获取设备信息
        DeviceDocumentMainExample example = new DeviceDocumentMainExample();
        example.createCriteria().andIdCodeEqualTo(icCardCode).andDeptCodeIn(bIds);
        resultDTO.setScanTime(new Date());
        List<DeviceDocumentMain> deviceDocumentMains = deviceDocumentMainMapper.selectByExample(example);
        DeviceDocumentMain device = deviceDocumentMains.size() == 0 ? null : deviceDocumentMains.get(0);
        Assert.notNull(device, "不存在对应的设备信息");

        resultDTO.setDeviceId(device.getFixedassetsCode());
        resultDTO.setDeviceName(device.getDeviceName());

        return resultDTO;
    }

    @Override
    public List getProcessById(IdDto idDto) {
        BasicInfoUserDeviceProcessMapExample example = new BasicInfoUserDeviceProcessMapExample();
        example.createCriteria().andUserCodeEqualTo(idDto.getUserId());
        List<BasicInfoUserDeviceProcessMap> maps = userProcessMapper.selectByExample(example);
        List<BasicInfoDeviceProcess> list = new ArrayList<>();
        for (BasicInfoUserDeviceProcessMap map : maps) {
            BasicInfoDeviceProcess record = processMapper.selectByPrimaryKey(map.getProcessCode());
            list.add(record);
        }
        return list;
    }

    @Override
    public String upload(MultipartFile file) throws IOException {
        String fileCode = UploadUtil.uploadPic(AddressEnum.getCurrentPath(AddressEnum.DEVICE_SPOTCHECK_RECORD.getCode()), file, 10);
        return fileCode;
    }

    @Override
    public void cancelLoad(String path) {
        UploadUtil.deleteFile(AddressEnum.getCurrentPath(AddressEnum.DEVICE_SPOTCHECK_RECORD.getCode()), path);
    }

//    @Override
//    public List<DeviceSpotcheckRecordHistoryGetDTO> pageToday(DeviceSpotcheckRecordHistoryPostDTO dto) {
//        List<DeviceSpotcheckRecordHistoryGetDTO> deviceSpotcheckRecordHistoryGetDTOS = new ArrayList<>();
//
//        UnifyTransform unifyTransform = new UnifyTransform();
//
//        int userId = dto.getUserId();
//
//        BasicInfoUserDeviceProcessMapExample mapExample = new BasicInfoUserDeviceProcessMapExample();
//        mapExample.createCriteria().andUserCodeEqualTo(userId);
//        List<BasicInfoUserDeviceProcessMap> maps = userProcessMapper.selectByExample(mapExample);
//        BasicInfoUserDeviceProcessMap map = maps.size() == 0 ? null : maps.get(0);
//        Assert.notNull(map, "此用户还未分配工序");
//
//        short defaultProcessCode = map.getProcessCode();
//        short processCode = dto.getProcessCode() == null ? defaultProcessCode : dto.getProcessCode();
//
//        Date d0 = unifyTransform.get0PointOfDay();
//        Date d1 = unifyTransform.get24PointOfDay();
//
//        DeviceSpotcheckRecordHeadExample example = new DeviceSpotcheckRecordHeadExample();
//        DeviceSpotcheckRecordHeadExample.Criteria criteria1 = example.createCriteria();
//        criteria1.andProcessCodeEqualTo(processCode).andFinishTimeBetween(d0, d1);
//
//        DeviceSpotcheckRecordHeadExample.Criteria criteria2 = example.createCriteria();
//        criteria2.andProcessCodeEqualTo(processCode).andConfirmTimeBetween(d0, d1);
//
//        example.or(criteria2);
//        List<DeviceSpotcheckRecordHead> heads = headMapper.selectByExample(example);
//
//        for (DeviceSpotcheckRecordHead head : heads) {
//            DeviceSpotcheckRecordHistoryGetDTO dto1 = new DeviceSpotcheckRecordHistoryGetDTO();
//            dto1.setRecordId(head.getCode());
//            dto1.setDeviceName(head.getDeviceName());
//            dto1.setFixedassetsCode(head.getFixedassetsCode());
//            dto1.setFinishTime(head.getFinishTime());
//            dto1.setSpotcheckPeople(head.getSpotcheckPeople() == null ? null : authUserService.findById(head.getSpotcheckPeople()).getName());
//            dto1.setConfirmTime(head.getConfirmTime());
////            dto1.setConfirmPeople();
//            dto1.setFlag(head.getEditFlag());
//            dto1.setProcessCode(head.getProcessCode());
//            dto1.setProcessName(processMapper.selectByPrimaryKey(head.getProcessCode()).getProcessName());
//            DeviceSpotcheckRecordDetailsExample detailsExample = new DeviceSpotcheckRecordDetailsExample();
//            detailsExample.createCriteria().andRecordCodeEqualTo(head.getCode()).andMainValuesEqualTo(1);
//            int num = deviceSpotcheckRecordDetailsMapper.countByExample(detailsExample);
//            dto1.setErrorNum(num);
//            deviceSpotcheckRecordHistoryGetDTOS.add(dto1);
//        }
//        deviceSpotcheckRecordHistoryGetDTOS.sort((DeviceSpotcheckRecordHistoryGetDTO o1, DeviceSpotcheckRecordHistoryGetDTO o2) -> (o1.getErrorNum() - o2.getErrorNum()) >= 0 ? -1 : 1);
//        int size = dto.getSize() == null ? 5 : dto.getSize();
//        int page = dto.getPage() == null ? 1 : dto.getPage();
//        Page pageInfo = new Page(size, page, deviceSpotcheckRecordHistoryGetDTOS);
//        return pageInfo.getList();
//    }

    @Override
    public List<DeviceSpotcheckRecordHistoryGetDTO> pageAbnormal(DeviceSpotcheckRecordHistoryPostDTO dto) {
        List<DeviceSpotcheckRecordHistoryGetDTO> dtoList = new ArrayList<>();
        UnifyTransform unifyTransform = new UnifyTransform();

        int userId = dto.getUserId();

        int statusId = dto.getStatusId();

        BasicInfoUserDeviceProcessMapExample mapExample = new BasicInfoUserDeviceProcessMapExample();
        mapExample.createCriteria().andUserCodeEqualTo(userId);
        List<BasicInfoUserDeviceProcessMap> maps = userProcessMapper.selectByExample(mapExample);
        BasicInfoUserDeviceProcessMap map = maps.size() == 0 ? null : maps.get(0);
        Assert.notNull(map, "此用户还未分配工序");

        //默认工序是该用户被分配的第一个工序
        short defaultProcessCode = map.getProcessCode();
        short processCode = dto.getProcessCode() == null ? defaultProcessCode : dto.getProcessCode();

        Date startTime;
        Date endTime;
        if (statusId == 0) {
            //今日记录
            startTime = unifyTransform.get0PointOfDay();
            endTime = unifyTransform.get24PointOfDay();
        } else {
            //默认起止日期是近一个月
            Date currentTime = new Date();
            long k = 3600;
            startTime = new Date(currentTime.getTime() - k * 30 * 24 * 1000);
            if (dto.getStartTime() != null) {
                startTime = dto.getStartTime();
            }
            endTime = new Date(currentTime.getTime() + 1 * 24 * 3600 * 1000);
            if (dto.getEndTime() != null) {
                endTime = new Date(dto.getEndTime().getTime() + 1 * 24 * 3600 * 1000);
            }
        }

        DeviceSpotcheckRecordHeadExample example = new DeviceSpotcheckRecordHeadExample();
        DeviceSpotcheckRecordHeadExample.Criteria criteria1 = example.createCriteria();
        criteria1.andProcessCodeEqualTo(processCode).andFinishTimeBetween(startTime, endTime);

        DeviceSpotcheckRecordHeadExample.Criteria criteria2 = example.createCriteria();
        criteria2.andProcessCodeEqualTo(processCode).andFinishTimeBetween(startTime, endTime);

        example.or(criteria2);

        List<DeviceSpotcheckRecordHead> heads = headMapper.selectByExample(example);

        for (DeviceSpotcheckRecordHead head : heads) {
            long recordCode = head.getCode();
            DeviceSpotcheckRecordDetailsExample example1 = new DeviceSpotcheckRecordDetailsExample();
            example1.createCriteria().andRecordCodeEqualTo(recordCode).andMainValuesEqualTo(1);//main_value==1 表示有异常
            int num = deviceSpotcheckRecordDetailsMapper.countByExample(example1);
            if ((statusId == 1 && num > 0) || statusId == 2 || statusId == 0) {
                DeviceSpotcheckRecordHistoryGetDTO dto1 = new DeviceSpotcheckRecordHistoryGetDTO();
                dto1.setRecordId(head.getCode());
                dto1.setDeviceName(head.getDeviceName());
                dto1.setFixedassetsCode(head.getFixedassetsCode());
                dto1.setFinishTime(head.getFinishTime());
                dto1.setSpotcheckPeople(head.getSpotcheckPeople() == null ? null : authUserService.findById(head.getSpotcheckPeople()).getName());
                dto1.setConfirmTime(head.getConfirmTime());
//            dto1.setConfirmPeople();
                dto1.setFlag(head.getEditFlag());
                dto1.setProcessCode(head.getProcessCode());
                dto1.setProcessName(processMapper.selectByPrimaryKey(head.getProcessCode()).getProcessName());
                dto1.setErrorNum(num);
                dtoList.add(dto1);
            }
        }
        dtoList.sort((x, y) -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            int a = Integer.parseInt(dateFormat.format(x.getFinishTime()));
            int b = Integer.parseInt(dateFormat.format(y.getFinishTime()));
            if (a == b) {
                return y.getErrorNum() - x.getErrorNum();
            } else {
                return b - a;
            }
        });
        int size = dto.getSize() == null ? 5 : dto.getSize();
        int page = dto.getPage() == null ? 1 : dto.getPage();
        Page pageInfo = new Page(size, page, dtoList);
        return pageInfo.getList();
    }

    @Override
    public List getDeviceStatus() {
        BasicInfoDeviceStatusExample example = new BasicInfoDeviceStatusExample();
        example.createCriteria();
        return statusMapper.selectByExample(example);
    }
}