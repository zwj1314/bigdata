package com.itheima.demo1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/22
 * @description:
 */
public class Test01 {

    /*
    原来的方式
     */
    @Test
    public void run1(){
        UserService us = new UserServiceImpl();
        us.sayHello();
    }

    /*
    注解的方式
     */
    @Test
    public void run2(){
        //获取工厂，加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取对象
        UserService us = (UserService) ac.getBean("userService");
        us.sayHello();

    }
}
