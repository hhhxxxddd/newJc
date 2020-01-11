package com.jinchi.auth.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author：XudongHu
 * @description: 不解释
 * @date:11:05 2018/11/14
 */
@Configuration
public class PageHelperConfig {
        /**
         * 注入pagehelper配置
         */
        @Bean
        public PageHelper getPageHelper() {
            PageHelper pageHelper = new PageHelper();
            Properties properties = new Properties();
            properties.setProperty("helperDialect", "mysql");
            properties.setProperty("reasonable", "true");
            properties.setProperty("supportMethodsArguments", "true");
            properties.setProperty("params", "count=countSql");
            properties.setProperty("offsetAsPageNum","true");
            properties.setProperty("rowBoundsWithCount","true");
            pageHelper.setProperties(properties);
            return pageHelper;
        }

}
