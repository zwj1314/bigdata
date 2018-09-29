package com.itheima.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.itheima.domain.Customer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/28
 * @description:
 */
public class Demo1 {
    /*
    演示fastjson的使用
     */
    @Test
    public void run1(){
        Customer c = new Customer();
        c.setCust_id(20L);
        c.setCust_name("zhangjian");
        c.setCust_phone("110");

        //装换成json的字符串
        String jsonString = JSON.toJSONString(c);
        System.out.println(jsonString);
    }

    @Test
    public void run2(){
        List<Customer> list = new ArrayList<Customer>();
        Customer c = new Customer();
        c.setCust_id(20L);
        c.setCust_name("zhangjian");
        c.setCust_phone("110");
        list.add(c);

        Customer c1 = new Customer();
        c1.setCust_id(20L);
        c1.setCust_name("zhangjian1");
        c1.setCust_phone("1100000");
        list.add(c1);

        //装换成json的字符串
        String jsonString = JSON.toJSONString(list);
        System.out.println(jsonString);
    }

    /*
    默认的情况下，fastjson禁止循环的引用
     */
    @Test
    public void run3(){
        List<Customer> list = new ArrayList<Customer>();
        Customer c = new Customer();
        c.setCust_id(20L);
        c.setCust_name("zhangjian");
        c.setCust_phone("110");
        list.add(c);

        list.add(c);


        //装换成json的字符串
        //String jsonString = JSON.toJSONString(list);
        //禁止循环的引用
        String jsonString = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);

        System.out.println(jsonString);
    }


    /*
    fastjson禁止循环的引用会产生死循环的问题
     */
    @Test
    public void run4(){
        Person p = new Person();
        p.setPname("zhangjian");

        Role r = new Role();
        r.setRname("管理员");

        p.setRole(r);
        r.setPerson(p);



        //禁止循环的引用,产生死循环
        /*
        解决方法：
            1.其中一个属性不转化为json，添加注解@JSONField(serialize = false)
         */
        String jsonString = JSON.toJSONString(r, SerializerFeature.DisableCircularReferenceDetect);

        System.out.println(jsonString);
    }


}
