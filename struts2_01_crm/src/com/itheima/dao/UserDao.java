package com.itheima.dao;

import com.itheima.domain.User;
import com.itheima.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:通过用户名和密码(已经封装成了User对象)查询数据库
 */
public class UserDao {

    public User findByNameAndPwd(User user){
        Session session = HibernateUtils.getCurrentSession();
        //使用用户名和密码进行查询
        Query query = session.createQuery("from User where username = ? and password = ?");
        //设置参数
        query.setParameter(0, user.getUsername());
        query.setParameter(1, user.getPassword());
        //
        List<User> list = query.list();
        if (list.size() > 0){
            User u = list.get(0);//不加泛型的话，返回的是Object 类型
            return u;
        }

        return null;



    }
}
