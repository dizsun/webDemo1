<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="dserror.jsp" %>
<%@ page import="java.sql.*" %>
<%@ page import="util.DbDao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    DbDao dbDao = (DbDao)getServletConfig().getServletContext().getAttribute("dbDao");
    Timestamp date = Timestamp.valueOf("2017-11-07 10:33:42");
    dbDao.insert("insert into account(name,code,creator,brief_intro,date) value(?,?,?,?,?)",
            "hello","12423","fdah","53473",date);
    ResultSet resultSet = dbDao.query("select id from account where date=?",date);
    if(resultSet.next()){
        int id = resultSet.getInt("id");
        dbDao.insert("insert into user_account(user_openid,account_id) value(?,?)","o4mLz0Jyvs84tOLwk1BSXPZHTtVU",id);
        response.getWriter().write("400");
    }else {
        response.getWriter().write("410");
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