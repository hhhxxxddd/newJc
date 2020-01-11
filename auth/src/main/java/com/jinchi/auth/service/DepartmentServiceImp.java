package com.jinchi.auth.service;

import com.jinchi.auth.domain.Department;
import com.jinchi.auth.dto.PageBean;
import com.jinchi.auth.mapper.DepartmentMapper;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门实现类
 */
@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 删除部门
     */
    @Override
    public void deleteById(Integer id) {
        Assert.notNull(departmentMapper.byId(id), String.format("id为%d的部门不存在", id));
        departmentMapper.deleteById(id);
    }

    /**
     * 根据ids删除多个部门
     */
    @Override
    public void batchDelete(List<Integer> id) {
        departmentMapper.batchDelete(id);
    }

    /**
     * 详情
     * @param id 主键
     * @return
     */
    @Override
    public Department byId(Integer id) {
        return departmentMapper.byId(id);
    }

    /**
     * 新增部门
     */
    @Override
    public Department add(Department department) {
        departmentMapper.insert(department);
        return department;
    }

    /**
     * 更新部门
     */
    @Override
    public Department update(Department department) {
        Department oldValue = departmentMapper.byId(department.getId());
        Assert.notNull(oldValue,String.format("更新失败,id为%d的部门不存在",department.getId()));
        departmentMapper.update(department);
        return department;
    }

    /**
     * 根据部门名称模糊查询所有部门-分页
     */
    @Override
    public PageBean byNameLikeByPage(String departmentName, PageBean pageBean) {
        pageBean.setTotal(departmentMapper.countSum(departmentName));
        pageBean.setList(departmentMapper.byNameLike(departmentName,pageBean));
        return pageBean;
    }

    /**
     * 查询所有
     */
    @Override
    public List<Department> findAll() {
        return departmentMapper.byNameLike("",null);
    }
}