package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoAnodeProcessName;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.AnodeProcessNameService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 16:42
 * @description:
 **/
@RestController
@RequestMapping(value = "/anodeProcessType")
@Api(tags = "正极成本-基础数据-工序名称")
public class AnodeProcessTypeController {

    @Autowired
    AnodeProcessNameService nameService;

    @PostMapping(value = "add")
    @ApiOperation(value = "新增")
    public Result add(@RequestBody BasicInfoAnodeProcessName name) {
        return ResultUtil.success(nameService.add(name));
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@ApiParam(name = "id", value = "主键") @RequestParam Integer id) {
        nameService.delete(id);
        return ResultUtil.success();
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "编辑")
    public Result update(@RequestBody BasicInfoAnodeProcessName name) {
        return ResultUtil.success(nameService.update(name));
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "查询所有")
    public Result getAll() {
        return ResultUtil.success(nameService.getAll());
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "分页")
    public Result getPage(@ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return ResultUtil.success(nameService.page(page, size));
    }
}
