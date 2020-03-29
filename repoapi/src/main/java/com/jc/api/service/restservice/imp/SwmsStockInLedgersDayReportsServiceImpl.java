package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.api.entity.SwmsStockInLedgersDayReports;
import com.jc.api.entity.SwmsStockOutRecordDetail;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.mapper.SwmsStockInLedgersDayReportsMapper;
import com.jc.api.service.restservice.ISwmsStockInLedgersDayReportsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物料入库日台账表 服务实现类
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-13
 */
@Service
@Slf4j
public class SwmsStockInLedgersDayReportsServiceImpl extends ServiceImpl<SwmsStockInLedgersDayReportsMapper, SwmsStockInLedgersDayReports> implements ISwmsStockInLedgersDayReportsService {

    @Autowired
    SwmsStockInLedgersDayReportsMapper reportsMapper;

    @Override
    public IPage<SwmsStockInLedgersDayReports> selectByPage(Page page, Integer typeId, Integer subTypeId, Integer supplierId, String startTime, String endTime) {
        return reportsMapper.selectPageVo(page, typeId, subTypeId, supplierId, startTime, endTime);
    }

    @Override
    public void updateByIds(Long[] ids, Integer status) {
        for (Long id : ids) {
            updateById(id, status);
        }
    }

    @Override
    public void updateById(Long id, Integer status) {
        reportsMapper.updateById(id, status);
    }

    @Override
    public void updateInrecords(SwmsStockOutRecordDetail detail) {
        QueryWrapper<SwmsStockInLedgersDayReports> byBatchByCode = new QueryWrapper<>();
        byBatchByCode.eq("material_batch",detail.getMaterialBatch())
                .eq("material_name_code",detail.getMaterialNameCode())
                .last("limit 1");
        SwmsStockInLedgersDayReports entity = reportsMapper.selectOne(byBatchByCode);

        if(entity == null){
            throw new DataNotFindException("出库日台帐信息未找到：" + detail.getMaterialBatch() + "," + detail.getMaterialCode());
        }

        entity.setOutDay(detail.getCreatedTime());
        entity.setOutWeight(entity.getWeight() + detail.getWeight());
        entity.setOutCounts(entity.getBagCounts() + detail.getBagNum());

        reportsMapper.updateById(entity);
    }
}
