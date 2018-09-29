package com.itheima.service;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.CstCustomerEntity;
import com.itheima.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:客户的业务层，保存客户
 */
public class CustomerService {

    public void saveCustomer(CstCustomerEntity customer){
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();


        //调用持久层的方法，保存
        new CustomerDao().save(customer);


        tr.commit();
    }
}
