package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorMaterialPlcMap;
import com.jinchi.common.dto.MatPlcDTO;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface PrecursorMatPlcMapService {

    BasicInfoPrecursorMaterialPlcMap add(BasicInfoPrecursorMaterialPlcMap basicInfoPrecursorMaterialPlcMap);

    List getAll(String condition);

    Page getAllByPage(String condition, Integer page,Integer size);

    void delete(Integer id);

    void deleteByIds(Integer[] ids);

    BasicInfoPrecursorMaterialPlcMap update(BasicInfoPrecursorMaterialPlcMap basicInfoPrecursorMaterialPlcMap);

    MatPlcDTO getById(Integer id);
}
