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
	<center>
	<!-- 查询用户权限表单 -->
		<form action="authorityServlet?method=getAuthorities" method="post">
			name:<input type="text" name="username">
			<input type="submit" value="Submit">
		</form>
		<br><br>
		<!-- 用户非空，才展示权限列表 -->
		<c:if test="${!empty requestScope.user }">
			${requestScope.user.username }的权限是:
			<br><br>
			<form action="" method="post">
				<c:forEach items="${requestScope.authorities }" var="auth">
					<input type="checkbox" name="authority">${auth.dispalyName }<br><br>
				</c:forEach>
				<input type="submit" value="Update">
			</form>
		</c:if>
	</center>
</body>
</html>