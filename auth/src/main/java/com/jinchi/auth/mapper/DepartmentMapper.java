package com.jinchi.auth.mapper;


import com.jinchi.auth.domain.Department;
import com.jinchi.auth.dto.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 部门 mapper
 */
@Component
@Mapper
public interface DepartmentMapper {
    void deleteById(@Param("id") Integer id); //根据id删除一个部门

    Department byId(@Param("id") Integer id);//根据id查询部门

    void insert(Department department);//增加一个部门

    void update(Department department);//编辑一个部门

//    List<Department> byNameLike(@Param("departmentName") String departmentName); //根据部门名称模糊查询所有

    List<Department> byNameLike(@Param("departmentName") String departmentName,@Param("pageBean") PageBean pageBean);

    Integer countSum(@Param("departmentName") String departmentName);

    void batchDelete(@Param("ids") List<Integer> ids); //根据ids删除多条数据

}