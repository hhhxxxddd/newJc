package com.jinchi.common.service;

import com.jinchi.common.domain.RepoBaseEndPosition;

import java.util.List;

/**
 * @author XudongHu
 * @className RepoBaseEndPositionService
 * @apiNote 基本出库点
 * @modifer
 * @since 2019/10/28 19:54
 */
public interface RepoBaseEndPositionService {
    List<RepoBaseEndPosition> findAllByExample(RepoBaseEndPosition repoBaseEndPosition);

    RepoBaseEndPosition getById(Integer id);
}
