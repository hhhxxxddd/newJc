package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoDept;
import com.jinchi.common.dto.DeptManageDTO;

import java.util.List;

public interface DeptManageService {
    public BasicInfoDept addOne(BasicInfoDept basicInfoDept);

    public BasicInfoDept update(BasicInfoDept basicInfoDept);

    public void deleteById(Integer id);

    public BasicInfoDept getDeptById(Integer id);

    public List<DeptManageDTO> getCata();

    BasicInfoDept getDetailById(Integer id);
}
