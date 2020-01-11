package com.jc.api.entity.vo;

import com.jc.api.entity.po.MaterialStock;
import com.jc.api.entity.po.MaterialType;
import com.jinchi.common.web.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

/**
 * @author XudongHu
 * @apiNote 物料库存Vo
 * @className MaterialStockVo
 * @modifier
 * @since 19.12.10日0:14
 */
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "物料库存VO")
public class MaterialStockJointVo extends BaseVo<MaterialStock> {
    private String materialInfoId;
    private String materialName; //物料名称
    private String materialNameCode; //物料名称代号
    private List<MaterialType> completeType;  //物料完整类型链条
    private MaterialStock materialStock;

    public MaterialStockJointVo(MaterialStock materialStock){
        this.materialInfoId = materialStock.getId();
        this.materialStock = materialStock;
    }
}
