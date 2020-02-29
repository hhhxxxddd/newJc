package com.jc.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jinchi.common.web.entity.po.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SWMS_basic_mat_sup")
@ApiModel(value = "SWMS_basic_mat_sup", description = "物料供应商映射表")
public class SwmsBasicMatSup  extends BasePo implements Serializable {

    @ApiModelProperty(value = "物料id")
    @TableField("mat_id")
    private Integer matId;

    @ApiModelProperty(value = "供应商id")
    @TableField("sup_id")
    private Integer supId;
}

