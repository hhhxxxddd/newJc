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
 * 物料供应商表
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
@TableName("SWMS_basic_supplier_info")
@ApiModel(value="SwmsBasicSupplierInfo对象", description="物料供应商表")
public class SwmsBasicSupplierInfo extends BasePo implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "供应商名称")
    private String materialSupplierName;

    @ApiModelProperty(value = "供应商代号")
    private String materialSupplierCode;

    @ApiModelProperty(value = "是否自动添加标志位 0 表示自动添加 1表示手动添加")
    private Boolean autoFlag;


}
