package com.jinchi.common.utils;

import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.constant.QualitySampleTypeEnum;
import org.reflections.util.Utils;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author：XudongHu
 * @className:NumberGenerator
 * @description: 批号编号文件号生成工具
 * @date:12:19 2018/11/28
 */
public class NumberGenerator {
    //批号生成规则
    public static String batchNumberGenerator(Integer dataType) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        //获取凌晨时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date thisDay = calendar.getTime();


        BatchTypeEnum batchEnum = BatchTypeEnum.getByDataType(dataType);

        Assert.isTrue(null != batchEnum, "找不到此类型批号枚举");

        //===>前缀
        StringBuilder batchNumberBuffer = new StringBuilder("BN");
        //===>中间字
        batchNumberBuffer.append("/" + batchEnum.markCode() + "-");
        //===>后缀

        String postfix;
        synchronized (NumberGenerator.class){
            Integer count = batchEnum.getCount();
            StringBuffer countString = new StringBuffer(count.toString());

            //==> 变成诸如 001 002 超过三位 取消0的样子  1111
            while(countString.length()<3) countString.insert(0,"0");
            postfix = simpleDateFormat.format(thisDay) + countString;
            batchEnum.setCount(count+1);
        }

        return batchNumberBuffer.append(postfix).toString();
    }

    //物料编号生成规则
    //todo
    public static String serialNumberGenerator(Integer materialType) {
        QualitySampleTypeEnum type = QualitySampleTypeEnum.getByType(materialType);
        String[] uuId = UUID.randomUUID().toString().split("-");
        String serialNumber = "SN/" +type.getSampleCode()+"-"+uuId[0] + uuId[1];
        return serialNumber.toUpperCase();
    }


    //设备编号生成规则
    //todo 待设计
    public static String equipmentCode(){
        String[] uuId = UUID.randomUUID().toString().split("-");
        String code = "EP/"+uuId[0]+uuId[1];
        return code.toUpperCase();
    }

    //厂商编号生成规则
    //todo 待设计
    public static String manufacturerCode(){
        String[] uuId = UUID.randomUUID().toString().split("-");
        String code = "MF/"+uuId[0]+uuId[1];
        return code.toUpperCase();
    }

    /**
     * 上传的文件随机生成文件名
     * @param postfix 后缀 .pdf等 需要加.
     * @return
     */
    public static String fileCode(String postfix){
        String newPostfix = Utils.isEmpty(postfix)?"":postfix;

        String[] uuid = UUID.randomUUID().toString().split("-");

        StringBuilder fileCode = new StringBuilder();
        for (String s : uuid) {
            fileCode.append(s);
        }
        fileCode.append(newPostfix);

        return fileCode.toString();
    }


}
