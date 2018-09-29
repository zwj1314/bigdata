package com.itheima.demo1;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/19
 * @description:
 */
public class UserServiceImpl implements UserService {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void sayHello() {

        System.out.println("Hello Spring "+ name);

    }

    public void init(){

        System.out.println("初始化...");

    }

    public void destroy(){

        System.out.println("销毁...");

    }
}
