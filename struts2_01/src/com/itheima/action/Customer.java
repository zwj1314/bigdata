package com.itheima.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:
 */
public class Customer extends ActionSupport {

    //保存客户
    public String save(){
        System.out.println("保存客户....");
        return NONE;
    }

    //删除客户
    public String delete(){
        System.out.println("删除客户...");
        return NONE;
    }
}
