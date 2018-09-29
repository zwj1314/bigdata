package com.itheima.dao;

import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/26
 * @description:
 */
public interface CustomerDao {
    
    public void save(Customer customer);


    public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}
