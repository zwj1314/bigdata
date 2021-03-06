package com.itheima.action;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:模型驱动的方式，实现ModelDriven接口,必须手动实例化对象（需要自己new）
 */
public class Regist5Action extends ActionSupport {


    //必须要手动实例化
    private Map<String, User> map;

    public Map<String, User> getMap() {
        return map;
    }

    public void setMap(Map<String, User> map) {
        this.map = map;
    }


    public String execute() throws Exception {
        System.out.println(map);
        return NONE;
    }

}
