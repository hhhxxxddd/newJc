package com.jinchi.auth.controller;

import com.jinchi.auth.domain.Result;
import com.jinchi.auth.service.CardService;
import com.jinchi.auth.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:ExternalController
 * @description:
 * @date:23:13 2019/3/18
 * @Modifer:
 */
@RestController
@RequestMapping(value = "/external")
@Api(tags = "外部接口")
public class ExternalController {
    @Autowired
    private CardService cardService;

    @ApiOperation(value = "刷卡登陆")
    @GetMapping(value = "/cardVerify")
    public Result<Map<Object, Object>> tokenByCard(@ApiParam(name = "cardNumber", value = "id卡值") @RequestParam String cardNumber, HttpServletResponse response) {
        Map<Object, Object> map = cardService.tokenByCard(cardNumber);
        response.addHeader("Authorization", map.getOrDefault("token", "unauthorized").toString());
        response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        map.remove("token");
        return ResultUtil.success(map);
    }
}
