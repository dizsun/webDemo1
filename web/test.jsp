<%@ page import="util.RedisUtil" %><%--
  Created by IntelliJ IDEA.
  User: dizsun
  Date: 2017/11/2
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="index.jsp" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
<%
    RedisUtil redisUtil=new RedisUtil();
    String sessionId = redisUtil.queryString(request.getSession().getId());
    if(sessionId!=null && sessionId!=""){
        out.write("hello");
    }else {
        out.write("error");
    }
%>
</body>
</html>
