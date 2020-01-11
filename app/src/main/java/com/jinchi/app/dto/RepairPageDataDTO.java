package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-24 14:38
 * @description:
 **/
public class RepairPageDataDTO {
    private Integer wait;
    private Integer apply;
    private Integer confirm;

    private List<RepairPageData> list;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getWait() {
        return wait;
    }

    public void setWait(Integer wait) {
        this.wait = wait;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getApply() {
        return apply;
    }

    public void setApply(Integer apply) {
        this.apply = apply;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public List<RepairPageData> getList() {
        return list;
    }

    public void setList(List<RepairPageData> list) {
        this.list = list;
    }
}
