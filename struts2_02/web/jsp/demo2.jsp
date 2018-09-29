<%--
  Created by IntelliJ IDEA.
  User: zhangjian
  Date: 2018/8/18
  Time: 下午3:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>属性驱动的方式</h3>

<form action="${ pageContext.request.contextPath }/regist1.action" method="post">
    姓名:<input type="text" name="username"/><br/>
    密码:<input type="password" name="password"/><br/>
    年龄:<input type="text" name="age"/><br/>
    <input type="submit" value="注册"/>
</form>


<h3>属性驱动的方式（把数据封装到javabean的对象中）</h3>
<%--注意：页面的编写规则发生了变化，使用的是OGNL表达式的写法--%>
<form action="${ pageContext.request.contextPath }/regist2.action" method="post">
    姓名:<input type="text" name="user.username"/><br/>
    密码:<input type="password" name="user.password"/><br/>
    年龄:<input type="text" name="user.age"/><br/>
    <input type="submit" value="注册"/>
</form>


<h3>模型驱动的方式</h3>
<%--注意：页面的编写规则发生了变化，使用的是OGNL表达式的写法--%>
<form action="${ pageContext.request.contextPath }/regist3.action" method="post">
    姓名:<input type="text" name="username"/><br/>
    密码:<input type="password" name="password"/><br/>
    年龄:<input type="text" name="age"/><br/>
    <input type="submit" value="注册"/>
</form>


<h3>向List集合中封装数据，默认使用属性驱动的方式</h3>
<%--注意：页面的编写规则发生了变化，使用的是OGNL表达式的写法--%>
<form action="${ pageContext.request.contextPath }/regist4.action" method="post">
    姓名:<input type="text" name="list[0].username"/><br/>
    密码:<input type="password" name="list[0].password"/><br/>
    年龄:<input type="text" name="list[0].age"/><br/>
</form>

    姓名:<input type="text" name="list[1].username"/><br/>
    密码:<input type="password" name="list[1].password"/><br/>
    年龄:<input type="text" name="list[1].age"/><br/>
    <input type="submit" value="注册"/>
</form>


<h3>向map集合中封装数据，默认使用属性驱动的方式</h3>
<%--注意：页面的编写规则发生了变化，使用的是OGNL表达式的写法--%>
<form action="${ pageContext.request.contextPath }/regist5.action" method="post">
    姓名:<input type="text" name="map['one'].username"/><br/>
    密码:<input type="password" name="map['one'].password"/><br/>
    年龄:<input type="text" name="map['one'].age"/><br/>
</form>

    姓名:<input type="text" name="map['two'].username"/><br/>
    密码:<input type="password" name="map['two'].password"/><br/>
    年龄:<input type="text" name="map['two'].age"/><br/>
    <input type="submit" value="注册"/>
</form>

</body>
</html>
