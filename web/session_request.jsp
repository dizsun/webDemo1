<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择物品</title>
</head>
<body>
<form method="post" action="session_response.jsp">
	书籍：<input type="checkbox" name="item" value="book"><br/>
	电脑：<input type="checkbox" name="item" value="computer"><br/>
	汽车：<input type="checkbox" name="item" value="car"><br/>
	<input type="submit" value="购买">
</form>
</body>
</html>