package com.jinchi.app.service;


import com.jinchi.app.dto.BasicInfoDeptDto;

import java.util.List;

public interface UserDeviceService {

    List<BasicInfoDeptDto> getDeptByAuthId(int id);

}
