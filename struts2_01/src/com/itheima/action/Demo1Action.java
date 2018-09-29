package com.itheima.action;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:POJO类,没有任何的继承和实现
 */
public class Demo1Action {

    /**
     * execute是默认方法
     * return null; 不会进行跳转
     *
     */
    public String execute(){
        System.out.println("Demo01Action就是POJO类");
        return null;
    }
}
