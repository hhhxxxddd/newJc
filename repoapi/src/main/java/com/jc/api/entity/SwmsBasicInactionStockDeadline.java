package com.jc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 呆滞期限设置表
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
@TableName("SWMS_basic_inaction_stock_deadline")
@ApiModel(value="SwmsBasicInactionStockDeadline对象", description="呆滞期限设置表")
public class SwmsBasicInactionStockDeadline extends BasePo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "物料类型id")
    private Integer materialTypeId;

    @ApiModelProperty(value = "子类型id")
    private Integer subTypeId;

    @ApiModelProperty(value = "呆滞期限(天)")
    private Integer deadline;

    //映射 - 物料类型名称
    @TableField(exist = false)
    private String typeName;

    //映射 - 物料子类型名称
    @TableField(exist = false)
    private String subTypeName;

}
