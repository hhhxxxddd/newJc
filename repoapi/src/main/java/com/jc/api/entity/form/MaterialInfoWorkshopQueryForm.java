package com.jc.api.entity.form;

import com.jc.api.entity.param.MaterialInfoWorkshopQueryParam;
import com.jinchi.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author XudongHu
 * @apiNote 物料供应车间查询表单
 * @className MaterialInfoWorkshopQueryForm
 * @modifier
 * @since 19.12.9日3:27
 */
@ApiModel
@Data
public class MaterialInfoWorkshopQueryForm extends BaseQueryForm<MaterialInfoWorkshopQueryParam> {
    @ApiModelProperty(value = "物料供应车间代号", example = "0")
    private String materialWorkshopCode;
    @ApiModelProperty(value = "物料供应车间名称", example = "1号车间")
    private String materialWorkshopName;
    @ApiModelProperty(value = "是否为自动添加", example = "0")
    private Integer autoFlag = 0;
}
