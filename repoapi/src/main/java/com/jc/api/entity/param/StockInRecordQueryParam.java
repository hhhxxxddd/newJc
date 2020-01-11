package com.jc.api.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.entity.po.StockInRecord;
import com.jinchi.common.web.entity.param.BaseParam;
import io.netty.util.internal.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;








/**
 * @author XudongHu
 * @apiNote 入库查询参数
 * @className StockInRecordParam
 * @modifier
 * @since 2019/11/1日17:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockInRecordQueryParam extends BaseParam<StockInRecord> {
    private String materialCode;
    private Date createdTimeStart;
    private Date createdTimeEnd;
    private String createdPerson;

    @Override
    public QueryWrapper<StockInRecord> build() {
        QueryWrapper<StockInRecord> queryWrapper = new QueryWrapper<>();
        if(this.getCreatedTimeStart()!=null) queryWrapper.ge("created_time",this.getCreatedTimeStart());
        if(this.getCreatedTimeEnd()!=null) queryWrapper.le("created_time",this.getCreatedTimeEnd());
        if(!StringUtil.isNullOrEmpty(this.getMaterialCode())) queryWrapper.likeRight("material_code",this.getMaterialCode());
        if(!StringUtil.isNullOrEmpty(this.getCreatedPerson())) queryWrapper.likeRight("created_person",this.getCreatedPerson());
        return queryWrapper;
    }
}




