package com.jinchi.app.controller;

import com.jinchi.app.dto.*;
import com.jinchi.app.service.ChemicalService;
import com.jinchi.app.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/chemical")
@Api(tags = "化验统计-app")
public class ChemicalController {

    @Autowired
    ChemicalService chemicalService;

    @PostMapping(value = "page")
    @ApiOperation(value = "分页模糊查询")
    public Result page(@RequestBody @Valid RepairPostDTO repairPostDTO){
        repairPostDTO.setPage(repairPostDTO.getPage()==null?1:repairPostDTO.getPage());
        repairPostDTO.setSize(repairPostDTO.getSize()==null?5:repairPostDTO.getSize());
        return ResultUtil.success(chemicalService.page(repairPostDTO.getId().intValue(),repairPostDTO.getCondition(),repairPostDTO.getPage(),repairPostDTO.getSize()));
    }

    @PostMapping(value = "detail")
    @ApiOperation(value = "详情")
    public Result detail(@RequestBody @Valid IdDto idDto){
        return  ResultUtil.success(chemicalService.detail(idDto.getId().intValue()));
    }
}
