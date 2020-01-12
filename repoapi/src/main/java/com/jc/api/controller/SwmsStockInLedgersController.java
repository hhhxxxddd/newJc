package com.jc.api.controller;

import com.jc.api.service.restservice.ISwmsStockInLedgersService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XudongHu
 * @apiNote 入库台账controller
 * @className SwmsStockInLedgersController
 * @modifier
 * @since 20.1.12日19:15
 */
@RestController
@Api(tags = "智能仓库-入库台账管理界面")
@RequestMapping(value = "/SwmsStockInLedgers")
@Slf4j
public class SwmsStockInLedgersController {
    @Autowired
    private ISwmsStockInLedgersService iSwmsStockInLedgersService;
}
