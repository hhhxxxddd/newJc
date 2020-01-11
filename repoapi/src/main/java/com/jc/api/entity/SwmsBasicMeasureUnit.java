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
 * 计量单位信息表
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
@TableName("SWMS_basic_measure_unit")
@ApiModel(value="SwmsBasicMeasureUnit对象", description="计量单位信息表")
public class SwmsBasicMeasureUnit extends BasePo implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "计量单位")
    private String measureUnit;

    @ApiModelProperty(value = "计量单位描述")
    private String measureUnitDesc;

    @ApiModelProperty(value = "是否自动添加标志位 0自动 1 手动")
    private Boolean autoFlag;


}
