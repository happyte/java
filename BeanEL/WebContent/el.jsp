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
	<form action="el.jsp" method="post">
		username:<input type="text" name="username"
		value="<%= request.getParameter("username") == null? "":request.getParameter("username") %>">
		<br>
		username:<input type="text" name="username" value="${param.username }">
		<br>
		<input type="submit" value="Submit">
	</form>
	
	<a href="el2.jsp?score=61&name=happyte&name=zs">To EL2 Page</a>
</body>
</html>