package com.jinchi.app.controller;

import com.jinchi.app.dto.*;
import com.jinchi.app.service.DeviceRepairReportService;
import com.jinchi.app.service.UserDeviceService;
import com.jinchi.app.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/repairReport")
@Api(tags = "设备维修（保养人）-app")
public class DeviceRepairReportController {
    @Autowired
    DeviceRepairReportService deviceRepairReportService;
    @Autowired
    UserDeviceService userDeviceService;

    @PostMapping(value = "page")
    @ApiOperation(value = "查询")
    public Result getByPage(@RequestBody @Valid RepairPostDTO repairPostDTO){
        RepairPageDataDTO ans = deviceRepairReportService.page(repairPostDTO);
        if(ans != null) {
            return ResultUtil.success(ans);
        }
        return ResultUtil.success(new ArrayList<>());
    }

    @PostMapping(value = "detail")
    @ApiOperation(value = "详情")
    public Result detail(@RequestBody IdDto id){
        return ResultUtil.success(deviceRepairReportService.detail(id.getId(), id.getUserId()));
    }

    @PostMapping(value = "confirmEvaluation")
    @ApiOperation(value = "评价")
    public Result confirmEvaluation(@RequestBody @Valid DeviceRepairEvaluationDTO deviceRepairEvaluationDTO){
        return ResultUtil.success(deviceRepairReportService.comfirmEvalution(deviceRepairEvaluationDTO));
    }

    @PostMapping(value = "evaluation")
    @ApiOperation(value = "评价")
    public Result evaluation(){
        return ResultUtil.success(deviceRepairReportService.evaluation());
    }

    @PostMapping(value = "delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestBody IdDto id){
        deviceRepairReportService.delete(id.getId());
        return ResultUtil.success(new IdDto());
    }

    @PostMapping(value = "cancel")
    @ApiOperation(value = "撤单")
    public Result cancel(@RequestBody IdDto id){
        deviceRepairReportService.rescind(id.getId());
        return ResultUtil.success(new IdDto());
    }

    @PostMapping(value = "commit")
    @ApiOperation(value = "提交/暂存")
    public Result commit(@RequestBody @Valid DeviceRepairApplyDTO deviceRepairApplyDTO){
        deviceRepairReportService.commit(deviceRepairApplyDTO);
        return ResultUtil.success(new IdDto() );
    }

    @PostMapping(value = "getDevicesByPage")
    @ApiOperation(value = "选择设备")
    public Result getDevices(@RequestBody RepairPostDTO repairPostDTO){
        List ans = deviceRepairReportService.getDeviceByDeptId(repairPostDTO);
        if(ans.size()!=0) {
            return ResultUtil.success(ans);
        }
        return  ResultUtil.success(new ArrayList<>());
    }

    @PostMapping(value = "getDeptCata")
    @ApiOperation(value = "部门树")
    public Result getDeptCata(@RequestBody IdDto userId){
        List ans = deviceRepairReportService.getDeptCata(userId.getId().intValue());
        if(ans.size()!=0) {
            return ResultUtil.success(ans);
        }
        return ResultUtil.success(new ArrayList<>());
    }

    @PostMapping(value = "getQuickPhase")
    @ApiOperation(value = "选择快捷短语")
    public Result getQuickPhase(){
        return ResultUtil.success(deviceRepairReportService.getQuickPhase());
    }

    @PostMapping(value = "getDeviceByIdCard")
    @ApiOperation(value = "根据idcode获取一个设备")
    public Result getDeviceByIdCard(@RequestBody RepairPostDTO repairPostDTO){
        DeviceDTO deviceDTO = deviceRepairReportService.getDeviceByIdCard(repairPostDTO);
        if(deviceDTO!=null) {
            return ResultUtil.success(deviceDTO);
        }
        return  ResultUtil.error("不存在该设备，或该设备不属于您所属部门");

    }
}
//http://125.94.71.249/jc/app/repairReport/commit