package com.jinchi.common.service;

import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.RawmaterialLineWeightDTO;

import java.util.List;

public interface PrecursorRawmaterialLineWeightService {

    RawmaterialLineWeightDTO add(RawmaterialLineWeightDTO dto);

    RawmaterialLineWeightDTO getRecordById(Integer id);

    void delete(Integer id);

    void deleteByIds(Integer[] ids);

    List getAll(String condition);

    Page page(String condition, Integer page, Integer size);

    RawmaterialLineWeightDTO update(RawmaterialLineWeightDTO rawmaterialLineWeightDTO);
}
