package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.param.MaterialStockQueryParam;
import com.jc.api.entity.po.MaterialStock;
import com.jc.api.entity.vo.MaterialStockJointVo;

import java.util.List;

/**
 * @author XudongHu
 * @className MaterialStockService
 * @apiNote 物料库存service
 * @modifer
 * @since 2019/10/30日15:31
 */
@Deprecated
public interface IMaterialStockService{

    /**
     * 增减库存
     * @param materialStock po
     * @return materialStock po
     */
    MaterialStock stockSetting(MaterialStock materialStock);

    /**
     * 清空库存
     * 即将所有库存重量和袋数置0
     * @return boolean
     */
    Boolean stockTruncate();

    /**
     * 根据物料信息id查询库存
     * @param materialInfoId 物料信息id
     * @return materialStock
     */
    MaterialStock getByInfoId(Integer materialInfoId);

    /**
     * 查询所有-条件
     * @param materialStockQueryParam 库存信息查询参数
     * @return vo
     */
    List<MaterialStockJointVo> getAll(MaterialStockQueryParam materialStockQueryParam);

    /**
     * 查询所有-条件/分页
     * @param page 分页参数
     * @param materialStockQueryParam 库存信息查询参数
     * @return vo
     */
    IPage<MaterialStockJointVo> getAllByPage(Page page, MaterialStockQueryParam materialStockQueryParam);


}
