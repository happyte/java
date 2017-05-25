<%@page import="com.zs.javaweb.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4><font color="red">c:out 对特殊字符进行转换</font></h4>
	<%
		request.setAttribute("book", "<<Java>>");
	%>
	book: ${requestScope.book }
	<br>
	book:<c:out value="${requestScope.book }" default="booktitle"></c:out>
	<br><br>
	
	<h4><font color="red">c:set 为域赋属性值,target,value都支持EL表达式,还可以对JavaBean属性赋值，好像不必存在空的构造函数</font></h4>
	<c:set var="subject" value="${param.subject }" scope="session"></c:set>
	subject:${sessionScope.subject }
	<br><br>
	
	<%
		Customer customer = new Customer(1,"zs",24);
		request.setAttribute("customer", customer);
	%>
	ID: ${requestScope.customer.id }
 	<c:set target="${requestScope.customer }" property="id" value="${param.id }"></c:set>
	<br>
 	ID: ${requestScope.customer.id }
 	<br><br>
 	
 	<h4><font color="red">c:remove 移除指定域对象的指定属性值</font></h4>
 	<c:set var="date" value="2017-5-25" scope="session"></c:set>
 	date:${sessionScope.date }
 	<br>
 	<c:remove var="date" scope="session"/>
 	date:${sessionScope.date }
	
</body>
</html>