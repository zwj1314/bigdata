package com.itheima.interceptor;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:自定义拦截器，判断当前系统是否已经登录，如果登录，继续执行。
 *              如果没有登录，则跳转到登录页面。
 */
public class UserInterceptor extends MethodFilterInterceptor {

    /**
     *
     * @param actionInvocation
     * @return
     * @throws Exception
     * 进行拦截的方法
     */
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //获取session
        User u =(User) ServletActionContext.getRequest().getSession().getAttribute("u");
        if (u == null){
            //说明：没有登录
            return "login";

        }
        return actionInvocation.invoke();


    }
}
