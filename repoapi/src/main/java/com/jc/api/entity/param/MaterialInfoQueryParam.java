package com.jc.api.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.entity.po.MaterialInfo;
import com.jinchi.common.web.entity.param.BaseParam;
import io.netty.util.internal.StringUtil;
import lombok.*;

/**
 * @author XudongHu
 * @apiNote 物料信息 联合 查询参数
 * @className MaterialInfoQueryParam
 * @modifier
 * @since 2019/11/2日2:08
 */
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialInfoQueryParam extends BaseParam<MaterialInfo> {
    private String materialName;
    private String materialNameCode;
    private Integer materialTypeId;
    private Integer materialSupplierId;
    private Boolean depthQuery = false;
    private Integer niFlag;
    private Integer coFlag;
    private Integer mnFlag;
    private Integer nhFlag;
    private Integer alkaliFlag;
    private Integer autoFlag;

    @Override
    public QueryWrapper<MaterialInfo> build() {
        QueryWrapper<MaterialInfo> queryWrapper = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(this.getMaterialName()))
            queryWrapper.likeRight("material_name", this.getMaterialName());
        if (!StringUtil.isNullOrEmpty(this.getMaterialNameCode()))
            queryWrapper.likeRight("material_name_code", this.getMaterialNameCode());
        if (materialSupplierId!=null) queryWrapper.eq("material_supplier_id",this.getMaterialSupplierId());
        if (niFlag != null) queryWrapper.eq("ni_flag", this.getNiFlag());
        if (coFlag != null) queryWrapper.eq("co_flag", this.getCoFlag());
        if (mnFlag != null) queryWrapper.eq("mn_flag", this.getMnFlag());
        if (nhFlag != null) queryWrapper.eq("nh_flag", this.getNhFlag());
        if (alkaliFlag != null) queryWrapper.eq("alkali_flag", this.getAlkaliFlag());
        if (autoFlag != null) queryWrapper.eq("auto_flag", this.getAutoFlag());

        return queryWrapper;
    }
}
