package com.jc.api.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jc.api.entity.po.StockOutRecord;
import com.jinchi.common.web.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author XudongHu
 * @apiNote 出库内容记录vo
 * @className StockOutRecordVo
 * @modifier
 * @since 19.11.6日21:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "出库页面内容vo")
public class StockOutRecordVo extends BaseVo<StockOutRecord> {
    private String id;
    private String stockOutRecordHeadCode; //出库单号 FK
    private String groupName; //组名 NN
    private StockAccountJointVo stockAccountJointVo;//库存台账Vo
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date completionTime; //物料出库时间
}
