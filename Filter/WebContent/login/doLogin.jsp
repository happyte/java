<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 判断是否登录，登录的话把username传到session中，否则回到login.jsp -->
	<%
		String usernmae = request.getParameter("name");
		if(usernmae != null && !usernmae.trim().equals("")){
			session.setAttribute("userSessionKey", usernmae);
			response.sendRedirect("list.jsp");
		}
		else
			response.sendRedirect("login.jsp");
	%>

</body>
</html>