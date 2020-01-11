package com.jinchi.app.controller;

import com.jinchi.app.dto.*;
import com.jinchi.app.service.DeviceSpotcheckService;
import com.jinchi.app.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-02 12:45
 * @description:
 **/

@RestController
@RequestMapping(value = "/deviceSpotcheck")
@Api(tags = "设备点检模块-app")
public class DeviceSpotcheckController {

    @Autowired
    DeviceSpotcheckService deviceSpotcheckService;

    @PostMapping(value = "plans")
    @ApiOperation(value = "获取当前用户所属设备部门或者车间的全部点检计划")
    public Result getSpotcheckPlans(@RequestBody DeviceSpotcheckPlansPostDTO dto) {
        return ResultUtil.success(deviceSpotcheckService.getSpotCheckPlans(dto));
    }

    @PostMapping(value = "plansV1")
    @ApiOperation(value = "点检任务V1")
    public Result getSpotcheckPlansV1(@RequestBody DeviceSpotcheckPlansPostDTO dto) {
        return ResultUtil.success(deviceSpotcheckService.getSpotCheckPlansV1(dto));
    }

    @PostMapping(value = "generateRecord")
    @ApiOperation(value = "马上点检 返回点检基础数据和点检项目列表")
    public Result generateRecord(@RequestBody SpotcheckRecordDTO dto) {
        DeviceSpotcheckRecordDetailDTO detailDTO = deviceSpotcheckService.generateRecord(dto);
        if (detailDTO != null) {
            return ResultUtil.success(detailDTO);
        } else {
            return ResultUtil.error("操作失败");
        }

    }

    @PostMapping(value = "today")
    @ApiOperation(value = "查看今日点检记录")
    public Result spotcheckRecordToday(@RequestBody DeviceSpotcheckRecordHistoryPostDTO dto) {
        return ResultUtil.success(deviceSpotcheckService.today(dto));
    }

    @PostMapping(value = "history")
    @ApiOperation(value = "查看历史点检记录")
    public Result spotcheckRecordHistory(@RequestBody DeviceSpotcheckRecordHistoryPostDTO dto) {
        return ResultUtil.success(deviceSpotcheckService.history(dto));
    }

    @PostMapping(value = "process")
    @ApiOperation(value = "查找工序")
    public Result getProcess(@RequestBody IdDto dto) {
        return ResultUtil.success(deviceSpotcheckService.getProcessById(dto));
    }

    @PostMapping(value = "deviceStatus")
    @ApiOperation(value = "获取所有设备状态")
    public Result getDeviceStatus() {
        return ResultUtil.success(deviceSpotcheckService.getDeviceStatus());
    }

    @PostMapping(value = "historyV1")
    @ApiOperation(value = "查看历史点检记录V1")
    public Result spotcheckRecordHistoryV1(@RequestBody DeviceSpotcheckRecordHistoryPostDTO dto) {
        return ResultUtil.success(deviceSpotcheckService.historyV1(dto));
    }

    @PostMapping(value = "detail")
    @ApiOperation(value = "查看点检详情")
    public Result spotcheckRecordDetail(@RequestBody IdDto idDto) {
        return ResultUtil.success(deviceSpotcheckService.detail(idDto));
    }

    @PostMapping(value = "updateByFlag")
    @ApiOperation(value = "暂存或提交")
    public Result spotcheckRecordUpdate(@RequestBody DeviceSpotcheckUpdateDTO dto) {
        if (deviceSpotcheckService.updateByFlag(dto) != 0) {
            return ResultUtil.error("操作失败");
        } else {
            return ResultUtil.success(new IdDto());
        }
    }

    @PostMapping(value = "getDataByIdCard")
    @ApiOperation(value = "nfc打卡")
    public Result getDataByIdCard(@RequestBody SpotcheckNFCDTO dto) {
        SpotcheckNFCResultDTO resultDTO = deviceSpotcheckService.getDataByNfc(dto);
        if (resultDTO == null) {
            return ResultUtil.error("操作失败");
        } else {
            return ResultUtil.success(resultDTO);
        }
    }

    @PostMapping(value = "page")
    @ApiOperation(value = "点检主管查询")
    public Result spotcheckRecordForManager(@RequestBody DeviceSpotcheckRecordHistoryPostDTO dto) {
        return ResultUtil.success(deviceSpotcheckService.page(dto));
    }

    @PostMapping(value = "pageNew")
    @ApiOperation(value = "点检主管查询  今日记录statusId=0 异常记录statusId=1 历史记录statusId=2")
    public Result pageAbnormal(@RequestBody DeviceSpotcheckRecordHistoryPostDTO dto) {
        return ResultUtil.success(deviceSpotcheckService.pageAbnormal(dto));
    }

    @PostMapping(value = "confirm")
    @ApiOperation(value = "点检主管确认")
    public Result spotcheckManagerConfirm(@RequestBody IdDto idDto) {
        if (deviceSpotcheckService.managerConfirm(idDto) == 1) {
            return ResultUtil.success(new IdDto());
        } else {
            return ResultUtil.error("操作失败！");
        }
    }
}
