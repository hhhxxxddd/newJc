package com.jinchi.common.service;

import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.PowerCheckModelDTO;

import java.util.List;

public interface PowerCheckModelService {

    PowerCheckModelDTO add(PowerCheckModelDTO dto);

    void delete(Long id);

    void deleteByIds(Long[] ids);

    PowerCheckModelDTO detail(Long id);

    PowerCheckModelDTO update(PowerCheckModelDTO dto);

    List getAll(String condition);

    Page byPage(String condition, Integer page, Integer size);

    List getModelBySiteCode(Long siteCode);
}
