package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author：XudongHu
 * @className:EquipmentCheckPointRecord
 * @description: 设备指导点检内容
 * @date:23:25 2019/3/2
 */
@ApiModel(description = "设备指导详情")
public class EquipmentCheckPointRecord {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("每日点检内容") //NN
    @NotBlank(message = "每日点检内容不能为空")
    private String checkContent;

    @ApiModelProperty("检查标准")     //NN
    @NotBlank(message = "检查标准不能为空")
    private String checkStandard;

    @ApiModelProperty("检查频率")
    private String checkFrequency;

    @ApiModelProperty("检查点拍照")    //服务器地址
    private String checkPointPicName;

    @ApiModelProperty("设备指导id")     //FK
    private Integer instructorId;

    public Integer getId() {
        return id;
    }

    public EquipmentCheckPointRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCheckContent() {
        return checkContent;
    }

    public EquipmentCheckPointRecord setCheckContent(String checkContent) {
        this.checkContent = checkContent;
        return this;
    }

    public String getCheckStandard() {
        return checkStandard;
    }

    public EquipmentCheckPointRecord setCheckStandard(String checkStandard) {
        this.checkStandard = checkStandard;
        return this;
    }

    public String getCheckFrequency() {
        return checkFrequency;
    }

    public EquipmentCheckPointRecord setCheckFrequency(String checkFrequency) {
        this.checkFrequency = checkFrequency;
        return this;
    }

    public String getCheckPointPicName() {
        return checkPointPicName;
    }

    public EquipmentCheckPointRecord setCheckPointPicName(String checkPointPicName) {
        this.checkPointPicName = checkPointPicName;
        return this;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public EquipmentCheckPointRecord setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
        return this;
    }

    @Override
    public String toString() {
        return "EquipmentCheckPointRecord{" +
                "id=" + id +
                ", checkContent='" + checkContent + '\'' +
                ", checkStandard='" + checkStandard + '\'' +
                ", checkFrequency='" + checkFrequency + '\'' +
                ", checkPointPicName='" + checkPointPicName + '\'' +
                ", instructorId=" + instructorId +
                '}';
    }
}
