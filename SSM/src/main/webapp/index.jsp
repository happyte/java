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
	<!-- 更新员工模态框 -->
	<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">员工更新</h4>
	      </div>
	      <div class="modal-body">
	       	<form class="form-horizontal">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">姓名</label>
				    <div class="col-sm-10">
				      <p class="form-control-static" id="empName_update_static"></p>
				    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">邮箱</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="email_update_input" name="email" placeholder="邮箱">
				      <span class="help-block"></span>
				    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">性别</label>
				<label class="checkbox-inline">
					<input type="radio" name="gender" id="inlineCheckbox1" value="M" checked="checked"> 男
				</label>
				<label class="checkbox-inline">
					<input type="radio" name="gender" id="inlineCheckbox1" value="F"> 女
				</label>
			  </div>
			  <div class="form-group">
			  	<label class="col-sm-2 control-label">部门名称</label>
			  		<div class="col-sm-4">
					    <select class="form-control" name="dId">
						 
						</select>
					</div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="update_emp_btn">更新</button>
	      </div>
	    </div>
	  </div>
	</div>

	<!-- 添加员工模态框 -->
	<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
				      <input type="text" class="form-control" id="empName_add_input" name="empName" placeholder="姓名">
				      <span class="help-block"></span>
				    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">邮箱</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="email_add_input" name="email" placeholder="邮箱">
				      <span class="help-block"></span>
				    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">性别</label>
				<label class="checkbox-inline">
					<input type="radio" name="gender" id="inlineCheckbox1" value="M" checked="checked"> 男
				</label>
				<label class="checkbox-inline">
					<input type="radio" name="gender" id="inlineCheckbox1" value="F"> 女
				</label>
			  </div>
			  <div class="form-group">
			  	<label class="col-sm-2 control-label">部门名称</label>
			  		<div class="col-sm-4">
					    <select class="form-control" name="dId">
						 
						</select>
					</div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="add_emp_btn">保存</button>
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
				<button class="btn btn btn-danger btn-sm" id="emp_delete_all"> 
				<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
				</button>
			</div>
		</div>
		<!-- 员工列表 -->
		<div class="row">
			<table class="table" id="emps_table">
				<thead>
					<tr>
						<th><input type="checkbox" id="check_all"></th>
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
		var curPage;
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
				var checkTd = $("<td><input type='checkbox' class='check_item'></td>")
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var emailTd = $("<td></td>").append(item.email);
				var genderTd = $("<td></td>").append(item.gender == 'M'? "男":"女");
				var deptNameTd = $("<td></td>").append(item.department.deptName);
				var editBtn = $("<button></button>").addClass("btn btn-info btn-sm update_btn")
							  .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
							  .append("编辑");
				editBtn.attr("edit_update",item.empId);
				var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
				              .append($("<span></span>").addClass("glyphicon glyphicon-trash"))   
				              .append("删除");
				delBtn.attr("emp_delete",item.empId);
				var btnTd  = $("<td></td>").append(editBtn).append(" ").append(delBtn);
				$("<tr></tr>").append(checkTd)
							  .append(empIdTd)
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
			curPage = pageNum;
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
		
		//ajax请求显示部门信息
		function getDept(ele) {
			$(ele).empty();
			$.ajax({
				url:"${APP_PATH}/getDepts",
				type:"GET",
				success:function(result){
					//console.log(result);
					$.each(result.extendMap.depts,function(){
						var option = $("<option></option>").append(this.deptName).attr("value",this.deptId);
						$(ele).append(option);
					}); 
				}
			});
		}
		
		$("#emp_add_modal_button").click(function(){
			//清除之前表单的信息
			reset("#empAddModal form");
			//先发送Ajax请求
			getDept("#empAddModal select");
			//显示表单
			$('#empAddModal').modal({
				backdrop:"static"
			});
		});
		//清空表单及样式
		function reset(ele) {
			$(ele)[0].reset();
			$(ele).find("*").removeClass("has-error has-success");
			$(ele).find(".help-block").text("");
		}
		
		$("#add_emp_btn").click(function() {
			if(!validate_form()){
				empNameValidate($("#empName_add_input").val());
				return false;
			}
			if($(this).attr("ajax-validate") == "error"){
				empNameValidate($("#empName_add_input").val());
				return false;
			} 
			$.ajax({
				url:"${APP_PATH}/emp",
				type:"POST",
				data:$("#empAddModal form").serialize(),
				success:function(result){
					if(result.code == 100){
						//关闭模态框
						$('#empAddModal').modal('hide');
						//跳转到最后一页
						to_page(1000);
					}
					else {
						if(undefined != result.extendMap.errorMap.email){
							show_validate_msg("#email_add_input", "error", result.extendMap.errorMap.email);
						}
						if(undefined != result.extendMap.errorMap.empName){
							show_validate_msg("#empName_add_input", "error", result.extendMap.errorMap.empName);
						}
					}
				}
			});
		});
		
		//校验输入与正则表达式是否匹配
		function validate_form() {
			//1、拿到要校验的数据，使用正则表达式
			var empName = $("#empName_add_input").val();
			var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
			if(!regName.test(empName)){
				//alert("用户名可以是2-5位中文或者6-16位英文和数字的组合");
				show_validate_msg("#empName_add_input", "error", "用户名可以是2-5位中文或者6-16位英文和数字的组合");
				return false;
			}else{
				show_validate_msg("#empName_add_input", "success", "");
			}
			
			//2、校验邮箱信息
			var email = $("#email_add_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(email)){
				show_validate_msg("#email_add_input", "error", "邮箱格式不正确");
				return false;
			}
			else {
				show_validate_msg("#email_add_input", "success", "");
			}
			return true;
		}
		
		//显示校验结果的提示信息
		function show_validate_msg(ele,status,msg){
			//清除当前元素的校验状态
			$(ele).parent().removeClass("has-success has-error");
			$(ele).next("span").text("");
			if("success"==status){
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);
			}else if("error" == status){
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
			}
		}
		
		//绑定用户名的change事件,function是回调函数
		$("#empName_add_input").change(function() {
			empNameValidate($(this).val());
		});
		
		function empNameValidate(ele) {
			var empName = ele;
			$.ajax({
				url:"${APP_PATH}/checkuser",
				data:"empName="+empName,
				type:"POST",
				contentType: "application/x-www-form-urlencoded",  //为了防止中文传到后台中文乱码
				success:function(result){
					if(result.code == 100){
						show_validate_msg("#empName_add_input","success","用户名可用");
						$("#add_emp_btn").attr("ajax-validate","success");
					}
					else {
						show_validate_msg("#empName_add_input","error",result.extendMap.rex_va);
						$("#add_emp_btn").attr("ajax-validate","error");
					}
				}
			});
		}
		
		$(document).on("click",".update_btn",function(){
			getDept("#empUpdateModal select");
			var id = $(this).attr("edit_update");
			getEmp(id);
			$("#update_emp_btn").attr("edit_update",id);
			$('#empUpdateModal').modal({
				backdrop:"static"
			});
		});
		
		function getEmp(id) {
			//发送ajax请求
			$.ajax({
				url:"${APP_PATH}/emp/"+id,
				type:"GET",
				success:function(result){
					console.log(result);
					var empData = result.extendMap.emp;
					$("#empName_update_static").text(empData.empName);
					$("#empUpdateModal input[name=email]").val(empData.email);
					$("#empUpdateModal input[name=gender]").val([empData.gender]);
					$("#empUpdateModal select").val([empData.dId]);
				}
			});
		}
		
		$("#update_emp_btn").click(function() {
			//先校验邮箱格式
			var email = $("#email_update_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(email)){
				show_validate_msg("#email_update_input", "error", "邮箱格式不正确");
				return false;
			}
			$.ajax({
				url:"${APP_PATH}/emp/"+$(this).attr("edit_update"),
				type:"PUT",
				data:$("#empUpdateModal form").serialize(),
				success:function(result){
					//关闭模态框
					$("#empUpdateModal").modal('hide');
					//跳转到当前页
					to_page(curPage);
				}
			});
		});
		
		$("#check_all").click(function() {
			//id一个页面只可以使用一次；class可以多次引用。
			$(".check_item").prop("checked",$("#check_all").prop("checked"));
		});
		
		$(document).on("click",".check_item",function(){
			//如果被选中的框等于所有框的个数
			var flag = $(".check_item:checked").length == $(".check_item").length;
			$("#check_all").prop("checked",flag);
		});
		
		$(document).on("click",".delete_btn",function(){
			//获取自定义属性
			var empName = $(this).parent().parent().find("td:eq(2)").text();
			var id = $(this).attr("emp_delete");
			if(confirm("确认要删除"+empName+"吗?")){
				$.ajax({
					url:"${APP_PATH}/emp/"+id,
					type:"DELETE",
					success:function(result){
						alert(result.message);
						to_page(curPage);
					}
				});
			}
		});
		
		$("#emp_delete_all").click(function() {
			var empNames = "";
			var id_strs = ""
			$.each($(".check_item:checked"),function(){
				empNames += $(this).parent().parent().find("td:eq(2)").text()+",";
				id_strs += $(this).parent().parent().find("td:eq(1)").text()+"-";
			}); 
			empNames = empNames.substring(0,empNames.length-1);
			id_strs = id_strs.substring(0,id_strs.length-1);
			$.ajax({
				url:"${APP_PATH}/emp/"+id_strs,
				type:"DELETE",
				success:function(result){
					alert(result.message);
					to_page(curPage);
				}
			});
		});
	</script>
</body>
</html>