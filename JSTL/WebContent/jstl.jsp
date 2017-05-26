<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.zs.javaweb.Customer"%>
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
	<h4><font color="red">c:out 对特殊字符进行转换</font></h4>
	<%
		request.setAttribute("book", "<<Java>>");
	%>
	book: ${requestScope.book }
	<br>
	book:<c:out value="${requestScope.book }" default="booktitle"></c:out>
	<br><br>
	
	<h4><font color="red">c:set 为域赋属性值,target,value都支持EL表达式,还可以对JavaBean属性赋值，好像不必存在空的构造函数</font></h4>
	<c:set var="subject" value="${param.subject }" scope="session"></c:set>
	subject:${sessionScope.subject }
	<br><br>
	
	<%
		Customer customer = new Customer(1,"zs",24);
		request.setAttribute("customer", customer);
	%>
	ID: ${requestScope.customer.id }
 	<c:set target="${requestScope.customer }" property="id" value="${param.id }"></c:set>
	<br>
 	ID: ${requestScope.customer.id }
 	<br><br>
 	
 	<h4><font color="red">c:remove 移除指定域对象的指定属性值</font></h4>
 	<c:set var="date" value="2017-5-25" scope="session"></c:set>
 	date:${sessionScope.date }
 	<br>
 	<c:remove var="date" scope="session"/>
 	date:${sessionScope.date }
 	<br><br>
 	
 	<h4><font color="red">c:if 不能实现else操作，但可以把结果存储起来</font></h4>
  	<c:set var="age" value="20" scope="request"></c:set>
  	<!-- test是个boolean值 -->
  	<c:if test="${requestScope.age > 18 }">成年了</c:if>
  	<br><br>
  	<c:if test="${param.age > 18 }" var="isAdult" scope="request"></c:if>
  	idAdult:${requestScope.isAdult }
  	<br><br>
  	
  	<h4><font color="red">c:choose,c:when,c:otherwise</font></h4>
  	<c:choose>
  		<c:when test="${param.age > 60 }">老年</c:when>
  		<c:when test="${param.age > 35 }">中年</c:when>
  		<c:when test="${param.age > 18 }">青年</c:when>
  		<c:otherwise>未成年</c:otherwise>
  	</c:choose>
  	<br><br>
  	
  	<h4><font color="red">c:forEach 对数组、Collection、Map进行遍历,begin,end,step</font></h4>
  	<%
  		List<Customer> custs = new ArrayList<Customer>();
  		custs.add(new Customer(1,"happyte",24));
  		custs.add(new Customer(2,"lemon",24));
  		custs.add(new Customer(3,"tree",24));
  		custs.add(new Customer(4,"haha",24));
  		custs.add(new Customer(5,"zs",24));
  		request.setAttribute("custs", custs);
  	%>
  	<c:forEach items="${requestScope.custs }" var="cust" begin="1" step="2" varStatus="status">
  		${status.index},${status.count},${status.first},${status.last},${cust.id }--${cust.name }--${cust.age }<br>
  	</c:forEach>
  	<br><br>
  	
  	<!-- 遍历Map -->
  	<%
  		Map<String,Customer>custMap = new HashMap<String,Customer>();
  		custMap.put("a", new Customer(1,"happyte",24));
  		custMap.put("b", new Customer(2,"lemon",24));
  		custMap.put("c", new Customer(3,"tree",24));
  		request.setAttribute("custMap",custMap);
  	%>
  	<c:forEach items="${requestScope.custMap }" var="cust">
  		${cust.key }--${cust.value.id }--${cust.value.name }<br>
  	</c:forEach>
	
</body>
</html>