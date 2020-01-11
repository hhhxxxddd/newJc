package com.jc.api.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.entity.po.StockInRecordAccount;
import com.jinchi.common.web.entity.param.BaseParam;
import io.netty.util.internal.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author XudongHu
 * @apiNote 库存台账查询参数
 * @className StockInRecordAccountQueryParam
 * @modifier
 * @since 19.12.10日3:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockInRecordAccountQueryParam extends BaseParam<StockInRecordAccount> {
    private Long materialInfoId;// 物料信息id
    private String materialCode; //NN 物料编码 冗余
    private String materialBatch;//NN 物料批号 冗余
    private String bagNo; //NN 物料袋号 冗余
    private Integer materialLeafTypeId; //Fk 物料子类型id 冗余
    private Integer materialRootTypeId; //Fk 物料根类型id 冗余
    private Integer materialWorkshopId; //Fk 物料供应车间id 冗余
    private Integer materialSupplierId; //Fk 物料供应商id 冗余
    private String materialNameCode;// NN 物料名称代号 冗余
    private Double minWeight; //NN 此袋重量 冗余 保留两位小数
    private Double maxWeight;  //
    private Integer materialStatus; //NN 物料状态 0已出库 1在库中 2待出库 默认1
    private Date createdTimeStart; //NN 入库 开始时间
    private Date createdTimeEnd;  //
    private String createdPerson; //NN 入库人 冗余

    @Override
    public QueryWrapper<StockInRecordAccount> build() {
        QueryWrapper<StockInRecordAccount> queryWrapper = new QueryWrapper<>();
        if (materialInfoId!=null) queryWrapper.eq("material_info_id",this.getMaterialInfoId());
        if (!StringUtil.isNullOrEmpty(materialCode)) queryWrapper.likeRight("material_code", this.getMaterialCode());
        if (!StringUtil.isNullOrEmpty(materialBatch)) queryWrapper.likeRight("material_batch", this.getMaterialBatch());
        if (!StringUtil.isNullOrEmpty(materialNameCode))
            queryWrapper.likeRight("material_name_code", this.getMaterialNameCode());
        if (!StringUtil.isNullOrEmpty(bagNo)) queryWrapper.eq("bag_no", this.getBagNo());
        if (materialLeafTypeId != null) queryWrapper.eq("material_leaf_type_id", this.getMaterialLeafTypeId());
        if (materialRootTypeId != null) queryWrapper.eq("material_root_type_id", this.getMaterialRootTypeId());
        if (materialWorkshopId != null) queryWrapper.eq("material_workshop_id", this.getMaterialWorkshopId());
        if (materialSupplierId != null) queryWrapper.eq("material_supplier_id", this.getMaterialSupplierId());
        if (minWeight != null) queryWrapper.ge("weight", this.getMinWeight());
        if (maxWeight != null) queryWrapper.le("weight", this.getMaxWeight());
        if (materialStatus != null) queryWrapper.eq("material_status", this.getMaterialStatus());
        if (createdTimeStart != null) queryWrapper.ge("created_time", this.getCreatedTimeStart());
        if (createdTimeEnd != null) queryWrapper.le("created_time", this.getCreatedTimeEnd());
        if (!StringUtil.isNullOrEmpty(createdPerson)) queryWrapper.likeRight("created_person", this.getCreatedPerson());
        return super.build();
    }
}
