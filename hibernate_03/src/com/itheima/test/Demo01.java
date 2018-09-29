package com.itheima.test;

import com.itheima.domain.CstCustomerEntity;
import com.itheima.domain.CstLinkmanEntity;
import com.itheima.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:测试一对多
 */
public class Demo01 {

    /**
     * 最麻烦的双向关联的方式，保存数据
     */
    @Test
    public void test1(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();
        //保存客户和联系人的数据
        CstCustomerEntity c1 = new CstCustomerEntity();
        c1.setCustName("zhangjian");

        //创建两个联系人
        CstLinkmanEntity l1 = new CstLinkmanEntity();
        l1.setLkmName("zhouboheng");

        CstLinkmanEntity l2 = new CstLinkmanEntity();
        l2.setLkmName("zhaowei");

        //演示双向关联
        c1.getLinkmans().add(l1);
        c1.getLinkmans().add(l2);

        l1.setCustomer(c1);
        l2.setCustomer(c1);

        //保存数据
        session.save(c1);
        session.save(l1);
        session.save(l2);

        tr.commit();


    }

    /*
    单向关联，如果不配置级联保存，出现异常。保存客户，级联联系人
     */
    @Test
    public void test2(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();
        //保存客户和联系人的数据
        CstCustomerEntity c1 = new CstCustomerEntity();
        c1.setCustName("zhangjian1");

        //创建两个联系人
        CstLinkmanEntity l1 = new CstLinkmanEntity();
        l1.setLkmName("zhouboheng1");

        CstLinkmanEntity l2 = new CstLinkmanEntity();
        l2.setLkmName("zhaowei1");

        //演示单向关联（级联保存）
        c1.getLinkmans().add(l1);
        c1.getLinkmans().add(l2);

        //保存数据
        session.save(c1);

        tr.commit();


    }

    /*
   单向关联，如果不配置级联保存，出现异常。保存联系人，级联客户
    */
    @Test
    public void test3(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();
        //保存客户和联系人的数据
        CstCustomerEntity c1 = new CstCustomerEntity();
        c1.setCustName("zhangjian1");

        //创建两个联系人
        CstLinkmanEntity l1 = new CstLinkmanEntity();
        l1.setLkmName("zhouboheng1");

        CstLinkmanEntity l2 = new CstLinkmanEntity();
        l2.setLkmName("zhaowei1");

        //演示单向关联（级联保存）联系人关联客户
        l1.setCustomer(c1);
        l2.setCustomer(c1);


        //保存数据
        session.save(l1);
        session.save(l2);

        tr.commit();

    }

    /*
    测试删除，客户下有两个联系人。正常情况下，客户是有外键约束的(联系人中有客户的id)，必须先删除联系人，然后才能删除客户
   */
    @Test
    public void test4(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();

        //先查询该客户
        CstCustomerEntity c1 = session.get(CstCustomerEntity.class, 1L);

        /*
        先将联系人的外键设为null，然后删除客户
        客户的信息被删除了，联系人的信息还在，只是外键为null了
         */
        session.delete(c1);

        tr.commit();

    }

    /*
    级联删除，删除客户后，其对应的联系人也会自动删除
   */
    @Test
    public void test5(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();

        //先查询该客户
        CstCustomerEntity c1 = session.get(CstCustomerEntity.class, 2L);

        /*
        级联删除
         */
        session.delete(c1);

        tr.commit();

    }

    /*
   级联删除，删除联系人，其对应的客户也会自动删除,客户对应的其他联系人的外键设为null
  */
    @Test
    public void test6(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();

        //先查询该联系人
        CstLinkmanEntity l1 = session.get(CstLinkmanEntity.class, 2L);

        /*
        级联删除
         */
        session.delete(l1);

        tr.commit();

    }

    /*
    解除关系，从集合中删除联系人
     */
    @Test
    public void test7(){

        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();

        //先获取客户
        CstCustomerEntity c1 = session.get(CstCustomerEntity.class, 1L);

        //获取联系人
        CstLinkmanEntity l1 = session.get(CstLinkmanEntity.class, 1L);

        //解除
        c1.getLinkmans().remove(l1);

        tr.commit();

    }

    /*
    孤儿删除，解除关系的时候删除联系人
     */
    @Test
    public void test8(){

        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();

        //先获取客户
        CstCustomerEntity c1 = session.get(CstCustomerEntity.class, 2L);

        //获取联系人
        CstLinkmanEntity l1 = session.get(CstLinkmanEntity.class, 2L);

        //解除
        c1.getLinkmans().remove(l1);

        tr.commit();

    }

    /*
    放弃外键的维护，需求：
    一个客户A有两个联系人1，2，客户B下没有人，现在想让客户A的一个联系人1归属到客户B下
     */
    @Test
    public void test9(){

        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();

        //先获取客户A
        CstCustomerEntity c2 = session.get(CstCustomerEntity.class, 2L);

        //获取联系人
        CstLinkmanEntity l1 = session.get(CstLinkmanEntity.class, 1L);

        //做双向关联（产生两天sql语句，都是对外键的维护）
        c2.getLinkmans().add(l1);
        l1.setCustomer(c2);

        //持久化对象不需要修改
        //session.update();

        tr.commit();

    }

    /*
    一对多保存数据时在开发中的规范：
    一方：放弃维护主键，不使用级联保存
    多方：级联保存（没有维护主键的选项），自动维护主键
     */
    @Test
    public void test10(){

        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();

        //级联保存
        CstCustomerEntity c1 = new CstCustomerEntity();
        c1.setCustName("zhangjian2");

        //创建两个联系人
        CstLinkmanEntity l1 = new CstLinkmanEntity();
        l1.setLkmName("zhouboheng1");

        CstLinkmanEntity l2 = new CstLinkmanEntity();
        l2.setLkmName("zhaowei1");


        l1.setCustomer(c1);
        l2.setCustomer(c1);

        session.save(l1);
        session.save(l2);

        tr.commit();

    }








}
