<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="zs" uri="http://www.zs.com/jsp/mytag/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 父标签打印name到控制台 -->
	<zs:parentTag>
		<!-- 子标签把父标签的name属性打印到JSP页面上 -->
		<zs:sonTag/>
	</zs:parentTag>
</body>
</html>