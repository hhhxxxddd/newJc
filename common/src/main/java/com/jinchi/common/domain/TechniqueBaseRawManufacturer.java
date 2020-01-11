package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author：XudongHu
 * @className:TechniqueBaseRawManufactuere
 * @description: 技术原材料生产厂家
 * @date:10:39 2018/12/22
 */
@ApiModel(description = "原材料生产厂家实体")
public class TechniqueBaseRawManufacturer {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("厂家名称")
    @NotBlank(message = "厂家名称不能为空")
    private String name;

    @ApiModelProperty("厂家代号")
    private String code;

    public Integer getId() {
        return id;
    }

    public TechniqueBaseRawManufacturer setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TechniqueBaseRawManufacturer setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public TechniqueBaseRawManufacturer setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return "TechniqueBaseRawManufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
