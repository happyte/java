<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var lastName = $(this).next(":input").val();
			var flag = confirm("确定要删除"+lastName+"的信息吗?");
			if(flag){
				//执行Ajax删除
				var $tr = $(this).parent().parent();
				var url = this.href;
				var args = {"time":new Date()};
				$.post(url,args,function(data){
					if(data == "1"){
						$tr.remove();
						alert("删除成功");
					}
					else if(data == "0")
						alert("删除失败");
				});
			}
			//取消超级链接
			return false;
		});
	})
</script>
</head>
<body>
	<s:if test="#request.employees.size() == 0">
		没有任何员工信息
	</s:if>
	<s:else>
		<table border="1" cellspacing="0" cellpadding="10">
			<tr>
				<td>ID</td>
				<td>LastName</td>
				<td>Email</td>
				<td>Birth</td>
				<td>CreateTime</td>
				<td>Dept</td>
				<td>Delete</td>
			</tr>
			<s:iterator value="#request.employees">
				<tr>
					<td>${id}</td>
					<td>${lastName }</td>
					<td>${email }</td>
					<td>${birth }</td>
					<td>${createTime }</td>
					<td>${department.departmentName }</td>
					<td>
						<a href="emp-delete.action?id=${id}" class="delete">Delete</a>
						<input type="hidden" value="${lastName }">
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:else>
</body>
</html>