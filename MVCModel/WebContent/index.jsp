<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="scripts/jquery-3.2.1.js"></script>
	<script type="text/javascript">
		$(function () {
			$(".delete").click(function () {
				var content = $(this).parent().parent().find("td:eq(1)").text();
				var flag = confirm("确定要删除"+content+"的信息吗?");
				return flag;
			});	
		});
	</script>
</head>

<body>
	<form action="query.do" method="post">
		<table>
			<tr>
				<td>CustomerName:</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>address:</td>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<td>phone:</td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td><input type="submit" value="query"></td>
				<td><a href="newcustomer.jsp">Add New Customer</a></td>
			</tr>
		</table>
	</form>
	
	<!-- 判断request获取的customers是否为空 -->
	<c:if test="${!empty requestScope.customers }">
		<table border="1" cellpadding="10" cellspacing="0">
				<tr>
					<th>ID</th>
					<th>CustomerName</th>
					<th>address</th>
					<th>phone</th>
					<th>DELETE/UPDATE</th>
				</tr>
				<hr>
				<br><br>
		<c:forEach items="${requestScope.customers }" var="customer">
			<tr>
				<td>${customer.id }</td>
				<td>${customer.name }</td>
				<td>${customer.address }</td>
				<td>${customer.phone }</td>
				<td>
					<a href="delete.do?id=${customer.id }" class="delete">DELETE</a>
					<a href="edit.do?id=${customer.id }">UPDATE</a>
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
</body>
</html>