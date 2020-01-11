package com.jinchi.app.service;

import com.jinchi.app.dto.DeviceDocumentStatisticConditionDTO;
import com.jinchi.app.dto.DeviceSpotcheckStatisticDTO;

public interface DeviceSpotcheckStatisticService {
    DeviceSpotcheckStatisticDTO getDataByMonth(DeviceDocumentStatisticConditionDTO dto);

    DeviceSpotcheckStatisticDTO getDataByMonthforPc(Integer detpId, Integer year, Integer month, String condition);
}
