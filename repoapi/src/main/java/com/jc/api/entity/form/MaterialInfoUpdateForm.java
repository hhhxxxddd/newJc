package com.jc.api.entity.form;

import com.jc.api.entity.po.MaterialInfo;
import com.jinchi.common.web.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author XudongHu
 * @apiNote 物料信息更新 表单
 * @className MaterialInfoUpdateForm
 * @modifier
 * @since 19.12.9日9:28
 */
@ApiModel
@Data
public class MaterialInfoUpdateForm extends BaseForm<MaterialInfo> {
    @ApiModelProperty(value = "物料名称", example = "铁")
    private String materialName;
    @ApiModelProperty(value = "物料代号", example = "Fe")
    private String materialNameCode;
    @ApiModelProperty(value = "物料类型id", example = "1")
    private Integer materialTypeId;
    @ApiModelProperty(value = "物料供应商id",example = "1")
    private Integer materialSupplierId;
    @ApiModelProperty(value = "是否含ni", example = "0")
    @Nullable
    @Min(value = 0, message = "取值为0或者1")
    @Max(value = 1,message = "取值为0或者1")
    private Integer niFlag = 0;
    @ApiModelProperty(value = "是否含co", example = "0")
    @Nullable
    @Min(value = 0, message = "取值为0或者1")
    @Max(value = 1,message = "取值为0或者1")
    private Integer coFlag = 0;
    @ApiModelProperty(value = "是否含mn", example = "0")
    @Nullable
    @Min(value = 0, message = "取值为0或者1")
    @Max(value = 1,message = "取值为0或者1")
    private Integer mnFlag = 0;
    @ApiModelProperty(value = "是否含alkali", example = "0")
    @Nullable
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
