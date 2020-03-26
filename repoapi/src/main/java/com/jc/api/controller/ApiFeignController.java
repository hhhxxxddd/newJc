package com.jc.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.api.entity.*;
import com.jc.api.mapper.SwmsStockInLedgersMapper;
import com.jc.api.mapper.SwmsStockInventoryReallyReportsMapper;
import com.jc.api.mapper.SwmsStockOutRecordDetailMapper;
import com.jc.api.mapper.SwmsStockOutRecordHeadMapper;
import com.jc.api.service.restservice.imp.StockOutRecordHeadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ApiFeignController {

    @Autowired
    SwmsStockOutRecordHeadMapper headMapper;
    @Autowired
    SwmsStockOutRecordDetailMapper detailMapper;
    @Autowired
    SwmsStockInLedgersMapper ledgersMapper;
    @Autowired
    SwmsStockInventoryReallyReportsMapper reallyReportsMapper;
    @Autowired
    StockOutRecordHeadService stockOutRecordHeadService;
    /**\
     * 审核通过，修改相应的标志位
     * @param commonBatchId
     * @return
     */
    @PostMapping(value = "/jc/passAudit")
    Boolean passAudit(@RequestParam Long commonBatchId){
        log.info("Feign-api：审核通过操作");
        //头表变成进行中
        SwmsStockOutRecordHead head = getByBatchId(commonBatchId);
        if(head == null){
            throw new RuntimeException("申请单" + commonBatchId + "不存在");
        }
        head.setMaterialStatus(1);
        headMapper.updateById(head);

        //入库台账变成出库待执行
        List<SwmsStockOutRecordDetail> details = getDetailByHeadId(Long.valueOf(head.getId()));
        details.forEach(e->{
            SwmsStockInLedgers ledgers = ledgersMapper.selectById(e.getStockInRecordAccountId());
            ledgers.setMaterialStatus(2);
            ledgersMapper.updateById(ledgers);
        });

        //实际库存表不变，已经出库，在之前的地方已经修改过可用库存了。

        //向新松发送请求
        stockOutRecordHeadService.outStart(commonBatchId.intValue(),2);
        return true;
    }

    @PostMapping(value = "/jc/notPassAudit")
    Boolean notPass(@RequestParam Long commonBatchId){
        log.info("Feign-api：审核不通过操作");
        //头表不变
        SwmsStockOutRecordHead head = getByBatchId(commonBatchId);
        if(head == null){
            throw new RuntimeException("申请单" + commonBatchId + "不存在");
        }

        //入库台账变成在库中
        List<SwmsStockOutRecordDetail> details = getDetailByHeadId(Long.valueOf(head.getId()));
        details.forEach(e->{
            SwmsStockInLedgers ledgers = ledgersMapper.selectById(e.getStockInRecordAccountId());
            ledgers.setMaterialStatus(0);
            ledgersMapper.updateById(ledgers);

            //实际库存表修改其可用库存
            QueryWrapper<SwmsStockInventoryReallyReports> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("material_name_code",ledgers.getMaterialNameCode())
                    .eq("material_supplier_code",ledgers.getMaterialSupplierCode())
                    .eq("material_batch",ledgers.getMaterialBatch());
            List<SwmsStockInventoryReallyReports> reports = reallyReportsMapper.selectList(queryWrapper);
            if(reports.size() > 0){
                SwmsStockInventoryReallyReports reports1 = reports.get(0);
                reports1.setUsefulWeight(reports1.getUsefulWeight() + ledgers.getWeight());
                reallyReportsMapper.updateById(reports1);
            }
        });
        return true;
    }

    private SwmsStockOutRecordHead getByBatchId(Long batchId){
        QueryWrapper<SwmsStockOutRecordHead> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("application_form_id",batchId);
        List<SwmsStockOutRecordHead> ans = headMapper.selectList(queryWrapper);
        if(ans.size() > 0){
            return ans.get(0);
        }
        return null;
    }

    private List getDetailByHeadId(Long headId){
        QueryWrapper<SwmsStockOutRecordDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stock_out_record_head_id",headId);
        return detailMapper.selectList(queryWrapper);
    }

}
