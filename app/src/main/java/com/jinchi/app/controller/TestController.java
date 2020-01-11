package com.jinchi.app.controller;

import com.jinchi.app.dto.Result;
import com.jinchi.app.mapper.DeviceRepairApplicationMapper;
import com.jinchi.app.service.DeviceRepairReportService;
import com.jinchi.app.utils.ResultUtil;
import com.jinchi.app.utils.UnifyTransform;
import com.jinchi.app.utils.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-17 09:27
 * @description:
 **/
@RestController
@RequestMapping(value = "test")
@Api(tags = "测试")
public class TestController {

    @Autowired
    DeviceRepairReportService deviceRepairReportService;
    @Autowired
    DeviceRepairApplicationMapper repairApplicationMapper;

    @PostMapping(value = "upload")
    @ApiOperation(value = "测试上传")
    public void upload(@RequestBody MultipartFile file) throws IOException {
        String fileName = UploadUtil.uploadPic("D:\\upload\\",file,5);
        System.out.println("照片上传:" + fileName);
    }

    @PostMapping(value = "page")
    @ApiOperation(value = "查询")
    public Result getByPage() {
        String date = new UnifyTransform().getFirstDayOfCurrentMonth();
        System.out.println(date);
        String sql1 = "select COUNT(*) from device_repair_application as r,basic_info_dept as d where (r.repair_status = 3 or r.repair_status = 4) and r.dept_code=d.`code` and (d.`code` = 2 or d.`code` = 4)and finish_time > '2019-09-01'";
        int num1 = repairApplicationMapper.countByCondition(sql1);
        int id = 2;
        String sql2 = "select COUNT(*) from device_repair_application as r,basic_info_dept as d where (r.repair_status = 3 or r.repair_status = 4) and r.dept_code=d.`code` and d.`code` = '" + id + "' and finish_time >" + " '" + date + "'";
        int num2 = repairApplicationMapper.countByCondition(sql2);
        return ResultUtil.success(num1 + " " + num2);
    }
}
