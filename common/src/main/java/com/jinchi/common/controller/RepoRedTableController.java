package com.jinchi.common.controller;

import com.jinchi.common.domain.ProductLine;
import com.jinchi.common.domain.RepoRedTable;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.RepoRedTableService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 说明:红单管理记录接口
 * <br>
 *
 * @author ZSCDumin
 * <br>
 * 邮箱: 2712220318@qq.com
 * <br>
 * 日期: 2018/11/27
 * <br>
 * 版本: 1.0
 */
@RestController
@Api(tags = "智能仓库-红单管理")
public class RepoRedTableController {
    @Autowired
    private RepoRedTableService repoRedTableService;

    /**
     * 新增红单管理记录
     */
//    @Secured("QUALITY_PRODUCTINE_ADD")
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
    @PostMapping(value = "/repoRedTable")
    @ApiOperation(value = "新增红单管理记录")
    public Result<CommonBatchNumberDTO> insert(@RequestBody @Valid CommonBatchNumberDTO<RepoRedTable> commonBatchNumberDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(repoRedTableService.insert(commonBatchNumberDTO));
    }

    /**
     * 更新红单管理记录
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
//    @Secured("QUALITY_PRODUCTINE_UPDATE")
    @PutMapping(value = "/repoRedTable")
    @ApiOperation(value = "更新红单管理记录")
    public Result<CommonBatchNumberDTO> update(@RequestBody @Valid CommonBatchNumberDTO<RepoRedTable> commonBatchNumberDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(repoRedTableService.update(commonBatchNumberDTO));
    }


    /**
     * 根据Id删除红单管理记录
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
//    @Secured("QUALITY_PRODUCTINE_DELETE")
    @DeleteMapping(value = "/repoRedTable/{id}")
    @ApiOperation(value = "删除红单管理记录")
    public Result<Object> deleteById(@ApiParam(name = "id", value = "红单管理记录主键") @PathVariable Integer id) {
        if (repoRedTableService.getById(id) == null) {
            return ResultUtil.error("此id=" + id + "不存在,删除失败");
        } else {
            repoRedTableService.deleteById(id);
            return ResultUtil.success();
        }
    }

    /**
     * 查找一个红单管理记录
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
//    @Secured("QUALITY_PRODUCTINE_GETBYID")
    @GetMapping(value = "/repoRedTable/batchNumberId")
    @ApiOperation(value = "根据批号查找红单管理记录")
    public Result<Object> getByBatchNumberId(@ApiParam(name = "batchNumberId", value = "批号") @RequestParam Integer batchNumberId) {
        return ResultUtil.success(repoRedTableService.getByBatchNumberId(batchNumberId));
    }


    /**
     * 查找一个红单管理记录
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
//    @Secured("QUALITY_PRODUCTINE_GETBYID")
    @GetMapping(value = "/repoRedTable/{id}")
    @ApiOperation(value = "查找红单管理记录")
    public Result<Object> getById(@ApiParam(name = "id", value = "红单管理记录主键") @PathVariable Integer id) {
        if (repoRedTableService.getById(id) == null) {
            return ResultUtil.error("此id=" + id + "不存在,查找失败");
        } else {
            return ResultUtil.success(repoRedTableService.getById(id));
        }
    }


    /**
     * 删除多个产品线
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
//    @Secured("QUALITY_PRODUCTINE_DELETEBYIDS")
    @DeleteMapping(value = "/repoRedTables")
    @ApiOperation(value = "删除多个红单管理记录")
    public Result<ProductLine> deleteByIds(@ApiParam(value = "ids", name = "ids") @RequestBody Integer[] ids) {
        repoRedTableService.deleteByIds(ids);
        return ResultUtil.success();
    }

    /**
     * 查询所有红单管理记录-分页
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
//    @Secured("QUALITY_PRODUCTINE_GETALLBYPAGE")
    @GetMapping(value = "/repoRedTables/")
    @ApiOperation(value = "查询所有红单管理记录-分页")
    public Result<PageBean> getAllByPage
    (@ApiParam(name = "materialType", value = "材料类型") @RequestParam(name = "materialType", defaultValue = "1") Integer materialType,
     @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
     @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
     @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "id") String orderField,
     @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType) {
        return ResultUtil.success(repoRedTableService.getAllByPage(materialType, page, size, orderField, orderType));
    }

    /**
     * 根据材料编号字段查询所有红单管理记录-分页
     */
//    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_AUTH_USER_QUERY\")")
//    @Secured("QUALITY_PRODUCTINE_GETBYNAMELIKEBYPAGE")
    @GetMapping(value = "/repoRedTables/serialNumber")
    @ApiOperation(value = "根据材料编号字段查询所有红单管理记录-分页")
    public Result getByAnyFiledLikeByPage(
            @ApiParam(name = "materialType", value = "材料类型") @RequestParam(name = "materialType", defaultValue = "1") Integer materialType,
            @ApiParam(name = "serialNumber", value = "材料编号") @RequestParam(name = "serialNumber") String serialNumber,
            @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
            @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "id") String orderField,
            @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String
                    orderType) {
        return ResultUtil.success(repoRedTableService.getBySerialNumberLikeByPage(materialType, serialNumber, page, size, orderField, orderType));
    }

}
