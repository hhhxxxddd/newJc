package com.jinchi.common.dto.repository;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @author：XudongHu
 * @className:RepoSendOutApplyDTO
 * @description:
 * @date:14:10 2018/12/4
 */
public class RepoSendOutDetailDTO {

    @ApiModelProperty("库存id")
    private Integer stockId;

    @ApiModelProperty("出库重量")
    @NotNull(message = "出库重量不能为空")
    private Integer weight;

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "RepoSendOutDetailDTO{" +
                "stockId=" + stockId +
                ", weight=" + weight +
                '}';
    }
}
