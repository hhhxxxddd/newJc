package com.jinchi.common.controller;

import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.repository.RealStockDTO;
import com.jinchi.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sortout")
@Api(tags = "智能仓库-盘库测试接口")
public class SortingOutStockController {

    /**
     * 模拟仓库接口
     * @param batch
     * @param materialName
     * @return
     */
    @GetMapping(value = "/realStock")
    @ApiOperation(value = "根据批次查找真实库存",notes = "根据批次查找真实库存")
    public Result<RealStockDTO> realStock(String batch, String materialName) {
        RealStockDTO realStockDTO = new RealStockDTO();
        realStockDTO.setBatch(batch);
        realStockDTO.setMaterialName(materialName);
        realStockDTO.setRealWeight(90);
        return ResultUtil.success(realStockDTO);
    }
}
