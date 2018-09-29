package com.itheima.action;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:属性驱动的方式，数据封装到javabean对象中
 */
public class Regist2Action extends ActionSupport {


    private User user;

    //注意一：现在要提供get和set的方法
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String execute() throws Exception {

        System.out.println(user);

        return NONE;
    }
}
