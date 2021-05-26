package com.at.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author user
 * @create 2021-05-24 21:30
 */

@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }

    /**
     * 自旋锁
     * @return
     */
    public final int getAndIncrement(){
        int current,next;
        do{
            current = this.atomicInteger.get();
            next = current >=2147483647? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("访问次数--->"+next);
        return next;
    }

}
