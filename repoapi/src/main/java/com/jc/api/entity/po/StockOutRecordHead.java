package com.jc.api.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jinchi.common.web.entity.po.BasePo;
import lombok.*;

import java.util.Date;

/**
 * @author XudongHu
 * @className StockOutHead
 * @apiNote 出库单表头po
 * @modifer
 * @since 2019/10/31日12:00
 */
@ToString(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "stock_out_head")
@Deprecated
public class StockOutRecordHead extends BasePo {
    private Long applicationFormId; //审核id
    private String stockOutRecordHeadCode;  // 批号同时也是出库单号
    private Integer bagsSum; //出库总袋数
    private Double weightSum;//出库总重量
    private Integer createdPersonId; //创建人id FK
    private Integer endPositionId; //出库点id FK
    private Integer productionLineId;//产线id FK
    private Integer outMaterialType;// 出库一级类型
    private Integer outUsage; //出库用途 1前驱体,2正极
    private Integer completionFlag;//是否完成标志
    private Date completionTime;//完成时间
}
