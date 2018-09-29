package com.itheima.domain;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:
 */
public class User {

    private Long uid;
    private String username;
    private String password;
    private String name;



    public User(Long uid, String username, String password, String name) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
