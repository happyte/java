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
	
	ProductName:<s:property value="test.productName"/>
	<br><br>
	
	ProductDesc:<s:property value="[0].productDesc"/>
	<br><br>
	
	ProductDesc:<s:property value="[1].productDesc"/>
	<br><br>
	
	ProductPrice:<s:property value="[0].productPrice"/>
	<br><br>
	
	
</body>
</html>