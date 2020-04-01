package com.jinchi.app.dto;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @time: 2020/3/27 20:55
 * @description:
 */
public class PowerCheckDTO {

    CheckHeadDTO head;

    List<CheckDetailDTO> details;

    String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public CheckHeadDTO getHead() {
        return head;
    }

    public void setHead(CheckHeadDTO head) {
        this.head = head;
    }

    public List<CheckDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<CheckDetailDTO> details) {
        this.details = details;
    }
}
