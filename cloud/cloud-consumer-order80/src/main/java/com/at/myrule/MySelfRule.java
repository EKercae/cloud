package com.at.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author user
 * @create 2021-05-24 16:31
 */
//@Configuration
public class MySelfRule {

    //@Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
