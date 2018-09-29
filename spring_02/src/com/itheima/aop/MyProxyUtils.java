package com.itheima.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/22
 * @description:使用jdk的方式生成代理对象
 */
public class MyProxyUtils {

    public static UserDao getProxy(final UserDao dao){
        //使用Proxy类生成代理对象
        UserDao proxy = (UserDao) Proxy.newProxyInstance(dao.getClass().getClassLoader(), dao.getClass().getInterfaces(), new InvocationHandler() {

            //代理对象的方法一执行，invoke方法就会执行一次
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //System.out.println("aaaa");

                //方法的增强
                if ("save".equals(method.getName())){
                    System.out.println("记录日志...");
                    //开启事务

                }
                //提交事务
                //让dao类的save或者update方法正常的执行下去
                return method.invoke(dao, args);
            }
        });
        return proxy;
    }

}
