package com.itheima.dao;

import com.itheima.domain.Customer;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/26
 * @description:
 */
@Transactional
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

/*    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }*/


    /*
    保存客户
     */
    @Override
    public void save(Customer customer) {
        System.out.println("持久层，保存客户...");
        //数据保存到数据库中
        this.getHibernateTemplate().save(customer);

    }

    /*
    修改客户
     */
    @Override
    public void update(Customer customer) {
        this.getHibernateTemplate().update(customer);
    }

    /*
    通过主键查询某个客户
     */
    @Override
    public Customer getById(Long id) {
        return this.getHibernateTemplate().get(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list =  (List<Customer>) this.getHibernateTemplate().find("from Customer ");
        return list;
    }

    /*
    演示延时加载
     */
    @Override
    public Customer loadById(long id) {
        return this.getHibernateTemplate().load(Customer.class, id);
    }
}
