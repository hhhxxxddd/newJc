package com.jinchi.common.controller;

import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.repository.RepoDiffRecordDTO;
import com.jinchi.common.dto.repository.RepoStockDTO;
import com.jinchi.common.service.RepoDiffRecordService;
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

/**
 * Created by WangZhihao on 2018/12/17.
 */
@RestController
@RequestMapping(value = "/RepoDiffRecord")
@Api(tags = "智能仓库-盘库记录")
public class RepoDiffRecordController {
    @Autowired
    RepoDiffRecordService repoDiffRecordService;


    @GetMapping(value = "/stock")
    @ApiOperation(value = "盘库页面")
    public Result<List<RepoStockDTO>> stock(@ApiParam(name = "materialClass", value = "物料类型") @RequestParam(name = "materialClass", defaultValue = "1") Integer materialClass,
                                            @ApiParam(name = "materialName", value = "物料名称") @RequestParam(name = "materialName", required = false) String materialName) {
        return ResultUtil.success(repoDiffRecordService.stockByMaterialClassAndName(materialClass, materialName));
    }

    /**
     * 查询所有
     *
     * @param materialClass
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询所有")
    public Result<List<RepoDiffRecordDTO>> findAll(@ApiParam(name = "materialClass", value = "物料类型") @RequestParam(name = "materialClass", defaultValue = "1") Integer materialClass) {
        return ResultUtil.success(repoDiffRecordService.byMaterialClass(materialClass));
    }

    /**
     * @param materialClass
     * @param materialName
     * @return
     */
    @GetMapping(value = "/getByMaterialNameLike")
    @ApiOperation(value = "根据产品名称模糊查询")
    public Result<List<RepoDiffRecordDTO>> findAllByMaterialClassByName(@ApiParam(name = "materialClass", value = "物料类型") @RequestParam(name = "materialClass", defaultValue = "1") Integer materialClass,
                                                                        @ApiParam(name = "materialName", value = "物料名称") @RequestParam(name = "materialName", required = false) String materialName) {
        return ResultUtil.success(repoDiffRecordService.byMaterialClassByNameLike(materialClass, materialName));
    }

    @GetMapping(value = "/pages")
    @ApiOperation(value = "根据产品名称模糊分页查询")
    public Result<PageBean> findAllByPageByMaterialClassByName(@ApiParam(name = "materialClass", value = "物料类型") @RequestParam(name = "materialClass", defaultValue = "1") Integer materialClass,
                                                               @ApiParam(name = "materialName", value = "物料名称") @RequestParam(name = "materialName", required = false) String materialName,
                                                               PageBean pageBean) {
        return ResultUtil.success(repoDiffRecordService.byPageByMaterialNameByNameLike(materialClass, materialName, pageBean));
    }

}
