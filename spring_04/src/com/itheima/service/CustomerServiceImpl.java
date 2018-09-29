package com.itheima.service;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;

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

    @Override
    public void save(Customer customer) {
        System.out.println("业务层，保存客户...");
        customerDao.save(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public Customer getById(Long id) {
        return customerDao.getById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public Customer loadById(long id) {
        return customerDao.loadById(id);
    }
}
