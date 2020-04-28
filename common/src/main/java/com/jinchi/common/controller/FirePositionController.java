package com.jinchi.common.controller;

import com.jinchi.common.domain.FirePosition;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.FirePositionService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firePosition")
@Api(tags = "火法质量-岗位信息")
public class FirePositionController {

    @Autowired
    FirePositionService service;

    @PostMapping
    @ApiOperation("新增")
    public Result add(@RequestParam String name){
        return ResultUtil.success(service.add(name));
    }

    @DeleteMapping
    @ApiOperation("删除")
    public Result delete(@RequestParam Integer id){
        service.delete(id);
        return ResultUtil.success();
    }

    @DeleteMapping("ids")
    @ApiOperation("批量删除")
    public Result deleteByIds(@RequestParam Integer[] ids){
        service.deleteIds(ids);
        return ResultUtil.success();
    }

    @GetMapping
    @ApiOperation("按名称分页查询")
    public Result page(@RequestParam(required = false,defaultValue = "") String name,
                       @RequestParam(required = false,defaultValue = "1") Integer page,
                       @RequestParam(required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(service.page(name,page,size));
    }

    @PutMapping
    @ApiOperation("更新")
    public Result update(@RequestBody FirePosition firePosition){
        return ResultUtil.success(service.update(firePosition));
    }

    @PostMapping("assign")
    @ApiOperation("分配岗位")
    public Result assign(@RequestParam Integer positionId,@RequestParam Integer[] userIds){
        service.assign(positionId,userIds);
        return ResultUtil.success();
    }

    @PostMapping("assignItems")
    @ApiOperation("分配项目")
    public Result assignItems(@RequestParam Integer positionId,@RequestParam Long[] itemIds){
        service.assion(positionId,itemIds);
        return ResultUtil.success();
    }

    @GetMapping("userDetail")
    @ApiOperation("用户详情")
    public Result userDetail(@RequestParam Integer positionId){
        return ResultUtil.success(service.getUser(positionId));
    }

    @GetMapping("itemDetail")
    @ApiOperation("项目详情")
    public Result itemDetail(@RequestParam Integer positionId){
        return ResultUtil.success(service.getItems(positionId));
    }
}
