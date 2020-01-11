package com.jc.api.entity.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jc.api.entity.po.StockOutRecord;
import com.jc.api.entity.po.StockOutRecordHead;
import com.jinchi.common.web.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author XudongHu
 * @apiNote 出库表头表单
 * @className StockOutHeadForm
 * @modifier
 * @since 19.11.5日22:16
 */
@ApiModel
@Data
public class StockOutRecordHeadForm extends BaseForm<StockOutRecordHead> {
    @ApiModelProperty(value = "出库一级类型id", example = "1")
    @NotNull(message = "必须要填写出库一级类型")
    private Integer outMaterialType;
    @ApiModelProperty(value = "出库用途 1前驱体,2正极", example = "1")
    @NotNull(message = "必须要填写出库用途")
    private Integer outUsage;
    @ApiModelProperty(value = "创建人id", example = "1")
    @NotNull(message = "必须输入创建人id")
    private Integer createdPersonId; //创建人id NN
    @ApiModelProperty(value = "出库点id", example = "1")
    @NotNull(message = "必须存在出库点id")
    private Integer endPositionId;
    @ApiModelProperty(value = "产线id", example = "1")
    @NotNull(message = "必须存在产线id")
    private Integer productionLineId;//产线id
    @ApiModelProperty(value = "库存分组集合    group1:[1,2,3,],group2:[4,5,6]...")
    @NotNull(message = "出库单内容不能为空")
    private Map<String, List<Integer>> outLists;

    @Override
    public StockOutRecordHead toPo(Class<StockOutRecordHead> clazz) {
        return super.toPo(clazz);
    }

    @JsonIgnore
    public List<StockOutRecord> getRecords() {
        Map<String, List<Integer>> outLists = this.getOutLists();
        List<StockOutRecord> lists = new ArrayList<>();
        outLists.forEach((k, v) -> {
            v.forEach(e -> {
                StockOutRecord stockOutRecord = new StockOutRecord();
                stockOutRecord.setGroupName(k);
                lists.add(stockOutRecord);
            });
        });
        return lists;
    }
}
