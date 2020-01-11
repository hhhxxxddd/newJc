package com.jinchi.common.service;

import com.jinchi.common.dto.DeviceSpotCheckRecordHeadDTO;
import com.jinchi.common.dto.Page;

public interface DeviceSpotCheckQueryService {

    Page deviceDetailPage(Long id,Integer page,Integer size);

    DeviceSpotCheckRecordHeadDTO deviceRecordDetail(Long id);
}
