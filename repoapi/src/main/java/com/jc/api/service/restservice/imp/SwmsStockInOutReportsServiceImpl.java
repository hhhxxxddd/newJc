package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.api.entity.SwmsStockInOutReports;
import com.jc.api.mapper.SwmsStockInOutReportsMapper;
import com.jc.api.service.restservice.ISwmsStockInOutReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物料进出库查询报表 服务实现类
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-13
 */
@Service
public class SwmsStockInOutReportsServiceImpl extends ServiceImpl<SwmsStockInOutReportsMapper, SwmsStockInOutReports> implements ISwmsStockInOutReportsService {

    @Autowired
    SwmsStockInOutReportsMapper inOutReportsMapper;

    @Override
    public IPage<SwmsStockInOutReports> selectByPage(Page page, Integer typeId, Integer subTypeId, Integer materialCode, Integer supplierCode, String batch) {
        return inOutReportsMapper.selectPageVo(page, typeId, subTypeId, materialCode, supplierCode, batch);
    }
}
