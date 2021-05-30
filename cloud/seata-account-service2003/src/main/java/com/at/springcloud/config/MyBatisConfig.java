package com.at.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author user
 * @create 2021-05-30 21:09
 */
@Configuration
@MapperScan("com.at.springcloud.dao")
public class MyBatisConfig {
}
