package com.jinchi.app.dto;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-08-26 15:48
 * @description:
 **/

public class DeviceMaintenanceGetDto {
    private BasicInfoDeptDto basicInfoDeptDto;
    private DeviceMaintenanceRecordHeadDto deviceMaintenanceRecordHeadDto;


    public DeviceMaintenanceGetDto() {
    }

    public DeviceMaintenanceGetDto(BasicInfoDeptDto basicInfoDeptDto, DeviceMaintenanceRecordHeadDto deviceMaintenanceRecordHeadDto) {
        this.basicInfoDeptDto = basicInfoDeptDto;
        this.deviceMaintenanceRecordHeadDto = deviceMaintenanceRecordHeadDto;
    }

    public BasicInfoDeptDto getBasicInfoDeptDto() {
        return basicInfoDeptDto;
    }

    public void setBasicInfoDeptDto(BasicInfoDeptDto basicInfoDeptDto) {
        this.basicInfoDeptDto = basicInfoDeptDto;
    }

    public DeviceMaintenanceRecordHeadDto getDeviceMaintenanceRecordHeadDto() {
        return deviceMaintenanceRecordHeadDto;
    }

    public void setDeviceMaintenanceRecordHeadDto(DeviceMaintenanceRecordHeadDto deviceMaintenanceRecordHeadDto) {
        this.deviceMaintenanceRecordHeadDto = deviceMaintenanceRecordHeadDto;
    }

    @Override
    public String toString() {
        return "DeviceMaintenanceGetDto{" +
                "basicInfoDeptDto=" + basicInfoDeptDto +
                ", deviceMaintenanceRecordHeadDto=" + deviceMaintenanceRecordHeadDto +
                '}';
    }
}
