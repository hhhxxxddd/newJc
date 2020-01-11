package com.jinchi.common.model.factorypattern;

import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.domain.CommonBatchNumber;
import com.jinchi.common.utils.NumberGenerator;

import java.util.Date;

/**
 * @author：XudongHu
 * @className:CommonBatchFactory
 * @description: 批号工厂
 * @date:10:34 2019/3/8
 * @Modifer:
 */
public class CommonBatchFactory {
    //初始化批号
    // 是否发布 0
    // 备注  无
    // 状态 -1  已保存未提交
    // 创建时间 当前时间
    // 类型     枚举类中的类型
    // 描述      枚举类中的描述
    // 编号      根据类型生成
    public static CommonBatchNumber initialize(){
        return new CommonBatchNumber()
                .setIsPublished(0).setMemo("无")
                .setStatus(BatchStatusEnum.SAVE.status())
                .setCreateTime(new Date());
    }

    public static CommonBatchNumber initialize(Integer typeCode){
        BatchTypeEnum batchTypeEnum = BatchTypeEnum.getByDataType(typeCode);
        return initialize()
                .setDataType(typeCode)
                .setDescription(batchTypeEnum.description())
                .setBatchNumber(NumberGenerator.batchNumberGenerator(typeCode));
    }

    public static CommonBatchNumber initialize(Integer createPersonId,Integer typeCode){
        return initialize(typeCode).setCreatePersonId(createPersonId);
    }

}
