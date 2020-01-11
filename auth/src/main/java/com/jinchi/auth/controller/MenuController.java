package com.jinchi.auth.controller;

import com.jinchi.auth.domain.Menu;
import com.jinchi.auth.domain.Result;
import com.jinchi.auth.dto.MenuDTO;
import com.jinchi.auth.dto.PageBean;
import com.jinchi.auth.service.MenuService;
import com.jinchi.auth.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 说明:菜单操作接口类
 * <br>
 *
 * @author ZSCDumin
 * <br>
 * 邮箱: 2712220318@qq.com
 * <br>
 * 日期: 2018/11/13
 * <br>
 * 版本: 1.0
 */
@RestController
@RequestMapping(value = "/menu")
@Api(tags = "菜单接口")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 新增菜单
     */
    @Secured("MENU_AUTH_MENU_ADD")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    @PostMapping
    @ApiOperation(value = "新增")
    public Result<Menu> add(@RequestBody @Valid Menu menu) {
        return ResultUtil.success(menuService.add(menu));
    }

    /**
     * 更新菜单
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    @Secured("MENU_AUTH_MENU_UPDATE")
    @PutMapping
    @ApiOperation(value = "更新")
    public Result<Object> update(@RequestBody @Valid Menu menu) {
        return ResultUtil.success(menuService.update(menu));
    }

    /**
     * 删除一个菜单
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    @Secured("MENU_AUTH_MENU_DELETE")
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除")
    public Result<Object> deleteById(@ApiParam(name = "id", value = "菜单主键") @PathVariable Integer id) {
            menuService.deleteById(id);
            return ResultUtil.success();
    }

    /**
     * 查找一个菜单
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    @Secured("MENU_AUTH_MENU_GET")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "详情")
    public Result<Object> byId(@ApiParam(name = "id", value = "菜单主键") @PathVariable Integer id) {
            return ResultUtil.success(menuService.byId(id));
    }

    /**
     * 删除多个菜单
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    @Secured("MENU_AUTH_MENU_DELETEBYIDS")
    @DeleteMapping(value = "/batchDelete")
    @ApiOperation(value = "批量删除")
    public Result<Menu> deleteByIds(@ApiParam(value = "ids", name = "ids") @RequestBody Integer[] ids) {
        menuService.deleteByIds(ids);
        return ResultUtil.success();
    }


    /**
     * 根据菜单名称查询所有菜单-分页
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    @GetMapping(value = "/byNameLikeByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<PageBean> byNameLikeByPage(@ApiParam(name = "menuName", value = "菜单名") @RequestParam(name = "menuName", required = false) String menuName, PageBean pageBean) {
        System.out.println(pageBean);
        PageBean<MenuDTO> ans = menuService.byNameLikeByPage(menuName, pageBean);
        System.out.println(ans.toString());
        return ResultUtil.success(ans);
    }

    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    @GetMapping(value = "/byFatherNameLikeByPage")
    @ApiOperation(value = "查询所有-父菜单名称模糊-分页")
    public Result<PageBean> byFatherNameLikeByPage(@ApiParam(name = "fatherName", value = "菜单名") @RequestParam(name = "fatherName", required = false) String menuName, PageBean pageBean) {
        return ResultUtil.success(menuService.byFatherNameLikeByPage(menuName, pageBean));
    }


    /**
     * 根据菜单类型查询所有菜单
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    @GetMapping(value = "/byType")
    @ApiOperation(value = "类型查询",notes = "默认查询所有")
    public Result<List<Menu>> findByMenuType(
            @ApiParam(name = "menuType", value = "菜单类型") @RequestParam(name = "menuType", defaultValue = "-1") Integer
                    menuType) {
        return ResultUtil.success(menuService.byType(menuType));
    }

    /**
     * 递归查找所有菜单
     */
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    @GetMapping(value = "/recursive")
    @ApiOperation(value = "查询所有-菜单树")
    public Result<List<MenuDTO>> recursive() {
        return ResultUtil.success(menuService.recursiveQuery());
    }

}
