package com.jinchi.common.dto;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-15 19:32
 * @description:
 **/

public class PrecursorMaterialDTO {
    Integer materialCode;
    String materialName;

    public Integer getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(Integer materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
