<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="script/jquery-3.2.1.js"></script>
<script type="text/javascript">
		$(function () {
			$("a").click(function () {
				var serializeVal = $(":hidden").serialize();  //序列化的结果为minPrice=&maxPrice=
				window.location.href = this.href + "&" + serializeVal;
				return false;
			});
		})
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="hidden" name="minPrice" value="${param.minPrice }">
	<input type="hidden" name="maxPrice" value="${param.maxPrice }">
	<center>
		<br><br>
		书名:${book.title }
		<br><br>
		作者:${book.author }
		<br><br>
		单价:${book.price }
		<br><br>
		出版时间:${book.publishingDate }
		<br><br>
		评论:${book.remark }
		<br><br>
		<a href="bookServlet?method=getBooks&pageNostr=${param.pageNostr }">继续购物</a>
	</center>
</body>
</html>