package com.itheima.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:Struts2中提供的类 ServletActionContext，原生Servlet的API的方式
 */
public class demo2Action extends ActionSupport {

    @Override
    public String execute() throws Exception {
        //获取到request对象
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("msg", "zhangjain");
        request.getSession().setAttribute("msg", "zhouboheng");
        ServletActionContext.getServletContext().setAttribute("msg", "zhaowei");

        return SUCCESS;
    }
}
