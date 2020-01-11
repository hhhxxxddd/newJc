package com.jc.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinchi.common.web.entity.po.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 库龄区间设置表
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-10
 */
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SWMS_basic_stock_aging_range")
@ApiModel(value="SwmsBasicStockAgingRange对象", description="库龄区间设置表")
public class SwmsBasicStockAgingRange extends BasePo implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "查询时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date queryTime;

    @ApiModelProperty(value = "库龄区间1a")
    private Integer agingRange1a;

    @ApiModelProperty(value = "库龄区间1b")
    private Integer agingRange1b;

    @ApiModelProperty(value = "库龄区间2a")
    private Integer agingRange2a;

    @ApiModelProperty(value = "库龄区间2b")
    private Integer agingRange2b;

    @ApiModelProperty(value = "库龄区间3a")
    private Integer agingRange3a;

    @ApiModelProperty(value = "库龄区间3b")
    private Integer agingRange3b;

    @ApiModelProperty(value = "库龄区间4a")
    private Integer agingRange4a;

    @ApiModelProperty(value = "库龄区间4b")
    private Integer agingRange4b;

    @ApiModelProperty(value = "库龄区间5a")
    private Integer agingRange5a;

    @ApiModelProperty(value = "库龄区间5b")
    private Integer agingRange5b;

    @ApiModelProperty(value = "截止库龄")
    private Integer agingEnd;


}
