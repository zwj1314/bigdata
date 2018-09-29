package com.itheima.dao;

import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
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

    /**
     *
     * @param pageCode
     * @param pageSize
     * @param criteria
     * @return
     * 实现分页查询的功能
     */
    @Override
    public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        PageBean<Customer> page = new PageBean<Customer>();
        page.setPageCode(pageCode);
        page.setPageSize(pageSize);

        //先查询总记录数  select count(*)
        criteria.setProjection(Projections.rowCount());
        List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
        if (list != null && list.size() > 0){
            int totalCount = list.get(0).intValue();
            //总的记录数
            page.setTotalCount(totalCount);
        }

        //强调：把select count（*） 先晴空给，变成 select * ...
        criteria.setProjection(null);

        //提供分页查询
        List<Customer> beanList = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode - 1) * pageSize, pageSize);
        //分页查询数据，每页显示的数据，使用limit
        page.setBeanList(beanList);
        return page;
    }
}
