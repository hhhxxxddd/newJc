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
 * 出库点信息表
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
@TableName("SWMS_basic_delivery_address_info")
@ApiModel(value="SwmsBasicDeliveryAddressInfo对象", description="出库点信息表")
public class SwmsBasicDeliveryAddressInfo  extends BasePo implements Serializable{

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "出库点名称")
    private String deliveryAddressName;

    @ApiModelProperty(value = "出库类型")
    private boolean type;


}
