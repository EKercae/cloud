package com.at.springcloud.service;

import com.at.springcloud.entities.CommonResult;
import com.at.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author user
 * @create 2021-05-25 9:55
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    /**
     * 客户端根据id查询
     * @param id
     * @return
     */
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    /**
     * 测试8001超时
     * 默认超时一秒 报错
     * @return
     */
    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeOut();
}
