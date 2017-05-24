<%@page import="com.zs.javaweb.domain.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- EL运算符 -->
	${param.score > 60? "及格":"不及格"}
	
	<%
	 	List<String> names = new ArrayList<String>();
		names.add("zs");
		request.setAttribute("names", names);
	%>
	<br><br>
	<!-- empty可以作用于一个集合，若该集合不存在或者集合中没有元素，则结果为true -->
	names is empty:${empty requestScope.names}
	<br><br>
	contextPath:${pageContext.request.contextPath}  <!-- 当前WEB 项目的根路径 -->
	<br><br>
	sessionId:${pageContext.session.id}
	<br><br>
	sessionAttribute:${pageContext.session.attributeNames }
	<br><br>
	JSESSIONID:${cookie.JSESSIONID.name } -- ${cookie.JSESSIONID.value } <!-- 与上面的sessionId一样 -->
	<br><br>
<%-- 	names:${paramValues.name[0].class.name } --%>  <!-- 有问题 -->
	<!-- EL中的隐含对象 -->
	<%
		Customer cust = new Customer();
		cust.setAge(24);
		request.setAttribute("customer", cust);
	%>
	<br><br>
	<!-- EL中的.或[]运算符 -->
	age:${customer.age }
	<br>
	age:${requestScope["customer"]["age"] }

</body>
</html>