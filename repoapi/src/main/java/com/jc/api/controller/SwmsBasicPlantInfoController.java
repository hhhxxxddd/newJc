package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicPlantInfo;
import com.jc.api.service.restservice.ISwmsBasicPlantInfoService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Auther: River
 * @Date: 2020/1/11 15:24
 * @Description:
 */
@RestController
@Api(tags = "智能仓库-车间信息管理界面")
@RequestMapping(value = "/swmsBasicPlanInfo")
@Slf4j
public class SwmsBasicPlantInfoController {

    @Autowired
    private ISwmsBasicPlantInfoService iSwmsBasicPlantInfoService;

    @ApiOperation(value = "获取所有供应车间", notes = "获取所有供应车间")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/getAll")
    public Result getAll(){
        return Result.success(iSwmsBasicPlantInfoService.getAll(""));
    }

    @ApiOperation(value = "获取供应车间-条件", notes = "条件获取所有供应车间")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/conditions")
    public Result query(@RequestParam(required = false) String plantName){
        return Result.success(iSwmsBasicPlantInfoService.getAll(plantName));
    }

    @ApiOperation(value = "获取供应车间-条件/分页", notes = "条件获取供应车间记录-分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@RequestBody Page page, @RequestParam(required = false) String plantName) {
        log.debug("条件获取供应车间分页:{}", plantName);
        return Result.success(iSwmsBasicPlantInfoService.getAllByPage(page, plantName));
    }

    @ApiOperation(value = "新增供应车间信息", notes = "新增")
    @ApiImplicitParam(name = "swmsBasicPlantInfo", value = "新增参数", required = true, dataType = "SwmsBasicPlantInfo")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/add")
    public Result add(@Valid @RequestBody SwmsBasicPlantInfo swmsBasicPlantInfo) {
        swmsBasicPlantInfo.setAutoFlag(false);
        return Result.success(iSwmsBasicPlantInfoService.add(swmsBasicPlantInfo));
    }

    @ApiOperation(value = "仅供测试使用-自动新增供应车间信息", notes = "自动新增,存在则无动作,不存在则新增")
    @ApiImplicitParam(name = "plantCode", value = "供货车间代号", required = true, dataType = "String")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/autoAdd")
    public Result autoAdd(@RequestParam @NotBlank(message = "供应车间代号不能为空白") String plantCode) {
        return Result.success(iSwmsBasicPlantInfoService.autoAdd(new SwmsBasicPlantInfo().setPlantCode(plantCode)));
    }

    @ApiOperation(value = "更新供货商信息", notes = "更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "swmsBasicPlantInfo", value = "更新参数", required = true, dataType = "SwmsBasicPlantInfo")})
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody SwmsBasicPlantInfo swmsBasicPlantInfo) {
        return Result.success(iSwmsBasicPlantInfoService.update(swmsBasicPlantInfo));
    }

    @ApiOperation(value = "删除供应车间信息", notes = "根据id删除供应车间信息")
    @ApiImplicitParam(name = "id", value = "供应车间信息id", required = true, dataType = "long")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        log.debug("根据id删除供应车间记录:{}", id);
        return Result.success(iSwmsBasicPlantInfoService.delete(id));
    }

    @ApiOperation(value = "删除供应车间信息", notes = "根据id删除供应车间信息")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "ids")
    public Result deleteByIds(@RequestBody Integer[] ids) {
        log.debug("根据id删除供应车间记录:{}", ids);
        iSwmsBasicPlantInfoService.deleteByIds(ids);
        return Result.success();
    }
}
