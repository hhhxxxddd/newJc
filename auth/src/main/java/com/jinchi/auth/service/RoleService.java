package com.jinchi.auth.service;

import com.jinchi.auth.domain.Role;
import com.jinchi.auth.dto.Authorities;
import com.jinchi.auth.dto.PageBean;
import com.jinchi.auth.dto.SimpleUserDTO;

import java.util.List;
import java.util.Map;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:21:07 2018/10/29
 */
public interface RoleService {

    /**
     * 新增一个角色
     */
    Role add(Role role);

    /**
     * 更新一个角色
     */
    Role update(Role role);


    /**
     * 查询所有角色
     */
    List<Role> findAll();

    /**
     * 名称模糊查询所有角色-分页  --modify
     */
    PageBean byNameLikeByPage(String roleName, PageBean pageBean);

    /**
     * 查询所有角色-分页 -- modify
     */
//    PageBean byPage(PageBean pageBean);


    /**
     * 删除一名角色
     */
    void deleteById(Integer id);

    /**
     * 批量删除角色
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 查询角色分配的用户
     */
    Map<String,List<SimpleUserDTO>> findAssignUsers(Integer id);

    /**
     * 将角色分配给用户
     */
    void assignUser(Integer roleId,List<Integer> inUserIds);

    /**
     * 查询一名角色的所有权限
     */
    Map<Object,Object> authoritiesOfRole(Integer id);

    /**
     * 查询所有权限
     */
    List<Authorities> allAuthorities();

    /**
     * 给角色增加一条权限
     */
    void addOneAuth(Integer roleId,Integer menuId,Integer operationId);

    /**
     * 给角色删除一条权限
     */
    void deleteOneAuth(Integer roleId,Integer menuId,Integer operationId);

}
