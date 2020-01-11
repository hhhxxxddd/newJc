package com.jc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 物料进出月台账报表
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
@TableName("SWMS_stock_in_out_month_reports")
@ApiModel(value="SwmsStockInOutMonthReports对象", description="物料进出月台账报表")
public class SwmsStockInOutMonthReports extends BasePo implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "物料类型id")
    private Integer materialTypeId;

    @ApiModelProperty(value = "物料子类型id")
    private Integer materialSubTypeId;

    @ApiModelProperty(value = "物料名称代码")
    private Integer materialNameCode;

    @ApiModelProperty(value = "物料名称")
    private String materialName;

    @ApiModelProperty(value = "计量单位")
    private String measureUnit;

    @ApiModelProperty(value = "上月库存")
    private Float lastStockWeight;

    @ApiModelProperty(value = "本月入库")
    private Float currentInWeight;

    @ApiModelProperty(value = "本月出库")
    private Float currentOutWeight;

    @ApiModelProperty(value = "本月库存")
    private Float currentStockWeight;

    @ApiModelProperty(value = "统计年份")
    private String years;

    @ApiModelProperty(value = "统计月份")
    private String months;

    @ApiModelProperty(value = "开始日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date beginDate;

    @ApiModelProperty(value = "结束日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endDate;


}
