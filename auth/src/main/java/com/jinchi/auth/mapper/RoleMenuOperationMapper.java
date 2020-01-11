package com.jinchi.auth.mapper;

import com.jinchi.auth.domain.RoleMenuOperation;

/**
 * 说明:角色菜单操作权限
 * <br>
 *
 * @author ZSCDumin
 * <br>
 * 邮箱: 2712220318@qq.com
 * <br>
 * 日期: 2018/11/12
 * <br>
 * 版本: 1.0
 */
public interface RoleMenuOperationMapper {

    /**
     * 新增记录
     *
     * @param roleMenuOperation
     */
    void insert(RoleMenuOperation roleMenuOperation);

    /**
     * 更新记录
     *
     * @param roleMenuOperation
     */
    void update(RoleMenuOperation roleMenuOperation);

    /**
     * 通过ID删除记录
     *
     * @param id ID
     */
    void deleteById(Integer id);

    /**
     * 通过ID查找记录
     *
     * @param id ID
     */
    void findById(Integer id);
}
