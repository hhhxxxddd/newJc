package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.service.MiddleProductionDetectionService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:ExternalController
 * @description: 外部接口
 * @date:15:46 2019/3/18
 * @Modifer:
 */
@RestController
@RequestMapping(value = "/external")
@Api(tags = "外部接口")
public class ExternalController {
    @Autowired
    private MiddleProductionDetectionService middleProductionDetectionService;

    @ApiOperation(value = "送检人查看送检清单")
    @GetMapping
    public Result<List<Map<Object,Object>>> byIdCard(@ApiParam(name = "idCard",value = "id卡") @RequestParam String idCard){
        return ResultUtil.success(middleProductionDetectionService.delivererTask(idCard));
    }
}
