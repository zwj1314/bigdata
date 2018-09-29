package com.itheima.service;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import javax.persistence.Id;
import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/26
 * @description:
 */
public class CustomerServiceImpl implements CustomerService {
    
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /*
    保存客户
     */
    @Override
    public void save(Customer customer) {
        System.out.println("业务层，保存客户...");
        customerDao.save(customer);
    }

    /*
    分页查询
     */
    @Override
    public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        return customerDao.findByPage(pageCode, pageSize, criteria);
    }
}
