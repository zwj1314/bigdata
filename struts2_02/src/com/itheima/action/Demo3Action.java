package com.itheima.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:
 */
public class Demo3Action extends ActionSupport {

    @Override
    public String execute() throws Exception {

        System.out.println("我是action，我正常执行...");
        return NONE;

    }
}
