package com.itheima.action;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:模型驱动的方式，实现ModelDriven接口,必须手动实例化对象（需要自己new）
 */
public class Regist4Action extends ActionSupport{
    //必须要手动实例化
    private List<User> list;

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public String execute() throws Exception {
        System.out.println(list);
        return NONE;
    }

}
