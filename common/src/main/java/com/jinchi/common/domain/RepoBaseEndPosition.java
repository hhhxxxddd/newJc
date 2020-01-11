package com.jinchi.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author XudongHu
 * @className RepoBaseEndPosition
 * @apiNote 仓库基本出库点
 * @modifer
 * @since 2019/10/28 19:51
 */
@ApiModel(description = "仓库基本出库点")
public class RepoBaseEndPosition {
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("出库点名称")
    @NotNull(message = "出库点名不能为空")
    private String endPosition;

    @ApiModelProperty("出库点类型")
    @NotNull(message = "出库点类型不能为空")
    private Integer outType;  //1原材料 2中间品 3成品

    public Integer getId() {
        return id;
    }

    public RepoBaseEndPosition setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getEndPosition() {
        return endPosition;
    }

    public RepoBaseEndPosition setEndPosition(String endPosition) {
        this.endPosition = endPosition;
        return this;
    }

    public Integer getOutType() {
        return outType;
    }

    public RepoBaseEndPosition setOutType(Integer outType) {
        this.outType = outType;
        return this;
    }

    @Override
    public String toString() {
        return "RepoBaseEndPosition{" +
                "id=" + id +
                ", endPosition='" + endPosition + '\'' +
                ", outType=" + outType +
                '}';
    }
}
