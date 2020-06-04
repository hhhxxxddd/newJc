package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsStockInLedgers;
import com.jc.api.entity.SwmsStockInventoryReallyReports;
import com.jc.api.mapper.SwmsStockInLedgersMapper;
import com.jc.api.mapper.SwmsStockInventoryReallyReportsMapper;
import com.jc.api.service.restservice.ISwmsStockInventoryReallyReportsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XudongHu
 * @apiNote 物料实际库存报表实现类
 * @className SwmsStockInventoryReallyReportsService
 * @modifier
 * @since 20.1.12日18:24
 */
@Service
@Slf4j
public class SwmsStockInventoryReallyReportsService implements ISwmsStockInventoryReallyReportsService {
    @Autowired
    private SwmsStockInventoryReallyReportsMapper swmsStockInventoryReallyReportsMapper;

    @Autowired
    private SwmsStockInLedgersMapper swmsStockInLedgersMapper;

    /**
     * 添加库存
     *
     * @param entity
     * @return
     */
    @Override
    public SwmsStockInventoryReallyReports autoAdd(SwmsStockInventoryReallyReports entity) {
        Integer materialInfoId = entity.getMaterialNameCode();
        QueryWrapper<SwmsStockInventoryReallyReports> byInfoId = new QueryWrapper<>();
        byInfoId.eq("material_name_code", materialInfoId).last("limit 1");
        SwmsStockInventoryReallyReports oldValue = swmsStockInventoryReallyReportsMapper.selectOne(byInfoId);
        if(oldValue!=null){
            log.info("开始增加库存:");
            Float realWeight = oldValue.getRealWeight();
            Float usefulWeight = oldValue.getUsefulWeight();
            oldValue.setRealWeight(realWeight+entity.getRealWeight());
            oldValue.setUsefulWeight(usefulWeight+entity.getUsefulWeight());
            swmsStockInventoryReallyReportsMapper.updateById(oldValue);
            log.info("增加库存结束:");
            log.info("实际库存:{}->{}", realWeight, oldValue.getRealWeight());
            log.info("可用库存:{}-{}", usefulWeight, oldValue.getUsefulWeight());
        } else {
            log.info("发现新库存-开始初始化新增:{}", entity);
            swmsStockInventoryReallyReportsMapper.insert(entity);
            log.info("库存初始化新增结束");
            log.info(entity.toString());
        }
        return entity;
    }

    @Override
    public IPage<SwmsStockInventoryReallyReports> selectByPage(Page page, Integer typeId, Integer subTypeId, Integer materialNameCode, Integer supplierId) {
        return swmsStockInventoryReallyReportsMapper.selectPageVo(page, typeId, subTypeId, supplierId, materialNameCode);
    }

    @Override
    public List<SwmsStockInLedgers> getByBatch(Integer materialCode) {
        QueryWrapper<SwmsStockInLedgers> byBatch = new QueryWrapper<>();
        byBatch.eq("material_name_code", materialCode).ne("material_status", 2);
        return swmsStockInLedgersMapper.selectList(byBatch);
    }
}
