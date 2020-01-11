package com.jinchi.common.controller;

import com.github.pagehelper.PageInfo;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.RepoInRecordService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:RepoInRecordController
 * @description:
 * @date:15:23 2018/11/29
 */
@RestController
@RequestMapping(value = "/repoInRecord")
@Api(tags = "智能仓库-入库管理")
public class RepoInRecordController {
    @Autowired
    private RepoInRecordService repoInRecordService;

    /**
     * 根据名称和类型查询所有
     */
    @GetMapping
    @ApiOperation(value = "查询所有", notes = "根据名称和类型,都可为空,类型1原材料3成品")
    public Result<List<Map<Object,Object>>> findAllByNameAndType(@ApiParam(name = "materialName", value = "材料名称") @RequestParam(required = false) String materialName,
                                                                 @ApiParam(name = "materialType", value = "材料类型") @RequestParam(required = false) Integer materialType) {
        return ResultUtil.success(repoInRecordService.byNameLikeAndType(materialName, materialType));
    }

    /**
     * 根据名称和类型查询所有-分页
     */
    @GetMapping(value = "/pages")
    @ApiOperation(value = "查询所有-分页", notes = "根据名称和类型,都可为空,类型1原材料3产品")
    public Result<PageBean> findAllByNameAndTypeByPage(@ApiParam(name = "materialName", value = "材料名称") @RequestParam(required = false) String materialName,
                                                       @ApiParam(name = "materialType", value = "材料类型") @RequestParam(required = false) Integer materialType,
                                                       @ApiParam(name = "page", value = "页码") @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                       @ApiParam(name = "size", value = "条目数") @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                       @ApiParam(name = "orderField", value = "排序属性") @RequestParam(name = "orderField", defaultValue = "id") String orderField,
                                                       @ApiParam(name = "orderType", value = "排序方法,只能是asc升序或者desc降序") @RequestParam(name = "orderType", defaultValue = "desc") String orderType) {
        return ResultUtil.success(repoInRecordService.byNameLikeAndTypeByPage(materialName, materialType, page, size, orderField, orderType));
    }

}
