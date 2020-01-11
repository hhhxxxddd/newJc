package com.jinchi.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 入库纪录映射类。
 *
 * @author huxudong
 */
@ApiModel(description = "入库记录")
public class RepoInRecord {
    @ApiModelProperty("主键")
    private Integer id;                 //PK

    @ApiModelProperty("编号id")
    private Integer serialNumberId;     //NN 批号

    @ApiModelProperty("重量")
    private Integer weight;             //NN 重量

    @ApiModelProperty("入库时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date inTime;            //NN 入库时间

    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;      //NN 创建时间

    @ApiModelProperty("入库人名字")
    private String createPerson;        //NN 入库人名字

    public Integer getId() {
        return id;
    }

    public RepoInRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getSerialNumberId() {
        return serialNumberId;
    }

    public RepoInRecord setSerialNumberId(Integer serialNumberId) {
        this.serialNumberId = serialNumberId;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public RepoInRecord setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Date getInTime() {
        return inTime;
    }

    public RepoInRecord setInTime(Date inTime) {
        this.inTime = inTime;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public RepoInRecord setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public RepoInRecord setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
        return this;
    }

    @Override
    public String toString() {
        return "RepoInRecord{" +
                "id=" + id +
                ", serialNumberId=" + serialNumberId +
                ", weight=" + weight +
                ", inTime=" + inTime +
                ", createTime=" + createTime +
                ", createPerson='" + createPerson + '\'' +
                '}';
    }
}
