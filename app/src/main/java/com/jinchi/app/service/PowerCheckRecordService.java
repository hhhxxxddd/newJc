package com.jinchi.app.service;

import com.jinchi.app.dto.PowerCheckDTO;
import com.jinchi.app.dto.PowerCheckRecordDTO;
import com.jinchi.app.dto.QueryDTO;

import java.util.List;

public interface PowerCheckRecordService {

    PowerCheckDTO add(PowerCheckDTO dto);

    PowerCheckRecordDTO detail(Long id);

    List getTodayRecords();

    List page(QueryDTO dto);

    PowerCheckRecordDTO update(PowerCheckRecordDTO dto);
}
