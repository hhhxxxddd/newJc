package com.jc.api.entity.form;

import com.jc.api.entity.param.MaterialStockQueryParam;
import com.jinchi.common.web.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author XudongHu
 * @apiNote 物料库存查询表单
 * @className MaterialStockQueryForm
 * @modifier
 * @since 2019/11/3日2:24
 */
@ApiModel
@Data
public class MaterialStockQueryForm extends BaseQueryForm<MaterialStockQueryParam> {
    @ApiModelProperty(value = "物料名称",example = "铁")
    private String materialName;
    @ApiModelProperty(value = "物料名称代号",example = "铁")
    private String materialNameCode;
    @ApiModelProperty(value = "物料类型id",example = "1")
    private Integer materialTypeId;
    @ApiModelProperty(value = "是否查询子类型",example = "false")
    private Boolean depthQuery = false;
    @ApiModelProperty(value = "最小袋数",example = "0")
    private Integer minBagsSum;
    @ApiModelProperty(value = "最大袋数",example = "10000")
    private Integer maxBagsSum;
    @ApiModelProperty(value = "最小总重",example = "0.0")
    private Double minWeightSum;
    @ApiModelProperty(value = "最大总重",example = "10000.0")
    private Double maxWeightSum;
}
