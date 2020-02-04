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
 * 物料入库日台账表
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
@TableName("SWMS_stock_in_ledgers_day_reports")
@ApiModel(value="SwmsStockInLedgersDayReports对象", description="物料入库日台账表")
public class SwmsStockInLedgersDayReports extends BasePo implements Serializable {

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

    @ApiModelProperty(value = "检验状态Check_status=0，表示待检，为默认值；Check_status=1，表示合格；Check_status=2，表示不合格；Check_status=3，表示让步接收")
    private Integer checkStatus;

    @ApiModelProperty(value = "重量")
    private Float weight;

    @ApiModelProperty(value = "计量单位")
    private String measureUnit;

    @ApiModelProperty(value = "袋数")
    private Integer bagCounts;

    @ApiModelProperty(value = "入库日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdDay;

    @ApiModelProperty(value = "出库日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date outDay;

    @ApiModelProperty(value = "出库重量")
    private Float outWeight;

    @ApiModelProperty(value = "出库袋数")
    private Integer outCounts;

    @ApiModelProperty(value = "是否出完库标记位flag=0，表示未出完库，为默认值；flag=1，表示已出完库")
    private Boolean flag;

    @TableField(exist = false)
    private String supplierName;

    @TableField(exist = false)
    private String typeName;

    @TableField(exist = false)
    private String subTypeName;
}
