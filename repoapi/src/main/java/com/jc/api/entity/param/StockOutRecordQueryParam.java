package com.jc.api.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.entity.po.StockOutRecord;
import com.jinchi.common.web.entity.param.BaseParam;
import io.netty.util.internal.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockOutRecordQueryParam extends BaseParam<StockOutRecord> {
    private Integer materialInfoId; //物料信息 id
    private Integer stockOutRecordHeadId; //出库表头id FK
    private String groupName; //组名 NN
    private Date startDateTime;
    private Date endDateTime;

    @Override
    public QueryWrapper<StockOutRecord> build() {
        QueryWrapper<StockOutRecord> queryWrapper = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(groupName)) queryWrapper.likeRight("group_name",this.getGroupName());
        if (materialInfoId != null) queryWrapper.eq("material_info_id", materialInfoId);
        if (startDateTime != null) queryWrapper.gt("completion_time", startDateTime);
        if (endDateTime != null) queryWrapper.lt("completion_time", endDateTime);
        return queryWrapper;
    }
}
