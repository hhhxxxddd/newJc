package com.jinchi.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author：XudongHu
 * @className:EquipmentRepairRecord
 * @description: 设备维修实体
 * @date:12:19 2019/3/8
 * @Modifer:
 */
@ApiModel(description = "设备维修实体")
public class EquipmentRepairRecord {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("设备Id")
    @NotNull(message = "设备Id不能为空")
    private Integer instrumentId;

    @ApiModelProperty("部门id")
    @NotNull(message = "部门id不能为空")
    private Integer departmentId;

    @ApiModelProperty("生产线Id")
    @NotNull(message = "生产线Id不能为空")
    private Integer productLineId;

    @ApiModelProperty("申请人Id")
    @NotNull(message = "申请人id不能为空")
    private Integer repairApplierId;

    @ApiModelProperty("申请日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date repairApplyTime;

    @ApiModelProperty("故障描述")
    private String failureDescription;

    @ApiModelProperty("完工日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date finishedTime;

    @ApiModelProperty("完工结论")
    private String finishedConclusion;

    @ApiModelProperty("接单时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date orderTime;

    @ApiModelProperty("评价日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date rateTime;

    @ApiModelProperty("评价内容")
    private String rate;

    public Integer getId() {
        return id;
    }

    public EquipmentRepairRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public EquipmentRepairRecord setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getInstrumentId() {
        return instrumentId;
    }

    public EquipmentRepairRecord setInstrumentId(Integer instrumentId) {
        this.instrumentId = instrumentId;
        return this;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public EquipmentRepairRecord setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Integer getProductLineId() {
        return productLineId;
    }

    public EquipmentRepairRecord setProductLineId(Integer productLineId) {
        this.productLineId = productLineId;
        return this;
    }

    public Integer getRepairApplierId() {
        return repairApplierId;
    }

    public EquipmentRepairRecord setRepairApplierId(Integer repairApplierId) {
        this.repairApplierId = repairApplierId;
        return this;
    }

    public Date getRepairApplyTime() {
        return repairApplyTime;
    }

    public EquipmentRepairRecord setRepairApplyTime(Date repairApplyTime) {
        this.repairApplyTime = repairApplyTime;
        return this;
    }

    public String getFailureDescription() {
        return failureDescription;
    }

    public EquipmentRepairRecord setFailureDescription(String failureDescription) {
        this.failureDescription = failureDescription;
        return this;
    }

    public Date getFinishedTime() {
        return finishedTime;
    }

    public EquipmentRepairRecord setFinishedTime(Date finishedTime) {
        this.finishedTime = finishedTime;
        return this;
    }

    public String getFinishedConclusion() {
        return finishedConclusion;
    }

    public EquipmentRepairRecord setFinishedConclusion(String finishedConclusion) {
        this.finishedConclusion = finishedConclusion;
        return this;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public EquipmentRepairRecord setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public Date getRateTime() {
        return rateTime;
    }

    public EquipmentRepairRecord setRateTime(Date rateTime) {
        this.rateTime = rateTime;
        return this;
    }

    public String getRate() {
        return rate;
    }

    public EquipmentRepairRecord setRate(String rate) {
        this.rate = rate;
        return this;
    }
}
