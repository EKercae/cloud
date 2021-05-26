package com.at.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author user
 * @create 2021-05-25 18:12
 */
@Component
public class PaymentFallBackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "PaymentFallBackService fall back paymentInfo_ok 系统繁忙 ";
    }

    @Override
    public String payment_timeout(Integer id) {
        return "PaymentFallBackService fall back payment_timeout 系统繁忙 ";
    }
}
