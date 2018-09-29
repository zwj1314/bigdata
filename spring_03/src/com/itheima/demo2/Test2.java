package com.itheima.demo2;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/23
 * @description:演示jdbc的模版类
 */
public class Test2 {

    /*
    演示模版类
     */
    @Test
    public void run1(){

        //Spring框架提供类内置的连接池，同时也可以整合其他的连接池
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring_day03");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");

        //创建模版类
        JdbcTemplate template = new JdbcTemplate();
        //设置连接池
        template.setDataSource(dataSource);
        //完成操作
        template.update("INSERT  INTO  t_account VALUES (null, ?, ?)", "zhangjian", 10000);
    }
}
