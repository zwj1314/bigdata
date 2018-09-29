package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/12
 * @description:页面展示controller
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    @RequestMapping("/{page}")
    public String shoePage(@PathVariable String page){
        return page;
    }
}
