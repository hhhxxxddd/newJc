package com.jinchi.auth.quartz;

import com.jinchi.auth.mapper.TokenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author：XudongHu
 * @className:TokenQuarz
 * @description:
 * @date:17:04 2019/3/12
 * @Modifer:
 */
@Component
public class TokenQuartz {
    @Autowired
    private TokenMapper tokenMapper;
        private static final Logger logger = LoggerFactory.getLogger(TokenQuartz.class);
        private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //每隔一秒执行一次
//    @Scheduled(fixedRate = 1000)
//    public void reportCurrent(){
//        logger.info("现在时间：{}",dataFormat.format(new Date()));
//    }
        //每0秒0分0时触发,将Token表清空
        @Scheduled(cron = "0 0 0 * * ?")
        public void makeZero(){
            Integer sum = tokenMapper.tokenRemove();
            logger.info(sdf.format(new Date())+":已清除"+sum+"条压缩的token数据");
        }
}
