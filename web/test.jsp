<%@ page import="util.ResponseJsonUtils" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: dizsun
  Date: 2017/11/2
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
<%
    String appid = "wx2ba5834caba16490";
    String appsecret = "6dd9a0d6d2da385980b9f388400eeaa8";
    String js_code = request.getParameter("js_code");
    String requestUrl="https://api.weixin.qq.com/sns/jscode2session";
    String outputStr ="appid=wx2ba5834caba16490&secret=6dd9a0d6d2da385980b9f388400eeaa8&js_code=" + js_code + "&grant_type=authorization_code";
    String httpResult= ResponseJsonUtils.httpRequest(requestUrl,outputStr);
    Map<String,Object> data=new HashMap<>();
    data.put("data",httpResult);
    ResponseJsonUtils.jsonp(response,data);
%>
</body>
</html>
