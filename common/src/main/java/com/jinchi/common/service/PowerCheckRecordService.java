package com.jinchi.common.service;

import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.PowerCheckRecordDTO;

import java.util.List;

public interface PowerCheckRecordService {

    PowerCheckRecordDTO add(PowerCheckRecordDTO dto);

    PowerCheckRecordDTO update(PowerCheckRecordDTO dto);

    void delete(Long id);

    void deletes(Long[] ids);

    PowerCheckRecordDTO detail(Long id);

    List getAll(String condition);

    Page page(String condition, Integer page, Integer size);

    List getOperator();
}
