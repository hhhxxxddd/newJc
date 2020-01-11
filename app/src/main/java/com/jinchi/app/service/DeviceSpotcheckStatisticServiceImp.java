package com.jinchi.app.service;

import com.jinchi.app.domain.AppSpotcheckStatisticDetail;
import com.jinchi.app.domain.AppSpotcheckStatisticDetailExample;
import com.jinchi.app.domain.AppSpotcheckStatisticHead;
import com.jinchi.app.domain.AppSpotcheckStatisticHeadExample;
import com.jinchi.app.dto.DeviceDocumentStatisticConditionDTO;
import com.jinchi.app.dto.DeviceSpotcheckStatisticDTO;
import com.jinchi.app.dto.DeviceSpotcheckStatisticDetailDTO;
import com.jinchi.app.mapper.AppSpotcheckStatisticDetailMapper;
import com.jinchi.app.mapper.AppSpotcheckStatisticHeadMapper;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-12 14:38
 * @description:
 **/
@Service
public class DeviceSpotcheckStatisticServiceImp implements DeviceSpotcheckStatisticService {
    @Autowired
    AppSpotcheckStatisticHeadMapper appSpotcheckStatisticHeadMapper;
    @Autowired
    AppSpotcheckStatisticDetailMapper appSpotcheckStatisticDetailMapper;

    @Override
    public DeviceSpotcheckStatisticDTO getDataByMonth(DeviceDocumentStatisticConditionDTO dto) {
        DeviceSpotcheckStatisticDTO statisticDTO = new DeviceSpotcheckStatisticDTO();
        int deptCode = dto.getDeptId();
        Date startTime;
        Date endTime;
        Calendar calendar = Calendar.getInstance();
        if (dto.getSpotcheckStatisticalTime() == null || dto.getSpotcheckStatisticalTime().equals("")) {
            calendar.setTime(new Date());
        } else {
            Date d = dto.getSpotcheckStatisticalTime();
            calendar.setTime(d);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        startTime = calendar.getTime();
        endTime = new Date(startTime.getTime() + 24 * 60 * 60 * 1000);
        System.out.println(startTime + "    " + endTime);
        AppSpotcheckStatisticHeadExample example = new AppSpotcheckStatisticHeadExample();
        example.createCriteria().andDeptCodeEqualTo(deptCode).andStatisticalDateBetween(startTime, endTime);
        List<AppSpotcheckStatisticHead> heads = appSpotcheckStatisticHeadMapper.selectByExample(example);
        AppSpotcheckStatisticHead head = heads.size() == 0 ? null : heads.get(0);

        Assert.notNull(head, "不存在这条记录");

        AppSpotcheckStatisticDetailExample example1 = new AppSpotcheckStatisticDetailExample();
        example1.createCriteria().andReportHeadCodeEqualTo(head.getCode());
        List<AppSpotcheckStatisticDetail> details = appSpotcheckStatisticDetailMapper.selectByExample(example1);
        AppSpotcheckStatisticDetail detail = details.size() == 0 ? null : details.get(0);
        Assert.notNull(head, "不存在这条记录");

        List<DeviceSpotcheckStatisticDetailDTO> detailDTOList = new ArrayList<>();

        for (AppSpotcheckStatisticDetail d : details) {
            DeviceSpotcheckStatisticDetailDTO detailDTO = new DeviceSpotcheckStatisticDetailDTO();
            detailDTO.setStatisticTime(d.getStatisitcDate());
            detailDTO.setSpotcheckNum(d.getSpotcheckNum());
            detailDTO.setAnomalyNum(d.getAnomalyNum());
            detailDTOList.add(detailDTO);
        }
        statisticDTO.setDetailDTOList(detailDTOList);
        return statisticDTO;
    }

    @Override
    public DeviceSpotcheckStatisticDTO getDataByMonthforPc(Integer detpId, Integer year, Integer month, String condition) {
        LocalDate date = LocalDate.of(year, month, 1);
        ZonedDateTime time = date.atStartOfDay(ZoneId.systemDefault());
        Date start = Date.from(time.toInstant());

        date = date.with(lastDayOfMonth());
        ZonedDateTime time1 = date.atStartOfDay(ZoneId.systemDefault());
        Date end = Date.from(time1.toInstant());

        AppSpotcheckStatisticHeadExample example = new AppSpotcheckStatisticHeadExample();
        example.createCriteria().andStatisticalDateBetween(start, end).andDeptCodeEqualTo(detpId);
        List<AppSpotcheckStatisticHead> heads = appSpotcheckStatisticHeadMapper.selectByExample(example);

        List<AppSpotcheckStatisticDetail> details = new ArrayList<>();


        return null;
    }
}
