package com.itheima.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/22
 * @description:组件注解，标记类
 *
 */

@Component(value = "userService") //相当于<bean id="userService" class="com.itheima.demo1.UserServiceImpl"></bean>
//@Scope(value = "prototype") //多例
public class UserServiceImpl implements UserService{

    //给name属性注入zhangjian的字符串，setName方法可以省不写
    @Value(value = "zhangjian")
    private String name;

    /*public void setName(String name) {
        this.name = name;
    }*/


    //@Autowired按类型自动装配
   /* @Autowired
    @Qualifier(value = "userDao") //按名称注入*/
    @Resource(name = "userDao") //是java中的注解，spring框架支持该注解，按照名称去查找
    private UserDao userDao;


    @Override
    public void sayHello() {
        System.out.println("hello Spring!" + name);
        userDao.save();
    }
}
