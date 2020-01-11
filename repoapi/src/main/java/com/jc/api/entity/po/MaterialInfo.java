package com.jc.api.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jinchi.common.web.entity.po.BasePo;
import lombok.*;

/**
 * @author XudongHu
 * @className MaterialInfo
 * @apiNote 物料信息表
 * @modifer
 * @since 2019/10/30日13:16
 */
@Deprecated
@ToString(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "material_info")
public class MaterialInfo extends BasePo {
    private String materialNameCode; //NN 物料名称代号
    private String materialName; // 物料名称
    private Integer materialTypeId; //FK  物料类型id @see materialType
    private Integer materialSupplierId; //FK 物料供应商id
    private Integer niFlag;  //NN 是否含镍 默认0
    private Integer coFlag;  //NN 是否含钴 默认0
    private Integer mnFlag;  //NN 是否含锰 默认0
    private Integer nhFlag;  //NN 是否含氨 默认0
    private Integer alkaliFlag; //NN 是否含碱 默认0
    private Integer autoFlag; //NN 是否属于自动添加 默认0
}
