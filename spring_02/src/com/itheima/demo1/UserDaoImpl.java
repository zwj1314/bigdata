package com.itheima.demo1;

import org.springframework.stereotype.Repository;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/22
 * @description:UserDaoImpl交给了IOC的容器
 */
@Repository(value = "userDao") //相当于<bean id="userDao" class="com.itheima.demo1.UserDaoImpl"></bean>
public class UserDaoImpl implements UserDao {

    @Override
    public void save() {
        System.out.println("保存客户");
    }
}
