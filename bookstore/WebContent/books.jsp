<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="script/jquery-3.2.1.js"></script>
<script type="text/javascript">
		$(function () {
			$("a").click(function () {
				var serializeVal = $(":hidden").serialize();  //序列化的结果为minPrice=&maxPrice=
				var href = this.href + "&" + serializeVal;
				//this.href = href;   //window.location.href = href无法改变a标签中的href
				window.location.href = href;
				return false;
			});
			
			$("#pageNo").change(function () {
				var val = $(this).val();
				val = $.trim(val);    //去掉前后的空格符号
				var reg = /^\d+$/g;   //正则表达式,以数字开头结尾，且最少一个数字
				//正则表达式匹配
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
				var href = "bookServlet?method=getBooks&pageNostr=" + pageNo + "&" + $(":hidden").serialize()
				window.location.href = href;
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
	<br><br>
	<input type="hidden" name="minPrice" value="${param.minPrice }">
	<input type="hidden" name="maxPrice" value="${param.maxPrice }">
 	<center>
 		<!-- 只在表单中设置minPrice和maxPrice，翻页时查询条件会丢 -->
		<form action="bookServlet?method=getBooks" method="post">
			
			Price:<input type="text" size="5" name="minPrice">-
			<input type="text" size="5" name="maxPrice">
			<input type="submit" value="查找">
		</form>
		<br><br>
		<table cellpadding="10">
			<c:forEach items="${requestScope.bookpage.list }" var="book">
				<tr>
					<td>
						${book.title }
						<br>
						${book.author }
					</td>
					<td>
						${book.price }
					</td>
					<td>
						<a href="">加入购物车</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		共${bookpage.totalPageNumber }页
		&nbsp;&nbsp;
		当前第${bookpage.pageNo }页
		&nbsp;&nbsp;
		<c:if test="${bookpage.pageNo != 1 }">
			<a href="bookServlet?method=getBooks&pageNostr=1">首页</a>
			&nbsp;&nbsp;
			<a href="bookServlet?method=getBooks&pageNostr=${bookpage.prePage }">上一页</a>
			&nbsp;&nbsp;
		</c:if>
		<c:if test="${bookpage.pageNo != bookpage.totalPageNumber }">
			<a href="bookServlet?method=getBooks&pageNostr=${bookpage.nextPage }">下一页</a>
			&nbsp;&nbsp;
			<a href="bookServlet?method=getBooks&pageNostr=${bookpage.totalPageNumber }">末页</a>
			&nbsp;&nbsp;
		</c:if>
		转到<input type="text" size="5" id="pageNo">页
		
	</center> 
</body>
</html>