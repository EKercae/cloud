package com.at.springcloud.dao;

import com.at.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author user
 * @create 2021-05-22 17:20
 */
@Mapper
public interface PaymentDao {
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
    public Payment getPaymentById(@Param("id") Long id);

}
