package com.jinchi.app.service;

import com.jinchi.app.dto.*;

import java.util.List;

public interface DeviceMaintenanceRecordService {
    List<DeviceMaintenanceGetDto> getRecords(DeviceMaintenancePostDto deviceMaintenancePostDto);

    List<DeviceMaintenanceFormatDto> getFormatRecords(List<DeviceMaintenanceGetDto> deviceMaintenanceGetDtos);

    List<DeviceMaintenanceFormatDto> getByIdStatusCondition(DeviceMaintenancePostDto deviceMaintenancePostDto);

    DeviceMaintenanceDetailGetDto getRecordDetail(DeviceMaintenanceDetailPostDto deviceMaintenanceDetailPostDto);

    DeviceMaintenanceRecordDetailUpdateDto updateById(DeviceMaintenanceRecordDetailUpdateDto updateDto);

    String receiveRecordById(IdDto idDto);
}
