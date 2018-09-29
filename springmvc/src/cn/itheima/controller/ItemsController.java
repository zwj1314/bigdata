package cn.itheima.controller;

import cn.itheima.pojo.Items;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/31
 * @description:
 */
@Controller
public class ItemsController {

    //指定url到请求方法的映射
    @RequestMapping("/list")
    public ModelAndView itemsList(){
        List<Items> itemList = new ArrayList<>();

        //商品列表
        Items items_1 = new Items();
        items_1.setName("联想笔记本_3");
        items_1.setPrice(6000f);
        items_1.setDetail("ThinkPad T430 联想笔记本电脑！");

        Items items_2 = new Items();
        items_2.setName("苹果手机");
        items_2.setPrice(5000f);
        items_2.setDetail("iphone6苹果手机！");

        itemList.add(items_1);
        itemList.add(items_2);

        //模型和试图
        //model模型：模型对象中存放了返回给页面的数据
        //view试图:视图对象中指定了返回的页面的位置
        ModelAndView modelAndView = new ModelAndView();

        //将返回给页面的数据放入模型和视图对象中
        modelAndView.addObject("itemList", itemList);

        //
        modelAndView.setViewName("itemList");

        return modelAndView;

    }
}
