<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath }/emp" method="post" modelAttribute="employee">
		<!-- path 属性对应 html 表单标签的 name 属性值 -->	
		<!-- 表单回显 -->
		<c:if test="${employee.id == null }">
			LastName:<form:input path="lastName"></form:input>
		</c:if>
		<c:if test="${employee.id != null }">
			<!-- 表单回显的时候employee对象是有id的，@ModelAttribute接受的是表单的id -->
			<form:hidden path="id"/>
			<input type="hidden" name="_method" value="PUT">
		</c:if>
		<br>
		Email:<form:input path="email"></form:input>
		<br>
 		<% 
			Map<String, String> genders = new HashMap();
			genders.put("1", "Male");
			genders.put("0", "Female");
			
			request.setAttribute("genders", genders);
		%>
		Gender:
		<br>
		<form:radiobuttons path="gender" items="${requestScope.genders }" delimiter="<br>"/> 
		<br>
		<!-- itemLabel是显示的值,itemValue是与path值对应的， -->
		Department:<form:select path="department.id" 
					items="${requestScope.departments }" itemLabel="departmentName" itemValue="id">
					</form:select> 
		<br>
		<input type="submit" value="提交">
	</form:form>
</body>
</html>