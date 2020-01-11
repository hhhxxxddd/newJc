package com.jc.api.service.restservice.imp;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.constant.MaterialStatusEnum;
import com.jc.api.constant.StockOutStatusEnum;
import com.jc.api.entity.param.StockOutRecordHeadQueryParam;
import com.jc.api.entity.po.MaterialStock;
import com.jc.api.entity.po.StockInRecordAccount;
import com.jc.api.entity.po.StockOutRecord;
import com.jc.api.entity.po.StockOutRecordHead;
import com.jc.api.entity.vo.StockOutHeadVo;
import com.jc.api.entity.vo.StockOutRecordVo;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.exception.custom.StatusRefuseException;
import com.jc.api.mapper.StockInRecordAccountMapper;
import com.jc.api.mapper.StockOutRecordHeadMapper;
import com.jc.api.mapper.StockOutRecordMapper;
import com.jc.api.service.feignservice.ICommonService;
import com.jc.api.service.restservice.IMaterialStockService;
import com.jc.api.service.restservice.IStockInRecordAccountService;
import com.jc.api.service.restservice.IStockOutRecordHeadService;
import com.jc.api.service.restservice.IStockOutRecordService;
import com.jc.api.utils.WebServiceBindingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author：XudongHu
 * @className:OutPlanServiceImp
 * @description:
 * @date:23:40 2019/3/30
 * @Modifer:
 */
@Service
@Slf4j
public class StockOutRecordHeadService implements IStockOutRecordHeadService {
    @Autowired
    private ICommonService iCommonService;
    @Autowired
    private StockOutRecordMapper stockOutRecordMapper;
    @Autowired
    private StockOutRecordHeadMapper stockOutRecordHeadMapper;
    @Autowired
    private StockInRecordAccountMapper stockInRecordAccountMapper;
    @Autowired
    private IStockInRecordAccountService iStockInRecordAccountService;
    @Autowired
    private IMaterialStockService iMaterialStockService;
    @Autowired
    private IStockOutRecordService iStockOutRecordService;

    /**
     * 新增出库计划
     *
     * @param outHead 出库表头
     * @param records 出库内容
     * @return
     */
    @Override
    @Transactional
    public StockOutRecordHead repoOut(StockOutRecordHead outHead, List<StockOutRecord> records) {
        String outHeadJson = JSONObject.toJSONString(outHead);
        JSONObject jsonObject = JSONObject.parseObject(outHeadJson);
        //计算总袋数
        //计算总重量
        //检验物料状态
        Integer bagsSum = records.size();
        AtomicReference<Double> weightSum = new AtomicReference<>(0.0);
        records.forEach(e -> {
            StockInRecordAccount account = stockInRecordAccountMapper.selectById(e.getStockInRecordAccountId());
            if (account == null) throw new DataNotFindException("出库失败,无法识别台账id:" + e.getStockInRecordAccountId());
            if (!account.getMaterialStatus().equals(MaterialStatusEnum.IN_REPO.getCode()))
                throw new StatusRefuseException("出库失败,此袋物料不在库中或者已待出库:" + account.getId());
            weightSum.updateAndGet(v -> v + account.getWeight());
        });
        //==========>发送common开始
        String receivedObject = iCommonService.outAudit(jsonObject);
        log.info("拿到出库数据:{}", receivedObject);
        JSONObject receivedJson = JSONObject.parseObject(receivedObject);
        Integer applicationFormId = receivedJson.getInteger("applicationFormId");
        String stockOutRecordHeadCode = receivedJson.getString("stockOutRecordHeadCode");
        String createdPerson = receivedJson.getString("createdPerson");
        log.info(createdPerson);
        String endPosition = receivedJson.getString("endPosition");
        log.info(endPosition);
        String productionLine = receivedJson.getString("productionLine");
        log.info(productionLine);
        //拿到了信息
        outHead.setApplicationFormId(Long.valueOf(applicationFormId));
        outHead.setStockOutRecordHeadCode(stockOutRecordHeadCode);
        outHead.setCompletionFlag(StockOutStatusEnum.SAVE.getCode());
        outHead.setBagsSum(bagsSum);
        outHead.setWeightSum(weightSum.get());
        //=========>发送common结束
        //存表头
        Boolean insertHead = stockOutRecordHeadMapper.insert(outHead) > 0;
        log.info("出库表头新增:{}", insertHead);
        String headId = outHead.getId();
        //存表内容
        records.forEach(e -> {
            log.info(e.toString());
            e.setStockOutRecordHeadId(Integer.valueOf(headId));
            //修改物料状态为待出库
            iStockInRecordAccountService.out(e.getStockInRecordAccountId().toString(), MaterialStatusEnum.WAIT_FOR_OUT.getCode());
            Boolean insertRecord = stockOutRecordMapper.insert(e) > 0;
            log.info("新增出库物料记录:{}-{}", e, insertRecord);
        });
        return outHead;
    }

    /**
     * 更新未提交的出库计划
     *
     * @param outRecordHead 出库表头
     * @return
     */
    @Override
    public Boolean update(StockOutRecordHead outRecordHead) {
        StockOutRecordHead oldValue = stockOutRecordHeadMapper.selectById(outRecordHead.getId());
        if (oldValue == null) throw new DataNotFindException("更新出库计划失败,未找到此出库计划id:" + outRecordHead.getId());
        if (!oldValue.getCompletionFlag().equals(StockOutStatusEnum.SAVE.getCode()))
            throw new StatusRefuseException("更新出库计划失败,仅能更新保存数据");
        return stockOutRecordHeadMapper.updateById(outRecordHead) > 0;
    }

    /**
     * 删除未提交的出库计划
     *
     * @param id 主键
     * @return
     */
    @Override
    @Transactional
    public Boolean delete(Integer id) {
        StockOutRecordHead head = stockOutRecordHeadMapper.selectById(id);
        if (head == null) throw new DataNotFindException("删除失败,出库数据未找到:" + id);
        Integer completionFlag = head.getCompletionFlag();
        if (!completionFlag.equals(StockOutStatusEnum.SAVE.getCode())
                || !completionFlag.equals(StockOutStatusEnum.OUT_REFUSE.getCode()))
            throw new StatusRefuseException("删除失败,仅能删除保存状态的数据:" + completionFlag);

        //开始删除详情
        QueryWrapper<StockOutRecord> byHeadId = new QueryWrapper<>();
        byHeadId.eq("stock_out_head_id", id);
        List<StockOutRecord> records = stockOutRecordMapper.selectList(byHeadId);
        Set<String> ids = new HashSet<>();
        records.forEach(e -> {
            //库存改为在库中
            iStockInRecordAccountService.out(e.getStockInRecordAccountId().toString(), MaterialStatusEnum.IN_REPO.getCode());
            ids.add(e.getId());
        });
        //出库记录全部删除
        if (!ids.isEmpty()) stockOutRecordMapper.deleteBatchIds(ids);
        return stockOutRecordHeadMapper.deleteById(id) > 0;
    }

    /**
     * 查询详情
     *
     * @param id   主键/批号id
     * @param type 是否是否使用主键查询
     * @return
     */
    @Override
    public StockOutHeadVo byId(Integer id, boolean type) {
        StockOutRecordHead stockOutRecordHead;
        if (type) stockOutRecordHead = stockOutRecordHeadMapper.selectById(id);
        else {
            QueryWrapper<StockOutRecordHead> byApplicationId = new QueryWrapper<>();
            byApplicationId.eq("application_form_id", id).last("limit 1");
            stockOutRecordHead = stockOutRecordHeadMapper.selectOne(byApplicationId);
        }
        if (stockOutRecordHead.getId() == null) return null;
        StockOutHeadVo stockOutHeadVo = toVo(stockOutRecordHead);
        QueryWrapper<StockOutRecord> byHeadId = new QueryWrapper<>();
        byHeadId.eq("stock_out_head_id", stockOutRecordHead.getId());
        //内容查询
        List<StockOutRecord> records = stockOutRecordMapper.selectList(byHeadId);
        Map<String, List<StockOutRecordVo>> bigMap = new HashMap<>();
        records.forEach(e -> {
            bigMap.put(e.getGroupName(), new ArrayList<>());
        });
        records.forEach(e -> {
            List<StockOutRecordVo> stockOutRecordVos = bigMap.get(e.getGroupName());
            stockOutRecordVos.add(iStockOutRecordService.getById(e.getId()));
        });
        stockOutHeadVo.setRecords(bigMap);
        return stockOutHeadVo;
    }

    /**
     * 查询所有表头-分页/条件
     *
     * @param stockOutHeadQueryParam 出库表头查询参数
     * @return
     */
    @Override
    public IPage<StockOutHeadVo> pages(Page page, StockOutRecordHeadQueryParam stockOutHeadQueryParam) {
        QueryWrapper<StockOutRecordHead> queryWrapper = stockOutHeadQueryParam.build();
        IPage iPage = stockOutRecordHeadMapper.selectPage(page, queryWrapper);
        List<StockOutRecordHead> records = iPage.getRecords();
        List<StockOutHeadVo> stockOutRecordHeadVos = new ArrayList<>();
        records.forEach(e -> stockOutRecordHeadVos.add(toVo(e)));
        return iPage.setRecords(stockOutRecordHeadVos);
    }

    /**
     * 响应审核结果
     *
     * @param applicationFormId 审核流id
     * @return boolean
     */
    @Override
    @Transactional
    public Boolean outStart(Integer applicationFormId, Integer status) {
        QueryWrapper<StockOutRecordHead> byApplicationId = new QueryWrapper<>();
        byApplicationId.eq("application_form_id", applicationFormId).last("limit 1");
        StockOutRecordHead head = stockOutRecordHeadMapper.selectOne(byApplicationId);
        //修改状态
        if (head == null) throw new DataNotFindException("出库响应失败,不存在此审核id:" + applicationFormId);
        head.setCompletionFlag(status);
        stockOutRecordHeadMapper.updateById(head);
        if (!status.equals(StockOutStatusEnum.OUT_START.getCode())) return true;
        //出库开始
        String endPosition = iCommonService.endPosition(head.getEndPositionId());
        String personName = iCommonService.personName(head.getCreatedPersonId());
        String productionLine = iCommonService.productionLine(head.getProductionLineId());
        String stockOutRecordHeadCode = head.getStockOutRecordHeadCode();
        //找详情
        QueryWrapper<StockOutRecord> byHeadId = new QueryWrapper<>();
        byHeadId.eq("stock_out_head_id", head.getId());
        List<StockOutRecord> outRecords = stockOutRecordMapper.selectList(byHeadId);
        //发送新松
        HashMap<String, Object> bigMap = new HashMap<>();
        HashMap<String, List<Object>> medMap = new HashMap<>();
        bigMap.put("PLAN_CODE", stockOutRecordHeadCode);
        bigMap.put("PLAN_CREATER", personName);
        bigMap.put("END_POSITION", endPosition);
        bigMap.put("PRODUCTION_LINE_NO", productionLine);
        bigMap.put("SEND_OUT_LIST", medMap);
        //存储组号
        outRecords.forEach(e -> {
            medMap.put(e.getGroupName(), new ArrayList<>());
        });
        //存储每组的内容
        outRecords.forEach(e -> {
            //修改库存
            StockOutRecord record = stockOutRecordMapper.selectById(e.getId());
            StockInRecordAccount account = stockInRecordAccountMapper.selectById(record.getStockInRecordAccountId());
            List list = medMap.get(e.getGroupName());
            HashMap<String, String> smallMap = new HashMap<>();
//            smallMap.put("WEIGHT", account.getWeight().toString()+"KG");
//            smallMap.put("GOODS_NAME", );
//            smallMap.put("GOODS_TYPE", materialStock.getMaterialType());
//            smallMap.put("SUPPLIER", materialStock.getSupplier());
            smallMap.put("GOODS_BATCH_NO", account.getMaterialCode());
            list.add(smallMap);
        });
        // 转成JSON
        JSONObject jsonObject = new JSONObject(bigMap);
        String response = WebServiceBindingUtil.outPlan(jsonObject.toJSONString());
        log.info("出库发送成功:{}", response);
        return true;
    }

    /**
     * 出库上报-测试使(新松)
     *
     * @param stockOutRecordHeadCode 出库单号
     * @param materialCode           物料编码
     */
    @Override
    @Async("PoolTaskExecutor")
    @Transactional
    public void outPost(String stockOutRecordHeadCode, String materialCode) {
        log.info("出库上报开始,接收到出库数据,单号{},编码{}", stockOutRecordHeadCode, materialCode);
        //更新物料状态
        QueryWrapper<StockInRecordAccount> byMaterialCode = new QueryWrapper<>();
        byMaterialCode.eq("material_code", materialCode).last("limit 1");
        StockInRecordAccount recordAccount = stockInRecordAccountMapper.selectOne(byMaterialCode);
        Boolean out = iStockInRecordAccountService.out(recordAccount.getId(), MaterialStatusEnum.HAVE_OUT.getCode());
        log.info("物料状态更新为已出库:{}", out);
        //更新出库时间
        QueryWrapper<StockOutRecord> outRecordQueryWrapper = new QueryWrapper<>();
        outRecordQueryWrapper.eq("stock_in_record_account_id", recordAccount.getId()).last("limit 1");
        StockOutRecord record = stockOutRecordMapper.selectOne(outRecordQueryWrapper);
        record.setCompletionTime(new Date());
        boolean dateUpdate = stockOutRecordMapper.updateById(record) > 0;
        log.info("出库台账更新:{},出库时间:{}", dateUpdate, record.getCompletionTime());
        //更新库存
        MaterialStock materialStockMinus = MaterialStock
                .builder()
                .materialInfoId(recordAccount.getMaterialInfoId())
                .bagsSum(-1)
                .weightSum(-recordAccount.getWeight())
                .build();
        MaterialStock materialStock = iMaterialStockService.stockSetting(materialStockMinus);
        log.info("库存增减=====>库存{}剩余,袋数:{},重量:{}KG", materialStock.getMaterialInfoId(), materialStock.getBagsSum(), materialStock.getWeightSum());
    }

    /**
     * 出库结果完成上报-测试用(新松)
     *
     * @param stockOutRecordHeadCode 出库表单
     */
    @Override
    @Async("PoolTaskExecutor")
    public void outFinished(String stockOutRecordHeadCode) {
        log.info("出库结果完成上报开始,接收到单号:{},已完成出库任务", stockOutRecordHeadCode);
        QueryWrapper<StockOutRecordHead> byOrderNo = new QueryWrapper<>();
        byOrderNo.eq("stock_out_record_head_code", stockOutRecordHeadCode).last("limit 1");
        StockOutRecordHead stockOutRecordHead = stockOutRecordHeadMapper.selectOne(byOrderNo);
        if (stockOutRecordHead == null) throw new DataNotFindException("出库上报失败,单号未找到:" + stockOutRecordHeadCode);
        stockOutRecordHead.setCompletionFlag(StockOutStatusEnum.OUT_COMPLETE.getCode());
        stockOutRecordHead.setCompletionTime(new Date());
        boolean success = stockOutRecordHeadMapper.updateById(stockOutRecordHead) > 0;
        log.info("出库结果上报已保存:{}", success);
    }

    private StockOutHeadVo toVo(StockOutRecordHead head) {
        String endPosition = iCommonService.endPosition(head.getEndPositionId());
        String personName = iCommonService.personName(head.getCreatedPersonId());
        String productionLine = iCommonService.productionLine(head.getProductionLineId());
        StockOutHeadVo stockOutHeadVo = new StockOutHeadVo(head);
        stockOutHeadVo.setEndPosition(endPosition);
        stockOutHeadVo.setCreatedPerson(personName);
        stockOutHeadVo.setProductionLine(productionLine);
        return stockOutHeadVo;
    }
}
