package com.jinchi.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author：XudongHu
 * @className:TechniqueRawStandardRecord
 * @description:
 * @date:14:58 2018/12/27
 */
@ApiModel(description = "技术原材料标准")
public class TechniqueRawStandardRecord {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("物料id")
    @NotNull(message = "物料id不能为空")     //@See TechniqueBaseRawMaterial
    private Integer rawMaterialId;

    @ApiModelProperty("原材料厂商id")
    @NotNull(message = "原材料厂商id不能为空")
    private Integer rawManufacturerId;

    @ApiModelProperty("批号id")
    private Integer batchNumberId;

    @ApiModelProperty("生效时间")
    @NotNull(message = "生效日期不能为空")
    @Future(message = "请选择一个未来的时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date effectiveTime;

    public Integer getId() {
        return id;
    }

    public TechniqueRawStandardRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getRawMaterialId() {
        return rawMaterialId;
    }

    public TechniqueRawStandardRecord setRawMaterialId(Integer rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
        return this;
    }

    public Integer getRawManufacturerId() {
        return rawManufacturerId;
    }

    public TechniqueRawStandardRecord setRawManufacturerId(Integer rawManufacturerId) {
        this.rawManufacturerId = rawManufacturerId;
        return this;
    }

    public Integer getBatchNumberId() {
        return batchNumberId;
    }

    public TechniqueRawStandardRecord setBatchNumberId(Integer batchNumberId) {
        this.batchNumberId = batchNumberId;
        return this;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public TechniqueRawStandardRecord setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
        return this;
    }

    @Override
    public String toString() {
        return "TechniqueRawStandardRecord{" +
                "id=" + id +
                ", rawMaterialId=" + rawMaterialId +
                ", rawManufacturerId=" + rawManufacturerId +
                ", batchNumberId=" + batchNumberId +
                ", effectiveTime=" + effectiveTime +
                '}';
    }
}
