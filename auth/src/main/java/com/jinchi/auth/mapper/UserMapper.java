package com.jinchi.auth.mapper;


import com.jinchi.auth.domain.Menu;
import com.jinchi.auth.domain.Operation;
import com.jinchi.auth.domain.RoleMenuOperation;
import com.jinchi.auth.domain.User;
import com.jinchi.auth.dto.LoginInitialDTO;
import com.jinchi.auth.dto.MenuDTO;
import com.jinchi.auth.dto.SimpleUserDTO;
import com.jinchi.auth.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:11:07 2018/10/24
 */
//定义Mybatis的接口
@Component
@Mapper
public interface UserMapper {

    /**
     * 新增
     */
    void insert(@Param("user") User user);


    /**
     * 更新
     */
    void update(@Param("user") User user);

    /**
     * 根据id查询
     */
    UserDTO find(Integer id);

    /**
     * 根据id删除
     */
    void delete(Integer id);

    /**
     * 根据名称模糊查询
     */
    List<UserDTO> findByName(String name);

    /**
     * 查询所有
     */
    List<UserDTO> findAll();


    /**
     * 根据名称查询(权限使用)
     */
    User findByFullName(String username);

    /**
     * 根据名称查询-角色
     */
    User findRolesByUserName(String username);

    /**
     * 根据用户id查询所有权限
     */
    Set<String> findUserAuthsString(Integer id);

    Set<String> findUserRolesString(@Param("id") Integer id);

    /**
     * 查询所有id不等于传入数组的user
     */
    List<SimpleUserDTO> findAllIdNotIn(ArrayList<Integer> ids);

/**
 * 初始化登陆分割线---------------------------------------------------------------------------
 */
    /**
     * 用户登陆初始化返回数据
     */
    LoginInitialDTO getLoginInitial(@Param("userId") Integer userId);


    /**
     * 根据用户id查询其菜单ids
     */
    List<Menu> subMenusByUserId(@Param("userId") Integer userId);

    /**
     *   找出所有菜单(包括父菜单)
     */
    List<MenuDTO> findPrivateMenus(@Param("ids") List<Integer> ids);

    /**
     * 根据角色id和菜单id 查找权限
     * @param roleId 角色id
     * @param menuId 菜单id
     * @return
     */
    List<RoleMenuOperation> operationsByRoleIdAndMenuId(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    /**
     * 查询所有操作
     * @return
     */
    List<Operation> allOperations();

    User byCard(@Param("idCardNumber") String idCardNumber);
}
