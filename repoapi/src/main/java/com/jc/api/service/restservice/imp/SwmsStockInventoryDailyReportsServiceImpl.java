package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.api.entity.SwmsStockInventoryDailyReports;
import com.jc.api.mapper.SwmsStockInventoryDailyReportsMapper;
import com.jc.api.service.restservice.ISwmsStockInventoryDailyReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物料库存日报表 服务实现类
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-13
 */
@Service
public class SwmsStockInventoryDailyReportsServiceImpl extends ServiceImpl<SwmsStockInventoryDailyReportsMapper, SwmsStockInventoryDailyReports> implements ISwmsStockInventoryDailyReportsService {

    @Autowired
    SwmsStockInventoryDailyReportsMapper inventoryDailyReportsMapper;

    @Override
    public IPage<SwmsStockInventoryDailyReports> selectByPage(Page page, Integer typeId, Integer subTypeId, String startTime, String endTime) {
        return inventoryDailyReportsMapper.selectPageVo(page,typeId,subTypeId,startTime,endTime);
    }

    @Override
    public void update(Long id, String comments) {
        inventoryDailyReportsMapper.updateComments(id,comments);
    }
}
