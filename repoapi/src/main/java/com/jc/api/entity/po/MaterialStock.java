package com.jc.api.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jinchi.common.web.entity.po.BasePo;
import lombok.*;

/**
 * @author XudongHu
 * @className MaterialStock
 * @apiNote 库存表
 * @modifer
 * @since 2019/10/30日14:22
 */
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "material_stock")
@Deprecated
public class MaterialStock extends BasePo {
    private Long materialInfoId; //FK @see MaterialInfo
    private Integer bagsSum; //NN 总袋数,默认0
    private Double weightSum; //NN 总重量,默认0.00 保留两位小数
}
