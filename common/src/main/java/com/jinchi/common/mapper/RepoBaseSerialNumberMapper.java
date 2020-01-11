package com.jinchi.common.mapper;

import com.jinchi.common.domain.RepoBaseSerialNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:RepoBaseSerialNumberMapper
 * @description:
 * @date:11:05 2018/11/28
 */
@Mapper
@Component
public interface RepoBaseSerialNumberMapper {
    /**
     * 新增物料
     */
    void insert(RepoBaseSerialNumber repoBaseSerialNumber);

    /**
     * 根据主键删除物料
     */
    void deleteById(@Param("id") Integer id);

    /**
     * 根据主键批量删除物料
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据主键查询物料
     *
     * @param id
     * @return 物料
     */
    RepoBaseSerialNumber findById(@Param("id") Integer id);

    List<RepoBaseSerialNumber> byIds(List<Integer> ids);

    /**
     * 查询所有
     */
    List<RepoBaseSerialNumber> findAll(@Param(value = "materialClass") Integer materialClass);

    /**
     * 多条件查询
     *
     * @return 物料集合
     */
    List<RepoBaseSerialNumber> findByFactors(RepoBaseSerialNumber repoBaseSerialNumber);

    /**
     * 根据 编号全称查询
     * @param serialNumber 编号全称
     * @return
     */
    RepoBaseSerialNumber bySerialNumber(@Param("serialNumber") String serialNumber);

    /**
     * 根据厂商名和材料名查询 编号
     * @param rawName 材料名
     * @param manufacturerName 厂商名
     * @return
     */
    RepoBaseSerialNumber byStandard(@Param("rawName") String rawName,@Param("manufacturerName") String manufacturerName);
}
