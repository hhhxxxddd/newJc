package com.jinchi.common.service;

import com.jinchi.common.constant.EquipmentRepairStatusEnum;
import com.jinchi.common.domain.EquipmentBaseInstrument;
import com.jinchi.common.domain.EquipmentRepairRecord;
import com.jinchi.common.domain.ProductLine;
import com.jinchi.common.dto.AuthDepartmentDTO;
import com.jinchi.common.dto.AuthUserDTO;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.mapper.*;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author：XudongHu
 * @className:EquipmentRepairRecordServiceImp
 * @description: 设备维修service实现类
 * @date:14:02 2019/3/8
 * @Modifer:
 */
@Service
public class EquipmentRepairRecordServiceImp implements EquipmentRepairRecordService {
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private EquipmentBaseInstrumentMapper equipmentBaseInstrumentMapper;
    @Autowired
    private ProductLineMapper productLineMapper;
    @Autowired
    private AuthDepartmentMapper authDepartmentMapper;
    @Autowired
    private EquipmentRepairRecordMapper equipmentRepairRecordMapper;

    private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 验证外键的合法性
     *
     * @param repairRecord
     * @return
     */
    private Map verify(EquipmentRepairRecord repairRecord) {
        AuthDepartmentDTO department = authDepartmentMapper.byId(repairRecord.getDepartmentId());
        Assert.notNull(department, String.format("id为%d的设备维修找不到部门", repairRecord.getId()));

        EquipmentBaseInstrument instrument = equipmentBaseInstrumentMapper.byId(repairRecord.getInstrumentId());
        Assert.notNull(instrument, String.format("id为%d的设备维修找不到设备", repairRecord.getId()));

        ProductLine productLine = productLineMapper.byId(repairRecord.getProductLineId());
        Assert.notNull(productLine, String.format("id为%d的设备维修找不到生产线", repairRecord.getId()));

        AuthUserDTO userDTO = authUserMapper.byId(repairRecord.getRepairApplierId());
        Assert.notNull(userDTO, String.format("id为%d的设备维修找不到申请人", repairRecord.getId()));

        Map<Object, Object> map = new HashMap<>();
        map.put("instrument", instrument);
        map.put("department", department);
        map.put("productLine", productLine);
        map.put("applier", userDTO);

        return map;
    }


    /**
     * 详情map
     *
     * @return
     */
    private Map<Object, Object> detailMap(EquipmentRepairRecord e) {
        Map<Object, Object> detailMap = new HashMap<>();
        detailMap.put("id", e.getId());
        detailMap.put("status", e.getStatus());
        detailMap.put("foreignKey", verify(e));
        detailMap.put("repairApply", EquipmentRepairStatusEnum.getApplyDetail(e));
        detailMap.put("order", EquipmentRepairStatusEnum.getOrderDetail(e));
        detailMap.put("finished", EquipmentRepairStatusEnum.getFinshedDetail(e));
        detailMap.put("rate", EquipmentRepairStatusEnum.getRateDetail(e));
        return detailMap;
    }

    /**
     * 主页map
     *
     * @return
     */
    private Map<Object, Object> mainPageMap(EquipmentRepairRecord e) {
        Map<Object, Object> mainMap = new HashMap<>();
        mainMap.put("id", e.getId());
        mainMap.put("status", e.getStatus());
        mainMap.put("foreignKey", verify(e));
        mainMap.put("repairApply", SDF.format(e.getRepairApplyTime()));
        mainMap.put("order", SDF.format(e.getOrderTime()));
        mainMap.put("finished", SDF.format(e.getFinishedTime()));
        mainMap.put("rate", SDF.format(e.getRate()));
        return mainMap;
    }


    /**
     * 报修
     *
     * @param repairRecord 设备维修实体
     * @return
     */
    @Override
    public EquipmentRepairRecord callForRepair(EquipmentRepairRecord repairRecord) {
        repairRecord
                .setStatus(EquipmentRepairStatusEnum.WAIT.getStatus())
                .setRepairApplyTime(new Date());
        Assert.isTrue(!StringUtils.isEmpty(repairRecord.getFailureDescription()), "报修请输入故障描述");
        verify(repairRecord);
        equipmentRepairRecordMapper.insert(repairRecord);
        return repairRecord;
    }

    /**
     * 接单
     *
     * @param id 主键
     * @return
     */
    @Override
    public EquipmentRepairRecord orderRepair(Integer id) {
        EquipmentRepairRecord equipmentRepairRecord = equipmentRepairRecordMapper.byId(id);
        Assert.notNull(equipmentRepairRecord, String.format("接单失败,id为%d的设备维修找不到", id));
        //改变状态和接单时间
        equipmentRepairRecord
                .setStatus(EquipmentRepairStatusEnum.ORDER.getStatus())
                .setOrderTime(new Date());
        equipmentRepairRecordMapper.update(equipmentRepairRecord);
        return equipmentRepairRecord;
    }

    /**
     * 完工
     *
     * @return
     */
    @Override
    public EquipmentRepairRecord finishedRepair(Integer id, String conclusion) {
        EquipmentRepairRecord oldValue = equipmentRepairRecordMapper.byId(id);
        Assert.notNull(oldValue, String.format("申请完工失败,id为%d的设备维修找不到", id));
        Assert.isTrue(!StringUtils.isEmpty(conclusion), "申请完工请填写结论");

        oldValue.setStatus(EquipmentRepairStatusEnum.FINISHED.getStatus())
                .setFinishedTime(new Date())
                .setFinishedConclusion(conclusion);
        equipmentRepairRecordMapper.update(oldValue);

        return oldValue;
    }

    /**
     * 评价
     *
     * @return
     */
    @Override
    public EquipmentRepairRecord rate(Integer id, String rate) {
        EquipmentRepairRecord oldValue = equipmentRepairRecordMapper.byId(id);
        Assert.notNull(oldValue, String.format("评价失败,id为%d的设备维修找不到", id));
        Assert.isTrue(!StringUtils.isEmpty(rate), "评价不能为空");

        oldValue.setStatus(EquipmentRepairStatusEnum.RATED.getStatus())
                .setRate(rate)
                .setRateTime(new Date());
        equipmentRepairRecordMapper.update(oldValue);
        return oldValue;
    }

    /**
     * 详情
     *
     * @param id 主键
     * @return
     */
    @Override
    public Map<Object, Object> detailById(Integer id) {
        EquipmentRepairRecord e = equipmentRepairRecordMapper.byId(id);
        if (null == e) return null;
        return detailMap(e);
    }

    /**
     * 查询所有
     *
     * @param equipName 设备名称
     * @param pageBean  分页类
     * @return
     */
    @Override
    public PageBean byEquipNameLike(String equipName, PageBean pageBean) {
        List<EquipmentRepairRecord> repairRecords = equipmentRepairRecordMapper.byNameLikeByPage(equipName, pageBean);
        if (null == repairRecords || repairRecords.isEmpty()) return null;
        Integer countSum = equipmentRepairRecordMapper.countSum(equipName);

        List<Map<Object, Object>> content = new ArrayList<>();
        repairRecords.forEach(e -> content.add(mainPageMap(e)));

        pageBean.setList(content);
        pageBean.setTotal(countSum);

        return pageBean;
    }
}
