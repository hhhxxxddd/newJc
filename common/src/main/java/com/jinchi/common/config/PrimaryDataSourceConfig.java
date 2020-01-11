package com.jinchi.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class PrimaryDataSourceConfig {

    @Value("${primary.spring.datasource.url}")
    private String url;
    @Value("${primary.spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${primary.spring.datasource.username}")
    private String username;
    @Value("${primary.spring.datasource.password}")
    private String password;
    @Value("${primary.spring.datasource.druid.test-on-borrow}")
    private boolean testOnBorrow;
    @Value("${primary.spring.datasource.druid.validationQuery}")
    private String validationQuery;
    @Value("${primary.spring.datasource.druid.test-while-idle}")
    private boolean testWhileIdle;
    @Value("${primary.spring.datasource.druid.time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${primary.spring.datasource.druid.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis;

    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "primary.spring.datasource")  //将属性文件封装到实体DataSource
    @Primary
    public DataSource primaryDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        return druidDataSource;
    }

    /*----------------spring jdbc module-----------------*/
    @Bean(name = "primaryJdbcTemplate")
    @Primary
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource primaryDataSource) {
        return new JdbcTemplate(primaryDataSource);
    }

    @Bean(name = "primaryDataSourceTransactionManager")
    @Primary
    public DataSourceTransactionManager primaryDataSourceTransactionManager(@Qualifier("primaryDataSource") DataSource primaryDataSource) {
        return new DataSourceTransactionManager(primaryDataSource);
    }
}
