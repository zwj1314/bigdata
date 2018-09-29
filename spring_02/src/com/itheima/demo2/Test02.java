package com.itheima.demo2;

import com.itheima.demo1.UserService;
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
public class Test02 {

    @Resource(name = "userService")
    private UserService userService;

    @Test
    public void run1(){
        //原来的方式，获取工厂加载配置文件，getBean()

        //
        userService.sayHello();
    }
}
