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
</body>
</html>