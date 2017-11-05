<%--
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
<%=request.getHeader("Cookie")%>
<%=request.getSession(true).getAttribute("flag")%>
</body>
</html>
