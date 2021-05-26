package com.at.springcloud.controller;

import com.at.springcloud.entities.CommonResult;
import com.at.springcloud.entities.Payment;
import com.at.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 * @create 2021-05-25 10:02
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    /**
     * openfeign 集成了 ribbon  一般默认客户端等待1秒
     * @return
     */
    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        return paymentFeignService.paymentFeignTimeOut();
    }
}
