package com.jc.api.entity.form;

import com.jc.api.entity.po.MaterialType;
import com.jinchi.common.web.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author XudongHu
 * @apiNote 物料类型 新增/更新 表单
 * @className MaterialTypeForm
 * @modifier
 * @since 19.12.9日4:25
 */
@ApiModel
@Data
public class MaterialTypeForm extends BaseForm<MaterialType> {
    @ApiModelProperty(value = "物料类型代号",example = "RAW")
    @NotBlank(message = "物料类型代号唯一且不能为空")
    private String typeCode; //类型代号 UN
    @ApiModelProperty(value = "物料类型名称",example = "原材料")
    @NotBlank(message = "原材料名称唯一且不能为空")
    private String typeName;  //类型名 NN
    @ApiModelProperty(value = "父类型id",example = "")
    private Integer parentId;

    @Override
    public MaterialType toPo(Class<MaterialType> clazz) {
        MaterialType materialType = super.toPo(clazz);
        materialType.setAutoFlag(0);
        return materialType;
    }
}
