package com.jinchi.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @author：XudongHu
 * @description: 样品送检记录表
 * @date:23:33 2018/11/18
 */
@ApiModel(description = "样品送检记录实体")
public class SampleDeliveringRecord implements Serializable {


    //第一项不为空
    @ApiModelProperty("主键")
    private Integer id;    //PK

    @ApiModelProperty("送检人")
    @NotNull(message = "送检人id不能为空")
    private Integer delivererId; //NN F

    @ApiModelProperty("编号")//中间品的受检物料id，原材料和成品的标准id
    private Integer serialNumberId; //NN F

    @ApiModelProperty("送检工厂")
    @NotNull(message = "送检工厂设置不能为空")
    private Integer deliveryFactoryId; //NN F


    @ApiModelProperty("样品送检时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @NotNull(message = "送检时间不能为空")
    @Future(message = "必须选择一个未来的时间")
    private Date sampleDeliveringDate; //NN

    //检测项目，此处为检测项目ID形成的字符串，例:1、2、3
    @ApiModelProperty("检测项目")
    private String testItems;  //NN  F

    //1 原材料
    //2 中间件
    //3 成品
    @ApiModelProperty("类型")
    @NotNull(message = "送检类型不能为空")
    @Pattern(regexp = "^[1-3]$", message = "类型只能为原材料,中间品,成品中的一个")
    private Integer type; // NN

    // 1 待接收
    //2 接受
    //3 不接受
    @ApiModelProperty("状态")
    private Integer acceptStatus; //NN

    @ApiModelProperty("紧急备注")
    private String exceptionComment; //NULL

    @ApiModelProperty("不接受的原因")
    private String handleComment;  //NULL

    @ApiModelProperty("批号暂存")
    private String tempBatchNumber;

    public Integer getId() {
        return id;
    }

    public SampleDeliveringRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getDelivererId() {
        return delivererId;
    }

    public SampleDeliveringRecord setDelivererId(Integer delivererId) {
        this.delivererId = delivererId;
        return this;
    }

    public Integer getSerialNumberId() {
        return serialNumberId;
    }

    public SampleDeliveringRecord setSerialNumberId(Integer serialNumberId) {
        this.serialNumberId = serialNumberId;
        return this;
    }

    public Integer getDeliveryFactoryId() {
        return deliveryFactoryId;
    }

    public SampleDeliveringRecord setDeliveryFactoryId(Integer deliveryFactoryId) {
        this.deliveryFactoryId = deliveryFactoryId;
        return this;
    }

    public Date getSampleDeliveringDate() {
        return sampleDeliveringDate;
    }

    public SampleDeliveringRecord setSampleDeliveringDate(Date sampleDeliveringDate) {
        this.sampleDeliveringDate = sampleDeliveringDate;
        return this;
    }

    public String getTestItems() {
        return testItems;
    }

    public SampleDeliveringRecord setTestItems(String testItems) {
        this.testItems = testItems;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public SampleDeliveringRecord setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getAcceptStatus() {
        return acceptStatus;
    }

    public SampleDeliveringRecord setAcceptStatus(Integer acceptStatus) {
        this.acceptStatus = acceptStatus;
        return this;
    }

    public String getExceptionComment() {
        return exceptionComment;
    }

    public SampleDeliveringRecord setExceptionComment(String exceptionComment) {
        this.exceptionComment = exceptionComment;
        return this;
    }

    public String getHandleComment() {
        return handleComment;
    }

    public SampleDeliveringRecord setHandleComment(String handleComment) {
        this.handleComment = handleComment;
        return this;
    }

    public String getTempBatchNumber() {
        return tempBatchNumber;
    }

    public SampleDeliveringRecord setTempBatchNumber(String tempBatchNumber) {
        this.tempBatchNumber = tempBatchNumber;
        return this;
    }

    @Override
    public String toString() {
        return "SampleDeliveringRecord{" +
                "id=" + id +
                ", delivererId=" + delivererId +
                ", serialNumberId=" + serialNumberId +
                ", deliveryFactoryId=" + deliveryFactoryId +
                ", sampleDeliveringDate=" + sampleDeliveringDate +
                ", testItems='" + testItems + '\'' +
                ", type=" + type +
                ", acceptStatus=" + acceptStatus +
                ", exceptionComment='" + exceptionComment + '\'' +
                ", handleComment='" + handleComment + '\'' +
                ", tempBatchNumber='" + tempBatchNumber + '\'' +
                '}';
    }
}
