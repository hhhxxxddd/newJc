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
 * 物料出库日台账表
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
@TableName("SWMS_stock_out_ledgers_day_reports")
@ApiModel(value="SwmsStockOutLedgersDayReports对象", description="物料出库日台账表")
public class SwmsStockOutLedgersDayReports extends BasePo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "物料批号")
    private String materialBatch;

    @ApiModelProperty(value = "物料类型id")
    private Integer materialTypeId;

    @ApiModelProperty(value = "物料子类型id")
    private Integer materialSubTypeId;

    @ApiModelProperty(value = "物料入库车间id")
    private Integer materialWorkshopId;

    @ApiModelProperty(value = "物料名称代码")
    private Integer materialNameCode;

    @ApiModelProperty(value = "物料供应商代码")
    private Integer materialSupplierCode;

    @ApiModelProperty(value = "物料名称")
    private String materialName;

    @ApiModelProperty(value = "领用单位")
    private Integer deptCode;

    @ApiModelProperty(value = "重量")
    private Float weight;

    @ApiModelProperty(value = "计量单位")
    private String measureUnit;

    @ApiModelProperty(value = "袋数")
    private Integer bagCounts;

    @ApiModelProperty(value = "出库日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdDay;

    @TableField(exist = false)
    private String supplierName;

    @TableField(exist = false)
    private String typeName;

    @TableField(exist = false)
    private String subTypeName;

    @TableField(exist = false)
    private String deptName;
}
