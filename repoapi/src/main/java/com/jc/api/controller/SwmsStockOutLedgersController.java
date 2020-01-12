package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.service.restservice.ISwmsStockOutLedgersService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author XudongHu
 * @apiNote 出库台账controller
 * @className SwmsStockOutLedgersController
 * @modifier
 * @since 20.1.13日0:09
 */
@RestController
@Api(tags = "智能仓库-出库台账管理界面")
@RequestMapping(value = "/SwmsStockOutLedgers")
@Slf4j
public class SwmsStockOutLedgersController {
    @Autowired
    private ISwmsStockOutLedgersService iSwmsStockOutLedgersService;

    @ApiOperation(value = "获取所有出库台账", notes = "获取所有出库台账")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/getAll")
    public Result getAll(){
        return Result.success(iSwmsStockOutLedgersService.getAll(null));
    }

    @ApiOperation(value = "获取所有出库台账-物料编码模糊", notes = "物料编码获取所有出库台账")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/conditions")
    public Result query(@RequestParam(required = false) String materialCode){
        return Result.success(iSwmsStockOutLedgersService.getAll(materialCode));
    }

    @ApiOperation(value = "获取出库台账-物料编码/分页", notes = "条件获取出库台账记录-分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@RequestBody Page page, @RequestParam(required = false) String materialCode) {
        return Result.success(iSwmsStockOutLedgersService.getAllByPage(page,materialCode));
    }
}
