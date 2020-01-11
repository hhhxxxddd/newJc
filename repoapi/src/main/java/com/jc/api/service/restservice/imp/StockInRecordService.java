package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.param.StockInRecordQueryParam;
import com.jc.api.entity.po.StockInRecord;
import com.jc.api.mapper.StockInRecordMapper;
import com.jc.api.service.restservice.IStockInRecordAccountService;
import com.jc.api.service.restservice.IStockInRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author XudongHu
 * @className StockInRecordImp
 * @apiNote 入库记录 实现类
 * @modifer
 * @since 2019/10/31日4:10
 */
@Service
@Slf4j
public class StockInRecordService implements IStockInRecordService {
    @Autowired
    private StockInRecordMapper stockInRecordMapper;
    @Autowired
    private IStockInRecordAccountService iStockInRecordAccountService;

    /**
     * 入库记录
     *
     * @param materialCode 物料编码
     * @param createPerson 创建人
     * @return 是否成功
     */
    @Override
    @Transactional
    public Boolean insert(String materialCode, String createPerson) {
        log.info("入库开始============================>物料编码:" + materialCode);
        StockInRecord stockInRecord = StockInRecord.builder()
                .materialCode(materialCode)
                .createdTime(new Date())
                .createdPerson(createPerson)
                .build();
        boolean success = stockInRecordMapper.insert(stockInRecord) > 0;
        if (success) {
            log.info(stockInRecord.toString());
            log.info("入库操作成功:===============================>入库结束");
            //异步存入入库台账
            iStockInRecordAccountService.parsingAndInsert(stockInRecord.getId());
        } else {
            log.error("入库操作失败:==============================>数据丢失");
            log.error(stockInRecord.toString());
        }

        return success;
    }

    /**
     * 入库记录-分页/条件查询
     * @param page 分页bean
     * @param queryParam 条件参数
     * @return page
     */
    @Override
    public IPage getAllByPage(Page page, StockInRecordQueryParam queryParam) {
        QueryWrapper<StockInRecord> queryWrapper = queryParam.build();
        return stockInRecordMapper.selectPage(page, queryWrapper);
    }

    /**
     * 入库记录-条件查询
     * @param queryParam 条件参数
     * @return list
     */
    @Override
    public List<StockInRecord> getAll(StockInRecordQueryParam queryParam) {
        QueryWrapper<StockInRecord> queryWrapper = queryParam.build();
        return stockInRecordMapper.selectList(queryWrapper);
    }

    /**
     * 入库记录-根据id删除
     * @param id 主键
     * @return boolean
     */
    @Override
    public Boolean delete(String id) {
        return stockInRecordMapper.deleteById(id) > 0;
    }

    /**
     * 入库记录-根据ids批量删除
     * @param ids 主键集合
     * @return boolean
     */
    @Override
    public Boolean deleteBatch(Set<String> ids) {
        return stockInRecordMapper.deleteBatchIds(ids)>0;
    }
}
