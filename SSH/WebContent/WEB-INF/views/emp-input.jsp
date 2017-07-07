<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s"  uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(":input[name=lastName]").change(function() {
			var val = $(this).val();
			val = $.trim(val); //删除空格符
			var $this = $(this);
			//输入不为空
			if(val != ""){
				//清除font标签
				$this.nextAll("font").remove();
				var url = "emp-validateLastName";
				var args = {"lastName":val,"time":new Date()};
				//发送请求
				$.post(url,args,function(data){
					//可以使用
					if(data == "1"){
						$this.after("<font color='green'>名字可用!</font>");
					}
					//不能使用
					else if(data == "0"){
						$this.after("<font color='red'>名字不可用!</font>");
					}
					else{
						alert("服务器错误");
					}
				});
			}
			else{
				alert("名字不能为空");
			}
		});
	})
</script>
</head>
<body>
	<s:form action="emp-save" method="post">
		<!-- 需要进行Ajax操作，因为有些名字是不能用的, 在网页源代码上显示的是input标签 -->
		<s:if test="id != null">
			<s:textfield name="lastName" label="名字" disabled="true"></s:textfield>
			<s:hidden name="id"></s:hidden>
		</s:if>
		<s:else>
			<s:textfield name="lastName" label="名字"></s:textfield>
		</s:else>
		<s:textfield name="email" label="邮箱"></s:textfield>
		<s:textfield name="birth" label="生日"></s:textfield>
		<s:select list="#request.departments" listKey="id" 
				listValue="departmentName" name="department.id" label="部门">
		</s:select>
		<s:submit></s:submit>
	</s:form>
</body>
</html>