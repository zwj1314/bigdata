package com.itheima.web.action;

import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/26
 * @description:客户的控制层
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

    //实现保存客户的功能，接收用户传入的客户参数，并保存，不要忘记手动new。模型驱动
    private Customer customer = new Customer();
    @Override
    public Customer getModel() {
        return customer;
    }

    //提供service的成员属性，提供set方法
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public String add(){
        System.out.println("web层，保存客户");
        System.out.println(customer);

        //struts2中的action方法调用spring中的service方法，采用传统的方式。
        /*WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
        CustomerService cs = (CustomerService) context.getBean("customerService");
        cs.save(customer);*/

        customerService.save(customer);

        return NONE;
    }

    /*
    演示延时加载的问题
     */
    public String loadById(){
        Customer customer= customerService.loadById(2L);
        System.out.println(customer.getCust_name());
        return NONE;
    }

}
