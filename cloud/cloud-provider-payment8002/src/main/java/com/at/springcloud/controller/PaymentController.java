package com.at.springcloud.controller;

import com.at.springcloud.entities.CommonResult;
import com.at.springcloud.entities.Payment;
import com.at.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author user
 * @create 2021-05-22 17:40
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String servicePort;

    /**
     * 插入数据
     * @param payment
     * @return
     */
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*******插入结果" + result);
        if (result > 0){
            return new CommonResult(200,"插入数据库成功,servicePort:" + servicePort,result);
        } else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*******查询结果" + payment);
        if (payment != null){
            return new CommonResult(200,"查询成功,servicePort:" + servicePort,payment);
        } else {
            return new CommonResult(444,"没有对应记录,ID为" + id,null);
        }
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return servicePort;
    }
}
