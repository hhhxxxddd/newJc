package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsStockInventoryReallyReports;
import com.jc.api.entity.SwmsStockOutLedgers;
import com.jc.api.entity.SwmsStockOutRecordDetail;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.exception.custom.ParamVerifyException;
import com.jc.api.mapper.SwmsStockInventoryReallyReportsMapper;
import com.jc.api.mapper.SwmsStockOutLedgersMapper;
import com.jc.api.mapper.SwmsStockOutRecordDetailMapper;
import com.jc.api.service.restservice.ISwmsStockOutLedgersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
