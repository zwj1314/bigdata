package cn.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/9/1
 * @description:
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    //跳转到登录页面
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/submit")
    public String submit(String username, String pwd, HttpServletRequest request){
        HttpSession session = request.getSession();
        //判断用户名和密码的正确性,如果正确则将登录信息放到session中
        //这里简写，实际项目开发中需要去数据库中校验用户名和密码
        if ( username != null){
            session.setAttribute("username", username);
        }

        //跳转到列表页面
        return "redirect:/list";
    }

}
