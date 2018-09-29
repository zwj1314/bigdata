package com.itheima.demo3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/19
 * @description:
 */
public class User {

    private String[] arrs;
    private List<String> list;
    private Map<String, String> map;
    private Properties pro;

    public Properties getPro() {
        return pro;
    }

    public void setPro(Properties pro) {
        this.pro = pro;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }


    public String[] getArrs() {
        return arrs;
    }

    public void setArrs(String[] arrs) {
        this.arrs = arrs;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "User{" +
                "arrs=" + Arrays.toString(arrs) +
                ", list=" + list +
                ", map=" + map +
                ", pro=" + pro +
                '}';
    }
}
