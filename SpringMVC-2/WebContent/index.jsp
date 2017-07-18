<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#json").click(function() {
			url = this.href;
			args = {};
			$.post(url,args,function(data){
				for(var i=0;i<data.length;i++){
					var id = data[i].id;
					var lastName = data[i].lastName;
					alert(id+":"+lastName);
				}
			})
			return false;
		});
	})
</script>
<title>Insert title here</title>
</head>
<body>
	<form action="testUpload" method="post" enctype="multipart/form-data">
		File:<input type="file" name="file">
		Desc:<input type="text" name="desc">
		<input type="submit" value="Submit">
	</form>
	<a href="emps">List All Employees</a>
	<br>
	<a href="testJson" id="json">testJson</a>
	<!--  
		关于国际化:
		1. 在页面上能够根据浏览器语言设置的情况对文本(不是内容), 时间, 数值进行本地化处理
		2. 可以在 bean 中获取国际化资源文件 Locale 对应的消息
		3. 可以通过超链接切换 Locale, 而不再依赖于浏览器的语言设置情况
		
		解决:
		1. 使用 JSTL 的 fmt 标签
		2. 在 bean 中注入 ResourceBundleMessageSource 的示例, 使用其对应的 getMessage 方法即可
		3. 配置 LocalResolver 和 LocaleChangeInterceptor
	-->	
	<br><br>
	<a href="i18n">I18N PAGE</a>
	<br><br>
	<a href="testExceptionHandlerExceptionResolver?i=2">Test ExceptionHandler ExceptionResolver</a>
</html>