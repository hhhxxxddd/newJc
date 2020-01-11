package com.jinchi.auth.controller;

import com.github.pagehelper.PageInfo;
import com.jinchi.auth.domain.Result;
import com.jinchi.auth.domain.User;
import com.jinchi.auth.dto.UserDTO;
import com.jinchi.auth.service.DepartmentService;
import com.jinchi.auth.service.UserService;
import com.jinchi.auth.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @author：XudongHu
 * @description: demo Controller层
 * @date:11:11 2018/10/24
 */
@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户接口")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;


    /**
     * 查找用户所有权限
     */
    @PreAuthorize("hasRole(\"ROLE_AUTH_MENU_SAVE\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    //@Secured("ROLE_AUTH_USER_QUERY")
    @GetMapping(value = "/findAuths")
    @ApiOperation(value = "查找用户所有权限")
    public Result<Set<String>> findAuths(Integer id){
        return ResultUtil.success(userService.findUserAuths(id));
    }

    /***
     * 查找用户所有权限 MenuAndOperationDTO
     */
//    @Secured("ROLE_AUTH_USER_QUERY")

    /**
     * 查找用户所有角色
     */
    @PreAuthorize("hasRole(\"ROLE_AUTH_MENU_SAVE\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    @GetMapping(value = "/findRoles")
    @ApiOperation(value = "查找用户所有角色")
    public Result<Object> findRoles(String username){
        return ResultUtil.success(userService.findRolesByUsername(username));
    }

    /**
     * 注册
     */
    @PostMapping(value = "/signIn")
    @ApiOperation(value ="注册")
    public Result<UserDTO> add(@RequestBody @Valid UserDTO userDTO , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(userService.save(userDTO));
    }

    /**
     * 更新
     */
    @PreAuthorize("hasRole(\"ROLE_AUTH_MENU_SAVE\") or hasRole(\"ROLE_AUTH_USER_UPDATE\")")
    //@Secured("ROLE_AUTH_USER_UPDATE")
    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public Result<User> update(@RequestBody @Valid UserDTO userDTO , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(userService.update(userDTO));
    }

    @PreAuthorize("hasRole(\"ROLE_AUTH_MENU_SAVE\") or hasRole(\"ROLE_AUTH_USER_UPDATE\")")
    //@Secured("ROLE_AUTH_USER_UPDATE")
    @PostMapping(value = "/reset")
    @ApiOperation(value = "重置密码")
    public Result reset(@RequestParam  Integer id){

        return ResultUtil.success(userService.reset(id));
    }

    /**
     * 删除
     */
    @PreAuthorize("hasRole(\"ROLE_AUTH_MENU_SAVE\") or hasRole(\"ROLE_AUTH_USER_DELETE\")")
    //@Secured("ROLE_AUTH_USER_DELETE")
    @DeleteMapping(value = "/deleteById")
    @ApiOperation(value = "根据id删除")
    public Result<Object> deleteById(@ApiParam(value = "用户主键",name = "id") @RequestParam Integer id){
        UserDTO userDTO = userService.find(id);
        if(userDTO == null){
            return ResultUtil.error("不存在该用户！");
        }
        userService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 批量删除
     */
    @PreAuthorize("hasRole(\"ROLE_AUTH_MENU_SAVE\") or hasRole(\"ROLE_AUTH_USER_DELETE\")")
    //@Secured("ROLE_AUTH_USER_DELETE")
    @DeleteMapping(value = "/deleteByIds")
    @ApiOperation(value = "根据多个id删除")
    public Result<Object> deleteByIds(@ApiParam(value = "用户主键") @RequestBody Integer[] ids){
        StringBuilder sb = new StringBuilder();
        for(Integer id:ids){
            UserDTO userDTO = userService.find(id);
            if(userDTO == null){
                sb.append(id+",");
            }else{
                userService.delete(id);
            }
        }
        if(sb.length() == 0)
        {return ResultUtil.success();}
        else
        {return ResultUtil.error("部分未删除（"+sb.toString()+"),id不存在");}
    }

    /**
     * 根据id查询
     */
    @PreAuthorize("hasRole(\"ROLE_AUTH_MENU_SAVE\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    //@Secured("ROLE_AUTH_USER_QUERY")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据Id查询")
    public Result<UserDTO> getById( @ApiParam(name = "id",value = "用户主键")  @PathVariable Integer id) {
        return ResultUtil.success(userService.find(id));
    }


    /**
     * 查询所有
     */
    @PreAuthorize("hasRole(\"ROLE_AUTH_MENU_SAVE\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    //@Secured("ROLE_AUTH_USER_QUERY")
    @GetMapping(value = "/getAll")
    @ApiOperation(value = "查询所有")
    public Result<List<UserDTO>> getAll(){
        return ResultUtil.success(userService.findAll());
    }

    /**
     *
     *分页查询
     */
    @PreAuthorize("hasRole(\"ROLE_AUTH_MENU_SAVE\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    //@Secured("ROLE_AUTH_USER_QUERY")
    @GetMapping(value = "/getAllByPage")
    @ApiOperation(value = "分页查询")
    public Result<PageInfo<User>> getAllByPage(@ApiParam(name = "page",value = "页码") @RequestParam(name = "page",defaultValue = "0") Integer page,
                                           @ApiParam(name = "size",value = "条目数") @RequestParam(name = "size",defaultValue = "10") Integer size,
                                           @ApiParam(name = "orderField",value = "排序属性") @RequestParam(name = "orderField",defaultValue = "id") String orderField,
                                           @ApiParam(name = "orderType",value = "排序方法,只能是asc升序或者desc降序")  @RequestParam(name = "orderType",defaultValue = "desc") String orderType){
        return ResultUtil.success(userService.findAllByPage(page,size,orderField,orderType));
    }


    @PreAuthorize("hasRole(\"ROLE_AUTH_MENU_SAVE\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    @Secured("ROLE_AUTH_ROLE_QUERY")
    @GetMapping(value = "/getUserByNameByPage")
    @ApiOperation(value = "通过名称模糊查询所有用户-分页")
    public Result<PageInfo<User>> findUserByPage(@ApiParam(name = "page",value = "页码") @RequestParam(name = "page",defaultValue = "0") Integer page,
                                                              @ApiParam(name = "size",value = "条目数") @RequestParam(name = "size",defaultValue = "10") Integer size,
                                                              @ApiParam(name = "orderField",value = "排序属性") @RequestParam(name = "orderField",defaultValue = "id") String orderField,
                                                              @ApiParam(name = "orderType",value = "排序方法,只能是asc升序或者desc降序")  @RequestParam(name = "orderType",defaultValue = "desc") String orderType,
                                                              @ApiParam(value = "用户名")  @RequestParam String name  ){
        return ResultUtil.success(userService.findAllByPageByName(page,size,orderField,orderType,name));
    }

}
