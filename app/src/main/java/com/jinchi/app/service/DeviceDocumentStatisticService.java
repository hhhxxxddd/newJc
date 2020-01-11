package com.jinchi.app.service;

import com.jinchi.app.dto.BasicInfoDeptDto;
import com.jinchi.app.dto.DeptConditionDTO;
import com.jinchi.app.dto.DeviceDocumentStatisticConditionDTO;
import com.jinchi.app.dto.DeviceDocumentStatisticDTO;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-11 16:50
 * @description:
 **/

public interface DeviceDocumentStatisticService {
    List<BasicInfoDeptDto> getAll(DeptConditionDTO dto);
    DeviceDocumentStatisticDTO getDataByDateAndDept(DeviceDocumentStatisticConditionDTO dto);
}
