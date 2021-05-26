package com.at.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author user
 * @create 2021-05-25 12:15
 */
@Service
public class PaymentService {

    public String payment_ok(Integer id){
        return "当前线程:"+Thread.currentThread().getName()+"payment_ok().id:"+id;
    }


    /**
     * 服务降级
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "payment_timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String payment_timeout(Integer id){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "当前线程:"+Thread.currentThread().getName()+"payment_timeout().id:"+id;
    }

    public String payment_timeoutHandler(Integer id){
        return "当前线程:"+Thread.currentThread().getName()+"o(╥﹏╥)o 系统繁忙 id:"+id;
    }

    //============服务熔断=============

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),   //开启熔断器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),  //请求次数超过了峰值 熔断器将会关闭
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") //失败率到了多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("--------id不能为零！");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用成功,流水号为:" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return " 系统繁忙 请稍后再试或传入的参数有误！ o(╥﹏╥)o id" + id;
    }
}
