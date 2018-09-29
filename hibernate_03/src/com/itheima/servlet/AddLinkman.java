package com.itheima.servlet;

import com.itheima.domain.CstLinkmanEntity;
import com.itheima.service.LinkmanService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/17
 * @description:添加联系人
 */
@WebServlet(name = "addLinkman")
public class AddLinkman extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先解决中午乱码
        request.setCharacterEncoding("UTF-8");

        //接收用户添加的所有联系人信息，包括所属的客户的信息，但是这个信息在联系人的javabean中没有，所以封装不了一个联系人bean对象
        Map<String, String[]> map = request.getParameterMap();

        //先把客户的id获取到
        String scustId = map.get("custId")[0];

        //转换成Long类型
        Long custId = Long.parseLong(scustId);

        //将其他数据封装,有属性就封装，没有属性就不封装
        CstLinkmanEntity man = new CstLinkmanEntity();
        try {
            //封装数据
            BeanUtils.populate(man, map);

            //调用业务层去保存联系人
            new LinkmanService().save(man, custId);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
