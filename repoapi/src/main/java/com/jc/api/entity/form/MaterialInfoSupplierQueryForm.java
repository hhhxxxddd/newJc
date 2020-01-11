package com.jc.api.entity.form;

import com.jc.api.entity.param.MaterialInfoSupplierQueryParam;
import com.jinchi.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author XudongHu
 * @apiNote 物料供应商 查询 表单
 * @className MaterialInfoSupplierQueryForm
 * @modifier
 * @since 19.12.9日1:17
 */
@ApiModel
@Data
public class MaterialInfoSupplierQueryForm extends BaseQueryForm<MaterialInfoSupplierQueryParam> {
    @ApiModelProperty(value = "物料供应商代号", example = "QDBX")
    private String materialSupplierCode;
    @ApiModelProperty(value = "物料供应商名称", example = "启东北新")
    private String materialSupplierName;
    @ApiModelProperty(value = "是否为自动添加", example = "0")
    private Integer autoFlag = 0;
}
