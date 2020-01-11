package com.jinchi.common.dto;

import com.jinchi.common.domain.BasicInfoDept;

import java.util.List;

public class FirstDeptAndSecondDeptDTO {
    BasicInfoDept firstDept;
    List<SecondDeptDTO> secondDeptDTOList;

    @Override
    public String toString() {
        return " FirstDeptAndSecondDeptDTO{" +
                "firstDept=" + firstDept +
                ",secondDeptDTOList=" + secondDeptDTOList +
                '}';
    }

    public BasicInfoDept getFirstDept() {
        return firstDept;
    }

    public List<SecondDeptDTO> getSecondDeptDTOList() {
        return secondDeptDTOList;
    }

    public void setFirstDept(BasicInfoDept firstDept) {
        this.firstDept = firstDept;
    }

    public void setSecondDeptDTOList(List<SecondDeptDTO> secondDeptDTOList) {
        this.secondDeptDTOList = secondDeptDTOList;
    }
}
