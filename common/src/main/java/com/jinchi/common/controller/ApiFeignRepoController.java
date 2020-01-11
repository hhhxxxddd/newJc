package com.jinchi.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinchi.common.domain.ProductLine;
import com.jinchi.common.domain.RepoBaseEndPosition;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.repository.RepoOutHeadDTO;
import com.jinchi.common.service.AuthUserService;
import com.jinchi.common.service.ProductLineService;
import com.jinchi.common.service.RepoBaseEndPositionService;
import com.jinchi.common.service.RepoOutApplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author XudongHu
 * @className ApiFeignRepoController
 * @apiNote Feign调用的接口
 * @modifer
 * @since 2019/10/17 15:21
 */
@RestController
public class ApiFeignRepoController {
    @Autowired
    private RepoOutApplyService repoOutApplyService;
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private ProductLineService productLineService;
    @Autowired
    private RepoBaseEndPositionService repoBaseEndPositionService;
    private Logger logger = LoggerFactory.getLogger(ApiFeignRepoController.class);

    /**
     * json转为实体
     */
    static class JsonToPo {
        static RepoOutHeadDTO repoOutJsonToPo(JSONObject jsonObject) {
            RepoOutHeadDTO outHeadDTO = new RepoOutHeadDTO();
            Integer applicationFormId = jsonObject.getInteger("applicationFormId");
            Integer createdPersonId = jsonObject.getInteger("createdPersonId");
            Integer endPositionId = jsonObject.getInteger("endPositionId");
            Integer productionLineId = jsonObject.getInteger("productionLineId");
            Date createTime = jsonObject.getDate("createTime");
            outHeadDTO
                    .setApplicationFormId(applicationFormId)
                    .setCreatedPersonId(createdPersonId)
                    .setCreatedTime(createTime)
                    .setEndPositionId(endPositionId)
                    .setProductionLineId(productionLineId);
            return outHeadDTO;
        }


    }


    @PostMapping(value = "/jc/outAudit")
    public String outAudit(@RequestBody JSONObject jsonData) {
        logger.info("Feign-common:接收到出库请求");
        RepoOutHeadDTO repoOutHeadDTO = JsonToPo.repoOutJsonToPo(jsonData);
        logger.info(repoOutHeadDTO.toString());
        return repoOutApplyService.sendToOut(repoOutHeadDTO);
    }

    @PostMapping(value = "/jc/personName")
    public String personName(@RequestParam Integer id){
        logger.info("Feign-common:接收到人员查询请求");
        AuthUserDTO byId = authUserService.findById(id);
        return byId==null?"未知名称":byId.getName();
    }

    @PostMapping(value = "/jc/productionLine")
    public String productionLine(@RequestParam Integer id){
        logger.info("Feign-common:接收到生产线查询请求");
        ProductLine byId = productLineService.getById(id);
        return byId==null?"未知名称":byId.getName();
    }

    @PostMapping(value = "/jc/endPosition")
    public String endPosition(@RequestParam Integer id){
        logger.info("Feign-common:接收到出库点查询请求");
        RepoBaseEndPosition byId = repoBaseEndPositionService.getById(id);
        return byId==null?"未知名称":byId.getEndPosition();
    }

}
