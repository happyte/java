<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 问题1.如何覆盖默认的错误消息
		1） 类所在的包中建立ActionClassName.properties文件，
	        ActionClassName即为包含着输入字段的Action类的类名
		2） 在属性文件中添加如下键值对:invalid.fieldvalue.fieldName = xxx
		
		问题2.如果是simple主题，不会自动显示错误消息
		1)  通过debug标签观察到，如果转换出错，在值栈的Action(实现了ValidationAware接口)对象中有一个fieldErrors属性
		该属性的类型为Map<String,List<String>> 键:字段(属性名) 值:错误消息组成的List
		2)  还可以使用s:fielderror标签来显示，通过fieldName属性显示指定字段的错误
		
		问题3.
		
		问题4:如何自定义类型转换器
	 -->
	<s:debug></s:debug>
	<s:form action="conversion.action" method="post" theme="simple">
		<s:fielderror fieldName="age"></s:fielderror><br>
		<s:fielderror fieldName="date"></s:fielderror><br>
		Age:<s:textfield name="age" label="Age"></s:textfield>
		<br>
		Date:<s:textfield name="date" label="Date"></s:textfield>
		<br>
		<s:submit value="提交"></s:submit>
	</s:form>
</body>
</html>