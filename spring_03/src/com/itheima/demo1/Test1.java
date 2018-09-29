package com.itheima.demo1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/22
 * @description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test1 {

    @Resource(name = "customerDao")
    private CustomerDao customerDao;

    @Test
    public void run1(){
        customerDao.save();
        customerDao.update();

    }
}
