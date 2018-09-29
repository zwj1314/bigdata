package com.itheima.demo4;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/24
 * @description:
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    /*
        转账的方法
         */
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
