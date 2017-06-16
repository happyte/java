<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.zs.struts.valuestack.Person"%>
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
	
	s:set 向page,request,session,application域对象中加入一个属性值
	<br>
	<!-- value属性值自动进行OGNL解析 -->
	<s:set value="productName" scope="page" var="item"></s:set>
	productName:${pageScope.item }
	<br><br>
	
	s:push 向值栈中压入对象，结束完弹出
	<br>
	<%
		Person person = new Person();
		person.setName("tree");
		person.setAge(25);
		request.setAttribute("person", person);
	%>
	<s:push value="#request.person">
		${name }
	</s:push>
	<br><br>
	s:if s:else s:elseif 可以直接使用值栈中的属性
	<br><br>
	<s:if test="productPrice > 100">
		高档货
	</s:if>
	<s:elseif test="productPrice > 80">
		中档货
	</s:elseif>
	<s:else>
		垃圾货
	</s:else>
	<br><br>
	s:iterator,遍历集合，并把遍历到的每个元素压入弹出值栈
	<%
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("AA",10));
		persons.add(new Person("BB",20));
		persons.add(new Person("CC",30));
		persons.add(new Person("DD",40));
		request.setAttribute("persons", persons);
	%>
	<br><br>
	<s:iterator value="#request.persons" status="status">
		${status.index}--${name }--${age }<br>
	</s:iterator>
	<br><br>
	<br><br>
	<br><br>
	
</body>
</html>