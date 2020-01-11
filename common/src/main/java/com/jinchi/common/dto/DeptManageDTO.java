package com.jinchi.common.dto;

import com.jinchi.common.domain.BasicInfoDept;

import java.util.List;

public class DeptManageDTO {
    BasicInfoDept parent;
    List<BasicInfoDept> son;

    public BasicInfoDept getParent() {
        return parent;
    }

    public void setParent(BasicInfoDept parent) {
        this.parent = parent;
    }

    public List<BasicInfoDept> getSon() {
        return son;
    }

    public void setSon(List<BasicInfoDept> son) {
        this.son = son;
    }
}
