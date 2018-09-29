package com.itheima.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:编写Action类继承ActionSupport类，
 *              ActionSupport类已经实现了Action和其他的一些接口
 */
public class Demo3Action extends ActionSupport{

    @Override
    public String execute() throws Exception {
        System.out.println("Demo3Action继承了ActionSupport类");
        return NONE;
    }
}
