package com.itheima.demo2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/19
 * @description:
 */
public class Test2 {

    /*
    原来的方法
     */
    @Test
    public void run1(){
        CustomerServiceImpl cs = new CustomerServiceImpl();
        cs.save();
    }


    /*
    Spring方式
     */
    @Test
    public void run2(){
        //创建工厂，加载配置文件，CustomerServiceImpl创建了，CustomerDaoImpl同样被创建了，发现CustomerServiceImpl有属性CustomerDao
        //在配置文件中恰好配置了该属性，Spring则将CustomerDaoImpl创建的对象CustomerDao注入到CustomerServiceImpl中了
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        CustomerServiceImpl cs =(CustomerServiceImpl) ac.getBean("customerService");
        cs.save();
    }
}
