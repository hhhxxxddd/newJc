package com.jc.api.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jinchi.common.web.entity.po.BasePo;
import lombok.*;

import java.util.Date;

/**
 * @author XudongHu
 * @className StockOutRecord
 * @apiNote 出库单详情po
 * @modifer
 * @since 2019/10/31日11:57
 */
@ToString(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "stock_out_record")
@Deprecated
public class StockOutRecord extends BasePo {
    private Integer stockOutRecordHeadId; //出库表头id FK
    private Long stockInRecordAccountId;//入库台账id
    private String groupName; //组名 NN
    private Date completionTime; //物料出库时间
}
