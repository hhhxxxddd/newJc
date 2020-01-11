package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author：FangLe
 * @className:EquipmentBaseManufacturer
 * @description: 设备基本厂商表
 * @date:15:16 2019/1/11
 */
@ApiModel(description = "设备厂商表")
public class EquipmentBaseManufacturer {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("厂商名称")
    @NotBlank(message = "厂商名称不能为空")
    private String name;

    @ApiModelProperty("厂商编号")
    private String code;

    @ApiModelProperty("厂商联系方式")
    private String contact;

    @ApiModelProperty("厂商类型")
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EquipmentBaseManufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", contact='" + contact + '\'' +
                ", type=" + type +
                '}';
    }
}
