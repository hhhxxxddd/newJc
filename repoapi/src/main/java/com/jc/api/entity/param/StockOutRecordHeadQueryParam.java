package com.jc.api.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.entity.po.StockOutRecordHead;
import com.jinchi.common.web.entity.param.BaseParam;
import io.netty.util.internal.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author XudongHu
 * @apiNote 出库表头查询参数
 * @className StockOutHeadQueryParam
 * @modifier
 * @since 19.11.6日21:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockOutRecordHeadQueryParam extends BaseParam<StockOutRecordHead> {
    private String stockOutRecordHeadCode;  // 批号同时也是出库单号
    private Integer createdPersonId; //创建人id FK
    private Integer endPositionId; //出库点id FK
    private Integer productionLineId;//产线id FK
    private Integer outMaterialType;// 出库一级类型
    private Integer outUsage; //出库用途 1前驱体,2正极
    private Integer completionFlag;//是否完成标志
    private Date completionTimeStart;//完成时间
    private Date completionTimeEnd;

    @Override
    public QueryWrapper<StockOutRecordHead> build() {
        QueryWrapper<StockOutRecordHead> queryWrapper = new QueryWrapper<>();
        if(!StringUtil.isNullOrEmpty(stockOutRecordHeadCode)) queryWrapper.likeRight("stock_out_record_head_code",this.getStockOutRecordHeadCode());
        if(createdPersonId!=null) queryWrapper.eq("created_person_id",this.getCreatedPersonId());
        if(endPositionId!=null) queryWrapper.eq("end_position_id",this.getEndPositionId());
        if(productionLineId!=null) queryWrapper.eq("production_line_id",this.getProductionLineId());
        if(outMaterialType!=null) queryWrapper.eq("out_material_type",this.getOutMaterialType());
        if(outUsage!=null) queryWrapper.eq("out_usage",this.getOutUsage());
        if(completionFlag!=null) queryWrapper.eq("completion_flag",this.getCompletionFlag());
        if(completionTimeStart!=null) queryWrapper.ge("completion_time",this.getCompletionTimeStart());
        if(completionTimeEnd!=null) queryWrapper.le("completion_time",this.getCompletionTimeEnd());
        return queryWrapper;
    }
}
