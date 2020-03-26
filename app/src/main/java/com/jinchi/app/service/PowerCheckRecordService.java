package com.jinchi.app.service;

import com.jinchi.app.dto.PowerCheckRecordDTO;

import java.util.List;

public interface PowerCheckRecordService {

    PowerCheckRecordDTO add(PowerCheckRecordDTO dto);

    PowerCheckRecordDTO detail(Long id);

    List getTodayRecords();

    List page(Integer page, Integer size);

}
