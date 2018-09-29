package com.itheima.service;

import com.itheima.dao.CustomerDao;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/19
 * @description:
 */
public class CustomerServiceImpl implements CustomerService {
    //将Dao层的控制反转给spring，设置Dao层对象，调用方法（在applicationContext.xml注入）
    private CustomerDao customerDao;

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void save() {
        System.out.println("业务层：保存客户...");
        customerDao.save();
    }
}
