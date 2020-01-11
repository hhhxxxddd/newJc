package com.jc.api.entity.form;

import com.jc.api.entity.po.MaterialInfoSupplier;
import com.jinchi.common.web.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * @author XudongHu
 * @apiNote 物料供应商 新增/更新 表单
 * @className MaterialInfoSupplierForm
 * @modifier
 * @since 19.12.9日1:09
 */
@ApiModel
@Data
public class MaterialInfoSupplierForm extends BaseForm<MaterialInfoSupplier> {
    @ApiModelProperty(value = "物料供应商代号",example = "QDBX")
    @NotBlank(message = "物料供应商代号唯一且不能为空白")
    private String materialSupplierCode;
    @ApiModelProperty(value = "物料供应商名称",example = "启东北新")
    @NotBlank(message = "物料供应商名称不能为空白")
    private String materialSupplierName;

    @Override
    public MaterialInfoSupplier toPo(Class<MaterialInfoSupplier> clazz) {
        MaterialInfoSupplier t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, t);
        t.setAutoFlag(0);
        return t;
    }
}
