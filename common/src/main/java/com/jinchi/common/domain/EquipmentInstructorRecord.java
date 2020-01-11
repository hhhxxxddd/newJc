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
 * @className:EquipmentInstructorRecord
 * @description: 设备指导实体
 * @date:23:12 2019/3/2
 */
@ApiModel(description = "设备指导实体")
public class EquipmentInstructorRecord {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("指导书名称")
    @NotBlank(message = "指导书名称不能为空")
    private String name;

    @ApiModelProperty("批号")
    private Integer batchNumberId;

    @ApiModelProperty("生效日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @NotNull(message = "生效日期不能为空")
    private Date effectiveDate;

    @ApiModelProperty(value = "是否过时",notes = "0失效 1生效")
    private Integer obsolete;

    public Integer getId() {
        return id;
    }

    public EquipmentInstructorRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EquipmentInstructorRecord setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getBatchNumberId() {
        return batchNumberId;
    }

    public EquipmentInstructorRecord setBatchNumberId(Integer batchNumberId) {
        this.batchNumberId = batchNumberId;
        return this;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public EquipmentInstructorRecord setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }

    public Integer getObsolete() {
        return obsolete;
    }

    public EquipmentInstructorRecord setObsolete(Integer obsolete) {
        this.obsolete = obsolete;
        return this;
    }

    @Override
    public String toString() {
        return "EquipmentInstructorRecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", batchNumberId=" + batchNumberId +
                ", effectiveDate=" + effectiveDate +
                ", obsolete=" + obsolete +
                '}';
    }
}
