package com.itheima.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:编写简单的拦截器
 */
public class DemoInterceptor extends AbstractInterceptor{

    //intercept用来进行拦截的
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        System.out.println("Action方法执行之前...");
        //执行下一个拦截器
        String result = invocation.invoke();

        System.out.println("Action方法执行之后...");

        return result;
    }
}
