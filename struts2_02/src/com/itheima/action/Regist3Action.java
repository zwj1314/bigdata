package com.itheima.action;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:模型驱动的方式，实现ModelDriven接口,必须手动实例化对象（需要自己new）
 */
public class Regist3Action extends ActionSupport implements ModelDriven<User>{

    //必须要手动实例化
    private User user = new User();

    //获取模型对象
    @Override
    public User getModel() {
        return user;
    }

    public String execute() throws Exception {


        return NONE;
    }

}
