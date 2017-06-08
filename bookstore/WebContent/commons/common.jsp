<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="script/jquery-3.2.1.js"></script>
<script type="text/javascript">
	$(function () {
		$("a").click(function () {
			var serializeVal = $(":hidden").serialize();  //序列化的结果为minPrice=&maxPrice=
			//this.href = href;   //window.location.href = href无法改变a标签中的href
			window.location.href = this.href + "&" + serializeVal;
			return false;
		});
	});
</script>

<input type="hidden" name="minPrice" value="${param.minPrice }">
<input type="hidden" name="maxPrice" value="${param.maxPrice }">