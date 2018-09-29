package com.itheima.demo1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/19
 * @description:
 */
public class Test1 {

    /*
    原来的实现方式
     */
    @Test
    public void run1(){
        //创建实现类
        UserServiceImpl usi = new UserServiceImpl();
        usi.setName("zhangjian");
        //多态的实现
        //UserService us = new UserServiceImpl();
        usi.sayHello();

    }

    /*
    使用的是Spring框架的方式(IOC)
     */
    @Test
    public void run2(){
        //创建工厂，加载核心的配置文件(多态)
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从工厂中获取到对象
        UserServiceImpl usi = (UserServiceImpl) ac.getBean("userService");
        //调用对象的方法执行
        usi.sayHello();
    }


    /*
    使用的是Spring框架的方式,使用接口
     */
    @Test
    public void run3(){
        //创建工厂，加载核心的配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从工厂中获取到对象
        UserService us = (UserService) ac.getBean("userService");
        //调用对象的方法执行
        us.sayHello();
    }


    /*
    演示销毁的方法
     */
    @Test
    public void run4(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl usi = (UserServiceImpl) ac.getBean("userService");
        usi.sayHello();
        ac.close();

    }

    /*
    依赖注入(DI)
     */
    @Test
    public void run5(){
        //创建工厂，加载核心配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从工厂中获取到对象
        UserService us = (UserService) ac.getBean("userService");
        //调用对象的方法执行
        us.sayHello();

    }


}
