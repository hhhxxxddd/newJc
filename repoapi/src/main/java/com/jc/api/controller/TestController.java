package com.jc.api.controller;

import com.jc.api.service.restservice.ISwmsStockInJournalAccountService;
import com.jinchi.common.core.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XudongHu
 * @apiNote 测试接口
 * @className TestController
 * @modifier
 * @since 20.1.12日15:26
 */
@RestController
@Api(tags = "出入库模拟接口")
@RequestMapping(value = "/test")
@Slf4j
public class TestController {
    @Autowired
    private ISwmsStockInJournalAccountService iSwmsStockInJournalAccountService;

    //入库模拟
    @ApiOperation(value = "模拟新松入库", notes = "测试及加数据使用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "materialCode", value = "物料编码", example = "MC/BN180808-0-RAW-Fe-1-QDBX-60KG", required = true, dataType = "String"),
            @ApiImplicitParam(name = "createdPerson", value = "入库人", example = "胡旭东", required = true, dataType = "String")}
    )
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/in")
    public Boolean inApply(@RequestParam String materialCode, @RequestParam String createdPerson) {
       return iSwmsStockInJournalAccountService.insert(materialCode, createdPerson);
    }

    //出库模拟
}
