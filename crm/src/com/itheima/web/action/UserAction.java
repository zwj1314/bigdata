package com.itheima.web.action;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/27
 * @description:用户的控制器
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private User user = new User();

    @Override
    public User getModel() {
        return user;
    }


    /*
    注册功能
     */
    public String regist() {
//      System.out.println("注册成功...");
        //接收请求的参数
        userService.save(user);
        return LOGIN;

    }

    /*
    验证登录的功能,在用户注册的页面，根据登录名判断登录名是否已经存在
     */
    public String checkCode() {
        //ajax请求获取到code，由于是模型驱动，所以该code自动封装在user中，调用业务层，查询
        User u = userService.checkCode(user.getUser_code());
        //获取response对象
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        try {
            //获取输出流
            PrintWriter writer = response.getWriter();
            //进行判断
            if (u != null) {
                //说明登录名查询到用户了，已经存在，不能注册
                writer.print("no");

            } else {
                //不存在此登录名，可以注册
                writer.print("yes");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

    /*
    登录功能
     */
    public String login(){
        User existUser = userService.login(user);
        if (existUser == null){
            //用户名或者密码输入错误
            return LOGIN;
        }else {
            ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
            //登录成功
            return SUCCESS;
        }
    }

    /*
    退出功能
     */
    public String exit(){
        ServletActionContext.getRequest().getSession().removeAttribute("existUser");
        return LOGIN;
    }
}
