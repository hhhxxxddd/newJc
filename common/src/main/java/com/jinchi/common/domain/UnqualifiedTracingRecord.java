package com.jinchi.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author：XudongHu
 * @description: 不合格品跟踪表
 * @date:23:44 2018/11/18
 */
@ApiModel(description = "不合格品跟踪实体")
public class UnqualifiedTracingRecord {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("批号")
    private Integer batchNumberId;

    @ApiModelProperty("发生时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("发生工序")
    private Integer productionProcessId;

    @ApiModelProperty("处理人")
    private Integer handler;

    public Integer getId() {
        return id;
    }

    public UnqualifiedTracingRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getBatchNumberId() {
        return batchNumberId;
    }

    public UnqualifiedTracingRecord setBatchNumberId(Integer batchNumberId) {
        this.batchNumberId = batchNumberId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UnqualifiedTracingRecord setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getProductionProcessId() {
        return productionProcessId;
    }

    public UnqualifiedTracingRecord setProductionProcessId(Integer productionProcessId) {
        this.productionProcessId = productionProcessId;
        return this;
    }

    public Integer getHandler() {
        return handler;
    }

    public UnqualifiedTracingRecord setHandler(Integer handler) {
        this.handler = handler;
        return this;
    }

    @Override
    public String toString() {
        return "UnqualifiedTracingRecord{" +
                "id=" + id +
                ", batchNumberId=" + batchNumberId +
                ", createTime=" + createTime +
                ", productionProcessId=" + productionProcessId +
                ", handler=" + handler +
                '}';
    }
}
