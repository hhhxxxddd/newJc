package com.jinchi.common.service;


import com.jinchi.common.domain.EquipmentRepairRecord;
import com.jinchi.common.dto.PageBean;

import java.util.Map;

/**
 * @author：XudongHu
 * @className:EquipmentRepairRecordService
 * @description: 设备维修service
 * @date:14:01 2019/3/8
 * @Modifer:
 */
public interface EquipmentRepairRecordService {

    /**
     * app用
     * 报修
     * @param repairRecord 设备维修实体
     * @return
     */
    EquipmentRepairRecord callForRepair(EquipmentRepairRecord repairRecord);

    /**
     * app用
     * 接单
     * @param id 主键
     * @return
     */
    EquipmentRepairRecord orderRepair(Integer id);

    /**
     * app用
     * 完工
     * @return
     */
    EquipmentRepairRecord finishedRepair(Integer id,String conclusion);

    /**
     * app用
     * 评价
     * @return
     */
    EquipmentRepairRecord rate(Integer id,String rate);

    /**
     * app用
     * 网页用
     * 详情
     * @param id 主键
     * @return
     */
    Map<Object, Object> detailById(Integer id);

    /**
     * app用
     * 网页用
     * 查询所有
     * @param equipName 设备名称
     * @param pageBean 分页类
     * @return
     */
    PageBean byEquipNameLike(String equipName, PageBean pageBean);
}
