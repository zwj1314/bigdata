package com.itheima.aop;

import org.junit.Test;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/22
 * @description:
 */
public class Test01 {

    @Test
    public void run1(){
        UserDao dao = new UserDaoImpl();
        dao.save();
        dao.update();
        System.out.println("===============");
        //使用工具类，获取到代理对象
        UserDao proxy = MyProxyUtils.getProxy(dao);
        //调用代理对象的方法
        proxy.save();
        proxy.update();


    }
}
