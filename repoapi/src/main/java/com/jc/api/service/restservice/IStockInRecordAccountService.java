package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.param.StockInRecordAccountQueryParam;
import com.jc.api.entity.vo.StockAccountJointVo;

import java.util.List;

/**
 * @author XudongHu
 * @apiNote 入库记录台账接口
 * @className IStockInRecordAccountService
 * @modifier
 * @since 19.12.8日0:01
 */
@Deprecated
public interface IStockInRecordAccountService {
    /**
     * 新增台账
     * 解析并新增
     * @param stockInRecordId 入库台账id
     * @return boolean
     */
    void parsingAndInsert(String stockInRecordId);

    /**
     * 查询台账
     * @param id 主键
     * @return vo
     */
    StockAccountJointVo getById(String id);

    /**
     * 查询所有台账-条件
     * @param queryParam 查询参数
     * @return list
     */
    List<StockAccountJointVo> getAll(StockInRecordAccountQueryParam queryParam);

    /**
     * 查询所有台账-条件/分页
     * @param page 分页参数
     * @param queryParam 查询参数
     * @return page
     */
    IPage<StockAccountJointVo> getAllByPage(Page page,StockInRecordAccountQueryParam queryParam);

    /**
     * 出库-(更新物料状态)
     * @param id 台账主键
     * @param status 状态 查看枚举类
     * @return boolean
     */
    Boolean out(String id,Integer status);
}
