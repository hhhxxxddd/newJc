package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.api.entity.SwmsStockInOutReports;

/**
 * <p>
 * 物料进出库查询报表 服务类
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-13
 */
public interface ISwmsStockInOutReportsService extends IService<SwmsStockInOutReports> {

    IPage<SwmsStockInOutReports> selectByPage(Page page, Integer typeId, Integer subTypeId, String batch);
}
