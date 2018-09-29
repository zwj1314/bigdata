<%--
  Created by IntelliJ IDEA.
  User: zhangjian
  Date: 2018/8/18
  Time: 下午2:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>使用EL表达式来获取值</h3>

${requestScope.msg}
${sessionScope.msg}
${applicationScopeScope.msg}

</body>
</html>
