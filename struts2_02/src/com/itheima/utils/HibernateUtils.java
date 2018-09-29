package com.itheima.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:Hibernate SessionFactory工具类
 */
public class HibernateUtils {

    //
    private static final Configuration CONFIG;
    private static final SessionFactory FACTORY;

    static {
        CONFIG = new Configuration().configure();
        FACTORY = CONFIG.buildSessionFactory();
    }

    //getSession方法，返回Session对象
    public static Session getSession(){
        return FACTORY.openSession();
    }

    //getCurrentSession方法
    public static Session getCurrentSession(){
        //从ThreadLocal类中获取session对象
        return FACTORY.getCurrentSession();
    }

    public static void main(String[] args) {
        //调用获取session的方法
        getSession();
    }


}
