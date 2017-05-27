<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>


<c:if test="${!empty requestScope.message }">
	<br><br>
	<font color="red">${requestScope.message }</font>
	<br><br>
</c:if>

<body>
	<form action="addCustomer.do" method="post">
		<table>
			<tr>
				<td>CustomerName:</td>
				<td>
				<input type="text" name="name" value=<%= request.getParameter("name") == null ? "":request.getParameter("name") %>>
				</td>
			</tr>
			<tr>
				<td>address:</td>
				<td>
				<input type="text" name="address" value=<%= request.getParameter("address") == null ? "":request.getParameter("address") %>>
				</td>
			</tr>
			<tr>
				<td>phone:</td>
				<td>
				<input type="text" name="phone" value=<%= request.getParameter("phone") == null ? "":request.getParameter("phone") %>>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="submit"></td>
			</tr>
		</table>
	</form>

</body>
</html>