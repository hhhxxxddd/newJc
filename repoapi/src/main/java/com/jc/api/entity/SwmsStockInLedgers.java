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
 * 物料入库台账表
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
@TableName("SWMS_stock_in_ledgers")
@ApiModel(value="SwmsStockInLedgers对象", description="物料入库台账表")
public class SwmsStockInLedgers extends BasePo implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "物料入库流水id")
    private Long stockInRecordId;

    @ApiModelProperty(value = "物料编码")
    private String materialCode;

    @ApiModelProperty(value = "物料批号")
    private String materialBatch;

    @ApiModelProperty(value = "物料类型id")
    private Integer materialTypeId;

    @ApiModelProperty(value = "物料子类型id")
    private Integer materialSubTypeId;

    @ApiModelProperty(value = "物料入库车间id")
    private Integer materialWorkshopId;

    @ApiModelProperty(value = "物料名称代码")
    private String materialNameCode;

    @ApiModelProperty(value = "物料供应商代码")
    private String materialSupplierCode;

    @ApiModelProperty(value = "物料名称")
    private String materialName;

    @ApiModelProperty(value = "袋号")
    private Integer bagNum;

    @ApiModelProperty(value = "重量")
    private Float weight;

    @ApiModelProperty(value = "计量单位")
    private String measureUnit;

    @ApiModelProperty(value = "入库时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty(value = "入库人")
    private String createdPerson;

    @ApiModelProperty(value = "物料状态标志位material_status=0，表示在库中，为默认值；material_status=1，表示待出库；material_status=2，表示已出库")
    private Integer materialStatus;


}
