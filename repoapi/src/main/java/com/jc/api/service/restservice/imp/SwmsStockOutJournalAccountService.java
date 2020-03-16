package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.*;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.exception.custom.ParamVerifyException;
import com.jc.api.mapper.*;
import com.jc.api.service.restservice.ISwmsStockOutJournalAccountService;
import com.jc.api.service.restservice.ISwmsStockOutLedgersService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author XudongHu
 * @apiNote 出库流水实现类
 * @className SwmsStockOutJournalAccountService
 * @modifier
 * @since 20.1.12日22:02
 */
@Service
@Slf4j
public class SwmsStockOutJournalAccountService implements ISwmsStockOutJournalAccountService {
    @Autowired
    private SwmsStockOutJournalAccountMapper swmsStockOutJournalAccountMapper;
    @Autowired
    private SwmsStockInLedgersMapper swmsStockInLedgersMapper;
    @Autowired
    private SwmsStockOutRecordDetailMapper swmsStockOutRecordDetailMapper;
    @Autowired
    private SwmsStockOutLedgersMapper swmsStockOutLedgersMapper;
    @Autowired
    private ISwmsStockOutLedgersService iSwmsStockOutLedgersService;
    @Autowired
    private SwmsStockOutRecordHeadMapper swmsStockOutRecordHeadMapper;

    /**
     * 生成出库流水
     *
     * @param swmsStockOutRecordDetailId 出库详情id
     * @return
     */
    @Override
    @Transactional
    public SwmsStockOutJournalAccount generate(String swmsStockOutRecordDetailId) {
        SwmsStockOutRecordDetail detail = swmsStockOutRecordDetailMapper.selectById(swmsStockOutRecordDetailId);
        if (detail == null) {
            throw new DataNotFindException("出库流水生成失败,不存在该出库详情id:" + swmsStockOutRecordDetailId);
        }
        if (!detail.getCompletionFlag()) {
            throw new ParamVerifyException("出库流水生成失败,该出库单详情显示此出库未完成,id:" + swmsStockOutRecordDetailId);
        }
        SwmsStockOutJournalAccount entity = new SwmsStockOutJournalAccount();
        entity
                .setFlag(true)
                .setCreatedTime(detail.getCreatedTime())
                .setMaterialCode(detail.getMaterialCode());
        swmsStockOutJournalAccountMapper.insert(entity);
        //解析出库台账
        //todo/
        //
        return entity;
    }

    /**
     * 生成出库流水 (在不生成申请单的情况下)
     *
     * @param repoInAccountId 入库台账id
     * @return
     */
    @Override
    @Transactional
    public SwmsStockOutJournalAccount testGenerate(String repoInAccountId) {
        SwmsStockInLedgers stockInLedgers = swmsStockInLedgersMapper.selectById(repoInAccountId);
        if (null == stockInLedgers) {
            throw new DataNotFindException("生成出库流水失败,未找到入库台账id:" + repoInAccountId);
        }
        SwmsStockOutJournalAccount entity = new SwmsStockOutJournalAccount();
        entity.setMaterialCode(stockInLedgers.getMaterialCode());
        entity.setCreatedTime(new Date());
        entity.setFlag(false);
        //存流水
        swmsStockOutJournalAccountMapper.insert(entity);
        //解析台账
        QueryWrapper<SwmsStockOutLedgers> byMaterialCode = new QueryWrapper<>();
        byMaterialCode.eq("material_code", entity.getMaterialCode()).last("limit 1");
        if (null != swmsStockOutLedgersMapper.selectOne(byMaterialCode)) {
            throw new DataDuplicateException("出库台账生成失败,已存在的数据,物料编码重复:" + entity.getMaterialCode());
        }


        SwmsStockOutLedgers swmsStockOutLedgers = new SwmsStockOutLedgers();
        BeanUtils.copyProperties(stockInLedgers, swmsStockOutLedgers);
        swmsStockOutLedgers
                .setStockOutRecordAccountId(null)
                .setStockInRecordAccountId(Long.valueOf(stockInLedgers.getId()))
                .setStockOutRecordId(Long.valueOf(entity.getId()));
        swmsStockOutLedgersMapper.insert(swmsStockOutLedgers);
        return entity;
    }

    /**
     * 查询所有 - 物料编码模糊
     *
     * @param materialCode 物料编码
     * @return
     */
    @Override
    public List<SwmsStockOutJournalAccount> getAll(String materialCode) {
        QueryWrapper<SwmsStockOutJournalAccount> byMaterialCode = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(materialCode)) {
            byMaterialCode.likeRight("material_code", materialCode);
        }
        return swmsStockOutJournalAccountMapper.selectList(byMaterialCode);
    }

    /**
     * 出库上报
     *
     * @param stockOutRecordHeadCode
     * @param materialCode
     */
    @Override
    @Transactional
    @Async("PoolTaskExecutor")
    public void outPost(String stockOutRecordHeadCode, String materialCode) {
        log.info("出库上报开始,接收到出库数据,单号{},编码{}", stockOutRecordHeadCode, materialCode);
        //新增出库流水
        SwmsStockOutJournalAccount swmsStockOutJournalAccount = new SwmsStockOutJournalAccount();
        swmsStockOutJournalAccount
                .setCreatedTime(new Date())
                .setFlag(false)
                .setMaterialCode(materialCode);
        int insert = swmsStockOutJournalAccountMapper.insert(swmsStockOutJournalAccount);
        log.info("出库流水保存:{}", insert > 0);
        //判断是否存在入库台账记录
        QueryWrapper<SwmsStockInLedgers> byMaterialCode = new QueryWrapper<>();
        byMaterialCode.eq("material_code", materialCode).last("limit 1");
        SwmsStockInLedgers stockInLedgers = swmsStockInLedgersMapper.selectOne(byMaterialCode);
        //是否存在出库单详情
        QueryWrapper<SwmsStockOutRecordDetail> detailByMaterialCode = new QueryWrapper<>();
        byMaterialCode.eq("material_code", materialCode).last("limit 1");
        SwmsStockOutRecordDetail detail = swmsStockOutRecordDetailMapper.selectOne(detailByMaterialCode);
        //不存在或者已出库
        if (stockInLedgers == null || detail == null || stockInLedgers.getMaterialStatus().equals(2)) {
            log.info("出库上报失败,库中不存在此数据或已出库,单号{},编码{}", stockOutRecordHeadCode, materialCode);
            return;
        }
        //出库单状态变为已出库
        detail.setCompletionFlag(true);
        swmsStockOutRecordDetailMapper.updateById(detail);
        iSwmsStockOutLedgersService.generate(detail.getId(),stockInLedgers.getId());

    }

    /**
     * 出库完成上报
     *
     * @param stockOutRecordHeadCode
     */
    @Override
    @Async("PoolTaskExecutor")
    public void outFinished(String stockOutRecordHeadCode) {
        log.info("出库结果完成上报开始,接收到单号:{},已完成出库任务", stockOutRecordHeadCode);
        QueryWrapper<SwmsStockOutRecordHead> byOrderNo = new QueryWrapper<>();
        byOrderNo.eq("stock_out_record_head_code", stockOutRecordHeadCode).last("limit 1");
        SwmsStockOutRecordHead stockOutRecordHead = swmsStockOutRecordHeadMapper.selectOne(byOrderNo);
        if (stockOutRecordHead == null) throw new DataNotFindException("出库上报失败,单号未找到:" + stockOutRecordHeadCode);
        stockOutRecordHead.setMaterialStatus(2);
        stockOutRecordHead.setCompletionTime(new Date());
        boolean success = swmsStockOutRecordHeadMapper.updateById(stockOutRecordHead) > 0;
        log.info("出库结果上报已保存:{}", success);
    }

    /**
     * 查询所有 - 物料编码模糊/分页
     *
     * @param materialCode 物料编码
     * @return
     */
    @Override
    public IPage<SwmsStockOutJournalAccount> getAllByPage(Page page, String materialCode) {
        QueryWrapper<SwmsStockOutJournalAccount> byMaterialCode = new QueryWrapper<>();
        if (!StringUtil.isNullOrEmpty(materialCode)) {
            byMaterialCode.likeRight("material_code", materialCode);
        }
        return swmsStockOutJournalAccountMapper.selectPage(page, byMaterialCode);
    }
}
