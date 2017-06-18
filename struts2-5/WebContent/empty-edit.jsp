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
	<s:debug></s:debug>
	<br>
	<s:form action="empty-update">
		<s:hidden name="employeeId"></s:hidden>
		<s:textfield name="firstName" label="First Name"></s:textfield>
		<s:textfield name="lastName" label="Last Name"></s:textfield>
		<s:textfield name="email" label="Email"></s:textfield>
		<s:submit value="提交"></s:submit>
	</s:form>
</body>
</html>