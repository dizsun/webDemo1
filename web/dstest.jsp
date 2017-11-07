<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="dserror.jsp" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wxdb"
            , "root", "root");
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("select * from account");
	while(rs.next()){
	    out.write(rs.getInt(1)+"<br/>"
                +rs.getString(2)+"<br/>"
                +rs.getString(3)+"<br/>"
                +String.valueOf(rs.getTimestamp(4)).replace(".0","")+"<br/>"
                +rs.getInt(5)+"<br/>"
        +rs.getString(6));
    }
%>
<%--<table bgcolor="aaaaaa" border="1" width="480">--%>
<%--<tr>--%>
<%--<th>ID</th>--%>
<%--<th>Name</th>--%>
<%--<th>Code</th>--%>
<%--<th>District</th>--%>
<%--<th>Population</th>--%>
<%--</tr>--%>
<%--<%--%>
<%--while(rs.next()){--%>
<%--%>--%>
<%--<tr>--%>
<%--<td><%=rs.getString(1) %></td>--%>
<%--<td><%=rs.getString(2) %></td>--%>
<%--<td><%=rs.getString(3) %></td>--%>
<%--<td><%=rs.getString(4) %></td>--%>
<%--<td><%=rs.getString(5) %></td>--%>
<%--</tr>--%>
<%--<%--%>
<%--}--%>
<%--%>--%>
<%--</table>--%>
</body>
</html>