package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.param.MaterialStockQueryParam;
import com.jc.api.entity.po.MaterialStock;
import com.jc.api.entity.vo.MaterialInfoJointVo;
import com.jc.api.entity.vo.MaterialStockJointVo;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.mapper.MaterialStockMapper;
import com.jc.api.service.restservice.IMaterialInfoService;
import com.jc.api.service.restservice.IMaterialStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XudongHu
 * @className MaterialStockServiceImp
 * @apiNote 库存实现类
 * @modifer
 * @since 2019/10/30日15:49
 */
@Service
@Slf4j
@Deprecated
public class MaterialStockService implements IMaterialStockService {
    @Autowired
    private MaterialStockMapper materialStockMapper;
    @Autowired
    private IMaterialInfoService iMaterialInfoService;


    /**
     * 增减库存
     *
     * @param materialStock po
     * @return po
     */
    @Override
    public MaterialStock stockSetting(MaterialStock materialStock) {
        Long materialInfoId = materialStock.getMaterialInfoId();
        QueryWrapper<MaterialStock> byInfoId = new QueryWrapper<>();
        byInfoId.eq("material_info_id", materialInfoId).last("limit 1");
        MaterialStock oldValue = materialStockMapper.selectOne(byInfoId);
        if (oldValue == null) throw new DataNotFindException("设置库存失败,物料信息不存在:" + materialInfoId);
        log.info("库存增减=====>原始库存{}为,袋数:{},重量:{}KG",oldValue.getMaterialInfoId(),oldValue.getBagsSum(),oldValue.getWeightSum());
        Integer bagsSumResult = Math.max(materialStock.getBagsSum() == null ? oldValue.getBagsSum() : oldValue.getBagsSum() + materialStock.getBagsSum(), 0);
        Double weightSumResult = Math.max(materialStock.getWeightSum() == null ? oldValue.getWeightSum() : oldValue.getWeightSum() + materialStock.getWeightSum(), 0);
        oldValue.setBagsSum(bagsSumResult);
        oldValue.setWeightSum(weightSumResult);
        materialStockMapper.updateById(oldValue);
        return oldValue;
    }

    /**
     * 清空所有库存
     *
     * @return boolean
     */
    @Override
    @Transactional
    public Boolean stockTruncate() {
        List<MaterialStock> materialStocks = materialStockMapper.selectList(new QueryWrapper<>());
        materialStocks.forEach(e -> {
            e.setWeightSum(0.0);
            e.setBagsSum(0);
            materialStockMapper.updateById(e);
        });
        return true;
    }

    /**
     * 根据物料信息id查询
     *
     * @param materialInfoId 物料信息id
     * @return
     */
    @Override
    public MaterialStock getByInfoId(Integer materialInfoId) {
        QueryWrapper<MaterialStock> byMaterialInfoId = new QueryWrapper<>();
        byMaterialInfoId.eq("material_info_id", materialInfoId).last("limit 1");
        return materialStockMapper.selectOne(byMaterialInfoId);
    }

    /**
     * 查询所有-条件
     *
     * @param materialStockQueryParam 库存信息查询参数
     * @return list VO
     */
    @Override
    @Transactional
    public List<MaterialStockJointVo> getAll(MaterialStockQueryParam materialStockQueryParam) {
        List<MaterialStockJointVo> stockJointVos = new ArrayList<>();
        QueryWrapper<MaterialStock> stockQueryWrapper = materialStockQueryParam.build();
        List<MaterialStock> materialStocks = materialStockMapper.selectList(stockQueryWrapper);

        if (materialStockQueryParam.hasMaterialInfo()) {
            List<MaterialInfoJointVo> allInfoVo = iMaterialInfoService.getAllVo(materialStockQueryParam.buildMaterialInfoQueryParam());
            allInfoVo.forEach(e -> stockJointVos.add(infoVoToStockVo(e, null)));
            Map<String, MaterialStock> stockMap = new HashMap<>();
            materialStocks.forEach(e -> stockMap.put(e.getId(), e));

            stockJointVos.removeIf(e -> !inList(e.getId(), materialStocks));
            stockJointVos.forEach(e -> e.setMaterialStock(stockMap.get(e.getId())));
            return stockJointVos;
        }
        materialStocks.forEach(e -> stockJointVos.add(infoVoToStockVo(iMaterialInfoService.getById(e.getMaterialInfoId().toString()), e)));
        return stockJointVos;
    }

    /**
     * 查询所有-分页/条件
     *
     * @param page                    分页参数
     * @param materialStockQueryParam 库存信息查询参数
     * @return
     */
    @Override
    @Transactional
    public IPage<MaterialStockJointVo> getAllByPage(Page page, MaterialStockQueryParam materialStockQueryParam) {
        List<MaterialStockJointVo> stockJointVos = new ArrayList<>();
        QueryWrapper<MaterialStock> condition = materialStockQueryParam.build();
        //如果需要外键
        if (materialStockQueryParam.hasMaterialInfo()) {
            List<MaterialInfoJointVo> allInfoVo = iMaterialInfoService.getAllVo(materialStockQueryParam.buildMaterialInfoQueryParam());

            Map<String, MaterialInfoJointVo> infoVoMap = new HashMap<>();
            allInfoVo.forEach(e -> infoVoMap.put(e.getId(), e));

            List<String> infoIdList = new ArrayList<>();
            allInfoVo.forEach(e -> infoIdList.add(e.getId()));
            condition.in("material_info_id", infoIdList);

            IPage iPage = materialStockMapper.selectPage(page, condition);
            List<MaterialStock> stockRecords = iPage.getRecords();
            stockRecords.forEach(e -> {
                MaterialStockJointVo materialStockJointVo = infoVoToStockVo(infoVoMap.get(e.getMaterialInfoId().toString()), e);
                stockJointVos.add(materialStockJointVo);
            });

            return iPage.setRecords(stockJointVos);
        }

        IPage iPage = materialStockMapper.selectPage(page, condition);
        List<MaterialStock> stockRecords = iPage.getRecords();
        stockRecords.forEach(e -> {
            MaterialStockJointVo materialStockJointVo = infoVoToStockVo(iMaterialInfoService.getById(e.getMaterialInfoId().toString()), e);
            stockJointVos.add(materialStockJointVo);
        });
        return null;
    }

    //infoVo转stockVo
    private MaterialStockJointVo infoVoToStockVo(MaterialInfoJointVo v, MaterialStock materialStock) {
        return MaterialStockJointVo.builder()
                .completeType(v.getCompleteType())
                .materialName(v.getMaterialName())
                .materialNameCode(v.getMaterialNameCode())
                .materialInfoId(v.getId())
                .materialStock(materialStock)
                .build();
    }

    //物料信息id是否在库存信息中
    private Boolean inList(String id, List<MaterialStock> materialStocks) {
        boolean isExist = false;
        for (MaterialStock m : materialStocks) {
            if (m.getId().equals(id)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }
}
