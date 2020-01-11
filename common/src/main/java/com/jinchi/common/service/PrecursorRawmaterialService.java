package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoRawmaterial;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.RawmaterialsDTO;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-28 16:25
 * @description:
 **/

public interface PrecursorRawmaterialService {

    BasicInfoRawmaterial add(BasicInfoRawmaterial basicInfoRawmaterial);

    List getAll(String condition);

    void deleteById(Integer id);

    void deleteByIds(Integer[] ids);

    Page page(String condition, Integer page, Integer size);

    BasicInfoRawmaterial update(BasicInfoRawmaterial basicInfoRawmaterial);

    RawmaterialsDTO getById(Integer id);

    List getByTypeCode(Integer type);

    List getByDatatype(Integer flag);
}
