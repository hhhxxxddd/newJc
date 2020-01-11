package com.jinchi.common.constant;

/**
 * @author：XudongHu
 * @className:EquipmentManufactuer
 * @description: 设备厂商类型
 * @date:11:30 2019/1/14
 * @Modifer:
 */
public enum EquipmentManufacturerEnum {
    SUPLLY_MANU(1,"供应商"),
    REPAIR_MANU(2,"维修商");

    private Integer code;
    private String name;

    EquipmentManufacturerEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public EquipmentManufacturerEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public EquipmentManufacturerEnum setName(String name) {
        this.name = name;
        return this;
    }
}
