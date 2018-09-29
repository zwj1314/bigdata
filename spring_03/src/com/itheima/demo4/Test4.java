package com.itheima.demo4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/26
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test4 {

    @Resource(name = "accountService")
    private AccountService accountService;


    @Test
    public void run1(){
        //调用支付的方法
        accountService.pay("zhangjian", "zhouboheng", 1000);
    }
}
