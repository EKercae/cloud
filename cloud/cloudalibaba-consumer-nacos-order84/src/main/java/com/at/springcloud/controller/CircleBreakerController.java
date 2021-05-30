package com.at.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.at.springcloud.entities.CommonResult;
import com.at.springcloud.entities.Payment;
import com.at.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author user
 * @create 2021-05-30 9:24
 */
@RestController
public class CircleBreakerController {

    private static final String SERVICE_URL = "http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallBack")
    //@SentinelResource(value = "fallBack",fallback = "handlerFallBack")
    //@SentinelResource(value = "fallBack",blockHandler= "blockHandler")
    @SentinelResource(value = "fallBack",fallback = "handlerFallBack",blockHandler= "blockHandler")
    public CommonResult<Payment> fallBack(@PathVariable("id") Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4){
            throw new IllegalArgumentException("IllegalArgumentException 非法参数");
        }else if(result.getData() == null){
            throw new NullPointerException("NullPointerException 没有该id的记录");
        }
        return result;
    }

    /**
     * fallback
     * @param id
     * @param throwable
     * @return
     */
    public CommonResult handlerFallBack(@PathVariable("id") Long id,Throwable throwable){
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"兜底异常-->handlerFallBack:异常-->"+throwable.getMessage(),payment);
    }

    /**
     * blockHandler
     * @param id
     * @param blockException
     * @return
     */
    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException blockException){
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"控制流量-->blockHandler:异常-->"+blockException.getMessage(),payment);
    }

    //---------openFeign---------

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }

}
