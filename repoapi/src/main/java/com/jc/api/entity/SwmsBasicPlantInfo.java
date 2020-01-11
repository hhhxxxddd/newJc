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
 * 车间信息表
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
@TableName("SWMS_basic_plant_info")
@ApiModel(value="SwmsBasicPlantInfo对象", description="车间信息表")
public class SwmsBasicPlantInfo extends BasePo implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "车间名称")
    private String plantName;

    @ApiModelProperty(value = "车间代号")
    private String plantCode;

    @ApiModelProperty(value = "是否自动添加标志位 0 表示自动添加 1表示手动添加")
    private Boolean autoFlag;


}
