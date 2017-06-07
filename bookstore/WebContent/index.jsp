
 	<%
		response.sendRedirect(request.getContextPath() + "/bookServlet?method=getBooks");
	%> 
	<!--post方式 表单中的参数是不会显示到url中的 get 方式是会显示的 -->
<%-- 	<form action="<%=request.getContextPath() %>/bookServlet?method=getBooks" method="post">
		minPrice:<input type="text" name="minPrice"><br>
		maxPrice:<input type="text" name="maxPrice"><br>
		<input type="submit" value="submit">
	</form> --%> 
	<!-- EL表达式获取contextPath不能直接使用requestScope.contextPath -->
	<%-- <a href="${pageContext.request.contextPath }/bookServlet?method=getBooks&pageNostr=10">Test</a> --%>
