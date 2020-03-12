package com.jc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 物料库存日报总表
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
@TableName("SWMS_stock_inventory_daily_reports_totals")
@ApiModel(value="SwmsStockInventoryDailyReportsTotals对象", description="物料库存日报总表")
public class SwmsStockInventoryDailyReportsTotals extends BasePo implements Serializable {

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

    @ApiModelProperty(value = "前日库存")
    private Float lastWeight;

    @ApiModelProperty(value = "当日入库")
    private Float currentInRecord;

    @ApiModelProperty(value = "当日出库")
    private Float currentOutRecord;

    @ApiModelProperty(value = "现存量")
    private Float weight;

    @ApiModelProperty(value = "安全库存")
    private Float safityInventory;

    @ApiModelProperty(value = "库存日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date stockDate;

    @ApiModelProperty(value = "备注")
    private String comments;

    @TableField(exist = false)
    private String typeName;

    @TableField(exist = false)
    private String subTypeName;
}
