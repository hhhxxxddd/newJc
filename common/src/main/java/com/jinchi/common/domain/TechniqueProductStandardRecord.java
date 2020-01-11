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
 * @className:TechniqueProductStandardRecord
 * @description: 技术成品标准记录
 * @date:14:56 2018/12/27
 */
@ApiModel(description = "技术成品标准记录")
public class TechniqueProductStandardRecord {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("最小型号的id")
    private Integer productClassId;

    @ApiModelProperty("基本编号id") //只能是成品
    private Integer serialNumberId;

    @ApiModelProperty("批号id")
    private Integer batchNumberId;

    @ApiModelProperty("生效时间")
    @NotNull(message = "生效日期不能为空")
    @Future(message = "请选择一个未来的时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date effectiveTime;


    public Integer getSerialNumberId() {
        return serialNumberId;
    }

    public TechniqueProductStandardRecord setSerialNumberId(Integer serialNumberId) {
        this.serialNumberId = serialNumberId;
        return this;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public TechniqueProductStandardRecord setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public TechniqueProductStandardRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getProductClassId() {
        return productClassId;
    }

    public TechniqueProductStandardRecord setProductClassId(Integer productClassId) {
        this.productClassId = productClassId;
        return this;
    }

    public Integer getBatchNumberId() {
        return batchNumberId;
    }

    public TechniqueProductStandardRecord setBatchNumberId(Integer batchNumberId) {
        this.batchNumberId = batchNumberId;
        return this;
    }

    @Override
    public String toString() {
        return "TechniqueProductStandardRecord{" +
                "id=" + id +
                ", productClassId=" + productClassId +
                ", batchNumberId=" + batchNumberId +
                ", effectiveTime=" + effectiveTime +
                '}';
    }
}
