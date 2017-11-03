<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
Map<String,Integer>itemMap=(Map<String,Integer>)session.getAttribute("itemMap");
if(itemMap==null){
	itemMap=new HashMap<String,Integer>();
	itemMap.put("书籍",0);
	itemMap.put("电脑",0);
	itemMap.put("汽车",0);
}
String[] buys=request.getParameterValues("item");
for(String item : buys){
	if(item.equals("book")){
		int num1=itemMap.get("书籍").intValue();
		itemMap.put("书籍",num1+1);
	}
	else if(item.equals("computer")){
		int num2=itemMap.get("电脑").intValue();
		itemMap.put("电脑",num2+1);
	}
	else if(item.equals("car")){
		int num3=itemMap.get("汽车").intValue();
		itemMap.put("汽车",num3+1);
	}
}
session.setAttribute("itemMap", itemMap);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>清单</title>
</head>
<body>
您购买的物品：<br/>
书籍：<%=itemMap.get("书籍") %>本<br/>
电脑：<%=itemMap.get("电脑") %>台<br/>
汽车：<%=itemMap.get("汽车") %>辆<br/>
<a href="session_request.jsp">再次购买</a>
</body>
</html>