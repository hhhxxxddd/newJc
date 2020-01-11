package com.jinchi.common.service;

import com.jinchi.common.domain.RepoBaseSerialNumber;

import java.util.List;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:RepoBaseSerialNumberService
 * @description:
 * @date:13:55 2018/11/29
 */
public interface RepoBaseSerialNumberService {
    /**
     * 新增物料
     */
    RepoBaseSerialNumber add(RepoBaseSerialNumber repoBaseSerialNumber);

    RepoBaseSerialNumber addExtra(RepoBaseSerialNumber repoBaseSerialNumber);

    /**
     * 根据id删除物料
     */
    void deleteById(Integer id);

    /**
     * 根据ids删除物料
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询物料
     */
    RepoBaseSerialNumber findById(Integer id);

    /**
     * 查询所有
     */
    List<RepoBaseSerialNumber> findAll(Integer materialClass);

    /**
     * 多条件查询
     */
    List<RepoBaseSerialNumber> findByFactors(RepoBaseSerialNumber repoBaseSerialNumber);

    /**
     * 查询所有
     * 物料
     * 厂商
     * 去重
     */
    List<Map<Object,Object>> uniqueValue(Integer type, Integer isManufacturer);
}
