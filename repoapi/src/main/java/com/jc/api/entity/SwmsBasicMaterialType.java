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
 * 物料类型表
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
@TableName("SWMS_basic_material_type")
@ApiModel(value="SwmsBasicMaterialType对象", description="物料类型表")
public class SwmsBasicMaterialType extends BasePo implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "类型代号")
    private String typeCode;

    @ApiModelProperty(value = "是否自动添加标志位 0自动 1手动")
    private Boolean autoFlag;


}
