package com.itheima.dao;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/19
 * @description:
 */
public class CustomerDaoImpl implements CustomerDao {

    @Override
    public void save() {
        System.out.println("持久层保存客户>...<");
    }
}
