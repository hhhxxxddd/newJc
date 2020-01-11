package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.dto.MatDeliDTO;
import com.jc.api.entity.param.StockOutRecordQueryParam;
import com.jc.api.entity.vo.StockOutRecordVo;

import java.util.List;

@Deprecated
public interface IStockOutRecordService {

    StockOutRecordVo getById(String id);

    /**
     * 查询所有-条件
     * @param stockOutRecordQueryParam 出库记录参数
     * @return list vo
     */
    List<StockOutRecordVo> getAll(StockOutRecordQueryParam stockOutRecordQueryParam);

    /**
     * 查询所有 -条件/分页
     * @param page 分页参数
     * @param stockOutRecordQueryParam 出库记录参数
     * @return
     */
    IPage<StockOutRecordVo> getAllByPage(Page page, StockOutRecordQueryParam stockOutRecordQueryParam);

    List<MatDeliDTO> dateForMaterialDelivery(String startTime, String endTime, List<String> matNames);
}
