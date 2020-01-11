package com.jinchi.app.service;

import com.jinchi.app.domain.DeviceDocumentMain;
import com.jinchi.app.domain.DeviceDocumentMainExample;
import com.jinchi.app.mapper.DeviceDocumentMainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceDocumentMainServiceImp implements DeviceDocumentMainService {
    @Autowired
    DeviceDocumentMainMapper deviceDocumentMainMapper;

    @Override
    public DeviceDocumentMain getById(Long id) {
        DeviceDocumentMainExample example = new DeviceDocumentMainExample();
        example.createCriteria().andCodeEqualTo(id);
        DeviceDocumentMain deviceDocumentMain = deviceDocumentMainMapper.selectByExample(example).get(0);
        return deviceDocumentMain;
    }
}
