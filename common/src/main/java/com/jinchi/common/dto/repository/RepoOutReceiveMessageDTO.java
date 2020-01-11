package com.jinchi.common.dto.repository;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:RepoOutReceiveMessageDTO
 * @description: 出库接收实际出库数量的DTO
 * @date:15:54 2019/2/26
 * @Modifer:
 */
public class RepoOutReceiveMessageDTO {
    @ApiModelProperty("出库单号")
    @NotBlank(message = "出库单号不能为空")
    private String batchNumber;

    @ApiModelProperty("出库数据,key物料编码,value实际出货重量")
    @NotEmpty(message = "出库数据不能为空")
    private List<Map<String,Integer>> outData;

    public String getBatchNumber() {
        return batchNumber;
    }

    public RepoOutReceiveMessageDTO setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
        return this;
    }

    public List<Map<String, Integer>> getOutData() {
        return outData;
    }

    public RepoOutReceiveMessageDTO setOutData(List<Map<String, Integer>> outData) {
        this.outData = outData;
        return this;
    }

    @Override
    public String toString() {
        return "RepoOutReceiveMessageDTO{" +
                "batchNumber='" + batchNumber + '\'' +
                ", outData=" + outData +
                '}';
    }
}
