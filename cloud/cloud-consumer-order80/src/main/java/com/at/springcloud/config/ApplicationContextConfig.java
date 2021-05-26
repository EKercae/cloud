package com.at.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author user
 * @create 2021-05-22 19:04
 * LoadBalanced 赋予RestTemplate负载均衡的能力
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
