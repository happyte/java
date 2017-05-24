<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="customer" class="com.zs.javaweb.domain.Customer" scope="request">
	</jsp:useBean>
	
	<jsp:useBean id="customer2" class="com.zs.javaweb.domain.Customer" scope="request">
	</jsp:useBean>
	
	<jsp:setProperty property="age" name="customer" value="10"></jsp:setProperty>
	<!-- 若property的值为*,省略value属性值，将自动设置所有属性赋值为对应的请求参数的值,在url中设置 -->
	<jsp:setProperty property="*" name="customer2"/>
	
	
	customer_id:<jsp:getProperty property="id" name="customer"/>
	<br>
	customer_name:<jsp:getProperty property="name" name="customer"/>
	<br>
	customer_age:<jsp:getProperty property="age" name="customer"/>
	<br>
	
	customer2_id:<jsp:getProperty property="id" name="customer2"/>
	<br>
	
	customer2_name:<jsp:getProperty property="name" name="customer2"/>
	<br>
	
	customer2_age:<jsp:getProperty property="age" name="customer2"/>
	<br>

</body>
</html>