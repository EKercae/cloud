package com.at.springcloud.service;

import com.at.springcloud.entities.CommonResult;
import com.at.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author user
 * @create 2021-05-30 10:45
 */
@Component
public class PaymentFallBackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级-->PaymentFallBackService",new Payment(id,"PaymentFallBackService"));
    }
}
