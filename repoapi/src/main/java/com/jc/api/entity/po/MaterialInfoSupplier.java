package com.jc.api.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jinchi.common.web.entity.po.BasePo;
import lombok.*;

/**
 * @author XudongHu
 * @apiNote 物料供应商信息
 * @className MaterialInfoSupplier
 * @modifier
 * @since 19.11.29日21:30
 */
@Deprecated
@ToString(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "material_info_supplier")
public class MaterialInfoSupplier extends BasePo {
    private String materialSupplierCode; //UN 物料供应商代号
    private String materialSupplierName; //物料供应商名称
    private Integer autoFlag; //NN 是否自动添加flag
}
