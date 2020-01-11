package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoMaterialType;
import com.jinchi.common.dto.Page;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-28 16:25
 * @description:
 **/

public interface PrecursorMaterialTypeService {

    List getAll();

    BasicInfoMaterialType add(BasicInfoMaterialType basicInfoMaterialType);

    BasicInfoMaterialType getByCode(Integer id);

    void deleteById(Integer id);

    void deleteByIds(Integer[] ids);

    BasicInfoMaterialType update(BasicInfoMaterialType basicInfoMaterialType);

    Page page(Integer page, Integer size);

    List getRecordsByTypes(Byte dataType);
}
