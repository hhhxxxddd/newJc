package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorMaterialDetails;
import com.jinchi.common.domain.BasicInfoPrecursorMaterialLineWeight;
import com.jinchi.common.dto.LineWeightDTO;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface PrecursorMaterialService {

    BasicInfoPrecursorMaterialDetails getById(Integer id);

    LineWeightDTO getRecordById(Integer id);

//    BasicInfoPrecursorMaterialLineWeight add(BasicInfoPrecursorMaterialLineWeight basicInfoPrecursorMaterialLineWeight);

    LineWeightDTO add(LineWeightDTO lineWeightDTO);

    void delete(Integer id);

    void deleteByIds(Integer[] ids);

    List getAll(String condition);

    Page page(String condition,Integer page,Integer size);

//    BasicInfoPrecursorMaterialLineWeight update(BasicInfoPrecursorMaterialLineWeight basicInfoPrecursorMaterialLineWeight);

    LineWeightDTO update(LineWeightDTO lineWeightDTO);
}
