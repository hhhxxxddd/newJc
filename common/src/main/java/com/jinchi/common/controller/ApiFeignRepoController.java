package com.jinchi.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.repository.RepoOutHeadDTO;
import com.jinchi.common.mapper.BasicInfoAnodeProductionLineMapper;
import com.jinchi.common.mapper.BasicInfoPrecursorProductionLineMapper;
import com.jinchi.common.mapper.CommonBatchNumberMapper;
import com.jinchi.common.model.factorypattern.CommonBatchFactory;
import com.jinchi.common.service.*;
import com.jinchi.common.utils.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.jinchi.common.constant.BatchTypeEnum.FIRE_MAGE_OUT;

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
    @Autowired
    DeptManageService deptManageService;
    @Autowired
    BasicInfoAnodeProductionLineMapper lineMapper;
    @Autowired
    BasicInfoPrecursorProductionLineMapper sLineMapper;
    @Autowired
    CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    DataTaskRecordService dataTaskRecordService;
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

    @PostMapping(value = "/jc/deptName")
    public String deptName(@RequestParam Integer id){
        logger.info("Feign-common:查询部门名称");
        BasicInfoDept dept =  deptManageService.getDeptById(id);
        return dept==null?"未知部门":dept.getName();
    }

    @PostMapping(value = "/jc/fireLine")
    public String fireLine(@RequestParam Integer id){
        logger.info("Feign-common:查询火法产线");
        BasicInfoAnodeProductionLine line = lineMapper.selectByPrimaryKey(id);
        return line==null?"未知产线":line.getName();
    }

    @PostMapping(value = "/jc/line")
    public String line(@RequestParam Integer id){
        logger.info("Feign-common:查询湿法产线");
        BasicInfoPrecursorProductionLine line = sLineMapper.selectByPrimaryKey(id);
        return line==null?"未知产线":line.getName();
    }

    @PostMapping(value = "/jc/send2audit")
    public String send2audit(@RequestParam Integer personId,
                             @RequestParam Integer isUrgent,
                             @RequestParam Integer auditId){
        String batch = NumberGenerator.batchNumberGenerator(FIRE_MAGE_OUT.typeCode());
        CommonBatchNumber commonBatchNumber =
                CommonBatchFactory.initialize()
                        .setCreatePersonId(personId)
                        .setBatchNumber(batch)
                        .setIsUrgent(isUrgent)
                        .setDescription(FIRE_MAGE_OUT.description())
                        .setDataType(FIRE_MAGE_OUT.typeCode());
        commonBatchNumberMapper.insert(commonBatchNumber);
        //流程
        dataTaskRecordService.send2audit(commonBatchNumber.getId(),auditId,isUrgent);
        return commonBatchNumber.getBatchNumber() + "-" + commonBatchNumber.getId();
    }
}
