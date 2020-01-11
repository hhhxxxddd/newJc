package com.jinchi.common.controller;

import com.jinchi.common.dto.MaterialCodeDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.repository.RepoOutHeadDTO;
import com.jinchi.common.service.RepoInRecordService;
import com.jinchi.common.service.RepoOutApplyService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：XudongHu
 * @className:ExternalRepoController
 * @description: 用于给仓库人员使用的外部接口
 * @date:10:59 2019/2/28
 * @Modifer:
 */
@RestController
@RequestMapping(value = "/externalRepo")
@Api(tags = "智能仓库-外部接口")
public class ExternalRepoController {
    @Autowired
    private RepoInRecordService repoInRecordService;
    @Autowired
    private RepoOutApplyService repoOutApplyService;

    private AtomicInteger bagNumber = new AtomicInteger(0);    // todo
    private interface Encoder {
        String encode(MaterialCodeDTO dto);
    }


    /**
     * 出库上报
     * 每次成功出库一袋调用 修改仓库的值
     * @param request
     * @return
     */
    @PostMapping(value = "/outPost")
    @ApiOperation(value = "出库上报")
    public Result<Object> outPost(HttpServletRequest request) {
        request.getParameterMap().entrySet().stream().forEach(e ->
        {
            System.out.println(e.getKey());
            System.out.println(e.getValue());
            System.out.println(Arrays.toString(e.getValue()));
        });
        return ResultUtil.success(1);
    }


    /**
     * 出库结果完成
     * @param request
     * @return
     */
    @PostMapping(value = "/outFinished")
    @ApiOperation(value = "出库结果")
    public Result<String> outResult(HttpServletRequest request) {
        //key:PlanCode,values: [[单号1]]
        //key:GoodsName,values: [[主原材料]]
        //key:GoodsWeight, values: [[0]]
        request.getParameterMap().entrySet().stream().forEach(e -> {
            System.out.println(e.getKey());
            System.out.println(e.getValue());
            System.out.println(Arrays.toString(e.getValue()));
        });
        return ResultUtil.success(1);
    }

    //入库

    /**
     * 入库
     * @param
     * @return
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "入库申请")
    public Result<RepoOutHeadDTO> inApply(/*@ApiParam(value = "入库记录DTO") @Valid RepoInRecordDTO repoInRecordDTO*/HttpServletRequest request) {
        //Manage_End_Time -> [2019-03-15 16:44:07]
        //Operator -> [超级管理员]
        //Goods_Code -> [MC/BN180808-WS001-RAW-Fe-0001-QDBX-60kg]
        //[主原材料(包装)/(未包装)]-[成品]-[辅助材料]-[机体,可能是半成品]
        //仓库通过 类别和名字来确认出库
        request.getParameterMap().entrySet().stream().forEach(e -> {
            System.out.println(e.getKey());
            System.out.println(e.getValue());
            System.out.println(Arrays.toString(e.getValue()));
        });
        return ResultUtil.success(1);
    }


    /**
     * 物料编码生成
     * @param request
     * @return
     */
    @GetMapping
    @ApiOperation(value = "物料编码生成")
    public Result<String> itemCodeGenerate(HttpServletRequest request) {
        request.getParameterMap().entrySet().stream().forEach(e -> {
            System.out.println(e.getKey());
            System.out.println(e.getValue());
            System.out.println(Arrays.toString(e.getValue()));
        });
        return ResultUtil.success(1);
    }

}
