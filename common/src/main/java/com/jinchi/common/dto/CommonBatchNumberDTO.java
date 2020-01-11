package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jinchi.common.domain.CommonBatchNumber;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author：XudongHu
 * @className:CommonBatchNumberDTO
 * @description: 质量模块, 审核流程, 绝大部分可以使用此泛型
 * @date:15:04 2018/12/4
 */
public class CommonBatchNumberDTO<T> implements Serializable {
    @ApiModelProperty("公共批号")
    @NotNull(message = "批号设置不能为空")
    private CommonBatchNumber commonBatchNumber;

    @ApiModelProperty("创建人名称")
    private String createPersonName;

    @ApiModelProperty("详细数据")
    private T details;

    @ApiModelProperty("目标位置")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String endPosition;

    @ApiModelProperty("产线号")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String produceLine;

    public CommonBatchNumberDTO() {
    }

    public CommonBatchNumberDTO(CommonBatchNumber commonBatchNumber, String createPersonName) {
        this.convertDTO(commonBatchNumber, createPersonName);
    }

    public String getEndPosition() {
        return endPosition;
    }

    public CommonBatchNumberDTO setEndPosition(String endPosition) {
        this.endPosition = endPosition;
        return this;
    }

    public String getProduceLine() {
        return produceLine;
    }

    public CommonBatchNumberDTO setProduceLine(String produceLine) {
        this.produceLine = produceLine;
        return this;
    }

    public CommonBatchNumber getCommonBatchNumber() {
        return commonBatchNumber;
    }

    public CommonBatchNumberDTO setCommonBatchNumber(CommonBatchNumber commonBatchNumber) {
        this.commonBatchNumber = commonBatchNumber;
        return this;
    }

    public String getCreatePersonName() {
        return createPersonName;
    }

    public CommonBatchNumberDTO setCreatePersonName(String createPersonName) {
        this.createPersonName = createPersonName;
        return this;
    }

    public T getDetails() {
        return details;
    }

    public CommonBatchNumberDTO setDetails(T details) {
        this.details = details;
        return this;
    }

    /**
     * 将批号转换成DTO形式
     *
     * @param commonBatchNumber
     */
    public void convertDTO(@NotNull(message = "批号设置不能为空") CommonBatchNumber commonBatchNumber, String createPersonName) {
        this.commonBatchNumber = commonBatchNumber;
        this.createPersonName = createPersonName;
    }

    @Override
    public String toString() {
        return "CommonBatchNumberDTO{" +
                "commonBatchNumber=" + commonBatchNumber +
                ", createPersonName='" + createPersonName + '\'' +
                ", details=" + details +
                '}';
    }
}
