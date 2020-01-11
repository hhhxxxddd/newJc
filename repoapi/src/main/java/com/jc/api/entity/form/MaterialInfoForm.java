package com.jc.api.entity.form;

import com.jc.api.entity.po.MaterialInfo;
import com.jinchi.common.web.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author XudongHu
 * @apiNote 物料信息 新增 表单
 * @className MaterialInfoForm
 * @modif * @since 2019/11/2日6:07
 */
@ApiModel
@Data
public class MaterialInfoForm  extends BaseForm<MaterialInfo> {
    @ApiModelProperty(value = "物料名称", example = "铁")
    @NotBlank(message = "物料名称不能为空白")
    private String materialName;
    @ApiModelProperty(value = "物料代号", example = "Fe")
    @NotBlank(message = "物料代号唯一且不能为空白")
    private String materialNameCode;
    @ApiModelProperty(value = "物料类型id", example = "1")
    @NotNull(message = "物料类型id不能为空")
    private Integer materialTypeId;
    @ApiModelProperty(value = "物料供应商id",example = "1")
    @NotNull(message = "物料供应商id不能为空")
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

    @Override
    public MaterialInfo toPo(Class<MaterialInfo> clazz) {
        MaterialInfo materialInfo = super.toPo(clazz);
        materialInfo.setAutoFlag(0);
        return materialInfo;
    }
}
