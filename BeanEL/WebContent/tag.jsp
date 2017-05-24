<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.zs.javaweb.domain.Customer"%>
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
	<%
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer(1,"zs",24));
		customers.add(new Customer(2,"happyte",20));
		customers.add(new Customer(3,"tree",23));
		request.setAttribute("customers", customers);
	%>
	<c:forEach items="${requestScope.customers }" var="customer">
		--${customer.id }--${customer.name }--${customer.age }<br>
	</c:forEach>
</body>
</html>