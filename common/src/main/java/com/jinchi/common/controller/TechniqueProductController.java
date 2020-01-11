package com.jinchi.common.controller;

import com.jinchi.common.domain.TestItemsPlus;
import com.jinchi.common.dto.Result;
import com.jinchi.common.service.TechniqueProductService;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
@Api(tags = "技术-成品")
public class TechniqueProductController {
    @Autowired
    TechniqueProductService techniqueProductService;

    @PostMapping
    @ApiOperation(value = "新增成品")
    public Result add(@RequestParam String productName){
        techniqueProductService.add(productName);
        return ResultUtil.success();
    }

    @GetMapping
    @ApiOperation(value = "根据成品物料id查询检测项目")
    public Result getItemsByProductId(@RequestParam Integer id){
        return ResultUtil.success(techniqueProductService.getItemsByProductId(id));
    }

    @PostMapping(value = "addStandard")
    @ApiOperation(value = "新增成品标准")
    public Result addProductStandard(@ApiParam(name = "productId",value = "产品id") @RequestParam Integer productId,
                                     @ApiParam(name = "classId",value = "型号id") @RequestParam Integer classId,
                                     @ApiParam(name = "createUser",value = "创建人") @RequestParam Integer createUser,
                                     @ApiParam(name = "items",value = "检测项目") @RequestBody List<TestItemsPlus> items,
                                     @ApiParam(name = "effTime",value = "时间") @RequestParam String effTime){
        return ResultUtil.success(techniqueProductService.addProductStandard(productId,classId,createUser,items,effTime));
    }

    @GetMapping(value = "detailByCommonBatchId")
    @ApiOperation(value = "根据批号查询标准")
    public Result detailByCommonBatchId(@RequestParam Integer commonBatchId){
        return ResultUtil.success(techniqueProductService.detailByCommonBatchId(commonBatchId));
    }

    @GetMapping(value = "getAllProductCommonBatch")
    @ApiOperation(value = "获取所有成品标准")
    public Result getAllProductCommonBatch(){
        return ResultUtil.success(techniqueProductService.getAllProductCommonBatch());
    }

    @GetMapping(value = "/getAllProduct")
    @ApiOperation(value = "查询所有成品")
    public Result getAllProduct(){
        return  ResultUtil.success(techniqueProductService.getAllProduct());
    }

    @GetMapping(value = "getAllStandardByPIdandCId")
    @ApiOperation(value = "分页查询该产品和型号下的标准")
    public Result getAllStandardByPIdandCId(@RequestParam Integer productId,
                                            @RequestParam Integer classId,
                                            @RequestParam(defaultValue = "",required = false) String userName,
                                            @RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size){
        return  ResultUtil.success(techniqueProductService.getAllStandardByPIdandCId(productId,classId,userName,page,size));
    }

    @PutMapping(value = "updateByCommonBatchId")
    @ApiOperation(value = "根据批号信息更新")
    public Result updateByCommonBatchId(@RequestParam Integer commonBatchId, @RequestParam String effTime, @RequestBody List<TestItemsPlus> items){
        return ResultUtil.success(techniqueProductService.updateByCommonBatchId(commonBatchId,effTime,items));
    }

    @GetMapping(value = "getItemsByProductStandardId")
    @ApiOperation(value = "根据成品标准ID查询检测项目")
    public Result getItemsByProductStandardId(@RequestParam Integer productStandardId) {
        return ResultUtil.success(techniqueProductService.getItemsByProductStandardId(productStandardId));
    }
}
