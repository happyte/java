<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s"  uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:form action="emp-save" method="post">
		<s:textfield name="lastName" label="名字"></s:textfield>
		<s:textfield name="email" label="邮箱"></s:textfield>
		<s:textfield name="birth" label="生日"></s:textfield>
		<s:textfield name="createTime" label="创建时间"></s:textfield>
		<s:select list="#request.departments" listKey="id" 
				listValue="departmentName" name="department.id" label="部门">
		</s:select>
		<s:submit></s:submit>
	</s:form>
</body>
</html>