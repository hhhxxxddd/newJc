package com.jc.api.entity.form;

import com.jc.api.entity.po.MaterialInfoWorkshop;
import com.jinchi.common.web.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * @author XudongHu
 * @apiNote 物料供应车间表单
 * @className MaterialInfoWorkshopForm
 * @modifier
 * @since 19.12.9日3:24
 */
@ApiModel
@Data
public class MaterialInfoWorkshopForm extends BaseForm<MaterialInfoWorkshop> {
    @ApiModelProperty(value = "物料供应车间代号",example = "0")
    @NotBlank(message = "物料供应商代号唯一且不能为空白")
    private String materialWorkshopCode;
    @ApiModelProperty(value = "物料供应车间名称",example = "1号车间")
    @NotBlank(message = "物料供应商车间名称不能为空白")
    private String materialWorkshopName;

    @Override
    public MaterialInfoWorkshop toPo(Class<MaterialInfoWorkshop> clazz) {
        MaterialInfoWorkshop t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, t);
        t.setAutoFlag(0);
        return t;
    }
}
