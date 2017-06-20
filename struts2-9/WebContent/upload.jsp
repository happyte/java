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
	<s:debug></s:debug>
	<s:form action="testUploadAction" method="post" enctype="multipart/form-data" theme="simple">
		<s:fielderror name="ppt"></s:fielderror>
		<s:actionerror/>
		PPT:<s:file name="ppt" label="PPT"></s:file><br>
		Desc:<s:textfield name="pptDesc[0]" label="PPTDESC"></s:textfield><br>
		
		<br>
 		PPT:<s:file name="ppt" label="PPT"></s:file><br>
		Desc:<s:textfield name="pptDesc[1]" label="PPTDESC"></s:textfield><br>
		
		<br>
		PPT:<s:file name="ppt" label="PPT"></s:file><br>
		Desc:<s:textfield name="pptDesc[2]" label="PPTDESC"></s:textfield><br>
		
		<br> 
		<s:submit></s:submit>
	</s:form>
</body>
</html>