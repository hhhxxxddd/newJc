package com.jinchi.common.service;

import com.jinchi.common.constant.QualitySampleTypeEnum;
import com.jinchi.common.domain.RepoBaseSerialNumber;
import com.jinchi.common.domain.RepoStock;
import com.jinchi.common.domain.TechniqueBaseRawManufacturer;
import com.jinchi.common.mapper.RepoBaseSerialNumberMapper;
import com.jinchi.common.mapper.RepoStockMapper;
import com.jinchi.common.mapper.TechniqueBaseRawManufacturerMapper;
import com.jinchi.common.utils.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:RepoBaseSerialNumberServiceImp
 * @description: 仓库物料基本编号
 * @date:13:58 2018/11/29
 */
@Service
public class RepoBaseSerialNumberServiceImp implements RepoBaseSerialNumberService {
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    private RepoStockMapper repoStockMapper;
    @Autowired
    private TechniqueBaseRawManufacturerMapper techniqueBaseRawManufacturerMapper;

    /**
     * 新增一个基本编号
     *
     * @param repoBaseSerialNumber
     * @return
     * @Description 只要新增了一个编号, 库存就要新增一条库存
     */
    @Override
    @Transactional
    public RepoBaseSerialNumber add(RepoBaseSerialNumber repoBaseSerialNumber) {

        Integer materialClass = repoBaseSerialNumber.getMaterialClass();

        //设置编号
        repoBaseSerialNumber.setSerialNumber(NumberGenerator.serialNumberGenerator(materialClass));

        //保存这种基本物料
        repoBaseSerialNumberMapper.insert(repoBaseSerialNumber);


        //新增原材料,需要新增原材料厂商
        if (repoBaseSerialNumber.getMaterialClass().equals(QualitySampleTypeEnum.SAMPLE_RAWMATERIAL.get())) {

            String manufacturerName = repoBaseSerialNumber.getManufacturerName();

            TechniqueBaseRawManufacturer manufacturer = techniqueBaseRawManufacturerMapper.getByName(manufacturerName);

            if (null == manufacturer)
                techniqueBaseRawManufacturerMapper.insert(new TechniqueBaseRawManufacturer().setName(manufacturerName));
        }

        //新增了一种物料,就要新增这种物料的库存
        RepoStock repoStock = new RepoStock();
        repoStock.setMaterialType(materialClass);
        repoStock.setWeight(0);
        repoStock.setSerialNumberId(repoBaseSerialNumber.getId());
        repoStockMapper.addNewStock(repoStock);
        return repoBaseSerialNumber;
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        Assert.notNull(repoBaseSerialNumberMapper.findById(id), "删除失败,找不到这个物料");
        repoBaseSerialNumberMapper.deleteById(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(List<Integer> ids) {
        repoBaseSerialNumberMapper.deleteByIds(ids);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public RepoBaseSerialNumber findById(Integer id) {
        return repoBaseSerialNumberMapper.findById(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<RepoBaseSerialNumber> findAll(Integer materialClass) {
        return repoBaseSerialNumberMapper.findAll(materialClass);
    }

    /**
     * 多条件查询物基本物料
     *
     * @return
     */
    @Override
    public List<RepoBaseSerialNumber> findByFactors(RepoBaseSerialNumber repoBaseSerialNumber) {
        return repoBaseSerialNumberMapper.findByFactors(repoBaseSerialNumber);
    }

    /**
     * 查询所有原材料或者厂商
     *
     * @param type           物料类型
     * @param isManufacturer 是否为厂商
     * @return
     */
    @Override
    public List<Map<Object, Object>> uniqueValue(Integer type, Integer isManufacturer) {
        //0不是厂商
        //1是厂商
        List<RepoBaseSerialNumber> values = repoBaseSerialNumberMapper.findByFactors(new RepoBaseSerialNumber().setMaterialClass(type));
        if (null == values || values.isEmpty()) return null;

        List<Map<Object, Object>> lists = new ArrayList<>();

        if (isManufacturer.equals(1)) {
            for (int i = 0; i < values.size(); i++) {
                RepoBaseSerialNumber serialNumber = values.get(i);
                Map<Object, Object> value = new HashMap<>();
                value.put("id",serialNumber.getId());
                value.put("manufacturer",serialNumber.getManufacturerName());
                lists.add(value);
            }
        } else {
            for (int i = 0; i < values.size(); i++) {
                RepoBaseSerialNumber serialNumber = values.get(i);
                Map<Object, Object> value = new HashMap<>();
                value.put("id",serialNumber.getId());
                value.put("manufacturer",serialNumber.getMaterialName());
                lists.add(value);
            }

        }
        return lists;
    }

    @Override
    public RepoBaseSerialNumber addExtra(RepoBaseSerialNumber repoBaseSerialNumber) {

        Integer materialClass = repoBaseSerialNumber.getMaterialClass();

        //设置编号
        repoBaseSerialNumber.setSerialNumber(NumberGenerator.serialNumberGenerator(materialClass));

        //保存这种基本物料
        repoBaseSerialNumberMapper.insert(repoBaseSerialNumber);

        //新增了一种物料,就要新增这种物料的库存
        RepoStock repoStock = new RepoStock();
        repoStock.setMaterialType(materialClass);
        repoStock.setWeight(0);
        repoStock.setSerialNumberId(repoBaseSerialNumber.getId());
        repoStockMapper.addNewStock(repoStock);
        return repoBaseSerialNumber;
    }
}
