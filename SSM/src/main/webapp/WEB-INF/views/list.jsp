<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.2.1.min.js"></script>
<link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 搭建页面 -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM整合项目</h1>
			</div>
		</div>
		<!-- 新增删除按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary btn-sm"> 
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>新增
				</button>
				<button class="btn btn btn-danger btn-sm"> 
				<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
				</button>
			</div>
		</div>
		<!-- 员工列表 -->
		<div class="row">
			<table class="table">
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>邮箱</th>
					<th>性别</th>
					<th>部门</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${pageInfo.list }" var="emp">
					<tr>
					<th>${emp.empId }</th>
					<th>${emp.empName }</th>
					<th>${emp.email }</th>
					<th>${emp.gender=="F"?"男":"女"}</th>
					<th>${emp.department.deptName }</th>
					<th>
						<button class="btn btn-info btn-sm"> 
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑
						</button>
						<button class="btn btn-danger btn-sm"> 
							<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
						</button>
					</th>
				</tr>
				</c:forEach>
			</table>
		</div>
		<!-- 分页 -->
		<div class="row ">
			<!-- 当前一共有多少页 -->
			<div class="col-md-6">
				当前第${pageInfo.pageNum }页，共有${pageInfo.pages }页，总计${pageInfo.total }条记录
			</div>
			<!-- 分页导航 -->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				  <ul class="pagination">
				  	<li><a href="${APP_PATH}/emps?pageNo=1">首页</a></li>
				  	<c:if test="${pageInfo.hasPreviousPage }">
				  		<li>
				      		<a href="${APP_PATH}/emps?pageNo=${pageInfo.pageNum-1}" aria-label="Previous">
				        		<span aria-hidden="true">&laquo;</span>
				     		 </a>
				    	</li>
				  	</c:if>
				    <c:forEach items="${pageInfo.navigatepageNums }" var="page_num">
				    	<c:if test="${page_num == pageInfo.pageNum }">
				    		<li class="active"><a href="#">${page_num}</a></li>
				    	</c:if>
				    	<c:if test="${page_num != pageInfo.pageNum }">
				    		<li><a href="${APP_PATH}/emps?pageNo=${page_num}">${page_num}</a></li>
				    	</c:if>
				    </c:forEach>
				    <c:if test="${pageInfo.hasNextPage }">
				    	<li>
				      		<a href="${APP_PATH}/emps?pageNo=${pageInfo.pageNum+1}" aria-label="Next">
				        		<span aria-hidden="true">&raquo;</span>
				      		</a>
				    	</li>
				    </c:if>
				    <li><a href="${APP_PATH}/emps?pageNo=${pageInfo.pages}">尾页</a></li>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>