package com.jinchi.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 该类用来纪录不同批号对应的货物的实际数量和应有数量之间的差异。
 *
 * @author lllyyyggg
 * @see RepoBaseSerialNumber
 */
public class RepoDiffRecord {
    private Integer id;                 //PK
    private Integer serialNumberId;     //NN   批号
    private Integer realWeight;         //NN   实际重量
    private Integer supposedWeight;     //NN   记录重量
    private Integer weightDiff;         //NN   重量差异
    private Integer creator;            //NN   差异表创建

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;            //NULL 创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSerialNumberId() {
        return serialNumberId;
    }

    public void setSerialNumberId(Integer serialNumberId) {
        this.serialNumberId = serialNumberId;
    }

    public Integer getWeightDiff() {
        return weightDiff;
    }

    public void setWeightDiff(Integer weightDiff) {
        this.weightDiff = weightDiff;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRealWeight() {
        return realWeight;
    }

    public void setRealWeight(Integer realWeight) {
        this.realWeight = realWeight;
    }

    public Integer getSupposedWeight() {
        return supposedWeight;
    }

    public void setSupposedWeight(Integer supposedWeight) {
        this.supposedWeight = supposedWeight;
    }
}
