package com.at.springcloud.service;

/**
 * @author user
 * @create 2021-05-30 20:54
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
