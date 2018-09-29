package com.itheima.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:系统角色
 */
public class Role {

    private Long rid;
    private String rname;

    private Set<User> users = new HashSet<User>();

    public Role(Long rid, String rname) {
        this.rid = rid;
        this.rname = rname;
    }

    public Role() {
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                '}';
    }
}
