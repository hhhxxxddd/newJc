package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.param.StockInRecordQueryParam;
import com.jc.api.entity.po.StockInRecord;

import java.util.List;
import java.util.Set;

/**
 * @author XudongHu
 * @className StockInRecordService
 * @apiNote 入库接口
 * @modifer
 * @since 2019/10/31日4:01
 */
@Deprecated
public interface IStockInRecordService {
    /**
     * 新增入库记录
     * @param materialCode 物料编码
     * @param createPerson 创建人
     * @return 是否成功
     */
    Boolean insert(String materialCode, String createPerson);

    /**
     * 查询所有入库记录-分页-多条件
     * @param page 分页bean
     * @param queryParam 条件参数
     * @return page
     */
    IPage getAllByPage(Page page, StockInRecordQueryParam queryParam);

    /**
     * 查询所有入库记录 -多条件
     * @param queryParam 条件参数
     * @return list
     */
    List<StockInRecord> getAll(StockInRecordQueryParam queryParam);

    /**
     * 根据id删除入库记录
     * @param id 主键
     * @return boolean
     */
    Boolean delete(String id);

    /**
     * 批量删除入库记录
     * @param ids 主键集合
     * @return boolean
     */
    Boolean deleteBatch(Set<String> ids);
}
