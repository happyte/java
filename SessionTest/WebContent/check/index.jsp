<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<font color="red">
<%-- 		<%= request.getAttribute("message") %> --%>
		 <%=session.getAttribute("message")%>
	</font>
	<form action="<%=request.getContextPath() %>/checkCodeServlet" method="post">
		name:<input type="text" name="name">
		checkcode:<input type="text" name="CHECK_CODE_PARAM">
		<img alt="" src="<%=request.getContextPath() %>/validateColorServlet">
		<input type="submit" value="提交">
	</form>

</body>
</html>