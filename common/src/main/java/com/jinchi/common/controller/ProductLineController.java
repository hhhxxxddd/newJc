package com.jinchi.common.controller;

import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.ProductLine;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.ProductLineService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 说明:产品线控制器
 * <br>
 *
 * @author ZSCDumin
 * <br>
 * 邮箱: 2712220318@qq.com
 * <br>
 * 日期: 2018/11/16
 * <br>
 * 版本: 1.0
 */
@RestController
@RequestMapping(value = "/productLine")
@Api(tags = "质量流程-基础数据-产品线")
public class ProductLineController {
    @Autowired
    private ProductLineService productLineService;

    /**
     * 新增产品线
     */
//    @Secured("QUALITY_PRODUCTINE_ADD")
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    @PostMapping
    @ApiOperation(value = "新增")
    public Result<ProductLine> add(@RequestBody @Valid ProductLine productLine, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(productLineService.add(productLine));
    }

    /**
     * 更新产品线
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
//    @Secured("QUALITY_PRODUCTINE_UPDATE")
    @PutMapping
    @ApiOperation(value = "更新")
    public Result<ProductLine> update(@RequestBody @Valid ProductLine productLine, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(productLineService.update(productLine));
    }

    /**
     * 删除一个产品线
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
//    @Secured("QUALITY_PRODUCTINE_DELETE")
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除")
    public Result<Object> deleteById(@ApiParam(name = "id", value = "产品线主键") @PathVariable Integer id) {
        if (productLineService.getById(id) == null) {
            return ResultUtil.error("此id=" + id + "不存在,删除失败");
        } else {
            productLineService.deleteById(id);
            return ResultUtil.success();
        }
    }

    /**
     * 查找一个产品线
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
//    @Secured("QUALITY_PRODUCTINE_GETBYID")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查找")
    public Result<Object> getById(@ApiParam(name = "id", value = "产品线主键") @PathVariable Integer id) {
        if (productLineService.getById(id) == null) {
            return ResultUtil.error("此id=" + id + "不存在,查找失败");
        } else {
            return ResultUtil.success(productLineService.getById(id));
        }
    }

    /**
     * 删除多个产品线
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
//    @Secured("QUALITY_PRODUCTINE_DELETEBYIDS")
    @DeleteMapping
    @ApiOperation(value = "批量删除")
    public Result<ProductLine> deleteByIds(@ApiParam(value = "ids", name = "ids") @RequestBody Integer[] ids) {
        productLineService.deleteByIds(ids);
        return ResultUtil.success();
    }

    /**
     * 查询所有产品线-分页
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
//    @Secured("QUALITY_PRODUCTINE_GETALLBYPAGE")
    @GetMapping(value = "/pages")
    @ApiOperation(value = "查询所有-分页")
    public Result<PageInfo<ProductLine>> getAllByPage
    (@ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "0") Integer page,
     @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
     @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "id") String
             orderField,
     @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String
             orderType) {
        return ResultUtil.success(productLineService.getAllByPage(page, size, orderField, orderType));
    }

    @GetMapping
    @ApiOperation(value = "查询所有")
    public Result<List<ProductLine>> getAll() {
        return ResultUtil.success(productLineService.getAll());
    }

    /**
     * 根据产品线名称查询所有产品线-分页
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
//    @Secured("QUALITY_PRODUCTINE_GETBYNAMELIKEBYPAGE")
    @GetMapping(value = "/pagesNameLike")
    @ApiOperation(value = "查询所有-名称模糊-分页")
    public Result getByNameLikeByPage(
            @ApiParam(name = "productLineName", value = "产品线名") @RequestParam(name = "productLineName", defaultValue = "") String
                    productLineName,
            @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "0") Integer page,
            @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
            @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "id") String
                    orderField,
            @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String
                    orderType) {
        return ResultUtil.success(productLineService.getByNameLikeByPage(productLineName, page, size, orderField, orderType));
    }

}
