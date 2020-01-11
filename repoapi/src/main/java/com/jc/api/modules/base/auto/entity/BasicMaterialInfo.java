package com.jc.api.modules.base.auto.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jinchi.common.web.entity.po.BasePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 物料信息表
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
@TableName("SWMS_basic_material_info")
@ApiModel(value="SwmsBasicMaterialInfo对象", description="物料信息表")
public class BasicMaterialInfo extends BasePo{
    @ApiModelProperty(value = "物料名称")
    private String materialName;
    @ApiModelProperty(value = "物料名称代码")
    private String materialNameCode;
    @ApiModelProperty(value = "物料类型id")
    private Integer materialTypeId;
    @ApiModelProperty(value = "类型id")
    private Integer subTypeId;
    @ApiModelProperty(value = "计量单位")
    private String measureUnit;
    @ApiModelProperty(value = "是否含镍 0不含 1含")
    private Boolean niFlag;
    @ApiModelProperty(value = "是否含钴")
    private Boolean coFlag;
    @ApiModelProperty(value = "是否含锰")
    private Boolean mnFlag;
    @ApiModelProperty(value = "是否含氨")
    private Boolean nhFlag;
    @ApiModelProperty(value = "是否含碱")
    private Boolean alkaliFlag;
    @ApiModelProperty(value = "是否自动添加标志位 0自动 1 手动")
    private Boolean autoFlag;
    @ApiModelProperty(value = "流量统计标志位 0参加 1 不参加")
    private Boolean streamFlag;
}
