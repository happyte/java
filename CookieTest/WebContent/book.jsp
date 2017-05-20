<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Book Detail Page</h4>
	<%= request.getParameter("book")%>
	<br><br>
	<a href="books.jsp">Return ...</a>
	
	<%
 		//1.获取超链接参数
		String book = request.getParameter("book");
		//2.获取所有的cookies,因为不一定都是有关book的cookies
		Cookie[] cookies = request.getCookies();
		//3.用于存放有关book的cookie的list
		//ArrayList<Cookie> bookCookies = new ArrayList<Cookie>();
		List<Cookie> bookCookies = new ArrayList<Cookie>();
		//4.用于获取books.jsp传入的book相匹配的cookie
		Cookie tempCookie = null;
		if(cookies != null && cookies.length > 0){
			for(Cookie cookie:cookies){
				//与book相关的cookie
				if(cookie.getName().startsWith("ZS_BOOK_")){
					bookCookies.add(cookie);
					//books.jsp传入的book相匹配的cookie已经存在于list中
					if(cookie.getValue().equals(book)){
						tempCookie = cookie;
						System.out.println("出现的tempCookie:"+tempCookie.getValue());
					}
				}
			}
		}
		//如果tempCookie不在cookies中，则取之前的cookie(即为第一个cookie),删除该cookie
		if(tempCookie == null && bookCookies.size() >= 5){
			tempCookie = bookCookies.get(0);
		}
		//两种情况，tempCookie为第一个cookie,或者已经存在其中
		if(tempCookie != null){
			tempCookie.setMaxAge(0);
			response.addCookie(tempCookie);
		}
		//把参数的book封装成一个cookie
		Cookie cc = new Cookie("ZS_BOOK_"+book,book);
		response.addCookie(cc); 
		
	%>

</body>
</html>