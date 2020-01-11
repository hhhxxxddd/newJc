package com.jinchi.common.controller;


import com.github.pagehelper.PageInfo;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.TaskDTO;
import com.jinchi.common.service.TaskService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


/**
 * @author：XudongHu
 * @className:BatchAuditTaskController
 * @description:
 * @date:0:16 2018/11/23
 */
@RestController
@RequestMapping(value = "/batchAuditTask")
@Api(tags = "质量流程-流程管理")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * 新增
     */
    @PostMapping
    @ApiOperation(value = "新增")
    public Result<CommonBatchNumberDTO<List<TaskDTO>>> add(@Valid @ApiParam(value = "批号流程") @RequestBody CommonBatchNumberDTO<List<TaskDTO>> batchTaskDTO) {
        return ResultUtil.success(taskService.add(batchTaskDTO));
    }

    /**
     * 更新
     */
    @PutMapping
    @ApiOperation(value = "更新")
    public Result<CommonBatchNumberDTO<List<TaskDTO>>> update(@Valid @ApiParam(value = "批号流程") @RequestBody CommonBatchNumberDTO<List<TaskDTO>> batchTaskDTO) {
        return ResultUtil.success(taskService.update(batchTaskDTO));
    }


    /**
     * 根据公共批号id查询
     */
    @GetMapping(value = "/{batchNumberId}")
    @ApiOperation(value = "查询", notes = "根据批号id")
    public Result<CommonBatchNumberDTO<List<TaskDTO>>> getByBatchNumberId(@ApiParam(name = "batchNumberId", value = "批号id") @PathVariable Integer batchNumberId) {
        return ResultUtil.success(taskService.findByBatchNumberId(batchNumberId));
    }

    /**
     * 根据公共批号删除
     */
    @DeleteMapping(value = "/{batchNumberId}")
    @ApiOperation(value = "删除", notes = "根据批号id")
    public Result<Object> deleteByBatchNumberId(@ApiParam(name = "batchNumberId", value = "批号id") @PathVariable Integer batchNumberId) {
        taskService.deleteByBatchNumberId(batchNumberId);
        return ResultUtil.success();
    }


    /**
     * 批量删除
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除", notes = "根据批号ids")
    public Result<Object> deleteByBatchNumberIds(@ApiParam(value = "批号ids") @RequestBody Integer[] batchNumberIds) {

        taskService.deleteByBatch(Arrays.asList(batchNumberIds));
        return ResultUtil.success();
    }


    /**
     * 查询所有
     */
    @GetMapping
    @ApiOperation(value = "查询所有")
    public Result<List<CommonBatchNumberDTO>> getAll() {
        return ResultUtil.success(taskService.findAll());
    }

    /**
     * 查询所有
     */
    @GetMapping(value = "/validTasks")
    @ApiOperation(value = "查询所有有效流程", notes = "所有已提交的流程")
    public Result<List<CommonBatchNumberDTO>> validTasks() {
        return ResultUtil.success(taskService.validTasks());
    }

    /**
     * 查询所有-分页
     */
    @GetMapping(value = "/pages")
    @ApiOperation(value = "查询所有-分页", notes = "根据名称模糊,可为空")
    public Result<PageInfo> getAllByPage(@ApiParam(name = "taskName", value = "流程名") @RequestParam(required = false) String taskName,
                                         @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "0") Integer page,
                                         @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                                         @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "id") String orderField,
                                         @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType) {
        return ResultUtil.success(taskService.findAllByNameLikeByPage(taskName, page, size, orderField, orderType));
    }

}
