package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorPlcAddress;
import com.jinchi.common.dto.Page;

import java.util.List;

public interface PrecursorPlcAddressService {

    BasicInfoPrecursorPlcAddress add(BasicInfoPrecursorPlcAddress basicInfoPrecursorPlcAddress);

    void delete(Integer id);

    void deleteByIds(Integer[] ids);

    List getAll(String condition);

    Page getByPage(String condition,Integer page,Integer size);

    BasicInfoPrecursorPlcAddress update(BasicInfoPrecursorPlcAddress basicInfoPrecursorPlcAddress);

    BasicInfoPrecursorPlcAddress getById(Integer id);
}
