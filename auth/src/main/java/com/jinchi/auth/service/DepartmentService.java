package com.jinchi.auth.service;


import com.jinchi.auth.domain.Department;
import com.jinchi.auth.dto.PageBean;

import java.util.List;

/**
 * 部门Service
 */

public interface DepartmentService {
    void deleteById(Integer id); //根据id删除一个部门

    void batchDelete(List<Integer> ids); //根据ids删除多条数据

    Department byId(Integer id);//根据id查询部门

    Department add(Department department);//增加一个部门

    Department update(Department department);//编辑一个部门

    //根据部门名称模糊查询所有-分页
    PageBean byNameLikeByPage(String departmentName, PageBean pageBean);

    List<Department> findAll(); //查询所有部门
}