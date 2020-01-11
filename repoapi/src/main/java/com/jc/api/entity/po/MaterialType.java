package com.jc.api.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jinchi.common.web.entity.po.BasePo;
import lombok.*;

/**
 * @author XudongHu
 * @className MaterialType
 * @apiNote 物料类型po
 * @modifer
 * @since 2019/10/30日14:18
 */
@Deprecated
@ToString(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "material_type")
public class MaterialType extends BasePo {
    private Integer level;  //类型等级 默认是1级
    private String typeCode; //类型代号 UN
    private String typeName;  //类型名 NN
    private Integer parentId; //父类型外键自关联 FK NN
    private Integer autoFlag; //NN 是否自动添加
}
