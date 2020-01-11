package com.jinchi.common.utils;

import com.jinchi.common.constant.BatchTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @author：XudongHu
 * @className:ScheduleTasks
 * @description: 定时任务
 * @date:14:18 2019/1/23
 * @Modifer:
 */
@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private  static final SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");

      //每隔一秒执行一次
//    @Scheduled(fixedRate = 1000)
//    public void reportCurrent(){
//        logger.info("现在时间：{}",dataFormat.format(new Date()));
//    }
    //每0秒0分0时触发,将批号枚举类中的count归零
    @Scheduled(cron = "0 0 0 * * ?")
    public void makeZero(){
        for (BatchTypeEnum batchTypeEnum: BatchTypeEnum.values()){
            batchTypeEnum.setCount(1);
        }
    }
}
