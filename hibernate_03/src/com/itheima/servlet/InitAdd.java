package com.itheima.servlet;

import com.itheima.domain.CstCustomerEntity;
import com.itheima.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:初始化到添加联系人的页面
 */
@WebServlet(name = "initAdd")
public class InitAdd extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询所有的客户
        List<CstCustomerEntity> list = new CustomerService().findAll();
        //保存到request中
        request.setAttribute("list", list);
        //转发到添加联系人的页面中（在添加的时候，先将所有的客户信息查询出来）
        request.getRequestDispatcher("/jsp/linkman/add.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
