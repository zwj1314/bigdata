package com.itheima.demo3;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/19
 * @description:
 */
public class Test3 {

    @Test
    public void run1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Car car = (Car) ac.getBean("car");
        System.out.println(car);
    }

    @Test
    public void run2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = (Person) ac.getBean("person");
        System.out.println(person);
    }

    /*
    测试注入集合
     */
    @Test
    public void run3(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) ac.getBean("user");
        System.out.println(user);
    }

}
