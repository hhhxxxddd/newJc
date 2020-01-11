package com.jinchi.common.service;

import com.jinchi.common.dto.LineWeightDTO;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface AnodeMaterialTypesService {

    LineWeightDTO add(LineWeightDTO dto);

    LineWeightDTO update(LineWeightDTO dto);

    void deleteById(Integer id);

    void deleteByIds(Integer[] ids);

    List getAll(String condition);

    Page page(String condition, Integer page, Integer size);
}
