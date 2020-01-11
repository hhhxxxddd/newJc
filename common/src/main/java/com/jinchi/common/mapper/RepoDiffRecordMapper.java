package com.jinchi.common.mapper;

import com.jinchi.common.domain.RepoDiffRecord;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.repository.RepoStockDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by WangZhihao on 2018/11/30.
 */
@Mapper
@Component
public interface RepoDiffRecordMapper {

    RepoDiffRecord getBySerialNumber(Integer serialNumberId);

    List<RepoDiffRecord> getAll();

    void update(RepoStockDTO repoStockDTO);

    void insert(RepoDiffRecord repoDiffRecord);

    List<RepoDiffRecord> getAllByFactors(@Param("materialClass") Integer materialClass,
                                         @Param("materialName") String materialName,
                                         @Param("pageBean") PageBean pageBean);

    /**
     * 根据条件查询总数
     *
     * @return
     */
    Integer countSum(@Param("materialClass") Integer materialClass,
                     @Param("materialName") String materialName);
}
