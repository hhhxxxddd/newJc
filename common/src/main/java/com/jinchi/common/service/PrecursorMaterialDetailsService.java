package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorMaterialDetails;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.PrecursorMaterialDetailsDTO;

import java.util.List;

public interface PrecursorMaterialDetailsService {

    BasicInfoPrecursorMaterialDetails add(BasicInfoPrecursorMaterialDetails basicInfoPrecursorMaterialDetails);

    void delete(Integer id);

    void deleteByIds(Integer[] ids);

    BasicInfoPrecursorMaterialDetails update(BasicInfoPrecursorMaterialDetails basicInfoPrecursorMaterialDetailse);

    Page page(String condition,Integer page,Integer size);

    List getAll(String condition);

    Page getByType(Byte type, Integer page, Integer size, String condition);

    PrecursorMaterialDetailsDTO getOne(Integer id);

    List<BasicInfoPrecursorMaterialDetails> getDataByProcessAndType(Integer processCode, Byte types);

    List getMaterialByProcessType(Integer processCode);

    List getHCMaterial();
}
