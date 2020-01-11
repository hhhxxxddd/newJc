package com.jinchi.common.service;


import com.jinchi.common.domain.BasicInfoDept;

import java.util.List;

public interface UserDeviceService {

    List<BasicInfoDept> getDeptByAuthId(int id);

}
