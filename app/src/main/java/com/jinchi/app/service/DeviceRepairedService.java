package com.jinchi.app.service;

import com.jinchi.app.dto.DeviceRepairDetail;
import com.jinchi.app.dto.DeviceRepairedApplyDTO;

public interface DeviceRepairedService {

    DeviceRepairDetail detail(Long id);

    void commit(DeviceRepairedApplyDTO deviceRepairedApplyDTO);

//    List<RepairPageData> page(RepairPostDTO repairPostDTO);

//    List getPartnerById(Integer id);
//
//    void cooperation(DeviceRepairHelperDTO repairHelperDTO);
}
