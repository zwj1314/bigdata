package com.itheima.action;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:Struts框架都使用Action类处理用户的请求
 */
public class HelloAction {

    /**
     * Action类中的方法签名是有要求的，必须这么做
     * public 共有的
     * 必须有返回值，且返回值类型必须是string类型
     * 方法名可以是任意的，但是不能有参数列表
     * 页面的跳转：
     *  1、return "字符串"
     *  2、需要在struts.xml配置文件中，配置跳转的页面
     */

    public String sayHello(){
        System.out.println("hello struts2");
        return "ok";
    }
}
