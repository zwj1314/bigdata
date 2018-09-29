package com.itheima.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:完全解耦合的方式，使用servlet的API
 */
public class demo1Action extends ActionSupport{

    @Override
    public String execute() throws Exception {
        //System.out.println("执行了...");
        //完全解耦合的方式
        ActionContext context = ActionContext.getContext();
        //获取到请求的参数,封装所有的请求的参数
        Map<String, Object> map = context.getParameters();
        //遍历获取数据
        Set<String> keys = map.keySet();
        for (String key: keys){
            //通过key来获取到值
            String[] vals =(String[]) map.get(key);
            System.out.println(key + ":" + Arrays.toString(vals));
        }

        //如果向request对象中存入值
        context.put("msg", "zhangjian");
        //获取其他map集合
        context.getSession().put("msg", "zhouboheng");
        context.getApplication().put("msg", "zhaowei");

        return SUCCESS;
    }
}
