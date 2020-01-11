package com.jc.api.entity.form;

import com.jc.api.entity.param.MaterialTypeQueryParam;
import com.jinchi.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author XudongHu
 * @apiNote 物料类型 查询 表单
 * @className MaterialTypeQueryForm
 * @modifier
 * @since 19.12.9日5:10
 */
@ApiModel
@Data
public class MaterialTypeQueryForm extends BaseQueryForm<MaterialTypeQueryParam> {
    @ApiModelProperty(value = "物料类型代号",example = "RAW")
    private String typeCode; //类型代号 UN
    @ApiModelProperty(value = "物料类型名称",example = "原材料")
    private String typeName;  //类型名 NN
    @ApiModelProperty(value = "父类型id",example = "")
    private Integer parentId;
    @ApiModelProperty(value = "类型层级,最大根类型为1",example = "1")
    private Integer level;
    @ApiModelProperty(value = "是否为自动添加",example = "0")
    private Integer autoFlag = 0;
}
