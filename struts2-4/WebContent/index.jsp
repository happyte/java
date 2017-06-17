
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="product-save.action?name=zs" method="post">
		ProductName:<input type="text" name="productName">
		<br><br>
		ProductDesc:<input type="text" name="productDesc">
		<br><br>
		ProductPrice:<input type="text" name="productPrice">
		<br><br>
		<input type="submit" value="提交">
	</form>
	<%
		session.setAttribute("date", new Date());
	%>
	<a href="emp-input.action">emp-input</a>
</body>
</html>