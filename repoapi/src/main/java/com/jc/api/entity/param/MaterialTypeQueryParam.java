package com.jc.api.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.entity.po.MaterialType;
import com.jinchi.common.web.entity.param.BaseParam;
import io.netty.util.internal.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XudongHu
 * @apiNote 物料类型查询参数
 * @className MaterialTypeQueryParam
 * @modifier
 * @since 19.12.9日4:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialTypeQueryParam extends BaseParam<MaterialType> {
    private Integer level;  //类型等级 默认是1级
    private String typeCode; //类型代号 UN
    private String typeName;  //类型名 NN
    private Integer parentId; //父类型外键自关联 FK NN
    private Integer autoFlag; //NN 是否自动添加

    @Override
    public QueryWrapper<MaterialType> build() {
        QueryWrapper<MaterialType> build = new QueryWrapper<>();
        if(level!=null) build.eq("level",this.getLevel());
        if(!StringUtil.isNullOrEmpty(typeCode)) build.likeRight("type_code",this.getTypeCode());
        if(!StringUtil.isNullOrEmpty(typeName)) build.likeRight("type_name",this.getTypeName());
        if(parentId!=null) build.eq("parent_id",this.getParentId());
        if(autoFlag!=null) build.eq("auto_flag",this.getAutoFlag());
        return build;
    }
}
