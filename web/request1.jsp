<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>request请求处理</title>
</head>
<body>
<%
	Enumeration<String> headerNames=request.getHeaderNames();
	while(headerNames.hasMoreElements()){
		String headerName=headerNames.nextElement();
		out.println(headerName+"-->"+request.getHeader(headerName)+"<br/>");
	}
	out.println("<hr/>");
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
%>
您的名字是:<%=name %>
</body>
</html>