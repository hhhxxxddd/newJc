package com.jinchi.auth.mapper;

import com.jinchi.auth.domain.Role;
import com.jinchi.auth.domain.UserRole;
import com.jinchi.auth.dto.Authorities;
import com.jinchi.auth.dto.PageBean;
import com.jinchi.auth.dto.SimpleUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @author：XudongHu
 * @description: 不解释
 * @date:21:01 2018/10/29
 */
@Component
@Mapper
public interface RoleMapper {
    /**
     * 新增
     */
    void insert(Role role);

    /**
     * 更新
     */
    void update(Role role);

    /**
     * 删除
     */
    void deleteById(@Param("id") Integer id);

    /**
     * 批量删除
     */
    void deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 查询  - modify
     */
    Role byId(@Param("id") Integer id);

    /**
     * 查询所有  modify  --不分页
     */
    List<Role> findAll();

    /**
     * 根据角色名称模糊查询所有 分页 - modify
     */
    List<Role> byNameLike(@Param("roleName") String roleName, @Param("pageBean") PageBean pageBean);

    /**
     * 查询角色的所有权限
     */
    //authoritiesOfRole
    List<Authorities> authoritiesOfRole(@Param("id") Integer id);

    /**
     * 查询所有权限
     */
    //authorities
    List<Authorities> authorities();

    /**
     * 通过角色找已分配的用户
     */
    List<SimpleUserDTO> findUsersByRole(@Param("roleId") Integer roleId);

    /**
     * 给角色分配用户第一阶段
     * (根据角色id和用户id添加)
     */
    void assignUser2Role(@Param("userRoles") List<UserRole> userRoles);

    /**
     * 给角色分配用户第二阶段
     * (根据不属于角色id和用户id的组合删除)
     */
    void cancelAssign(@Param("roleId") Integer roleId, @Param("userIds") List<Integer> userIds);

    /**
     * 给角色增加一条权限
     */
    void addOneOperation(@Param("roleId") Integer roleId,@Param("menuId") Integer menuId,@Param("operationId") Integer operationId);

    /**
     * 给角色减去一条权限
     */
    void cancelOneOperation(@Param("roleId") Integer roleId,@Param("menuId") Integer menuId,@Param("operationId") Integer operationId);

    /**
     * 计算角色下所有用户的个数
     */
    Integer countSum(@Param("roleName") String roleName);
}
