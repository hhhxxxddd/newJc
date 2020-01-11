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
 * 物料出库申请单头表
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
@TableName("SWMS_stock_out_record_head")
@ApiModel(value="SwmsStockOutRecordHead对象", description="物料出库申请单头表")
public class SwmsStockOutRecordHead extends BasePo implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "审核表id")
    private Long applicationFormId;

    @ApiModelProperty(value = "出库单号 对应commonBatchNumber中的批号")
    private Long stockOutRecordHeadCode;

    @ApiModelProperty(value = "领用单位id")
    private Integer deptCode;

    @ApiModelProperty(value = "使用火法产线")
    private Integer hfLineCode;

    @ApiModelProperty(value = "使用湿法产线")
    private Integer sfLineCode;

    @ApiModelProperty(value = "批次信息")
    private String batchCode;

    @ApiModelProperty(value = "出库类别")
    private Integer deliveryTypeCode;

    @ApiModelProperty(value = "出库点")
    private Integer deliveryAddressCode;

    @ApiModelProperty(value = "物料子类型id")
    private Integer materialSubTypeId;

    @ApiModelProperty(value = "物料入库车间id")
    private Integer materialWorkshopId;

    @ApiModelProperty(value = "申请时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty(value = "创建人id")
    private Integer createdPersonId;

    @ApiModelProperty(value = "创建人名称")
    private String createdPerson;

    @ApiModelProperty(value = "出库日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date completionTime;

    @ApiModelProperty(value = "出库状态标志位material_status=0，表示未出库，为默认值；material_status=1，表示进行中；material_status=2，表示已完成")
    private Integer materialStatus;

    @ApiModelProperty(value = "系统模块标志位sys_flag=0，表示火法物料出库，为默认值,此时，产线数据存在Hf_line_code；sys_flag=1，表示湿法物料出库，此时，产线数据存在Sf_line_code")
    private Boolean sysFlag;


}
