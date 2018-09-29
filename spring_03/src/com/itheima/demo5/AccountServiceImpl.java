package com.itheima.demo5;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/24
 * @description:若在类上添加注解@Transactional,则该类中的所有的方法全部都有事务
 */
@Transactional
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    /*
        转账的方法
         */
//    @Transactional()
    @Override
    public void pay(final String out, final String in, final double money) {
        //先扣钱
        accountDao.outMoney(out, money);

        //模拟异常
        int a = 10/0;

        //再加钱
        accountDao.inMoney(in, money);
    }
}
