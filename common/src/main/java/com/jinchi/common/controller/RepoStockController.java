package com.jinchi.common.controller;

import com.github.pagehelper.PageInfo;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.repository.RepoStockDTO;
import com.jinchi.common.service.RepoStockService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by WangZhihao on 2018/11/30.
 */
@RestController
@RequestMapping(value = "/RepoStock")
@Api(tags = "智能仓库-库存管理")
public class RepoStockController {
    @Autowired
    private RepoStockService repoStockService;

    /**
     * 查询所有
     *
     * @param materialClass
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询所有")
    public Result<List<RepoStockDTO>> findAll(@ApiParam(name = "materialClass", value = "物料类型") @RequestParam(name = "materialClass", defaultValue = "1") Integer materialClass) {
        return ResultUtil.success(repoStockService.findAllByMaterialClass(materialClass));
    }

    /**
     * 根据名称模糊查询所有库存记录-分页
     *
     * @param materialName
     * @param materialClass
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     */
    @GetMapping(value = "/pages")
    @ApiOperation(value = "查询所有-分页")
    public Result<PageBean> findAllByNameByPage(@ApiParam(name = "materialName", value = "材料名称") @RequestParam(required = false) String materialName,
                                                @ApiParam(name = "materialClass", value = "物料类型") @RequestParam(name = "materialClass") Integer materialClass,
                                                @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "id") String orderField,
                                                @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType) {
        return ResultUtil.success(repoStockService.findAllByNameByPage(materialClass, materialName, page, size, orderField, orderType));
    }

    /**
     * 根据物料类型和物料名称查询所有库存记录
     */
    @GetMapping(value = "/names")
    @ApiOperation(value = "根据物料类型和物料名称查询所有")
    public Result<List<RepoStockDTO>> findAllByClassAndName(@ApiParam(name = "materialClass", value = "物料类型") @RequestParam(name = "materialClass", defaultValue = "1") Integer materialClass,
                                                            @ApiParam(name = "materialName", value = "材料名称") @RequestParam(required = false) String materialName) {
        return ResultUtil.success(repoStockService.findAllByClassAndName(materialClass, materialName));
    }

    /**
     * 一键盘库
     *
     * @param id
     * @param creator
     * @return
     */
    @PostMapping(value = "/oneKeyStock")
    @ApiOperation(value = "一键盘库")
    public Result<Object> oneKeyStock(@ApiParam(name = "id", value = "记录主键") @RequestParam Integer id,
                                      @ApiParam(name = "creator", value = "创建人") @RequestParam Integer creator) {
        return ResultUtil.success(repoStockService.oneKeyStock(id, creator));
    }

}
