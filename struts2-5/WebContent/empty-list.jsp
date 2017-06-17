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
	<s:form action="empty-save.action">
		<s:textfield name="firstName" label="First Name"></s:textfield>
		<s:textfield name="lastName" label="Last Name"></s:textfield>
		<s:textfield name="email" label="Email"></s:textfield>
		<s:submit value="提交"></s:submit>
	</s:form>
	
	<table cellpadding="10" cellspacing="0" border="1">
		<thead>
			<tr>
				<td>EmployeeId</td>
				<td>FirstName</td>
				<td>LastName</td>
				<td>Email</td>
				<td>Edit</td>
				<td>Delete</td>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="#request.emps">
				<tr>
					<td>${employeeId }</td>
					<td>${firstName }</td>
					<td>${lastName }</td>
					<td>${email }</td>
					<td><a href="empty-edit.action?employeeId=${employeeId }">Edit</a></td>
					<td><a href="empty-delete.action?employeeId=${employeeId }">Delete</a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</body>
</html>