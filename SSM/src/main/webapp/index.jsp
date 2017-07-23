<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<!-- 添加员工模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">员工添加</h4>
	      </div>
	      <div class="modal-body">
	       	<form class="form-horizontal">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">姓名</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="empName" placeholder="姓名">
				    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">邮箱</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="email" placeholder="邮箱">
				    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">性别</label>
				<label class="checkbox-inline">
					<input type="checkbox" id="inlineCheckbox1" value="M" checked="checked"> 男
				</label>
				<label class="checkbox-inline">
					<input type="checkbox" id="inlineCheckbox1" value="F"> 女
				</label>
			  </div>
			  <div class="form-group">
			  	<label class="col-sm-2 control-label">部门名称</label>
			  		<div class="col-sm-4">
					    <select class="form-control">
						 
						</select>
					</div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary">保存</button>
	      </div>
	    </div>
	  </div>
	</div>

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
				<button class="btn btn-primary btn-sm" id="emp_add_modal_button"> 
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>新增
				</button>
				<button class="btn btn btn-danger btn-sm"> 
				<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
				</button>
			</div>
		</div>
		<!-- 员工列表 -->
		<div class="row">
			<table class="table" id="emps_table">
				<thead>
					<tr>
						<th>ID</th>
						<th>姓名</th>
						<th>邮箱</th>
						<th>性别</th>
						<th>部门</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
		<!-- 分页 -->
		<div class="row ">
			<!-- 当前一共有多少页 -->
			<div class="col-md-6" id="page_info_area">
			</div>
			<!-- 分页导航 -->
			<div class="col-md-6" id="page_nav_area">
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			//去首页
			to_page(1);
		});
		function to_page(pageNo){
			$.ajax({
				url:"${APP_PATH}/emps",
				data:"pageNo="+pageNo,
				type:"GET",
				success:function(result){
					console.log(result);
					//1、解析并显示员工数据
					build_emps_table(result);
					//2、解析分页信息
					build_page_info(result);
					//3、解析分页栏
					build_page_nav(result);
				}
			});
		}
		function build_emps_table(result) {
			//清除之前的表格数据
			$("#emps_table tbody").empty();
			//用户信息
			var emps = result.extendMap.pageInfo.list;
			$.each(emps,function(index,item){
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var emailTd = $("<td></td>").append(item.email);
				var genderTd = $("<td></td>").append(item.gender == 'M'? "男":"女");
				var deptNameTd = $("<td></td>").append(item.department.deptName);
				var editBtn = $("<button></button>").addClass("btn btn-info btn-sm")
							  .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
							  .append("编辑");
				var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm")
				              .append($("<span></span>").addClass("glyphicon glyphicon-trash"))   
				              .append("删除");
				var btnTd  = $("<td></td>").append(editBtn).append(" ").append(delBtn);
				$("<tr></tr>").append(empIdTd)
							  .append(empNameTd)
							  .append(emailTd)
							  .append(genderTd)
							  .append(deptNameTd)
							  .append(btnTd)
							  .appendTo("#emps_table tbody");
			});
		}
		function build_page_info(result) {
			//清除之前的数据
			$("#page_info_area").empty();
			var pageNum = result.extendMap.pageInfo.pageNum;
			var pages = result.extendMap.pageInfo.pages;
			var total = result.extendMap.pageInfo.total;
			$("#page_info_area").append("当前第"+pageNum+"页，共有"+pages+"页，总计"+total+"条记录");
		}
		function build_page_nav(result) {
			//清除之前的数据
			//标签需要一层一层包含
			$("#page_nav_area").empty();
			var ul = $("<nav></nav>").addClass("pagination");
			var firstPageLi = $("<li></li>").append($("<a></a>").append("首页"));
			var prePageLi  = $("<li></li>").append($("<a></a>").append("&laquo;"));
			//如果已经是第一页
			if(result.extendMap.pageInfo.hasPreviousPage == false){
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			}
			else {
				firstPageLi.click(function() {
					to_page(1);
				});
				prePageLi.click(function() {
					to_page(result.extendMap.pageInfo.pageNum-1);
				});
			}
			var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
			var lastPageLi = $("<li></li>").append($("<a></a>").append("末页"));
			//已经是最后一页
			if(result.extendMap.pageInfo.hasNextPage == false){
				lastPageLi.addClass("disabled");
				nextPageLi.addClass("disabled");
			}
			else {
				lastPageLi.click(function() {
					to_page(result.extendMap.pageInfo.pages);
				});
				nextPageLi.click(function() {
					to_page(result.extendMap.pageInfo.pageNum+1);
				});
			}
			ul.append(firstPageLi).append(prePageLi);
			$.each(result.extendMap.pageInfo.navigatepageNums,function(index,item) {
				var numLi = $("<li></li>").append($("<a></a>").append(item));
				if(result.extendMap.pageInfo.pageNum == item){
					numLi.addClass("active");
				}
				numLi.click(function() {
					to_page(item);
				});
				ul.append(numLi);
			});
			ul.append(nextPageLi).append(lastPageLi);
			var nav = $("<nav></nav>").append(ul);
			nav.appendTo("#page_nav_area");
		}
		
		$("#emp_add_modal_button").click(function(){
			$('#myModal').modal({
				backdrop:"static"
			});
		});
	</script>
</body>
</html>