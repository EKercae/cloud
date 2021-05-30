package com.at.springcloud.service.impl;

import com.at.springcloud.dao.AccountDao;
import com.at.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author user
 * @create 2021-05-30 21:27
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("------->account-service中扣减账户余额开始");
        accountDao.decrease(userId,money);
        log.info("------->account-service中扣减账户余额结束");
    }
}
