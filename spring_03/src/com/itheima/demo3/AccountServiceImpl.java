package com.itheima.demo3;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/24
 * @description:
 */
public class AccountServiceImpl implements AccountService{

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    //注入事务的模版类
    private TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }


    /*
        转账的方法
         */
    @Override
    public void pay(final String out, final String in, final double money) {
       transactionTemplate.execute(new TransactionCallbackWithoutResult() {

           //事务的执行，如果有问题，回滚；没有问题则提交
           @Override
           protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
               //先扣钱
               accountDao.outMoney(out, money);

               //模拟异常
               //int a = 10/0;

               //再加钱
               accountDao.inMoney(in, money);

           }
       });
    }
}
