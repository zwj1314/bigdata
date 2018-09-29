package com.itheima.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:动态方法访问的方式
 */
public class UserAction extends ActionSupport {

    //保存用户
    public String save(){
        System.out.println("保存用户....");
        return NONE;
    }

    //删除用户
    public String delete(){
        System.out.println("删除用户...");
        return NONE;
    }
}
