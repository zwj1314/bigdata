package com.itheima.action;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/18
 * @description:
 */
public class UserAction extends ActionSupport {

    /**
     * 处理登录功能
     *
     */
    public String login() {
        //还没有学习怎么封装数据，现在还需要使用request对象

        //获取request对象
        HttpServletRequest request = ServletActionContext.getRequest();
        //获取请求参数
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            //封装参数
            BeanUtils.populate(user, map);
            //调用业务层
            User u = new UserService().login(user);
            //判断
            if (u == null){
                //说明用户名或者密码错误
                return LOGIN;
            }else {
                request.getSession().setAttribute("u", u);
                return SUCCESS;
            }

        } catch (Exception e){
            e.printStackTrace();

        }

        return NONE;
    }
}
