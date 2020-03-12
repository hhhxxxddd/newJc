package com.jc.api.controller;


import com.jc.api.service.restservice.IStockInRecordService;
import com.jc.api.service.restservice.IStockOutRecordHeadService;
import com.jc.api.service.restservice.ISwmsStockInJournalAccountService;
import com.jc.api.utils.XinSongHttpAnalyzeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:ApiController
 * @description: 外部调用接口
 * @date:23:27 2019/3/30
 * @Modifer:
 */
@RestController
@RequestMapping(value = "/jc")
@Api(tags = "仓库使用接口-仅供新松调用")
@Slf4j
public class ApiController {
    @Autowired
    private IStockInRecordService IStockInRecordService;
    @Autowired
    private IStockOutRecordHeadService iStockOutRecordHeadService;
    @Autowired
    private ISwmsStockInJournalAccountService iSwmsStockInJournalAccountService;

    private static Map<String,Object> returnMap = new HashMap<>();
    static {
        returnMap.put("code",1);
        returnMap.put("message","成功");
    }

    @PostMapping(value = "/outPost")
    @ApiOperation(value = "新松出库上报")
    public Object outPost(HttpServletRequest request) {
        XinSongHttpAnalyzeUtil.StockOutReceiver stockOutReceiver = XinSongHttpAnalyzeUtil.stockOutContentLoading(request);
        iStockOutRecordHeadService.outPost(stockOutReceiver.getPlanCode(), stockOutReceiver.getGoodsCode());
        log.info("出库上报接口调用完毕============================>");
        return returnMap;
    }

    @PostMapping(value = "/outFinished")
    @ApiOperation(value = "新松返回出库结果完成")
    public Object outResult(HttpServletRequest request) {
        XinSongHttpAnalyzeUtil.StockOutFinishedReceiver stockOutFinishedReceiver = XinSongHttpAnalyzeUtil.stockOutFinishedContentLoading(request);
        iStockOutRecordHeadService.outFinished(stockOutFinishedReceiver.getPlanCode());
        log.info("出库结果上报接口调用完毕============================>");
        return returnMap;
    }

    @PostMapping(value = "/inPost")
    @ApiOperation(value = "新松入库")
    public Object inApply(HttpServletRequest request) {
        XinSongHttpAnalyzeUtil.StockInReceiver stockInReceiver = XinSongHttpAnalyzeUtil.stockInContentLoading(request);
        iSwmsStockInJournalAccountService.insert(stockInReceiver.getMaterialCode(), stockInReceiver.getOperator());
        return returnMap;
    }

}
