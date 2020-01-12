package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicDeliveryAddressInfo;
import com.jc.api.service.restservice.imp.SwmsBasicDeliveryAddressInfoService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/12 15:25
 * @Description:
 */
@RestController
@Api(tags = "智能仓库-出库点信息管理界面")
@RequestMapping(value = "/swmsBasicDeliveryAddressInfo")
@Slf4j
public class SwmsBasicDeliveryAddressInfoController {

    @Autowired
    private SwmsBasicDeliveryAddressInfoService swmsBasicDeliveryAddressInfoService;

    @ApiOperation(value = "获取所有出库点", notes = "获取所有出库点")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/getAll")
    public Result getAll(){
        return Result.success(swmsBasicDeliveryAddressInfoService.getAll(""));
    }

    @ApiOperation(value = "获取出库点-条件/分页", notes = "条件获取出库点-分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@RequestBody Page page, @RequestParam(required = false) String deliveryAddressName) {
        return Result.success(swmsBasicDeliveryAddressInfoService.getAllByPage(page, deliveryAddressName));
    }

    @ApiOperation(value = "新增出库点", notes = "新增")
    @ApiImplicitParam(name = "swmsBasicDeliveryAddressInfo", value = "新增参数", required = true, dataType = "SwmsBasicDeliveryAddressInfo")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/add")
    public Result add(@Valid @RequestBody SwmsBasicDeliveryAddressInfo swmsBasicDeliveryAddressInfo) {
        return Result.success(swmsBasicDeliveryAddressInfoService.add(swmsBasicDeliveryAddressInfo));
    }

    @ApiOperation(value = "更新出库点信息", notes = "更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "swmsBasicDeliveryAddressInfo", value = "更新参数", required = true, dataType = "SwmsBasicDeliveryAddressInfo")})
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody SwmsBasicDeliveryAddressInfo swmsBasicDeliveryAddressInfo) {
        return Result.success(swmsBasicDeliveryAddressInfoService.update(swmsBasicDeliveryAddressInfo));
    }

    @ApiOperation(value = "删除出库点信息", notes = "根据id删除出库点信息")
    @ApiImplicitParam(name = "id", value = "物料子类型信息id", required = true, dataType = "long")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        log.debug("根据id删除出库点记录:{}", id);
        return Result.success(swmsBasicDeliveryAddressInfoService.delete(id));
    }

    @ApiOperation(value = "批量删除出库点信息",notes = "根据id集合删除")
    @DeleteMapping(value = "/batchDelete")
    public Result batchDelete(@RequestParam Set<String> ids){
        return Result.success(swmsBasicDeliveryAddressInfoService.batchDelete(ids));
    }
}
