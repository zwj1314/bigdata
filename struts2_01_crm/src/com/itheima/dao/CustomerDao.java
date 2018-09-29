package com.itheima.dao;

import com.itheima.domain.CstCustomerEntity;
import com.itheima.utils.HibernateUtils;
import org.hibernate.Session;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:
 */
public class CustomerDao {


    public void save(CstCustomerEntity customer) {
        Session session = HibernateUtils.getCurrentSession();
        session.save(customer);
    }
}
