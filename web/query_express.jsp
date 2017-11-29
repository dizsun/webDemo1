<%--
  Created by IntelliJ IDEA.
  User: dizsun
  Date: 2017/11/13
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>快递查询</title>
</head>
<body>
<form method="get" action="${pageContext.request.contextPath}/queryExpress">
    快递编号：<br/>
    <input type="text" name="postid">
    <hr/>
    <input type="submit" value="提交">
</form>
</body>
</html>
