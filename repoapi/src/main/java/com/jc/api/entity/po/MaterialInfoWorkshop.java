package com.jc.api.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jinchi.common.web.entity.po.BasePo;
import lombok.*;

/**
 * @author XudongHu
 * @apiNote 物料供应车间
 * @className MaterialInfoWorkshop
 * @modifier
 * @since 19.11.29日21:33
 */
@Deprecated
@ToString(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "material_info_workshop")
public class MaterialInfoWorkshop extends BasePo {
    private String materialWorkshopCode; //UN 物料供应车间代号
    private String materialWorkshopName; //物料供应车间名称
    private Integer autoFlag; //NN 是否自动添加
}
