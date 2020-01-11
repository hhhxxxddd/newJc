package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.param.StockOutRecordHeadQueryParam;
import com.jc.api.entity.po.StockOutRecord;
import com.jc.api.entity.po.StockOutRecordHead;
import com.jc.api.entity.vo.StockOutHeadVo;

import java.util.List;

/**
 * @author：XudongHu
 * @className:OutPlanService
 * @description: 向仓库下发出库计划
 * @date:23:40 2019/3/30
 * @Modifer:
 */
public interface IStockOutRecordHeadService {

    /**
     * 新增出库计划
     * @param outHead 出库表头
     * @param records 出库内容
     * @return boolean
     */
    StockOutRecordHead repoOut(StockOutRecordHead outHead, List<StockOutRecord> records);

    /**
     * 更新出库计划
     * @param outRecordHead 出库表头
     * @return boolean
     */
    Boolean update(StockOutRecordHead outRecordHead);

    /**
     * 根据主键删除未提交的出库表头
     *
     * @param id 主键
     * @return boolean
     */
    Boolean delete(Integer id);


    /**
     * 根据主键或者批批号id
     * @param id 主键/批号id
     * @param type 是否是否使用主键查询
     * @return stockOutHeadVo
     */
    StockOutHeadVo byId(Integer id, boolean type);

    /**
     * 查询所有出库表头-分页/条件
     *
     * @param stockOutRecordHeadQueryParam 出库表头查询参数
     * @return page
     */
    IPage<StockOutHeadVo> pages(Page page, StockOutRecordHeadQueryParam stockOutRecordHeadQueryParam);

    /**
     * 响应审核结果
     *
     * 下发出库计划/更新出库计划状态
     * @param applicationFormId 审核流id
       @param status
     * @return boolean
     */
    Boolean outStart(Integer applicationFormId,Integer status);

    /**
     * 出库上报-模仿新松出库上报
     * @param stockOutRecordHeadCode 出库单号
     * @param materialCode 物料编码
     * @return
     */
    void outPost(String stockOutRecordHeadCode,String materialCode);
    /**
     * 出库完成上报-模仿新松出库完成上报
     *
     * @param stockOutRecordHeadCode 出库表单
     * @return boolean
     */
    void outFinished(String stockOutRecordHeadCode);
}
