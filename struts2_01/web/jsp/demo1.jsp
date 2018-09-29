<%--
  Created by IntelliJ IDEA.
  User: zhangjian
  Date: 2018/8/17
  Time: 下午11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>传统的配置文件的方式</title>
</head>
<body>

<h3>传统的配置文件的方式</h3>
<a href="${ pageContext.request.contextPath}/save.action">保存客户</a>
<a href="${pageContext.request.contextPath}/delete.action">删除客户</a>


<h3>通配符的方式(通常使用)</h3>
<a href="${ pageContext.request.contextPath}/linkman_save.action">保存联系人</a>
<a href="${pageContext.request.contextPath}/linkman_delete.action">删除联系人</a>



<h3>动态方法访问的方式</h3>
<a href="${ pageContext.request.contextPath}/user!save.action">保存用户</a>
<a href="${pageContext.request.contextPath}/user!delete.action">删除用户</a>


</body>
</html>
