package com.jc.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.service.restservice.ISwmsStockInJournalAccountService;
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
 * @apiNote 入库流水接口
 * @className SwmsStockInJournalAccountController
 * @modifier
 * @since 20.1.12日19:13
 */
@RestController
@Api(tags = "智能仓库-入库流水管理界面")
@RequestMapping(value = "/SwmsStockInJournalAccount")
@Slf4j
public class SwmsStockInJournalAccountController {
    @Autowired
    private ISwmsStockInJournalAccountService iSwmsStockInJournalAccountService;

    @ApiOperation(value = "获取所有入库流水", notes = "获取所有入库流水")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping(value = "/getAll")
    public Result getAll(){
        return Result.success(iSwmsStockInJournalAccountService.getAll(null));
    }

    @ApiOperation(value = "获取所有入库流水-名称模糊", notes = "名称模糊获取所有入库流水")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/conditions")
    public Result query(@RequestParam(required = false) String materialCode){
        return Result.success(iSwmsStockInJournalAccountService.getAll(materialCode));
    }

    @ApiOperation(value = "获取入库流水-条件/分页", notes = "条件获取入库流水记录-分页")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/pages")
    public Result queryPages(@RequestBody Page page, @RequestParam(required = false) String materialCode) {
        return Result.success(iSwmsStockInJournalAccountService.getAllByPage(page,materialCode));
    }

}
