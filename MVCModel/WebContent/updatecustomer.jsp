<%@page import="com.zs.mvcapp.domain.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<% 
	Customer customer = (Customer)request.getAttribute("customer");
	Object msg = request.getAttribute("message");
	if(msg != null){
%>
	<br><br>
	<font color="red"><%=msg %></font>
	<br><br>
<% 
	}
%>

<body>
	<form action="update.do" method="post">
		<!-- 两个hidden类型是为了标记原来存储的name,和查询的id值 -->
		<input type="hidden" name="id" value="<%= customer.getId() %>">
		<input type="hidden" name="oldName" value=<%= customer.getName() %>>
		<table>
			<tr>
				<td>CustomerName:</td>
				<td>
				<input type="text" name="name" value=<%= customer.getName() %>>
				</td>
			</tr>
			<tr>
				<td>address:</td>
				<td>
				<input type="text" name="address" value=<%= customer.getAddress() %>>
				</td>
			</tr>
			<tr>
				<td>phone:</td>
				<td>
				<input type="text" name="phone"  value=<%= customer.getPhone() %>>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="submit"></td>
			</tr>
		</table>
</body>
</html>