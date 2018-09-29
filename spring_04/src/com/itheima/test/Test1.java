package com.itheima.test;

import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/27
 * @description:测试hibernate模版类的简单方法
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test1 {

    @Resource(name = "customerService")
    private CustomerService customerService;

 /*   public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }*/

    @Test
    public void run1(){
        Customer customer = new Customer();
        customer.setCust_id(1L);
        customer.setCust_name("zhangjian");
        customerService.update(customer);

    }

    /*
    查询某个客户
     */
    @Test
    public void run2(){
        customerService.getById(2L);
    }

    /*
    查询所有客户
     */
    @Test
    public void run3(){
        customerService.findAll();
    }
}
