package com.jc.api.entity.form;

import com.jc.api.entity.po.StockOutRecordHead;
import com.jinchi.common.web.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author XudongHu
 * @apiNote 出库表头更新表单
 * @className StockOutRecordHeadUpdateForm
 * @modifier
 * @since 19.12.11日0:14
 */
@ApiModel
@Data
public class StockOutRecordHeadUpdateForm extends BaseForm<StockOutRecordHead> {
    @ApiModelProperty(value = "出库一级类型id", example = "1")
    private Integer outMaterialType;
    @ApiModelProperty(value = "出库用途 1前驱体,2正极", example = "1")
    private Integer outUsage;
    @ApiModelProperty(value = "出库点id", example = "1")
    private Integer endPositionId;
    @ApiModelProperty(value = "产线id", example = "1")
    private Integer productionLineId;
}
