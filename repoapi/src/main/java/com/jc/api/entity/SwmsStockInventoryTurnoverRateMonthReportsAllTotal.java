package com.jc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jinchi.common.web.entity.po.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 物料库存周转率总合计报表
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
@TableName("SWMS_stock_inventory_turnover_rate_month_reports_all_total")
@ApiModel(value="SwmsStockInventoryTurnoverRateMonthReportsAllTotal对象", description="物料库存周转率总合计报表")
public class SwmsStockInventoryTurnoverRateMonthReportsAllTotal extends BasePo implements Serializable {

    private static final long serialVersionUID=1L;


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

    @ApiModelProperty(value = "周转率")
    private Float turnoverRate;


}
