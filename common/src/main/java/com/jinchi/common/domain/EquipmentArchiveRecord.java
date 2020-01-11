package com.jinchi.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author：XudongHu
 * @className:EquipmentArchiveRecord
 * @description: 设备档案
 * @date:16:05 2019/1/11
 * @Modifer:
 */
@ApiModel(description = "设备档案实体")
public class EquipmentArchiveRecord {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("档案名称")
    @NotBlank(message = "档案名称不能为空")
    private String name;

    //Fk
    @ApiModelProperty("设备id")
    @NotNull(message = "设备id不能为空")
    private Integer instrumentId;

    @ApiModelProperty("安装时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date installTime;

    @ApiModelProperty("保修期限")
    @NotBlank(message = "保修期限不能为空")
    private String warrantyPeriod;

    @ApiModelProperty("供应商") //FK
    @NotNull(message = "供应商不能为空")
    private Integer supplyManufacturerId;

    @ApiModelProperty("维修商") //FK
    @NotNull(message = "维修商不能为空")
    private Integer repairManufacturerId;

    @ApiModelProperty("手册名称")
    private String manualName;

    public Integer getId() {
        return id;
    }

    public EquipmentArchiveRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EquipmentArchiveRecord setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getInstrumentId() {
        return instrumentId;
    }

    public EquipmentArchiveRecord setInstrumentId(Integer instrumentId) {
        this.instrumentId = instrumentId;
        return this;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public EquipmentArchiveRecord setInstallTime(Date installTime) {
        this.installTime = installTime;
        return this;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public EquipmentArchiveRecord setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
        return this;
    }

    public Integer getSupplyManufacturerId() {
        return supplyManufacturerId;
    }

    public EquipmentArchiveRecord setSupplyManufacturerId(Integer supplyManufacturerId) {
        this.supplyManufacturerId = supplyManufacturerId;
        return this;
    }

    public Integer getRepairManufacturerId() {
        return repairManufacturerId;
    }

    public EquipmentArchiveRecord setRepairManufacturerId(Integer repairManufacturerId) {
        this.repairManufacturerId = repairManufacturerId;
        return this;
    }

    public String getManualName() {
        return manualName;
    }

    public EquipmentArchiveRecord setManualName(String manualName) {
        this.manualName = manualName;
        return this;
    }
}
