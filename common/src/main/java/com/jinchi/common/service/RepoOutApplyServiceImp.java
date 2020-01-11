package com.jinchi.common.service;

import com.alibaba.fastjson.JSONObject;
import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.repository.RepoOutHeadDTO;
import com.jinchi.common.dto.repository.RepoOutReceiveMessageDTO;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.NumberGenerator;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:RepoOutApplyServiceImp
 * @description:
 * @date:12:36 2018/12/2
 */
@Service
public class RepoOutApplyServiceImp implements RepoOutApplyService {
    @Autowired
    private RepoOutApplyMapper repoOutApplyMapper;
    @Autowired
    private RepoStockMapper repoStockMapper;
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    private DataTaskRecordMapper dataTaskRecordMapper;
    @Autowired
    private RepoBaseEndPositionMapper repoBaseEndPositionMapper;
    @Autowired
    private ProductLineService productLineService;

    /**
     * 新增出库申请
     *
     * @return
     * @paramRepoOutApplyDTO 新增出库申请
     * @see com.jinchi.common.domain.CommonBatchNumber
     * @see com.jinchi.common.domain.RepoBaseSerialNumber
     */
    @Override
    @Transactional
    public String sendToOut(RepoOutHeadDTO outHead) {
        CommonBatchNumber commonBatchNumber = new CommonBatchNumber();
        //申请人
        AuthUserDTO authUserDTO = authUserMapper.byId(outHead.getCreatedPersonId());
        //产线
        ProductLine productLine = productLineService.getById(outHead.getProductionLineId());
        //位置
        RepoBaseEndPosition endPosition = repoBaseEndPositionMapper.findById(outHead.getEndPositionId());
        Assert.notNull(authUserDTO, "申请人不存在:" + outHead.getCreatedPersonId());
        Assert.notNull(productLine, "不存在的产线" + outHead.getProductionLineId());
        Assert.notNull(endPosition, "不存在的出库点" + outHead.getEndPositionId());
        //设置时间 和批号
        commonBatchNumber
                .setCreatePersonId(outHead.getCreatedPersonId())
                .setDataType(BatchTypeEnum.REPO_OUT_APPLY_RAW.typeCode())
                .setIsPublished(0)
                .setBatchNumber(NumberGenerator.batchNumberGenerator(BatchTypeEnum.REPO_OUT_APPLY_RAW.typeCode()))
                .setStatus(BatchStatusEnum.SAVE.status())
                .setCreateTime(new Date())
                .setBatchNumber(NumberGenerator.batchNumberGenerator(BatchTypeEnum.REPO_OUT_APPLY_RAW.typeCode()))
                .setDescription("新出库批号");
        //储存批号
        commonBatchNumberMapper.insert(commonBatchNumber);
        outHead
                //单号{批号}
                .setStockOutRecordHeadCode(commonBatchNumber.getBatchNumber())
                //审核id
                .setApplicationFormId(commonBatchNumber.getId())
                //出库点
                .setEndPosition(endPosition.getEndPosition())
                //创建人
                .setCreatedPerson(authUserDTO.getName())
                //产线
                .setProductionLine(productLine.getName());
        String outHeadJson = JSONObject.toJSONString(outHead);
        return outHeadJson;
    }

    /**
     * 根据批号id查询
     *
     * @param batchNumberId
     * @return
     */
    @Override
    @Deprecated
    public CommonBatchNumberDTO findByBatchNumberId(Integer batchNumberId) {
        CommonBatchNumberDTO commonBatchNumberDTO = commonBatchNumberMapper.createDTOByIdAndDataType(batchNumberId, null);

        if (commonBatchNumberDTO == null) return null;

        List<RepoOutApply> repoOutApplies = repoOutApplyMapper.findAllByBatchNumberId(batchNumberId);

        // fixMe:废弃,没有作用
        return commonBatchNumberDTO;
    }

    /**
     * 根据批号id删除
     *
     * @param batchNumberId 批号id
     */
    @Override
    @Transactional
    public void deleteByBatchNumberId(Integer batchNumberId) {
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(batchNumberId);

        Assert.isTrue(null != commonBatchNumber
                && (commonBatchNumber.getDataType().equals(BatchTypeEnum.REPO_OUT_APPLY_END.typeCode())
                || commonBatchNumber.getDataType().equals(BatchTypeEnum.REPO_OUT_APPLY_RAW.typeCode())), "删除失败,找不到该出库记录");


        Assert.isNull(dataTaskRecordMapper.findByDataBatchNumberId(batchNumberId), "已提交的数据禁止删除");

        repoOutApplyMapper.deleteByBatchNumberId(batchNumberId);

        commonBatchNumberMapper.deleteById(batchNumberId);
    }

    /**
     * 根据ids删除
     *
     * @param
     */
    @Override
    @Transactional
    public void deleteByBatchNumberIds(List<Integer> batchNumberIds) {

        //已提交数据禁止删除
        List<DataTaskRecord> dataTaskRecords = dataTaskRecordMapper.findByDataBatchNumberIds(batchNumberIds);

        Assert.isTrue(null == dataTaskRecords || 0 == dataTaskRecords.size(), "已提交数据禁止删除");


        for (int i = 0; i < batchNumberIds.size(); i++) {

            Integer id = batchNumberIds.get(i);

            CommonBatchNumber byId = commonBatchNumberMapper.byId(id);

            Assert.isTrue(null != byId && (byId.getDataType().equals(BatchTypeEnum.REPO_OUT_APPLY_RAW.typeCode())
                    || byId.getDataType().equals(BatchTypeEnum.REPO_OUT_APPLY_END.typeCode())), "删除失败,出库记录不存在");

        }

        //删除出库记录
        repoOutApplyMapper.deleteByBatchNumberIds(batchNumberIds);


        //将批号信息删除
        commonBatchNumberMapper.deleteByIds(batchNumberIds);


    }


    /**
     * 根据申请人名称模糊和类型查询所有出库信息
     */
    @Override
    public List<CommonBatchNumberDTO> findAllByNameLikeAndType(String personName, Integer materialType) {
        List<CommonBatchNumberDTO> commonBatchNumberDTOS = commonBatchNumberMapper.createDTOsByCommonDTO(new CommonBatchNumberDTO()
                .setCreatePersonName(personName)
                .setCommonBatchNumber(new CommonBatchNumber().setDataType(BatchTypeEnum.getOutTypeByMaterialType(materialType).typeCode())));
        return commonBatchNumberDTOS;
    }

    /**
     * 根据申请人名称模糊和类型查询所有出库信息-分页
     */
    @Override
    public PageBean findAllByNameLikeAndTypeByPage(String personName, Integer materialType, Integer page, Integer size, String orderField, String orderType) {

        PageBean pageBean = new PageBean();
        pageBean.setPageSize(size);
        pageBean.setPageNumber(page);
        pageBean.setSortType(orderType);
        pageBean.setSortField(orderField);


        List<CommonBatchNumberDTO> commonBatchNumberDTOS = commonBatchNumberMapper.createDTOsByCommonDTO(new CommonBatchNumberDTO()
                .setCreatePersonName(personName)
                .setCommonBatchNumber(new CommonBatchNumber().setDataType(BatchTypeEnum.getOutTypeByMaterialType(materialType).typeCode())));

        pageBean.setList(commonBatchNumberDTOS);
        pageBean.setTotal(commonBatchNumberDTOS.size());

        return pageBean;
    }

    /**
     * 发送出库记录
     *
     * @param batchNumberId 批号id
     */
    @Override
    public void sendOutMessage(Integer batchNumberId) {
        List<RepoOutApply> outApplies = repoOutApplyMapper.findAllByBatchNumberId(batchNumberId);
        Assert.notEmpty(outApplies, "数据库数据错误,不存在此出库数据");

        //todo 发送: List<物料编码-名称-重量 > + 唯一出库单号(批号)

        System.out.println(batchNumberId + "%d单号申请出库,请核实");
        commonBatchNumberMapper.update(commonBatchNumberMapper.byId(batchNumberId).setStatus(BatchStatusEnum.DOING.status()));
    }

    /**
     * 接收出库记录
     *
     * @return
     */
    @Override
    public String receiveOutMessage(RepoOutReceiveMessageDTO repoOutReceiveMessageDTO) {
        CommonBatchNumber outBatch = commonBatchNumberMapper.byBatchNumber(repoOutReceiveMessageDTO.getBatchNumber());
        Integer dataType = outBatch.getDataType();

        Assert.isTrue(null != outBatch &&
                (BatchTypeEnum.REPO_OUT_APPLY_RAW.typeCode().equals(dataType)
                        ||
                        BatchTypeEnum.REPO_OUT_APPLY_END.typeCode().equals(dataType)), "出库失败,找不到该单号");

        Assert.isTrue(outBatch.getStatus().equals(BatchStatusEnum.DOING.status()), "该单号出库已完成,不要重复出库");

        List<Map<String, Integer>> outData = repoOutReceiveMessageDTO.getOutData();


        for (int i = 0; i < outData.size(); i++) {
            Map<String, Integer> map = outData.get(i);
            String serialNumber = null;
            for (String sn : map.keySet()) {
                serialNumber = sn;
            }
            Integer value = map.getOrDefault(serialNumber, null);

            RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.bySerialNumber(serialNumber);
            Assert.notNull(repoBaseSerialNumber, "出库失败,找不到该编号");


            RepoStock repoStock = repoStockMapper.findBySerialNumberId(repoBaseSerialNumber.getId());
            Integer weight = repoStock.getWeight() - value;
            Assert.isTrue(weight > 0, String.format("不能出库,%s重量不够", repoBaseSerialNumber.getMaterialName()));
            repoStock.setWeight(weight);
            //更新库存
            repoStockMapper.updateStock(repoStock);
        }

        commonBatchNumberMapper.update(commonBatchNumberMapper.byId(outBatch.getId()).setStatus(BatchStatusEnum.FINISHED.status()));

        return String.format("%s单号出库成功", outBatch);
    }

    /**
     * 转换成DTO
     *
     * @param repoOutApplies
     * @return
     */


}
