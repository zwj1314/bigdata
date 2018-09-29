package com.itheima.action;

/**
 *
 * Struct2框架都是使用Action类处理用户的请求
 *
 */

public class HelloAction {
    /**
     * Action类中的方法签名有要求的，必须这么做
     * public 公有的
     * 必须有返回值，必须string类型
     * 方法名可以是任意的，但是不能有参数列表
     */
    public String sayHello(){
        return "hello";
    }


}
