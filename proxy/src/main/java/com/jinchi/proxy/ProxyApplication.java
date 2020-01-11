package com.jinchi.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ProxyApplication {

    public static final Logger log = LoggerFactory.getLogger(ProxyApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }
}
