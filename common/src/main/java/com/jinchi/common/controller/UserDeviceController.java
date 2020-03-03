package com.jinchi.common.controller;

import com.jinchi.common.domain.BasicInfoDept;
import com.jinchi.common.domain.BasicInfoUserDeviceDeptMap;
import com.jinchi.common.domain.BasicInfoUserDeviceDeptMapExample;
import com.jinchi.common.dto.Result;
import com.jinchi.common.mapper.BasicInfoUserDeviceDeptMapMapper;
import com.jinchi.common.service.DeptManageService;
import com.jinchi.common.utils.ResultUtil;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("userDevice")
@Api(tags = "用户设备部门")
public class UserDeviceController {

    @Autowired
    BasicInfoUserDeviceDeptMapMapper userDeviceDeptMapMapper;
    @Autowired
    DeptManageService deptManageService;

    @PostMapping(value = "getDeviceDept")
    public Result deptInfo(@RequestParam Integer userId){
        BasicInfoUserDeviceDeptMapExample example = new BasicInfoUserDeviceDeptMapExample();
        example.createCriteria().andAuthCodeEqualTo(userId);
        List<BasicInfoUserDeviceDeptMap> user = userDeviceDeptMapMapper.selectByExample(example);
        if(user.size() == 0)
            return ResultUtil.error("no device dept asigned!");
        return ResultUtil.success(deptManageService.getDeptById(user.get(0).getDeptCode()));
    }
}
