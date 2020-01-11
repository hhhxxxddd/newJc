package com.jinchi.common.dto;

public class DeviceMainManualDTO {
    Long id;
    String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeviceMainManualDTO{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }
}
