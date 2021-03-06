<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
		$(function () {
			
			$("#pageNo").change(function () {
				var val = $(this).val();
				val = $.trim(val);    //去掉前后的空格符号
				var reg = /^\d+$/g;   //正则表达式,以数字开头结尾，且最少一个数字
				//正则表达式不匹配
				if(!reg.test(val)){
					$(this).val("");    //置为空
					alert("输入的页码不合法");
					return;
				}
				//如果输入的数字超过范围
				var pageNo = parseInt(val);   //转化成数字
				if(pageNo < 1 || pageNo > parseInt("${bookpage.totalPageNumber}")){
					$(this).val("");    //置为空
					alert("输入的页码不合法");
					return;
				}
				window.location.href = "bookServlet?method=getBooks&pageNo=" + pageNo + "&" + $(":hidden").serialize();
				return false;
			});
		})
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>加入购物车</h2>
		<c:if test="${!empty param.title }">
		您已经将<font color="red">${param.title }</font>加入到购物车中
		</c:if>
		<br>
		<c:if test="${!empty sessionScope.ShoppingCart.books }">
		您的购物车中有${sessionScope.ShoppingCart.bookNumber }件商品,
		<a href="bookServlet?method=toForwardPage&page=cart&pageNo=${bookpage.pageNo }">查看购物车</a>
		</c:if>
	</center>
	<br><br>
 	<center>
 		<!-- 只在表单中设置minPrice和maxPrice，翻页时查询条件会丢 -->
		<form action="bookServlet?method=getBooks" method="post">
			Price:<input type="text" size="5" name="minPrice">-
			<input type="text" size="5" name="maxPrice">
			<input type="submit" value="查找">
		</form>
		<table cellpadding="10">
			<c:forEach items="${requestScope.bookpage.list }" var="book">
				<tr>
					<td>
						<a href="bookServlet?method=getBook&pageNo=${bookpage.pageNo }&id=${book.id}">
							${book.title }
						</a>
						<br>
						${book.author }
					</td>
					<td>
						${book.price }
					</td>
					<td>
						<!-- 把id传过去是为了查找该本书，title是回显 -->
						<a href="bookServlet?method=addToCart&pageNo=${bookpage.pageNo }&id=${book.id}&title=${book.title}">
							加入购物车
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		共${bookpage.totalPageNumber }页
		&nbsp;&nbsp;
		当前第${bookpage.pageNo }页
		&nbsp;&nbsp;
		<c:if test="${bookpage.pageNo != 1 }">
			<a href="bookServlet?method=getBooks&pageNo=1">首页</a>
			&nbsp;&nbsp;
			<a href="bookServlet?method=getBooks&pageNo=${bookpage.prePage }">上一页</a>
			&nbsp;&nbsp;
		</c:if>
		<c:if test="${bookpage.pageNo != bookpage.totalPageNumber }">
			<a href="bookServlet?method=getBooks&pageNo=${bookpage.nextPage }">下一页</a>
			&nbsp;&nbsp;
			<a href="bookServlet?method=getBooks&pageNo=${bookpage.totalPageNumber }">末页</a>
			&nbsp;&nbsp;
		</c:if>
		转到<input type="text" size="5" id="pageNo">页
		
	</center> 
</body>
</html>