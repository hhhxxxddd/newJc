package com.jinchi.app.dto;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-08-27 16:55
 * @description:
 **/

public class DeviceMaintenanceDetailPostDto {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeviceMaintenanceDetailPostDto{" +
                "id='" + id + '\'' +
                '}';
    }

    public DeviceMaintenanceDetailPostDto() {
    }

    public DeviceMaintenanceDetailPostDto(String id) {
        this.id = id;
    }
}
