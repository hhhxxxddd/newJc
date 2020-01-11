package com.jinchi.auth.service;

import com.jinchi.auth.domain.Menu;
import com.jinchi.auth.dto.MenuDTO;
import com.jinchi.auth.dto.PageBean;

import java.util.List;

/**
 * 说明: 菜单基本操作接口
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
public interface MenuService {

    /**
     * 添加新菜单
     *
     * @param menu 菜单对象
     * @return Menu
     */
    Menu add(Menu menu);

    /**
     * 更新一个菜单
     *
     * @param menu
     */
    Menu update(Menu menu);

    /**
     * 查找菜单
     *
     * @param id 菜单id
     * @return Menu
     */
    Menu byId(Integer id);

    /**
     * 根据类型查找菜单
     * @param type
     * @return
     */
    List<Menu> byType(Integer type);

    /**
     * 根据ids删除菜单
     *
     * @param ids 菜单ids
     */
    void deleteByIds(Integer[] ids);

    /**
     * 根据ID删除菜单
     *
     * @param id 菜单id
     */
    void deleteById(Integer id);

    /**
     * 根据菜单名称模糊查询所有菜单-分页
     *
     * @param menuName  菜单名
     * @return
     */
    PageBean byNameLikeByPage(String menuName, PageBean pageBean);

    PageBean byFatherNameLikeByPage(String fatherName,PageBean pageBean);

    /**
     * 递归查找所有菜单
     */
    List<MenuDTO> recursiveQuery();
}
