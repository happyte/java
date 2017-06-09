<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/commons/common.jsp" %>
<script type="text/javascript">
	$(function () {
		$(".delete").click(function () {
		    var $tr = $(this).parent().parent();
			var title = $.trim($tr.find("td:first").text());
			var flag = confirm("确定要删除"+title+"的信息吗?")
			if(flag){
				return true;
			}
			return false;
		});
		
		//ajax修改单个商品的数量
		$(":text").change(function () {
			var quantityVal = $.trim(this.value);  //修改后的数量
			var reg = /^\d+$/g;
			var flag2 = false;
			var quantity = -1;
			//判断输入不合法的情况,两种情况，输入aaa,输入小于0的数
			if(reg.test(quantityVal)){
				quantity = parseInt(quantityVal);
				if(quantity >= 0){
					flag2 = true;
				}
			}
			//false，输入不合法
			if(!flag2){
				alert("输入不合法");
				$(this).val($(this).attr("class"));
				return;
			}
			var $tr = $(this).parent().parent();
			var title = $.trim($tr.find("td:first").text());
			
			//档修改的数量为0时，即quantity ＝ 0，应该删除该条记录，调用删除标签a的click方法
			if(quantity == 0){
				var flag =  confirm("确定要删除"+title+"吗?");
				if(flag){
					//得到节点a
					var $a = $tr.find("td:last").find("a");
					//调用节点a的click方法
					$a[0].click();
					return;
				}
				else{
					$(this).val($(this).attr("class"));
					return;
				}
			}
			var flag = confirm("确定要修改"+title+"的数量吗?");
			//不修改的话恢复到之前未修改的值
			if(!flag){
				$(this).val($(this).attr("class")); //class属性存放着数量值
				return;
			}
			//确认的话发送post请求到bookServlet
			var url = "bookServlet";
			//请求参数为method:updateItemQuantity,id:idVal,quantity:quantityVal
			var idVal = $.trim(this.name); //id值
			var args = {"method":"updateQuantity","id":idVal,"quantity":quantityVal};
			//发送post请求
			$.post(url,args,function(data){
				var bookNumber = data.bookNumber;
				var totalMoney = data.totalMoney;
				var modifyValue = data.modifyValue;
				//id是#号,class是.type是:
				$("#bookNumber").text("您的购物车中有"+bookNumber+"本书");
				$("#totalMoney").text("总金额:￥"+totalMoney);
				//并没有修改掉值
				$(this).attr("class",modifyValue);
				$(this).attr("value",modifyValue);
			},"JSON");
			return false;
		});
	})
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>查看购物车</h2>
		<div id="bookNumber">
			您的购物车中有${sessionScope.ShoppingCart.bookNumber }本书
		</div>
 		<table cellpadding="10">
			<tr>
				<td>书名</td>
				<td>数量</td>
				<td>价格</td>
				<td>&nbsp;</td>
			</tr>
			<!--items是ShoppingCartItem的集合  -->
  			<c:forEach items="${sessionScope.ShoppingCart.items }" var="item">
				<tr>
					<td>${item.book.title}</td>
					<td>
						<input type="text" class="${item.quantity }" size="5" name="${item.book.id }" value="${item.quantity }">
					</td>
					<td>${item.book.price }</td>
					<td>
						<!-- 在整个项目中，pageNo参数是一直要带上的，id这里是为了找到删除哪本书的购物车记录 -->
						<a href="bookServlet?method=remove&pageNo=${param.pageNo }&
						id=${item.book.id}" class="delete">
							删除
						</a>
					</td>
				</tr>
			</c:forEach>  
  			<tr>
				<td colspan="4" id="totalMoney">总金额:￥${sessionScope.ShoppingCart.totalMoney }</td>
			</tr>  
 			<tr>
				<td colspan="4">
					<!-- 同样添加隐藏域和js操作，回到带查询条件的翻页 -->
					<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">继续购物</a>
					&nbsp;&nbsp;
					<a href="bookServlet?method=clear&pageNo=${param.pageNo }">清空购物车</a>
					&nbsp;&nbsp;
					<a href="bookServlet?method=toForwardPage&page=cash">结账</a>
					&nbsp;&nbsp;
				</td>
			</tr> 
		</table> 
	</center>
</body>
</html>