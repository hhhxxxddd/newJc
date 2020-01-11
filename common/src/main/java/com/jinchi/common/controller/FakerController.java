package com.jinchi.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.dto.Result;
import com.jinchi.common.feign.FeignRepoInService;
import com.jinchi.common.utils.NumberGenerator;
import com.jinchi.common.utils.ResultUtil;
import com.jinchi.common.utils.itemCode.DecoderV1;
import com.jinchi.common.utils.itemCode.MaterialCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XudongHu
 * @className FakerController
 * @apiNote TODO
 * @modifer
 * @since 2019/10/26 23:21
 */
@RestController
@RequestMapping(value = "/faker")
@Api(tags = "智能仓库-XX接口")
public class FakerController {
    private final Logger logger = LoggerFactory.getLogger(FakerController.class);
    @Autowired
    private FeignRepoInService feignRepoInService;

    @PostMapping
    @ApiOperation(value = "出库上报")
    public Result<Object> outPost(@RequestBody Map<String, Object> repoOut) throws UnsupportedEncodingException {
        String productionLine = (String) repoOut.get("productionLine");
        String createPersonName = (String) repoOut.get("createPersonName");
        String endPosition = (String) repoOut.get("endPosition");
        List<Map<String,Object>> data = (List<Map<String, Object>>) repoOut.get("data");


        HashMap<String, Object> bigMap = new HashMap<>();
        HashMap<String,Object> medMap = new HashMap<>();


        List<HashMap<String, String>> bigList = new ArrayList<>();
        String batchNumberGenerator = NumberGenerator.batchNumberGenerator(BatchTypeEnum.REPO_OUT_APPLY_RAW.typeCode());


        bigMap.put("PLAN_CODE", batchNumberGenerator);
        bigMap.put("PLAN_CREATER", createPersonName);
        bigMap.put("END_POSITION", endPosition);
        bigMap.put("PRODUCTION_LINE_NO", productionLine);
        bigMap.put("SEND_OUT_LIST", medMap);


        DecoderV1 decoderV1 = new DecoderV1();

        data.forEach(e -> {
            String groupName = (String)e.get("groupName");
            List<Map<String,String>> content = (List<Map<String, String>>) e.get("content");

            content.forEach(s -> {
                String serialNumber = s.get("serialNumber");
                MaterialCode.DecodedMaterialCode decode = decoderV1.decode(serialNumber);
                HashMap<String, String> smallMap = new HashMap<>();
                smallMap.put("WEIGHT", decode.getWeight());
                smallMap.put("GOODS_NAME", decode.getMaterialName());
                smallMap.put("GOODS_TYPE", decode.getMaterialType());
                smallMap.put("SUPPLIER", decode.getManufacturer());
                smallMap.put("GOODS_BATCH_NO", decode.getBatch());
                bigList.add(smallMap);
            });

            medMap.put("GROUP_NAME",groupName);
            medMap.put("CONTENT",bigList);

        });

        JSONObject jsonObject = new JSONObject(bigMap);
        logger.info(jsonObject.toJSONString());
        byte[] bytes = feignRepoInService.outGoods(jsonObject);
        return ResultUtil.success(jsonObject.toJSONString());
    }

}
