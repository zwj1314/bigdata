package com.itheima.service;

import com.itheima.domain.Customer;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/26
 * @description:
 */
public interface CustomerService {

    public void save(Customer customer);

    public void update(Customer customer);

    public Customer getById(Long id);

    public List<Customer> findAll();

    public Customer loadById(long id);
}
