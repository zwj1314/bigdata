package com.itheima.demo3;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/19
 * @description:演示构造方法的注入的方式
 */
public class Car {

    private String name;
    private double price;

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
