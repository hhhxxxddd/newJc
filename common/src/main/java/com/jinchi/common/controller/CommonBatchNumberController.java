package com.jinchi.common.controller;

import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.CommonNameDTO;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.DataTaskRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author：XudongHu
 * @className:CommonBatchNumberController
 * @description: 待办事项controller
 * @date:19:46 2018/12/2
 */

@RestController
@RequestMapping(value = "/toDoList")
@Api(tags = "质量流程-待办事项")
public class CommonBatchNumberController {
    @Autowired
    private DataTaskRecordService dataTaskRecordService;

    /**
     * 通用送审接口
     *
     * @param dataId 数据批号id
     * @param taskId 流程批号id
     * @return
     */
    @PostMapping(value = "/{taskId}")
    @ApiOperation(value = "送审-保存后")
    public Result<String> send2audit(@ApiParam(name = "dataId", value = "送审批号") @RequestParam Integer dataId,
                                     @ApiParam(name = "taskId", value = "流程批号") @PathVariable Integer taskId,
                                     @ApiParam(name = "isUrgent", value = "是否紧急,1紧急0不紧急") @RequestParam Integer isUrgent) {

        return ResultUtil.success(dataTaskRecordService.send2audit(dataId, taskId, isUrgent));
    }

    /**
     * 当前用户待办事项
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping(value = "/{userId}")
    @ApiOperation(value = "当前待办事项-用户")
    public Result<List<CommonBatchNumberDTO>> findToDoList(@ApiParam(name = "userId", value = "用户主键") @PathVariable Integer userId) {
        return ResultUtil.success(dataTaskRecordService.findToDoList(userId));
    }

    /**
     * 历史待办事项
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping(value = "/{userId}/history")
    @ApiOperation(value = "历史待办事项-用户")

    public Result<List<CommonBatchNumberDTO>> findFinToDoList(@ApiParam(name = "userId", value = "用户主键") @PathVariable Integer userId,
                                                              @ApiParam(name = "date", value = "日期") @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String date) {
        return ResultUtil.success(dataTaskRecordService.findFinToDoList(userId, date));
    }


    /**
     * 审核 流程数据
     *
     * @param dataId   数据批号id
     * @param userId   用户id
     * @param reply    反馈信息
     * @param isAccept 是否接受
     * @return
     */
    @PutMapping(value = "/{dataId}")
    @ApiOperation(value = "审核")
    public Result<String> audit(@ApiParam(value = "审核数据批号id", name = "dataId") @PathVariable Integer dataId,
                                @ApiParam(value = "用户id", name = "userId") @RequestParam Integer userId,
                                @ApiParam(value = "审核信息", name = "reply") @RequestParam String reply,
                                @ApiParam(value = "是否接受 0拒绝 1接受", name = "isAccept") @RequestParam Integer isAccept) {
        return ResultUtil.success(dataTaskRecordService.audit(dataId, userId, reply, isAccept));
    }

    /**
     * 查询数据的审核结果
     *
     * @param dataId
     * @return
     */
    @GetMapping(value = "/{dataId}/result")
    @ApiOperation(value = "流程审核结果")
    public Result<List<CommonNameDTO>> replies(@ApiParam(value = "数据id", name = "dataId") @PathVariable Integer dataId) {
        return ResultUtil.success(dataTaskRecordService.replies(dataId));
    }

}
