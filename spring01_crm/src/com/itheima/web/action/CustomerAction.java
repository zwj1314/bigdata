package com.itheima.web.action;

import com.itheima.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletContext;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/19
 * @description:客户的action
 */
public class CustomerAction extends ActionSupport {


    public String save() {

        System.out.println("web层保存客户...");
        //使用工厂,原来的方式
        /*ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CustomerService cs = (CustomerService) ac.getBean("customerService");
        cs.save();*/

        ServletContext servletContext = ServletActionContext.getServletContext();
        //需要使用WEB的工厂的方式
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        CustomerService cs = (CustomerService) context.getBean("customerService");
        cs.save();

        return NONE;
    }
}
