package com.jinchi.common.service;

import com.jinchi.common.domain.RepoBaseEndPosition;
import com.jinchi.common.mapper.RepoBaseEndPositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XudongHu
 * @className RepoBaseEndPositionServiceImp
 * @apiNote TODO
 * @modifer
 * @since 2019/10/28 20:04
 */
@Service
public class RepoBaseEndPositionServiceImp implements RepoBaseEndPositionService{
    @Autowired
    private RepoBaseEndPositionMapper repoBaseEndPositionMapper;

    @Override
    public List<RepoBaseEndPosition> findAllByExample(RepoBaseEndPosition repoBaseEndPosition) {
        return repoBaseEndPositionMapper.findAllByExample(repoBaseEndPosition);
    }

    @Override
    public RepoBaseEndPosition getById(Integer id) {
        return repoBaseEndPositionMapper.findById(id);
    }
}
