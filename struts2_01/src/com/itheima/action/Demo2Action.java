package com.itheima.action;

import com.opensymphony.xwork2.Action;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:实现Action接口
 */
public class Demo2Action implements Action {

    @Override
    public String execute() throws Exception {

        System.out.println("Demo2Action实现了Action的接口");


        //return NONE 表示页面不跳转
        return SUCCESS;//return "success";


    }
}
