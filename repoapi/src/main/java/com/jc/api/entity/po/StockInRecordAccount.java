package com.jc.api.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jinchi.common.web.entity.po.BasePo;
import lombok.*;

import java.util.Date;

/**
 * @author XudongHu
 * @apiNote 入库流水台账表
 * @className StockInRecordAccount
 * @modifier
 * @since 19.11.29日21:42
 */
@ToString(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "stock_in_record_account")
@Deprecated
public class StockInRecordAccount extends BasePo {
    private Long materialInfoId;// 物料信息id
    private Long stockInRecordId;//Fk 入库流水id
    private String materialCode; //NN 物料编码 冗余
    private String materialBatch;//NN 物料批号 冗余
    private String bagNo; //NN 物料袋号 冗余
    private Integer materialLeafTypeId; //Fk 物料子类型id 冗余
    private Integer materialRootTypeId; //Fk 物料根类型id 冗余
    private Integer materialWorkshopId; //Fk 物料供应车间id 冗余
    private Integer materialSupplierId; //Fk 物料供应商id 冗余
    private String materialNameCode;// NN 物料名称代号 冗余
    private Double weight; //NN 此袋重量 冗余 保留两位小数
    private Integer materialStatus; //NN 物料状态 0已出库 1在库中 2待出库 默认1
    private Date createdTime; //NN 入库时间 冗余
    private String createdPerson; //NN 入库人 冗余
}
