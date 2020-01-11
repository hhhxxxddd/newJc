package com.jinchi.common.controller;

import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.repository.RepoSendOutDetailDTO;
import com.jinchi.common.service.RepoOutApplyService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author：XudongHu
 * @className:RepoOutApplyController
 * @description:
 * @date:15:48 2018/12/2
 */
@RestController
@RequestMapping(value = "/repoOutApply")
@Api(tags = "智能仓库-出库管理")
public class RepoOutApplyController {
    @Autowired
    private RepoOutApplyService repoOutApplyService;


    /**
     * 新增出库申请
     */
    @PostMapping
    @ApiOperation(value = "新增", notes = "出库申请")
    public Result<CommonBatchNumberDTO> outApply(@ApiParam(value = "出库物品清单") @RequestBody CommonBatchNumberDTO<List<RepoSendOutDetailDTO>> batchRepoSendOutDetail) {

        return ResultUtil.success(null);
    }

    /**
     * 根据名称模糊和类型查询所有出库记录
     */
    @GetMapping
    @ApiOperation(value = "查询所有", notes = "根据申请人名称和类型,都可为空")
    public Result<List<CommonBatchNumberDTO>> nameLikeAndType(@ApiParam(value = "申请人名称", name = "personName") @RequestParam(required = false) String personName,
                                                              @ApiParam(value = "物料类型(原料1中间件2成品3)", name = "type") @RequestParam Integer type) {
        return ResultUtil.success(repoOutApplyService.findAllByNameLikeAndType(personName, type));
    }

    /**
     * 根据名称模糊和类型查询所有出库记录-分页
     */
    @GetMapping(value = "/pages")
    @ApiOperation(value = "查询所有-分页", notes = "根据申请人名称和类型,都可为空")
    public Result<PageBean> nameLikeAndTypeByPage(@ApiParam(name = "personName", value = "申请人名称") @RequestParam(required = false) String personName,
                                                  @ApiParam(name = "type", value = "物料类型 原料1中间件2成品3") @RequestParam Integer type,
                                                  @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                  @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                  @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "id") String orderField,
                                                  @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType) {
        return ResultUtil.success(repoOutApplyService.findAllByNameLikeAndTypeByPage(personName, type, page, size, orderField, orderType));
    }


    /**
     * 根据id删除出库记录
     */
    @DeleteMapping(value = "/{batchNumberId}")
    @ApiOperation(value = "删除", notes = "根据批号id")
    public Result<Object> deleteByBatchNumberId(@ApiParam(name = "batchNumberId", value = "批号id") @PathVariable Integer batchNumberId) {
        repoOutApplyService.deleteByBatchNumberId(batchNumberId);
        return ResultUtil.success();
    }


    /**
     * 根据ids批量删除出库记录
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除", notes = "根据批号ids")
    public Result<Object> deleteByBatchNumberIds(@ApiParam(name = "batchNumberIds", value = "批号ids") @RequestBody Integer[] batchNumberIds) {
        repoOutApplyService.deleteByBatchNumberIds(Arrays.asList(batchNumberIds));
        return ResultUtil.success();
    }


    /**
     * 根据批号id查询
     */
    @GetMapping(value = "/{batchNumberId}")
    @ApiOperation(value = "查询", notes = "根据批号id")
    public Result<CommonBatchNumberDTO> getByBatchNumberId(@ApiParam(name = "batchNumberId", value = "批号id") @PathVariable Integer batchNumberId) {
        return ResultUtil.success(repoOutApplyService.findByBatchNumberId(batchNumberId));
    }
}
