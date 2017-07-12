<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="springmvc/testMethod" method="post">
		<input type="submit" value="Submit">
	</form>
	<a href="hello">Hello</a>
	<br>
	
	<a href="springmvc/testRequestMapping">testRequestMapping</a>
	<br>
	
	<a href="springmvc/testMethod">testMethod</a>
	<br>
	
	<form action="springmvc/testMethod" method="post">
		<input type="submit" value="testMethod">
	</form>
	<br>
	
	<a href="springmvc/testHeadersAndParameters?username=zs&age=20">testHeadersAndParameters</a>
	<br>
	
	<a href="springmvc/testAntPath/mnxyz/abc">Test AntPath</a>
	<br>
	
	<a href="springmvc/testPathVariable/1">Test testPathVariable</a>
	<br>
	
	<a href="springmvc/testRestGet/1">Test testGet</a>
	<br>
	
	<form action="springmvc/testRestPost" method="post">
		<input type="submit" value="testPost">
	</form>
	<br>
	
	<form action="springmvc/testRestPut/1" method="post">
		<input type="hidden" name="_method" value="PUT"/>
		<input type="submit" value="TestRest PUT"/>
	</form>
	<br>
	
	<form action="springmvc/testRestDelete/1" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
		<input type="submit" value="TestRest DELETE"/>
	</form>
	<br>
	
	<a href="springmvc/testRequestParam?username=happyte&age=10">Test testRequestParam</a>
	<br>
	
	<a href="springmvc/testRequestHeader">Test testRequestHeader</a>
	<br>
	
	<form action="springmvc/testPojo" method="post">
		Username:<input type="text" name="username"><br>
		Password:<input type="password" name="password"><br>
		Email:<input type="text" name="email"><br>
		Age:<input type="text" name="age"><br>
		Province:<input type="text" name="address.province"><br>
		City:<input type="text" name="address.city"><br>
		<input type="submit" value="Submit POJO">
	</form>
	<br>
	
	<a href="springmvc/testModelANDView">Test testModelANDView</a>
	<br>
	
	<a href="springmvc/testMap">Test testMap</a>
	<br>
	
	<a href="springmvc/testSessionAttribute">Test testSessionAttribute</a>
	<br>
	<!-- 
		模拟修改的过程，密码为123456,此时的user是new出来的，不是从数据库中获取的
	 -->
	<form action="springmvc/testModelAttribute" method="post">
		<input type="hidden" name="id" value="1">
		username:<input type="text" name="username" value="happyte"><br>
		email:<input type="text" name="email" value="zs511129@163.com"><br>
		age:<input type="text" name="age" value="24"><br>
		<input type="submit" value="Submit">
	</form>
	<br>
	
	<a href="springmvc/testView">test View</a>
</body>
</html>