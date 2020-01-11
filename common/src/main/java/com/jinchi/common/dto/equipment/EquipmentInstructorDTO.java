package com.jinchi.common.dto.equipment;

import com.jinchi.common.domain.EquipmentCheckPointRecord;
import com.jinchi.common.domain.EquipmentInstructorRecord;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author：XudongHu
 * @className:EquipmentInstructorDTO
 * @description: 设备指导DTO
 * @date:23:35 2019/3/2
 */
public class EquipmentInstructorDTO {
    @ApiModelProperty("创建人Id")
    private Integer createPersonId;

    @ApiModelProperty("设备指导实体")
    @NotNull(message = "设备指导不能为空")
    private EquipmentInstructorRecord instructorRecord;

    @ApiModelProperty("设备检查点实体")
    private List<EquipmentCheckPointRecord> pointRecordList;

    public EquipmentInstructorRecord getInstructorRecord() {
        return instructorRecord;
    }

    public EquipmentInstructorDTO setInstructorRecord(EquipmentInstructorRecord instructorRecord) {
        this.instructorRecord = instructorRecord;
        return this;
    }

    public List<EquipmentCheckPointRecord> getPointRecordList() {
        return pointRecordList;
    }

    public EquipmentInstructorDTO setPointRecordList(List<EquipmentCheckPointRecord> pointRecordList) {
        this.pointRecordList = pointRecordList;
        return this;
    }

    public Integer getCreatePersonId() {
        return createPersonId;
    }

    public EquipmentInstructorDTO setCreatePersonId(Integer createPersonId) {
        this.createPersonId = createPersonId;
        return this;
    }

    @Override
    public String toString() {
        return "EquipmentInstructorDTO{" +
                "instructorRecord=" + instructorRecord +
                ", pointRecordList=" + pointRecordList +
                '}';
    }
}
