package com.jinchi.common.config;

import feign.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author XudongHu
 * @className FeignConfig
 * @apiNote Feign设置
 * @modifer
 * @since 2019/10/18 10:41
 */
@Configuration
public class FeignConfig {

    //默认的超时时间太短
    @Value("${service.feign.connectTimeout:20000}")
    private int connectTimeout;

    @Value("${service.feign.readTimeOut:20000}")
    private int readTimeout;

    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeout, readTimeout);
    }
}
