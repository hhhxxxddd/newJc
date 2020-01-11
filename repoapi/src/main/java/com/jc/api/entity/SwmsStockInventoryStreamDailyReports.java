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
 * 物料库存流量日报表
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
@TableName("SWMS_stock_inventory_stream_daily_reports")
@ApiModel(value="SwmsStockInventoryStreamDailyReports对象", description="物料库存流量日报表")
public class SwmsStockInventoryStreamDailyReports extends BasePo implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "计量单位")
    private String measureUnit;

    @ApiModelProperty(value = "当日入库")
    private Float currentInRecord;

    @ApiModelProperty(value = "当日出库")
    private Float currentOutRecord;

    @ApiModelProperty(value = "当日库存")
    private Float currentWeight;

    @ApiModelProperty(value = "统计年份")
    private String years;

    @ApiModelProperty(value = "统计月份")
    private String months;

    @ApiModelProperty(value = "库存日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date stockDate;


}
