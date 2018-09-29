package com.itheima.service;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:处理登录的功能
 */
public class UserService {

    public User login(User user){//将要查询的用户名和密码封装成User对象，并返回User对象
        //使用事务
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();
        User existUser = null;
        try {
            //调用持久层，查询数据
            existUser = new UserDao().findByNameAndPwd(user);

        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
        return existUser;
    }

    /*@Test
    public void test(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("12345");
        User u = this.login(user);
        if (u != null){
            System.out.println("登录成功了...");
        } else {
            System.out.println("用户名或者密码错误");
        }


    }*/
}
