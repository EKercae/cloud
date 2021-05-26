package com.at.springcloud.service;

import com.at.springcloud.entities.Payment;

/**
 * @author user
 * @create 2021-05-22 17:36
 */
public interface PaymentService {

    /**
     * 写
     * @param payment
     * @return
     */
    public int create(Payment payment);

    /**
     * 查
     * @param id
     * @return
     */
    public Payment getPaymentById(Long id);
}
