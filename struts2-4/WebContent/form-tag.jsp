<%@page import="com.zs.struts.valuestack.City"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 	<%
		List<City> cities = new ArrayList<City>();
		cities.add(new City(0,"zhejiang"));
		cities.add(new City(1,"jiangsu"));
		cities.add(new City(2,"beijing"));
		request.setAttribute("cities", cities);
	%>
	<s:debug></s:debug>
	<s:form action="save">
		<s:hidden name="userId"></s:hidden>
		<s:textfield name="username" label="UserName"></s:textfield>
		<s:password name="password" label="Password"></s:password>
		<s:textarea name="desc" label="Desc"></s:textarea>
 		<s:checkbox name="married" label="Married"></s:checkbox>
 		<!-- #代表一个map -->
  		<s:radio name="gender" list="#{'0':'Male','1':'Female'} " label="Gender"></s:radio>
  		<!-- 服务端需要设置成一个集合类型 -->
 		<s:checkboxlist name="cities" list="#request.cities" listKey="cityId" listValue="cityValue" label="Cities">
		</s:checkboxlist> 
		<!-- list中既作为键，又作为值 -->
		<s:select list="{11,12,13,14,15}" headerKey="" headerValue="请选择" name="age" label="Age">
			<!--s:optgroup可以作为select的子标签  -->
			<s:optgroup list="#{21:21} " label="21-30"></s:optgroup>
		</s:select>
		<s:submit></s:submit>
	</s:form>
</body>
</html>