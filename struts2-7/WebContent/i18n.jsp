<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:debug></s:debug>
	<s:form action="" theme="simple">
		<s:text name="username"><s:textfield name="username" label="%{getText('username')}"></s:textfield></s:text>
		<!-- key的方式是直接从资源文件中获取value值-->
		<s:text name="username"><s:textfield name="username" key="username"></s:textfield></s:text>
		<s:text name="password"><s:textfield name="password" key="password"></s:textfield></s:text>
		<s:submit key="submit" value="%{getText('submit')}"></s:submit>
	</s:form>
</body>
</html>