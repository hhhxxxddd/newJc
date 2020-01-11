package com.jinchi.common.mapper;

import com.jinchi.common.domain.RepoInRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：XudongHu
 * @className:RepoInRecordMapper
 * @description:
 * @date:14:52 2018/11/29
 */
@Mapper
@Component
public interface RepoInRecordMapper {

    /**
     * 根据 serialNumberId 查询-> 杜敏添加
     *
     * @param serialNumberId 材料编号
     * @return 入库实体
     */
    RepoInRecord bySerialNumberId(Integer serialNumberId);

    /**
     * 新增入库记录
     */
    void insert(RepoInRecord repoInRecord);

    /**
     * 根据名称模糊和类型查询物料
     */
    List<RepoInRecord> byNameAndType(@Param("materialName") String materialName, @Param("materialType") Integer materialType);

    Integer countSum(@Param("materialName") String materialName, @Param("materialType") Integer materialType);
}
