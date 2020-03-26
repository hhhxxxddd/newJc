package com.jinchi.app.service;

import com.jinchi.app.dto.PowerCheckRecordDTO;
import com.jinchi.app.dto.QueryDTO;

import java.util.List;

public interface PowerCheckRecordService {

    PowerCheckRecordDTO add(PowerCheckRecordDTO dto);

    PowerCheckRecordDTO detail(Long id);

    List getTodayRecords();

    List page(QueryDTO dto);

}
