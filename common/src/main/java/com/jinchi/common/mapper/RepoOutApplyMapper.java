package com.jinchi.common.mapper;

import com.jinchi.common.domain.RepoOutApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:RepoOutApplyMapper
 * @description: 出库Mapper
 * @date:12:34 2018/12/2
 */
@Mapper
@Component
public interface RepoOutApplyMapper {

    /**
     * 新增出库信息
     */
    void insert(RepoOutApply repoOutApply);

    /**
     * 根据名称模糊和类型查询所有出库信息
     */
    List<RepoOutApply> findAllByNameLikeAndType(@Param("materialName") String materialName,
                                                @Param("materialType") Integer materialType);


    /**
     * 根据id查询
     */
    RepoOutApply findById(@Param("id") Integer id);

    /**
     * 根据id删除
     */
    void deleteById(@Param("id") Integer id);

    /**
     * 根据ids删除
     */
    Integer deleteByIds(List<Integer> ids);

    /**
     * 根据批号id查询
     */
    List<RepoOutApply> findAllByBatchNumberId(@Param("batchNumberId") Integer batchNumberId);

    /**
     * 根据serialNumberId查询-> 杜敏添加
     */
    RepoOutApply getBySerialNumberId(@Param("serialNumberId") Integer serialNumberId);

    /**
     * 根据批号id删除
     */
    void deleteByBatchNumberId(@Param("batchNumberId") Integer batchNumberId);

    /**
     * 根据批号ids删除
     */
    Integer deleteByBatchNumberIds(List<Integer> batchNumberIds);
}
