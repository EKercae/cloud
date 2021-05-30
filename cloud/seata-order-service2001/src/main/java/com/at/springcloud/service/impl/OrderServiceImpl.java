package com.at.springcloud.service.impl;

import com.at.springcloud.dao.OrderDao;
import com.at.springcloud.domain.Order;
import com.at.springcloud.service.AccountService;
import com.at.springcloud.service.OrderService;
import com.at.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author user
 * @create 2021-05-30 17:21
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountService accountService;
    @Autowired
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("1------>开始新建订单");
        orderDao.create(order);
        log.info("2------>开始调用库存并减一库存");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("3------>账户扣减余额");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("4------>修改订单状态");
        orderDao.update(order.getUserId(),0);
        log.info("5------>结束");

    }
}
