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
 * 安全库存设置表
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
@TableName("SWMS_basic_safety_stock")
@ApiModel(value="SwmsBasicSafetyStock对象", description="安全库存设置表")
public class SwmsBasicSafetyStock extends BasePo implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "物料id")
    private Integer materialId;

    @ApiModelProperty(value = "物料类型id")
    private Integer materialTypeId;

    @ApiModelProperty(value = "子类型id")
    private Integer subTypeId;

    @ApiModelProperty(value = "安全库存量")
    private Float safetyStockValue;

    @ApiModelProperty(value = "供应商id")
    private Integer supId;

    //映射 - 类型名称
    @TableField(exist = false)
    private String typeName;

    //映射 - 子类型名称
    @TableField(exist = false)
    private String subTypeName;

    //映射 - 供应商名称
    @TableField(exist = false)
    private String materialSupplierName;

    //映射 - 物料名称
    @TableField(exist = false)
    private String materialName;

    //映射 - 物料单位
    @TableField(exist = false)
    private String measureUnit;

    //映射 - 物料代码
    @TableField(exist = false)
    private String materialNameCode;
}
