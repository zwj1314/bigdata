package com.itheima.demo2;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/19
 * @description:
 */
public class CustomerServiceImpl {

    //提供成员属性，提供set方法
    private CustomerDaoImpl customerDao;

    public void setCustomerDao(CustomerDaoImpl customerDao) {
        this.customerDao = customerDao;
    }

    public void save(){
        System.out.println("我是业务层Service...");
        //原来的编写方式
        //new CustomerDaoImpl().save();

        //Sping的方式
        customerDao.save();
    }
}
