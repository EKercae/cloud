package com.at.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.at.springcloud.entities.CommonResult;
import com.at.springcloud.entities.Payment;

/**
 * @author user
 * @create 2021-05-29 22:34
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException e){
        return new CommonResult(444,"自定义--->handlerException",new Payment(2020L,"handlerException--->1"));

    }
    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(444,"自定义--->handlerException",new Payment(2020L,"handlerException--->2"));

    }
}
