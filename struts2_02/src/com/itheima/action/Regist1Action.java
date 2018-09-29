package com.itheima.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:属性驱动的方式
 */
public class Regist1Action extends ActionSupport {

    private String username;
    private String password;
    private Integer age;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String execute() throws Exception {

        System.out.println(username + " " + password + " " + age);
        //System.out.println("属性驱动...");
        return NONE;
    }
}
