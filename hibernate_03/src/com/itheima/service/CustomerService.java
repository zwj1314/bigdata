package com.itheima.service;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.CstCustomerEntity;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/16
 * @description:
 */
public class CustomerService {

    //新增保存客户的信息，接收SaveCustomer传来的参数，调用CustomerDao的方法
    public void saveCustomer(CstCustomerEntity c){

        new CustomerDao().save(c);

    }

    //查询所有的客户信息
    public List<CstCustomerEntity> findAll(){
        return new CustomerDao().findAll();
    }

    public List<CstCustomerEntity> findAll(String custName){
        return new CustomerDao().findAll(custName);
    }
}
