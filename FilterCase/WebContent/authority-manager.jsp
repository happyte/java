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
			<form action="authorityServlet?method=updateAuthorities" method="post">
			<!-- 隐藏域是因为request更新了，update方法需要获取用户名 -->
			<input type="hidden" name="username" value="${requestScope.user.username }">
				<c:forEach items="${requestScope.authorities }" var="auth">
				<c:set var="flag" value="false"></c:set>
					<!-- 两层遍历，找出用户的权限，与当前行的权限进行对比 -->
					<c:forEach items="${ requestScope.user.authorities }" var="ua">
						<c:if test="${ua.url == auth.url }">
						<c:set var="flag" value="true"></c:set>
						</c:if>
					</c:forEach>
					<c:if test="${flag == true }">
						<input type="checkbox" name="authority" checked="checked" value="${auth.url }">${auth.dispalyName }<br><br>
					</c:if>
					<c:if test="${flag == false }">
						<input type="checkbox" name="authority" value="${auth.url }">${auth.dispalyName }<br><br>
					</c:if>
				</c:forEach>
				<input type="submit" value="Update">
			</form>
		</c:if>
	</center>
</body>
</html>