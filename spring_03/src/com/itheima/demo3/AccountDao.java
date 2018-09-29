package com.itheima.demo3;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/26
 * @description:
 */
public interface AccountDao {

    //扣钱
    public void outMoney(String out, double money);

    //加钱
    public void inMoney(String in, double money);

}
