package com.itheima.demo1;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/22
 * @description:
 */
public class CustomerDaoImpl implements CustomerDao {

    @Override
    public void save() {
        System.out.println("保存客户...");
    }

    @Override
    public void update() {
        System.out.println("修改客户...");
    }
}
