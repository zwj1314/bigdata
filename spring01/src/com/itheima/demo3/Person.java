package com.itheima.demo3;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/19
 * @description:
 */
public class Person {

    private String pname;
    private Car car;

    public Person(String pname, Car car) {
        this.pname = pname;
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pname='" + pname + '\'' +
                ", car=" + car +
                '}';
    }
}
