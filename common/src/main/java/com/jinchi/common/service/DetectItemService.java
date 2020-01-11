package com.jinchi.common.service;

import com.jinchi.common.domain.QualityBaseDetectItem;
import com.jinchi.common.dto.Page;
import io.swagger.models.auth.In;

import java.util.List;

public interface DetectItemService {

    QualityBaseDetectItem add(QualityBaseDetectItem item);

    void delete(Long id);

    void deleteByIds(Long[] ids);

    Page page(String condition, Integer page, Integer size);

    QualityBaseDetectItem detail(Long id);

    QualityBaseDetectItem update(QualityBaseDetectItem item);

    List getAll(String condition);
}
