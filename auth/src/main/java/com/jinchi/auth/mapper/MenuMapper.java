package com.jinchi.auth.mapper;

import com.jinchi.auth.domain.Menu;
import com.jinchi.auth.dto.MenuDTO;
import com.jinchi.auth.dto.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 说明:
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
@Component
@Mapper
public interface MenuMapper {

    /**
     * 添加一个新菜单
     *
     * @param menu 菜单
     */
    void insert(Menu menu);

    /**
     * 更新一个菜单
     *
     * @param menu
     */
    void update(Menu menu);

    /**
     * 根据ID删除菜单
     *
     * @param id 菜单ID
     */
    void deleteById(Integer id);

    /**
     * 根据ID查找菜单
     *
     * @param id 菜单ID
     * @return Menu
     */
    Menu byId(Integer id);

    /**
     * 查询所有的菜单
     *
     * @return List<Menu>
     */
    List<Menu> findAll();

    /**
     * 查询所有的菜单-包含子菜单
     */
    List<MenuDTO> recursiveAll();

    /**
     * 根据子菜单id递归查找此链上的所有菜单
     */
    MenuDTO recursiveQueryBySubMenuId(Integer subMenuId);
    /**
     * 通过菜单类型查找
     *
     * @param type 菜单类型
     * @return
     */
    List<Menu> byMenuType(@Param("type") Integer type);



    /**
     * 判断一个菜单是否有子菜单
     *
     * @param id id
     * @return Integer
     */
    Integer hasChild(Integer id);

    /**
     * 根据菜单名称模糊查询 -分页
     * @param menuName 菜单名称
     * @param pageBean 分页类
     * @return
     */
    List<Menu> byNameLikeByPage(@Param("menuName") String menuName, @Param("pageBean") PageBean pageBean);

    /**
     * 根据父菜单名称模糊查询到该父菜单-分页
     * @param menuName 父菜单名称
     * @param pageBean  分页类
     * @return
     */
    List<Menu> byFatherNameLikeByPage(@Param("menuName") String menuName, @Param("pageBean") PageBean pageBean);

    /**
     * 计算总数
     * @param menuName 名称模糊
     * @return
     */
    Integer countSum(@Param("menuName") String menuName);

    /**
     * 计算父菜单总数
     * @param menuName 名称模糊
     * @return
     */
    Integer countSumFather(@Param("menuName") String menuName);
}
