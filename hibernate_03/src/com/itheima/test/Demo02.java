package com.itheima.test;

import com.itheima.domain.Role;
import com.itheima.domain.User;
import com.itheima.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:
 */
public class Demo02 {

    @Test
    public void test1(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();

        /*
        模拟多对多,双向关联。由于双方都去维护外键，会报错。必须有一方放弃维护外键
         */
        //创建用户
        User u1 = new User();
        u1.setUsername("zhangjian1");
        User u2 = new User();
        u2.setUsername("zhangjian2");

        //创建角色
        Role r1 = new Role();
        r1.setRname("数据分析师");
        Role r2 = new Role();
        r2.setRname("大数据开发工程师");

        //关联
        u1.getRoles().add(r1);
        u1.getRoles().add(r2);
        r1.getUsers().add(u1);
        r2.getUsers().add(u1);

        u2.getRoles().add(r1);
        r1.getUsers().add(u2);

        session.save(u1);
        session.save(u2);
        session.save(r1);
        session.save(r2);

        tr.commit();
    }

    /*
    张三用户有2个角色，想让其没有其中一个角色
     */
    @Test
    public void test2(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();

        //查询张三用户
        User u1 = session.get(User.class, 1L);

        //查询角色
        Role r2 = session.get(Role.class, 2L);

        u1.getRoles().remove(r2);

        tr.commit();

    }

}
