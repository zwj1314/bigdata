package com.itheima.service;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.MD5Utils;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/27
 * @description:用户的业务层
 */
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /*
    通过登录名进行校验
     */
    @Override
    public User checkCode(String user_code) {

        return userDao.checkCode(user_code);
    }

    /*
    保存用户，密码需要加密
     */
    @Override
    public void save(User user) {
        String pwd = user.getUser_password();
        //给密码进行加密
        user.setUser_password(MD5Utils.md5(pwd));
        //用户的状态默认是1
        user.setUser_state("1");
        //调用持久层
        userDao.save(user);
    }

    /*
    登录功能,通过登录名和密码做校验，但数据库中的密码是加密的
    需要先给密码加密，再查询
     */
    @Override
    public User login(User user) {
        String pwd = user.getUser_password();
        //给密码进行加密
        user.setUser_password(MD5Utils.md5(pwd));
        return userDao.login(user);

    }
}
