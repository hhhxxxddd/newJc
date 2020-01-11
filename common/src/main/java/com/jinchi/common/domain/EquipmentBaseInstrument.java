package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author：XudongHu
 * @className:EquipmentBaseInstrument
 * @description: 设备基础设备表
 * @date:13:54 2019/1/11
 */
@ApiModel(description = "设备基础表")
public class EquipmentBaseInstrument {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("设备名称")
    @NotBlank(message = "设备名称不能为空")
    private String name;

    @ApiModelProperty("设备编号")
    private String code;

    public Integer getId() {
        return id;
    }

    public EquipmentBaseInstrument setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EquipmentBaseInstrument setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public EquipmentBaseInstrument setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return "EquipmentBaseInstrument{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
