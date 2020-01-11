package com.jinchi.auth.service;

import com.jinchi.auth.domain.Role;
import com.jinchi.auth.domain.UserRole;
import com.jinchi.auth.dto.Authorities;
import com.jinchi.auth.dto.PageBean;
import com.jinchi.auth.dto.SimpleUserDTO;
import com.jinchi.auth.exceptions.EnumExceptions;
import com.jinchi.auth.exceptions.JcExceptions;
import com.jinchi.auth.mapper.MenuMapper;
import com.jinchi.auth.mapper.OperationMapper;
import com.jinchi.auth.mapper.RoleMapper;
import com.jinchi.auth.mapper.UserMapper;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:21:09 2018/10/29
 */
@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private OperationMapper operationMapper;

    /**
     * 新增一名角色
     */
    @Override
    public Role add(Role role) {
        roleMapper.insert(role);
        return role;
    }

    /**
     * 更新
     */
    @Override
    public Role update(Role role) {
        Role oldValue = roleMapper.byId(role.getId());
        Assert.notNull(oldValue,"更新失败,角色不存在");
        roleMapper.update(role);
        return role;
    }

    /**
     * 根据id查询
     */
    public Role findById(Integer id) {
        return roleMapper.byId(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    /**
     * 根据角色名称模糊查询所有-分页
     */
    @Override
    public PageBean byNameLikeByPage(String roleName, PageBean pageBean) {

        System.out.println(roleName);
        pageBean.setTotal(roleMapper.countSum(roleName));
        List<Role> roles = roleMapper.byNameLike(roleName, pageBean);
        roles.forEach(e-> System.out.println(e));
        pageBean.setList(roles);
        return pageBean;

    }


    /**
     * 根据id删除
     */
    @Override
    public void deleteById(Integer id) {
        Assert.notNull(roleMapper.byId(id),String.format("删除失败id为%d的角色不存在",id));
        roleMapper.deleteById(id);
    }

    /**
     * 批量删除
     */

    @Override
    public void deleteByIds(List<Integer> ids) {
        roleMapper.deleteByIds(ids);
    }

    /**
     * 找到角色分配的用户
     * 0为未分配,1为已分配
     */
    @Override
    public Map<String, List<SimpleUserDTO>> findAssignUsers(Integer id) {
        List<SimpleUserDTO> simpleUserMixes1 = roleMapper.findUsersByRole(id);

        ArrayList<Integer> ids = new ArrayList<>();

        if (simpleUserMixes1 == null || simpleUserMixes1.size() == 0) {
            ids.add(-1);
        } else {
            for (SimpleUserDTO simpleUserMix : simpleUserMixes1) {
                ids.add(simpleUserMix.getId());
            }
        }

        List<SimpleUserDTO> simpleUserMixes0 = userMapper.findAllIdNotIn(ids);

        Map<String, List<SimpleUserDTO>> resultMap = new HashMap<>();
        resultMap.put("notAssigned", simpleUserMixes0);
        resultMap.put("assigned", simpleUserMixes1);

        return resultMap;
    }


    /**
     * 给角色分配用户
     */
    @Transactional
    @Override
    public void assignUser(Integer roleId, List<Integer> inUserIds) {

        //验证角色是否存在
        if (roleMapper.byId(roleId) == null) {
            throw new JcExceptions(EnumExceptions.ASSIGN_FAILED_ROLE_NOT_EXISTS);
        }
        List<UserRole> userRoleList = new ArrayList<>();

        //首先批量删除
        //所有不属于传入roleId+userId组合的
        //再根据情况新增
        if (inUserIds == null || inUserIds.size() == 0) {
            List<Integer> cancel = new ArrayList<>();
            cancel.add(-1);
            roleMapper.cancelAssign(roleId, cancel);
        } else {
            //验证用户是否存在
            for (Integer inUserId : inUserIds) {
                if (userMapper.find(inUserId) == null) {
                    throw new JcExceptions(EnumExceptions.ASSIGN_FAILED_USER_NOT_EXISTS);
                }
                userRoleList.add(new UserRole(inUserId, roleId));
            }
            roleMapper.cancelAssign(roleId, inUserIds);
            roleMapper.assignUser2Role(userRoleList);
        }

    }

    /**
     * 查询角色所有权限
     */
    @Override
    public Map<Object,Object> authoritiesOfRole(Integer id) {
        Role role = roleMapper.byId(id);
        Assert.notNull("id:"+id+"该角色不存在");
        roleMapper.authoritiesOfRole(id);
        Map<Object,Object> map = new HashMap<>();
        map.put("id",role.getId());
        map.put("roleName",role.getRoleName());
        map.put("duty",role.getDescription());
        map.put("authorities",roleMapper.authoritiesOfRole(id));
        return map;
    }

    /**
     * 查询所有权限
     */
    @Override
    public List<Authorities> allAuthorities() {
        return roleMapper.authorities();
    }

    /**
     * 增加一项权限
     */
    @Override
    public void addOneAuth(Integer roleId, Integer menuId, Integer operationId) {
        //验证这三个外键是否存在
        if (roleMapper.byId(roleId) == null || menuMapper.byId(menuId) == null
                || operationMapper.findById(operationId) == null) {
            throw new JcExceptions(EnumExceptions.DELETE_FAILED_REF_KEY_NOT_EXISTS);
        }
        roleMapper.addOneOperation(roleId, menuId, operationId);
    }

    /**
     * 删除一项权限
     */
    @Override
    public void deleteOneAuth(Integer roleId, Integer menuId, Integer operationId) {
        roleMapper.cancelOneOperation(roleId, menuId, operationId);
    }

}
