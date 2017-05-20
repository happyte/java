<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Books Page</h4>
	<a href="book.jsp?book=JavaWeb">JavaWeb</a><br><br>
	<a href="book.jsp?book=Python_Web">Python_Web</a><br><br>
	<a href="book.jsp?book=JavaScript">JavaScript</a><br><br>
	<a href="book.jsp?book=IOS">IOS</a><br><br>
	<a href="book.jsp?book=Android">Android</a><br><br>
	<a href="book.jsp?book=Ajax">Ajax</a><br><br>
	<a href="book.jsp?book=Spring">Spring</a><br><br>
	<a href="book.jsp?book=Structs">Structs</a><br><br>
	<a href="book.jsp?book=SQL">SQL</a><br><br>
	
	<br><br>
	
	<%
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0){
			for(Cookie cookie:cookies){
				if(cookie.getName().startsWith("ZS_BOOK_")){
					out.println(cookie.getValue()+"   ");
				}
			}
		}
	%>

</body>
</html>