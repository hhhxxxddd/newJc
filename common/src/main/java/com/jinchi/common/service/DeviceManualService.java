package com.jinchi.common.service;

import com.jinchi.common.domain.DeviceMainManual;

import java.util.List;

public interface DeviceManualService {

    DeviceMainManual addOne(DeviceMainManual deviceMainManual);

    void deleteById(Long code);

    DeviceMainManual getByCode(Long code);

    List<DeviceMainManual> getByMainCode(Long mainCode);

    void update(Long code,Long mainCode);
}
