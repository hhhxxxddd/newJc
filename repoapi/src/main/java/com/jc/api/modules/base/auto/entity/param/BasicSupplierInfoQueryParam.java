package com.jc.api.modules.base.auto.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.modules.base.auto.entity.BasicSupplierInfo;
import com.jinchi.common.web.entity.param.BaseParam;
import io.netty.util.internal.StringUtil;
import lombok.*;

/**
 * @author XudongHu
 * @apiNote 物料供应商查询参数
 * @className BasicSupplierInfoQueryParam
 * @modifier
 * @since 20.1.9日14:46
 */
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasicSupplierInfoQueryParam extends BaseParam<BasicSupplierInfo> {
    private String materialSupplierName;
    private String materialSupplierCode;
    private Boolean autoFlag;

    @Override
    public QueryWrapper<BasicSupplierInfo> build() {
        QueryWrapper<BasicSupplierInfo> queryWrapper = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(materialSupplierName)) queryWrapper.likeRight("material_supplier_name", this.getMaterialSupplierName());
        if(!StringUtil.isNullOrEmpty(materialSupplierCode)) queryWrapper.likeRight("material_supplier_code",this.getMaterialSupplierCode());
        if(autoFlag!=null) queryWrapper.eq("auto_flag",this.getAutoFlag());
        return queryWrapper;
    }
}
