<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
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
		<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">继续购物</a>
	</center>
</body>
</html>