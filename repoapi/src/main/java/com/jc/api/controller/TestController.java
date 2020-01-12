package com.jc.api.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
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
    //入库模拟
    //出库模拟
}
