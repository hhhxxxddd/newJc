package com.jc.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jinchi.common.web.entity.po.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 物料子类型表
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
@TableName("SWMS_basic_material_sub_type")
@ApiModel(value="SwmsBasicMaterialSubType对象", description="物料子类型表")
public class SwmsBasicMaterialSubType extends BasePo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "子类型名称")
    private String subTypeName;

    @ApiModelProperty(value = "子类型代号")
    private String subTypeCode;

    @ApiModelProperty(value = "所属类型id")
    private Integer typeId;

    @ApiModelProperty(value = "是否自动添加标志位 0自动 1手动")
    private Boolean autoFlag;

    //映射
    private String typeName;
}
