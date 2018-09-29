<%--
  Created by IntelliJ IDEA.
  User: zhangjian
  Date: 2018/8/18
  Time: 下午1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Struts2框架中使用Servlet的API</title>
</head>
<body>

<h3 演示servlet的API的第一种方式ActionContext类，通过该类中的一些方法来获取servlet的API（完全解耦）></h3>

<form action="${ pageContext.request.contextPath }/demo1Action.action" method="post">
    姓名:<input type="text" name="username"/><br/>
    密码:<input type="password" name="password"/><br/>
    <input type="submit" value="注册" />
</form>

<h3>使用的是ServletActionContext类中的方法</h3>

<form action="${ pageContext.request.contextPath }/demo2Action.action" method="post">
    姓名:<input type="text" name="username"/><br/>
    密码:<input type="password" name="password"/><br/>
    <input type="submit" value="注册" />
</form>

</body>
</html>
