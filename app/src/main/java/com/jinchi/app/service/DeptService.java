package com.jinchi.app.service;

import com.jinchi.app.domain.AuthDepartment;
import com.jinchi.app.domain.BasicInfoDept;

import java.util.List;

public interface DeptService {
    List<AuthDepartment> getAll();

    BasicInfoDept getById(Integer id);
}
