package com.jc.api.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.entity.po.MaterialStock;
import com.jinchi.common.web.entity.param.BaseParam;
import io.netty.util.internal.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XudongHu
 * @apiNote 物料库存 查询参数
 * @className MaterialStockQueryParam
 * @modifier
 * @since 2019/11/3日1:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialStockQueryParam extends BaseParam<MaterialStock> {
    private String materialName;//名称模糊
    private String materialNameCode;//名称代号模糊
    private Integer materialTypeId;//物料类型id
    private Boolean depthQuery = false; //是否深度查询
    private Integer minBagsSum;//最小袋数
    private Integer maxBagsSum;//最大袋数
    private Double minWeightSum;//最小总重
    private Double maxWeightSum;//最大总重

    @Override
    public QueryWrapper<MaterialStock> build() {
        QueryWrapper<MaterialStock> queryWrapper = new QueryWrapper<>();
        if (minBagsSum != null) queryWrapper.ge("bags_sum", this.getMinBagsSum());
        if (maxBagsSum != null) queryWrapper.le("bags_sum", this.getMaxBagsSum());
        if (minWeightSum != null) queryWrapper.ge("weight_sum", this.getMinWeightSum());
        if (maxWeightSum != null) queryWrapper.le("weight_sum", this.getMaxWeightSum());
        return queryWrapper;
    }

    public MaterialInfoQueryParam buildMaterialInfoQueryParam() {
        MaterialInfoQueryParam materialInfoQueryParam = new MaterialInfoQueryParam();
        if (!StringUtil.isNullOrEmpty(materialName)) materialInfoQueryParam.setMaterialName(this.getMaterialName());
        if (!StringUtil.isNullOrEmpty(materialNameCode)) materialInfoQueryParam.setMaterialNameCode(this.getMaterialNameCode());
        if (materialTypeId!=null) materialInfoQueryParam.setMaterialTypeId(this.getMaterialTypeId());
        materialInfoQueryParam.setDepthQuery(this.getDepthQuery());
        return materialInfoQueryParam;
    }

    public Boolean hasMaterialInfo(){
        return  !StringUtil.isNullOrEmpty(materialName)||
                !StringUtil.isNullOrEmpty(materialNameCode)||
                materialTypeId!=null;
    }
}
