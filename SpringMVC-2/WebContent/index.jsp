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
	<a href="emps">List All Employees</a>
	<br>
	<a href="testJson" id="json">testJson</a>
</body>
</html>