package com.itheima.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:系统用户
 */
public class User {

    private Long uid;
    private String username;
    private String password;

    //多对多中都是集合，集合必须手动初始化
    private Set<Role> roles = new HashSet<Role>();

    public User(Long uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
