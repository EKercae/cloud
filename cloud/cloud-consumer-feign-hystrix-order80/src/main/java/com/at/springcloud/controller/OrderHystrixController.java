package com.at.springcloud.controller;

import com.at.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 * @create 2021-05-25 16:59
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallBackMethod")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;


    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_ok(id);
        return result;
    }

    //@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
       // @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand
    public String payment_timeout(@PathVariable("id") Integer id){
        String result = paymentHystrixService.payment_timeout(id);
        return result;
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "系统繁忙！！！！";
    }

    /**
     * 全局fallback方法
     */
    public String payment_Global_FallBackMethod(){
        return "系统繁忙,请稍后再试!";
    }

}
