package com.jc.api.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.entity.po.MaterialInfoWorkshop;
import com.jinchi.common.web.entity.param.BaseParam;
import io.netty.util.internal.StringUtil;
import lombok.*;

/**
 * @author XudongHu
 * @apiNote 物料供应车间参数
 * @className MaterialInfoWorkshopQueryParam
 * @modifier
 * @since 19.12.9日3:04
 */
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialInfoWorkshopQueryParam extends BaseParam<MaterialInfoWorkshop> {
    private String materialWorkshopCode; //UN 物料供应车间代号
    private String materialWorkshopName; //物料供应车间名称
    private Integer autoFlag; //NN 是否自动添加

    @Override
    public QueryWrapper<MaterialInfoWorkshop> build(){
        QueryWrapper<MaterialInfoWorkshop> queryWrapper = new QueryWrapper<>();
        if(!StringUtil.isNullOrEmpty(materialWorkshopCode)) queryWrapper.likeRight("material_workshop_code",this.getMaterialWorkshopCode());
        if(!StringUtil.isNullOrEmpty(materialWorkshopName)) queryWrapper.likeRight("material_workshop_name",this.getMaterialWorkshopName());
        if(autoFlag !=null) queryWrapper.eq("auto_flag",this.getAutoFlag());
        return queryWrapper;
    }
}
