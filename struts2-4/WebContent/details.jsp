<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:debug></s:debug>
	<br><br>
	ProductName:<s:property value="productName"/>
	<br><br>
	
	ProductName:<s:property value="[1].productName"/>
	<br><br>
	
	ProductDesc:<s:property value="[0].productDesc"/>
	<br><br>
	
	ProductDesc:<s:property value="[1].productDesc"/>
	<br><br>
	
	ProductPrice:<s:property value="[0].productPrice"/>
	<br><br>
	
	value:<s:property value="#parameters.name[0]"/>
	<br>
	session:<s:property value="#session.date"/>
	<br>
	<s:url value="/testUrl" var="url">
		<!-- 对于value值，会自动进行OGNL解析,若不希望进行OGNL解析，则用单引号(双引号里面再加单引号) -->
		<s:param name="productName" value="'productName'"></s:param>
	</s:url>
	${url }
	<br>
	<!-- 构建一个请求action的地址 -->
	<s:url action="testAction" namespace="/hello"  method="save" var="url2"></s:url>
	${url2 }
	<br>
	
	<s:url value="testUrl" var="url3" includeParams="all"></s:url>
	${url3 }
	<br>
	
</body>
</html>