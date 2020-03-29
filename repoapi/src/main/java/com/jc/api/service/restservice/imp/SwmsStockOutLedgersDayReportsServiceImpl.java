package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.api.entity.SwmsStockOutLedgersDayReports;
import com.jc.api.mapper.SwmsStockOutLedgersDayReportsMapper;
import com.jc.api.service.restservice.ISwmsStockOutLedgersDayReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物料出库日台账表 服务实现类
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-13
 */
@Service
public class SwmsStockOutLedgersDayReportsServiceImpl extends ServiceImpl<SwmsStockOutLedgersDayReportsMapper, SwmsStockOutLedgersDayReports> implements ISwmsStockOutLedgersDayReportsService {
    @Autowired
    SwmsStockOutLedgersDayReportsMapper outLedgersDayReportsMapper;

    @Override
    public IPage<SwmsStockOutLedgersDayReports> selectByPage(Page page, Integer typeId, Integer subTypeId, Integer deptId, String startTime, String endTime) {
        return outLedgersDayReportsMapper.selectPageVo(page,typeId,subTypeId,deptId,startTime,endTime);
    }

}
