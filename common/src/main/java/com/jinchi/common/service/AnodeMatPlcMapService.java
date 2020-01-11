package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoAnodeMaterialPlcMap;
import com.jinchi.common.dto.AnodeMatPlcMapDTO;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface AnodeMatPlcMapService {

    BasicInfoAnodeMaterialPlcMap add(BasicInfoAnodeMaterialPlcMap map);

    BasicInfoAnodeMaterialPlcMap update(BasicInfoAnodeMaterialPlcMap map);

    void deleteById(Integer id);

    void deleteByIds(Integer[] ids);

    List getAll(String condition);

    Page page(String condition, Integer page, Integer size);

    AnodeMatPlcMapDTO getById(Integer id);

    BasicInfoAnodeMaterialPlcMap getOne(Integer id);
}
