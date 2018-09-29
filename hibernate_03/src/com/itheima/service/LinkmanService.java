package com.itheima.service;

import com.itheima.dao.CustomerDao;
import com.itheima.dao.LinkmanDao;
import com.itheima.domain.CstCustomerEntity;
import com.itheima.domain.CstLinkmanEntity;
import com.itheima.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:
 */
public class LinkmanService {

    /**
     * 编写业务，保存联系人
     * 先把客户查询出来，设置到联系人中，再保存联系人
     * @param man
     * @param custId
     */

    public void save(CstLinkmanEntity man, Long custId) {
        //先获取session
        Session session = HibernateUtils.getCurrentSession();
        //在service中开启事务
        Transaction tr = session.beginTransaction();

        try {
            //先查询客户，调用CustomDao中的查询方法
            CstCustomerEntity c = new CustomerDao().findById(custId);
            //设置
            man.setCustomer(c);
            //保存联系人,调用LinkmanDao中的save方法
            new LinkmanDao().save(man);
            System.out.println("保存联系人成功了...");

        } catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }


    }
}
