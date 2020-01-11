package com.jinchi.app.dto;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-14 16:43
 * @description:
 **/

public class DeviceRepairHelperDTO {
    Long repairId;
    List<Integer> partnerList;

    public Long getRepairId() {
        return repairId;
    }

    public void setRepairId(Long repairId) {
        this.repairId = repairId;
    }

    public List<Integer> getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(List<Integer> partnerList) {
        this.partnerList = partnerList;
    }
}
