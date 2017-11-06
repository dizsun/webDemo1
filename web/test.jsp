<%@ page import="util.RedisUtil" %><%--
  Created by IntelliJ IDEA.
  User: dizsun
  Date: 2017/11/2
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="index.jsp" %>
<html>
<body>
<%
    String str = request.getParameter("str");
    out.write(str+"</br>");
    out.write(new String(str.getBytes("ISO-8859-1"),"utf-8")+"</br>");
    out.write(new String(str.getBytes("ISO-8859-1"),"big5")+"</br>");
    out.write(new String(str.getBytes("ISO-8859-1"),"gbk")+"</br>");
    out.write(new String(str.getBytes("ISO-8859-1"),"gb2312")+"</br>");
    out.write(new String(str.getBytes(),"utf-8")+"</br>");
%>
</body>
</html>
