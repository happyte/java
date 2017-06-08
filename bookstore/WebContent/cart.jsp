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
	<center>
		<h2>查看购物车</h2>
		您的购物车中有${sessionScope.ShoppingCart.bookNumber }本书
 		<table cellpadding="10">
			<tr>
				<td>书名</td>
				<td>数量</td>
				<td>价格</td>
				<td>&nbsp;</td>
			</tr>
			<!--items是ShoppingCartItem的集合  -->
 			<c:forEach items="${sessionScope.ShoppingCart.items }" var="item">
				<tr>
					<td>${item.book.title }</td>
					<td><input type="text" size="5" name="quantity" value="${item.quantity }"></td>
					<td>${item.book.price }</td>
					<td><a href="">删除</a></td>
				</tr>
			</c:forEach> 
 			<tr>
				<td>总金额:￥${sessionScope.ShoppingCart.totalMoney }</td>
			</tr> 
			<tr>
				<td>
					<a href="">继续购物</a>
					&nbsp;&nbsp;
					<a href="">清空购物车</a>
					&nbsp;&nbsp;
					<a href="">结账</a>
					&nbsp;&nbsp;
				</td>
			</tr>
		</table> 
	</center>
</body>
</html>