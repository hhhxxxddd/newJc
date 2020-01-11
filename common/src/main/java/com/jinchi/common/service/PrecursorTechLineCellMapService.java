package com.jinchi.common.service;

import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.TechLineCellMapDTO;

import java.util.List;

public interface PrecursorTechLineCellMapService {

    TechLineCellMapDTO add(TechLineCellMapDTO techLineCellMapDTO);

    void deleteById(Integer lineCode);

    void deleteByIds(Integer[] lineCodes);

    TechLineCellMapDTO update(TechLineCellMapDTO techLineCellMapDTO);

    TechLineCellMapDTO getByLineCode(Integer lineCode);

    Page page(String condition,Integer page,Integer size);

    List getAll(String condition);

    List getByIds(Integer[] lineCodes);
}
