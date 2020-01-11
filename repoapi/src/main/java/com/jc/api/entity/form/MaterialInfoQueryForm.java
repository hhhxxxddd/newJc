package com.jc.api.entity.form;

import com.jc.api.entity.param.MaterialInfoQueryParam;
import com.jinchi.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author XudongHu
 * @apiNote 物料信息查询表单
 * @className MaterialInfoQueryForm
 * @modifier
 * @since 2019/11/2日3:11
 */
@ApiModel
@Data
public class MaterialInfoQueryForm extends BaseQueryForm<MaterialInfoQueryParam> {
    @ApiModelProperty(value = "物料名称", example = "铁")
    private String materialName;

    @ApiModelProperty(value = "物料代号", example = "Fe")
    private String materialNameCode;

    @ApiModelProperty(value = "物料类型id", example = "1")
    private Integer materialTypeId;

    @ApiModelProperty(value = "物料厂商id",example = "1")
    private Integer materialSupplierId;

    @ApiModelProperty(value = "是否含ni", example = "0")
    @Min(value = 0, message = "取值为0或者1")
    @Max(value = 1,message = "取值为0或者1")
    private Integer niFlag = 0;

    @ApiModelProperty(value = "是否含co", example = "0")
    @Min(value = 0, message = "取值为0或者1")
    @Max(value = 1,message = "取值为0或者1")
    private Integer coFlag = 0;

    @ApiModelProperty(value = "是否含mn", example = "0")
    @Min(value = 0, message = "取值为0或者1")
    @Max(value = 1,message = "取值为0或者1")
    private Integer mnFlag = 0;

    @ApiModelProperty(value = "是否含alkali", example = "0")
    @Min(value = 0, message = "取值为0或者1")
    @Max(value = 1,message = "取值为0或者1")
    private Integer alkaliFlag = 0;

    @ApiModelProperty(value = "是否为自动添加",example = "0")
    @Min(value = 0, message = "取值为0或者1")
    @Max(value = 1,message = "取值为0或者1")
    private Integer autoFlag = 0;

    @ApiModelProperty(value = "结果是否包含该类型的所有子类", example = "false")
    private Boolean depthQuery = false;
}
