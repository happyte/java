<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>SUCCESS!</h4>
	<br>
	date:${requestScope.time }
	<br>
	map:${requestScope.names }
	<br>
	user:${requestScope.user }
	<br>
	school:${requestScope.school }
	<br>
	user:${sessionScope.user }
	<br>
	school:${sessionScope.school }
	<br>
	<fmt:message key="i18n.username"></fmt:message>
	<br>
	<fmt:message key="i18n.password"></fmt:message>
	<br>
</body>
</html>