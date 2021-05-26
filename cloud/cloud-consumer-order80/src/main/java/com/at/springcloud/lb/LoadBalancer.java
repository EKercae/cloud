package com.at.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author user
 * @create 2021-05-24 21:28
 */
public interface LoadBalancer {
    /**
     * 获取集群服务器
     * @param serviceInstances
     * @return
     */
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
