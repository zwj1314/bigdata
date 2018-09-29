package com.itheima.aop;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/22
 * @description:
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void save() {
        System.out.println("保存用户...");
    }

    @Override
    public void update() {
        System.out.println("修改用户...");
    }
}
