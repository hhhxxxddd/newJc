package com.jinchi.app.service;

import com.jinchi.app.domain.*;
import com.jinchi.app.dto.*;
import com.jinchi.app.mapper.AppDeviceStatisticDetailMapper;
import com.jinchi.app.mapper.AppDeviceStatisticHeadMapper;
import com.jinchi.app.mapper.BasicInfoDeptMapper;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-11 16:52
 * @description:
 **/
@Service
public class DeviceDocumentStatisticServiceImp implements DeviceDocumentStatisticService {

    @Autowired
    BasicInfoDeptMapper basicInfoDeptMapper;

    @Autowired
    AppDeviceStatisticHeadMapper appDeviceStatisticHeadMapper;

    @Autowired
    AppDeviceStatisticDetailMapper appDeviceStatisticDetailMapper;

    @Override
    public List<BasicInfoDeptDto> getAll(DeptConditionDTO dto) {
        List<BasicInfoDeptDto> deptDtos = new ArrayList<>();
        List<BasicInfoDept> infoDeptList;

        BasicInfoDeptExample example = new BasicInfoDeptExample();

        if (dto.getCondition() == null || dto.getCondition().equals("")) {
            example.createCriteria().andParentCodeIsNull();
            infoDeptList = basicInfoDeptMapper.selectByExample(example);
        } else {
            example.createCriteria().andParentCodeIsNull().andNameLike("%" + dto.getCondition() + "%");
            infoDeptList = basicInfoDeptMapper.selectByExample(example);
        }

        for (int i = 0; i < infoDeptList.size(); i++) {
            BasicInfoDeptDto basicInfoDeptDto = new BasicInfoDeptDto();
            basicInfoDeptDto.setCode(infoDeptList.get(i).getCode());
            basicInfoDeptDto.setName(infoDeptList.get(i).getName());
            deptDtos.add(basicInfoDeptDto);
        }
        int size = 5;
        int page = 1;
        if (dto.getSize() != null) {
            size = dto.getSize();
        }
        if (dto.getPage() != null) {
            page = dto.getPage();
        }
        Page<BasicInfoDeptDto> pageInfo = new Page<>(size, page, deptDtos);
        return pageInfo.getList();
    }

    @Override
    public DeviceDocumentStatisticDTO getDataByDateAndDept(DeviceDocumentStatisticConditionDTO dto) {
        DeviceDocumentStatisticDTO statisticDTO = new DeviceDocumentStatisticDTO();
        Date startTime;
        Date endTime;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int a = calendar.get(Calendar.DAY_OF_MONTH);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(dto.getStatisticalTime());
        int b = calendar1.get(Calendar.DAY_OF_MONTH);

        if (a == b) {
            //如果传入时间是当天，则默认查询当前时间的前一天 也就是今天00:30:00的统计数据
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 0);
            startTime = calendar.getTime();
            endTime = new Date(startTime.getTime() + 30 * 60 * 1000);
        } else {
            //如果要查2019-09-10的统计数据 输入2019-09-10 则要把时间转化为 2019-09-11 00:30:00
            startTime = new Date(dto.getStatisticalTime().getTime() + 30 * 60 * 1000 + 24 * 3600 * 1000);
            endTime = new Date(startTime.getTime() + 30 * 60 * 1000);
        }
        System.out.println(startTime);
        System.out.println(endTime);
        int deptId = dto.getDeptId();

        AppDeviceStatisticHeadExample example = new AppDeviceStatisticHeadExample();
        example.createCriteria().andDeptCodeEqualTo(deptId).andStatisticalDateBetween(startTime,endTime);
        List<AppDeviceStatisticHead> heads = appDeviceStatisticHeadMapper.selectByExample(example);
        AppDeviceStatisticHead head = heads.size() == 0 ? null : heads.get(0);
        Assert.notNull(head, "不存在这条记录");

        DeviceDocumentStatisticHeadDTO headDTO = new DeviceDocumentStatisticHeadDTO();
        headDTO.setTotalDevice(head.getTotalDevice());
        headDTO.setTotalInService(head.getTotalInService());
        headDTO.setTotalStandby(head.getTotalStandby());
        headDTO.setTotalRecondition(head.getTotalRecondition());
        headDTO.setTotalScrap(head.getTotalScrap());
        statisticDTO.setHead(headDTO);

        long headCode = head.getCode();
        AppDeviceStatisticDetailExample example1 = new AppDeviceStatisticDetailExample();
        example1.createCriteria().andReportHeadCodeEqualTo(headCode);
        List<AppDeviceStatisticDetail> detail = appDeviceStatisticDetailMapper.selectByExample(example1);

        List<DeviceDocumentStatisticDetailDTO> detailDTOS = new ArrayList<>();
        for (AppDeviceStatisticDetail d : detail) {
            DeviceDocumentStatisticDetailDTO detailDTO = new DeviceDocumentStatisticDetailDTO();
            detailDTO.setDeviceName(d.getDeviceName());
            detailDTO.setDeviceNum(d.getDeviceNum());
            detailDTO.setInServiceNum(d.getInServiceNum());
            detailDTO.setStandbyNum(d.getStandbyNum());
            detailDTO.setReconditionNum(d.getReconditionNum());
            detailDTO.setScrapNum(d.getScrapNum());
            detailDTOS.add(detailDTO);
        }

        statisticDTO.setDetailDTOS(detailDTOS);

        return statisticDTO;
    }
}
