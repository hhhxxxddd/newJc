package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.processParamer.ProcessParamerMainDTO;
import com.jinchi.common.service.ProcessParamerService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "processParam")
@Api(tags = "前驱体-工艺参数")
public class ProcessParamerController {

    @Autowired
    ProcessParamerService processParamerService;

    @GetMapping(value = "page")
    @ApiOperation(value = "分页条件查询")
    public Result page(@RequestParam(required = false,defaultValue = "") String condition,
                       @RequestParam Integer status,
                       @RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResultUtil.success(processParamerService.page(condition,status,page,size));
    }

    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "删除")
    public Result delete(@PathVariable Long id) {
        processParamerService.delete(id);
        return ResultUtil.success();
    }

    @PostMapping(value = "saveOrcommit")
    @ApiOperation(value = "保存提交")
    public Result addHeaed(@RequestBody ProcessParamerMainDTO processParamerMainDTO,
                           @RequestParam Integer flag) {
        return ResultUtil.success(processParamerService.saveOrCommit(processParamerMainDTO, flag));
    }

    @GetMapping(value = "detail")
    @ApiOperation(value = "详情")
    public Result detail(@RequestParam Long id){
        return ResultUtil.success(processParamerService.detail(id));
    }

    @GetMapping(value = "mixRecipe")
    @ApiOperation(value = "混合盐获取配方")
    public Result mixRecipe(@RequestParam(required = false,defaultValue = "")String condition,
                            @RequestParam(required = false,defaultValue = "1")Integer page,
                            @RequestParam(required = false,defaultValue = "10")Integer size){
        return ResultUtil.success(processParamerService.mixRecipe(condition,page,size));
    }

    @GetMapping(value = "compoundRecipe")
    @ApiOperation(value = "合成获取配方")
    public Result compoundRecipe(@RequestParam(required = false,defaultValue = "")String condition,
                            @RequestParam(required = false,defaultValue = "1")Integer page,
                            @RequestParam(required = false,defaultValue = "10")Integer size){
        return ResultUtil.success(processParamerService.compoundRecipe(condition,page,size));
    }

    @GetMapping(value = "mixRecipeList")
    @ApiOperation(value = "混合盐获取配方(List)")
    public Result mixRecipeList(@RequestParam(required = false,defaultValue = "")String condition){
        return ResultUtil.success(processParamerService.mixRecipeList(condition));
    }

    @GetMapping(value = "compoundRecipeList")
    @ApiOperation(value = "合成获取配方(List)")
    public Result compoundRecipeList(@RequestParam(required = false,defaultValue = "")String condition){
        return ResultUtil.success(processParamerService.compoundRecipeList(condition));
    }


    @GetMapping(value = "detailByBatch")
    @ApiOperation(value= "根据批号获取工艺参数详情")
    public Result detailByBatch(@RequestParam Integer batchId){
        return ResultUtil.success(processParamerService.detailByBatch(batchId));
    }

    @DeleteMapping(value = "deleteByIds")
    @ApiOperation(value = "批量删除")
    public Result deleteByIds(@RequestBody Long[] ids){
        processParamerService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @PutMapping(value = "publish")
    @ApiOperation(value = "发布")
    public Result publish(@RequestParam Long id){
        processParamerService.publish(id);
        return ResultUtil.success();
    }
}
