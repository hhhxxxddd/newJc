package com.jc.api.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.entity.po.MaterialInfoSupplier;
import com.jinchi.common.web.entity.param.BaseParam;
import io.netty.util.internal.StringUtil;
import lombok.*;

/**
 * @author XudongHu
 * @apiNote 物料供应商参数
 * @className MaterialInfoSupplierParam
 * @modifier
 * @since 19.12.8日1:58
 */
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialInfoSupplierQueryParam extends BaseParam<MaterialInfoSupplier> {
    private String materialSupplierCode; //UN 物料供应商代号
    private String materialSupplierName; //物料供应商名称
    private Integer autoFlag; //NN 是否自动添加flag

    @Override
    public QueryWrapper<MaterialInfoSupplier> build(){
        QueryWrapper<MaterialInfoSupplier> queryWrapper = new QueryWrapper<>();
        if(!StringUtil.isNullOrEmpty(materialSupplierCode)) queryWrapper.likeRight("material_supplier_code",this.getMaterialSupplierCode());
        if(!StringUtil.isNullOrEmpty(materialSupplierName)) queryWrapper.likeRight("material_supplier_name",this.getMaterialSupplierName());
        if(autoFlag !=null) queryWrapper.eq("auto_flag",this.getAutoFlag());
        return queryWrapper;
    }
}
