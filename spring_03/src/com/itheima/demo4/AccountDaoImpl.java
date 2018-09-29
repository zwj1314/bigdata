package com.itheima.demo4;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/26
 * @description:
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

    /*private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

    @Override
    public void outMoney(String out, double money) {
/*
        jdbcTemplate.update(sql, args);
*/
        this.getJdbcTemplate().update("update t_account set money = money - ? where name = ?", money, out);

    }

    @Override
    public void inMoney(String in, double money) {
        this.getJdbcTemplate().update("update t_account set money = money + ? where name = ?", money, in);
    }
}
