package com.jinchi.common.dto.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author：XudongHu
 * @className:RepoOutRecordDTO
 * @description:
 * @date:15:51 2018/11/29
 */
@ApiModel
@Deprecated
public class RepoOutApplyDTO {
    @ApiModelProperty("出库单号,即批号")
    private String orderNo;
    @ApiModelProperty("批号id,审核用即 commonBatchNumberId")
    private Integer applicationFormId;
    @ApiModelProperty("创建人")
    private Integer createdPerson;
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    public RepoOutApplyDTO() {
    }

    public RepoOutApplyDTO(String orderNo, Integer applicationFormId, Integer createdPerson, Date createdTime) {
        this.orderNo = orderNo;
        this.applicationFormId = applicationFormId;
        this.createdPerson = createdPerson;
        this.createdTime = createdTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public RepoOutApplyDTO setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Integer getApplicationFormId() {
        return applicationFormId;
    }

    public RepoOutApplyDTO setApplicationFormId(Integer applicationFormId) {
        this.applicationFormId = applicationFormId;
        return this;
    }

    public Integer getCreatedPerson() {
        return createdPerson;
    }

    public RepoOutApplyDTO setCreatedPerson(Integer createdPerson) {
        this.createdPerson = createdPerson;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public RepoOutApplyDTO setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RepoOutApplyDTO)) return false;

        RepoOutApplyDTO that = (RepoOutApplyDTO) o;

        if (getOrderNo() != null ? !getOrderNo().equals(that.getOrderNo()) : that.getOrderNo() != null) return false;
        if (getApplicationFormId() != null ? !getApplicationFormId().equals(that.getApplicationFormId()) : that.getApplicationFormId() != null)
            return false;
        if (getCreatedPerson() != null ? !getCreatedPerson().equals(that.getCreatedPerson()) : that.getCreatedPerson() != null)
            return false;
        return getCreatedTime() != null ? getCreatedTime().equals(that.getCreatedTime()) : that.getCreatedTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getOrderNo() != null ? getOrderNo().hashCode() : 0;
        result = 31 * result + (getApplicationFormId() != null ? getApplicationFormId().hashCode() : 0);
        result = 31 * result + (getCreatedPerson() != null ? getCreatedPerson().hashCode() : 0);
        result = 31 * result + (getCreatedTime() != null ? getCreatedTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RepoOutApplyDTO{" +
                "orderNo='" + orderNo + '\'' +
                ", applicationFormId=" + applicationFormId +
                ", createdPerson=" + createdPerson +
                ", createdTime=" + createdTime +
                '}';
    }
}