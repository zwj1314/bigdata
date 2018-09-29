package com.itheima.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:通配符的方式
 */
public class LinkmanAction extends ActionSupport {

    //保存联系人
    public String save(){
        System.out.println("保存联系人....");
        return NONE;
    }

    //删除联系人
    public String delete(){
        System.out.println("删除联系人...");
        return NONE;
    }
}
