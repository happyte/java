<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//若可以获取到请求参数name,则打印出欢迎信息。把登陆信息设置到Cookie中，并设置Cookie的最大时限
	 	String name = request.getParameter("name");
		if(name != null && !name.trim().equals("")){
			Cookie cookie = new Cookie("name", name);
			cookie.setMaxAge(30);    //设置最大时限30s
			response.addCookie(cookie);
		}
		//没有获取到请求参数
		else{
			//从cookie中读取该用户信息
			Cookie[] cookies = request.getCookies();
			if(cookies != null && cookies.length > 0){
				for(Cookie cookie:cookies){
					if("name".equals(cookie.getName())){
						name = cookie.getValue();
					}
				}
			}
		}
		
		if(name != null && !name.trim().equals("")){
			out.println("Hello:"+name);
		}
		else{
			//既没有从login中获取用户信息，也没有从cookie中获取用户信息，则重定向到login.jsp
			response.sendRedirect("login.jsp");
		}
	%>

</body>
</html>