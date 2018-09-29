package com.itheima.dao;

import com.itheima.domain.CstCustomerEntity;
import com.itheima.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/16
 * @description:
 */
public class CustomerDao {
    /*
    保存客户
     */

    public void save(CstCustomerEntity c){

        //先获取session
        Session session = null;
        Transaction tr = null;

        try {
            session = HibernateUtils.getSession();
            tr = session.beginTransaction();
            session.save(c);
            tr.commit();

        } catch (Exception e){
            //回滚事务
            tr.rollback();
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    //查询所有的客户信息,没有查询条件
    public List<CstCustomerEntity> findAll(){

        //QBC查询
        Session session = null;
        Transaction tr = null;
        List<CstCustomerEntity> list = null;

        try {
            session = HibernateUtils.getSession();
            tr = session.beginTransaction();
            Criteria criteria = session.createCriteria(CstCustomerEntity.class);
            list = criteria.list();
            tr.commit();

        } catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }

        return list;
    }

    //查询所有的客户信息
    public List<CstCustomerEntity> findAll(String custName){

        //QBC查询
        Session session = null;
        Transaction tr = null;
        List<CstCustomerEntity> list = null;

        try {
            session = HibernateUtils.getSession();
            tr = session.beginTransaction();

            //查询
            Criteria criteria = session.createCriteria(CstCustomerEntity.class);

            //添加查询条件
            if (custName != null && !custName.trim().isEmpty()){
                criteria.add(Restrictions.like("custName", "%"+custName+"%"));
            }

            list = criteria.list();
            tr.commit();

        } catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }

        return list;
    }


    //通过主键来查询客户
    public CstCustomerEntity findById(Long custId) {

        Session session = HibernateUtils.getCurrentSession();
        return session.get(CstCustomerEntity.class, custId);

    }
}
