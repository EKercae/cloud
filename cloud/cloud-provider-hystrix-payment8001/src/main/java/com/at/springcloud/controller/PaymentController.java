package com.at.springcloud.controller;

import com.at.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 * @create 2021-05-25 12:20
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id){
        String result = paymentService.payment_ok(id);
        log.info("------------>"+result);
        return result;
    }

     @GetMapping("/payment/hystrix/timeout/{id}")
     public String payment_timeout(@PathVariable("id") Integer id){
        String result = paymentService.payment_timeout(id);
        log.info("------------>"+result);
        return result;
    }

    //========服务熔断=========
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("result------>" + result);
        return result;
    }

}
