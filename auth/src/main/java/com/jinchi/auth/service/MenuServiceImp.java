package com.jinchi.auth.service;

import com.jinchi.auth.domain.Menu;
import com.jinchi.auth.dto.MenuDTO;
import com.jinchi.auth.dto.PageBean;
import com.jinchi.auth.mapper.MenuMapper;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明:菜单基本操作接口实现类 <br>
 *
 * @author huxudong
 */
@Service
public class MenuServiceImp implements MenuService {
    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImp.class);
    private static final String defaultPrefix = "default";
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 新增菜单
     *
     * @param menu 菜单对象
     * @return
     */
    @Override
    public Menu add(Menu menu) {
        //需要判断是否存在父菜单
        Menu parent = new Menu();
        if (menu.getParent() != null&&menu.getParent()!=-1) Assert.isTrue((parent = menuMapper.byId(menu.getParent())) != null, String.format("id为%d的父菜单不存在", menu.getParent()));
        Integer menuType = (menu.getParent() == null||menu.getParent()==-1) ? 1 : parent.getMenuType()+1;
        menu.setMenuType(menuType);
        menuMapper.insert(menu.setPrefix(defaultPrefix));
        return menu;
    }

    /**
     * 更新菜单
     *
     * @param menu
     * @return
     */
    @Override
    public Menu update(Menu menu) {
        //判断是否存在这个菜单
        Assert.isTrue(menu.getId() != null && menuMapper.byId(menu.getId()) != null, String.format("id为%d的菜单不存在", menu.getId() == null ? "null" : menu.getId()));
        if (menu.getParent() != null&&menu.getParent()!=-1) Assert.notNull(menuMapper.byId(menu.getParent()), String.format("id为%d的父菜单不存在", menu.getParent()));
        menuMapper.update(menu);
        return menu;
    }

    /**
     * 根据id删除
     *
     * @param id 菜单id
     */
    @Override
    @Transactional
    public void deleteById(Integer id) {
        Menu menu = menuMapper.byId(id);
        Assert.notNull(menu, "删除失败,id:" + id + "的菜单不存在");
        Assert.isTrue(menuMapper.hasChild(id) <= 0, "删除失败,存在子菜单");
        menuMapper.deleteById(id);
    }

    /**
     * 批量删除
     *
     * @param ids 菜单ids
     */
    @Override
    public void deleteByIds(Integer[] ids) {
        //循环调用单个删除操作
        for (Integer id : ids) {
            deleteById(id);
        }
    }

    /**
     * 根据id查询
     *
     * @param id 菜单id
     * @return
     */
    @Override
    public Menu byId(Integer id) {
        return menuMapper.byId(id);
    }

    /**
     * 根据类型查询
     *
     * @param type
     * @return
     */
    @Override
    public List<Menu> byType(Integer type) {
        return menuMapper.byMenuType(type);
    }

    /**
     * 根据名称模糊查询所有菜单
     *
     * @return
     */
    @Override
    public PageBean byNameLikeByPage(String menuName, PageBean pageBean) {
        Integer countSum = menuMapper.countSum(menuName);
        if (countSum == 0) {
            pageBean.setTotal(0);
            pageBean.setList(new ArrayList());
            return pageBean;
        }
        List<Menu> menus = menuMapper.byNameLikeByPage(menuName, pageBean);
        List<MenuDTO> menuDTOS = new ArrayList<>();

        menus.forEach(e -> {
            Menu menu = menuMapper.byId(e.getParent());
            String name = menu == null ? "无" : menu.getMenuName();
            menuDTOS.add(new MenuDTO(e).setParentName(name));
        });

        pageBean.setList(menuDTOS);
        pageBean.setTotal(countSum);
        return pageBean;
    }

    /**
     * 根据父菜单的名称模糊查询-分页
     *
     * @param fatherName
     * @param pageBean
     * @return
     */
    @Override
    public PageBean byFatherNameLikeByPage(String fatherName, PageBean pageBean) {

        Integer sum = menuMapper.countSumFather(fatherName);
        if (sum <= 0) return pageBean;
        pageBean.setTotal(sum);
        pageBean.setList(menuMapper.byFatherNameLikeByPage(fatherName, pageBean));
        return pageBean;
    }


    /**
     * 递归查找所有菜单-树形结构
     *
     * @return
     */
    @Override
    public List<MenuDTO> recursiveQuery() {
        return menuMapper.recursiveAll();
    }
}
