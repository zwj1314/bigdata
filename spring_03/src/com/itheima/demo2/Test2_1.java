package com.itheima.demo2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/23
 * @description:测试JDBC的模版类，使用IOC的方式
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test2_1 {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Test
    public void run1(){
        jdbcTemplate.update("INSERT  INTO  t_account VALUES (null, ?, ?)", "zhouboheng", 10000);
    }


}
