package com.jinchi.common.mapper;

import com.jinchi.common.domain.RepoBaseEndPosition;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author XudongHu
 * @className RepoBaseEndPositionMapper
 * @apiNote 出库点位置
 * @modifer
 * @since 2019/10/28 20:05
 */
@Mapper
@Component
public interface RepoBaseEndPositionMapper {
    List<RepoBaseEndPosition> findAllByExample(RepoBaseEndPosition repoBaseEndPosition);

    RepoBaseEndPosition findById(@Param("id") Integer id);
}
