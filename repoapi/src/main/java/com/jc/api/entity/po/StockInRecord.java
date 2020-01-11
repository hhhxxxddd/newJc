package com.jc.api.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jinchi.common.web.entity.po.BasePo;
import lombok.*;

import java.util.Date;

/**
 * @author XudongHu
 * @className StockInRecord
 * @apiNote 入库记录表,如实记录便可
 * @modifer
 * @since 2019/10/31日4:04
 */
@ToString(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "stock_in_record")
@Deprecated
public class StockInRecord extends BasePo {
    private String materialCode; //物料编码  NN
    private Date createdTime; //创建时间 NN
    private String createdPerson; //创建人  NN
}
