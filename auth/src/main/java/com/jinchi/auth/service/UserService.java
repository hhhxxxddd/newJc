package com.jinchi.auth.service;

import com.github.pagehelper.PageInfo;
import com.jinchi.auth.domain.MyUser;
import com.jinchi.auth.domain.User;
import com.jinchi.auth.dto.PasswordChangeDTO;
import com.jinchi.auth.dto.UserDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:14:15 2018/10/25
 */
//定义service的接口
public interface UserService {

    /**
     * 登录
     */
    User login(MyUser myUser);

    /**
     * 修改密码
     * @param passwordChangeDTO
     * @return
     */
    Map<Object,Object> passwordChange(PasswordChangeDTO passwordChangeDTO);

    /**
     * 新增
     */
    UserDTO save(UserDTO userDTO);

    String reset(Integer id);
    /**
     * 更新
     */
    User update(UserDTO userDTO);

    /**
     * 查询一名
     */
    UserDTO find(Integer id);

    /**
     * 根据全名查询
     */
    User findByFullName(String username);

    /**
     * 根据名称查询其角色
     */
    User findRolesByUsername(String username);

    /**
     * 查询用户所有权限
     */
    Set<String> findUserAuths(Integer id);

    /**
     * 查询所有用户
     */
    List<UserDTO> findAll();


    /**
     * 根据id删除
     */
    void delete(Integer id);

    /**
     *
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     */
    PageInfo findAllByPage(Integer page, Integer size, String orderField, String orderType);

    /**
     *
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @param name
     * @return
     */
    PageInfo findAllByPageByName(Integer page, Integer size, String orderField, String orderType, String name);
}
