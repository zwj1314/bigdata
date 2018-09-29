package com.itheima.domain;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/26
 * @description:
 */
public class Customer {

    private Long cust_id;
    private String cust_name;
    private Long cust_user_id;
    private Long cust_create_id;

    /*//三个外键
    private String cust_source;
    private String cust_industry;
    private String cust_level;*/

    private String cust_linkman;
    private String cust_phone;
    private String cust_mobile;

    //一：客户的来源，多：客户
    private Dict source;
    //一：客户的行业 多：客户
    private Dict industry;
    //一：客户的级别 多：客户
    private Dict level;


    public Customer() {
    }

    public Long getCust_id() {
        return cust_id;
    }

    public void setCust_id(Long cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public Long getCust_user_id() {
        return cust_user_id;
    }

    public void setCust_user_id(Long cust_user_id) {
        this.cust_user_id = cust_user_id;
    }

    public Long getCust_create_id() {
        return cust_create_id;
    }

    public void setCust_create_id(Long cust_create_id) {
        this.cust_create_id = cust_create_id;
    }

    public String getCust_linkman() {
        return cust_linkman;
    }

    public void setCust_linkman(String cust_linkman) {
        this.cust_linkman = cust_linkman;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
    }

    public String getCust_mobile() {
        return cust_mobile;
    }

    public void setCust_mobile(String cust_mobile) {
        this.cust_mobile = cust_mobile;
    }

    public Dict getSource() {
        return source;
    }

    public void setSource(Dict source) {
        this.source = source;
    }

    public Dict getIndustry() {
        return industry;
    }

    public void setIndustry(Dict industry) {
        this.industry = industry;
    }

    public Dict getLevel() {
        return level;
    }

    public void setLevel(Dict level) {
        this.level = level;
    }



}
