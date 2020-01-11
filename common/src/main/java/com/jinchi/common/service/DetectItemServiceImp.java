package com.jinchi.common.service;

import com.jinchi.common.domain.QualityBaseDetectItem;
import com.jinchi.common.domain.QualityBaseDetectItemExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.QualityBaseDetectItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetectItemServiceImp implements DetectItemService {
    @Autowired
    QualityBaseDetectItemMapper itemMapper;

    @Override
    public QualityBaseDetectItem add(QualityBaseDetectItem item) {
        itemMapper.insertSelective(item);
        return item;
    }

    @Override
    public void delete(Long id) {
        itemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for(Long id:ids)
            delete(id);
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        return new Page(getAll(condition),page,size);
    }

    @Override
    public QualityBaseDetectItem detail(Long id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    @Override
    public QualityBaseDetectItem update(QualityBaseDetectItem item) {
        itemMapper.updateByPrimaryKeySelective(item);
        return item;
    }

    @Override
    public List getAll(String condition) {
        QualityBaseDetectItemExample example = new QualityBaseDetectItemExample();
        example.createCriteria().andNameLike(condition+"%");
        return itemMapper.selectByExample(example);
    }
}
