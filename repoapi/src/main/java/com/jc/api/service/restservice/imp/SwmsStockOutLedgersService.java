package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.*;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.exception.custom.ParamVerifyException;
import com.jc.api.mapper.SwmsStockInventoryReallyReportsMapper;
import com.jc.api.mapper.SwmsStockOutLedgersDayReportsMapper;
import com.jc.api.mapper.SwmsStockOutLedgersMapper;
import com.jc.api.mapper.SwmsStockOutRecordDetailMapper;
import com.jc.api.service.restservice.ISwmsStockInventoryDailyReportsService;
import com.jc.api.service.restservice.ISwmsStockOutLedgersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.EndElement;
import java.util.List;

/**
 * @author XudongHu
 * @apiNote 出库台账实现类
 * @className SwmsStockOutLedgersService
 * @modifier
 * @since 20.1.12日23:10
 */
@Service
@Slf4j
public class SwmsStockOutLedgersService implements ISwmsStockOutLedgersService {
    @Autowired
    private SwmsStockOutLedgersMapper swmsStockOutLedgersMapper;
    @Autowired
    private SwmsStockOutRecordDetailMapper swmsStockOutRecordDetailMapper;
    @Autowired
    private SwmsStockInventoryReallyReportsMapper swmsStockInventoryReallyReportsMapper;
    @Autowired
    private SwmsStockOutLedgersDayReportsMapper outLedgersDayReportsMapper;
    @Autowired
    private ISwmsStockInventoryDailyReportsService inventoryDailyReportsService;

    /**
     * 生成出库台账表
     *
     * @param swmsStockOutRecordDetailId 出库单明细id
     * @param swmsStockInRecordAccountId 入库台账id
     * @return
     */
    @Override
    public SwmsStockOutLedgers generate(String swmsStockOutRecordDetailId, String swmsStockInRecordAccountId) {
        SwmsStockOutRecordDetail detail = swmsStockOutRecordDetailMapper.selectById(swmsStockOutRecordDetailId);
        if (detail == null) {
            throw new DataNotFindException("出库台账生成失败,不存在该出库详情id:" + swmsStockOutRecordDetailId);
        }
        if (!detail.getCompletionFlag()) {
            throw new ParamVerifyException("出库台账生成失败,该出库单详情显示此出库未完成,id:" + swmsStockOutRecordDetailId);
        }
        QueryWrapper<SwmsStockOutLedgers> byMaterialCode = new QueryWrapper<>();
        byMaterialCode.eq("material_code", detail.getMaterialCode()).last("limit 1");
        if (null != swmsStockOutLedgersMapper.selectOne(byMaterialCode)) {
            throw new DataDuplicateException("出库台账生成失败,已存在的数据,物料编码重复:" + detail.getMaterialCode());
        }

        SwmsStockOutLedgers entity = new SwmsStockOutLedgers();
        BeanUtils.copyProperties(detail, entity);
        entity
                //入库台账id
                .setStockInRecordAccountId(Long.valueOf(swmsStockInRecordAccountId))
                //出库单明细id
                .setStockOutRecordAccountId(Long.valueOf(swmsStockOutRecordDetailId));
        swmsStockOutLedgersMapper.insert(entity);

        //生成出库日台帐
        SwmsStockOutLedgersDayReports outLedgersDayReports = new SwmsStockOutLedgersDayReports();
        outLedgersDayReports.setBagCounts(entity.getBagNum())
                .setCreatedDay(entity.getCreatedTime())
                .setDeptCode(1)//不存在的领用单位
                .setDeptName("未知部门")//不存在部门名称
                .setMaterialBatch(entity.getMaterialBatch())
                .setMaterialNameCode(entity.getMaterialNameCode())
                .setMaterialSubTypeId(entity.getMaterialSubTypeId())
                .setMaterialSupplierCode(entity.getMaterialSupplierCode())
                .setMaterialTypeId(entity.getMaterialTypeId())
                .setMaterialWorkshopId(entity.getMaterialWorkshopId())
                .setMeasureUnit(entity.getMeasureUnit())
                .setSubTypeName(entity.getSubTypeName())
                .setSupplierName(entity.getSupplierName())
                .setTypeName(entity.getMaterialTypeName())
                .setWeight(entity.getWeight());
        outLedgersDayReportsMapper.insert(outLedgersDayReports);

        //每天由新松接口写入入库、出库流水后，进行解析时，修改本表数据；
        //物料按供应商统计计算。
        inventoryDailyReportsService.updateDailyRecord(null,outLedgersDayReports);

        //todo 修改库存
        Float outWeight = entity.getWeight();
        Integer materialNameCode = entity.getMaterialNameCode();
        QueryWrapper<SwmsStockInventoryReallyReports> byMaterialNameCode = new QueryWrapper<>();
        byMaterialNameCode.eq("material_name_code", materialNameCode).last("limit 1");
        SwmsStockInventoryReallyReports reallyWeight = swmsStockInventoryReallyReportsMapper.selectOne(byMaterialNameCode);
        Float result = reallyWeight.getRealWeight() - outWeight;
        Float usefulResult = reallyWeight.getUsefulWeight() - outWeight;
        reallyWeight
                .setRealWeight(result)
                .setUsefulWeight(usefulResult);
        swmsStockInventoryReallyReportsMapper.updateById(reallyWeight);

        return entity;
    }

    /**
     * 查询所有 物料编码模糊
     *
     * @param materialCode
     * @return
     */
    @Override
    public List<SwmsStockOutLedgers> getAll(String materialCode) {
        return swmsStockOutLedgersMapper.selectEntity(materialCode);
    }

    /**
     * 查询所有  分页/编码模糊
     *
     * @param page
     * @param materialCode
     * @return
     */
    @Override
    public IPage<SwmsStockOutLedgers> getAllByPage(Page page, String materialCode) {
        return swmsStockOutLedgersMapper.selectEntityByPage(page, materialCode);
    }
}
