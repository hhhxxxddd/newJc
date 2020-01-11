package com.jinchi.app.controller;

import com.jinchi.app.dto.DeviceDTO;
import com.jinchi.app.dto.IdDto;
import com.jinchi.app.dto.Result;
import com.jinchi.app.utils.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "deviceBind")
public class DeviceBindController {

    @PostMapping(value = "getDept")
    public Result getDept() {
        return ResultUtil.success();
    }

    @PostMapping(value = "getDevicesByDeptId")
    public Result getDevicesByDeptId(@RequestBody IdDto idDto) {
        return ResultUtil.success();
    }

    @PostMapping(value = "bind")
    public Result bind(@RequestBody DeviceDTO deviceDTO) {
        return ResultUtil.success();
    }

}
