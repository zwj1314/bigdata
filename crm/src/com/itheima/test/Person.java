package com.itheima.test;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/28
 * @description:
 */
public class Person {
    private String pname;

    @JSONField(serialize = false)
    private Role role;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
