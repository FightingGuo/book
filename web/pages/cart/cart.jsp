<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含base标签，css样式、jquery标签--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			//给 删除 绑上单机事件
			$("a.deleteItem").click(function () {
				/*
                * confirm是确认提示框
                * 参数是它的提示内容
                * 它有两个按钮，一个是确认，一个是取消
                * 返回true表示点击了确认，返回false表示点击取消
                * */
				return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").text() +"】?");
				// return false;组织元素的默认行为  不提交请求
			})

			//给 清空购物车 绑上单机事件
			$("a.clearItem").click(function () {
				/*
                * confirm是确认提示框
                * 参数是它的提示内容
                * 它有两个按钮，一个是确认，一个是取消
                * 返回true表示点击了确认，返回false表示点击取消
                * */
				return confirm("你确定要删除购物车里的所有商品吗?");
				// return false;组织元素的默认行为  不提交请求
			})
			
			//给输入数量框绑上change事件  onchange时JS的原生事件 change时Jquery里的事件 两者效果相同
			$(".updateItem").change(function () {
				//获取商品名字
				var name=$(this).parent().parent().find("td:first").text();
				//获取商品数量
				var count=this.value;
				//获取商品id
				var id=$(this).attr('bookId');

				if( confirm("你确定要将【"+name+"】的商品数量改为"+count+"吗?")){

					//为true就去请求服务器修改数量
					location.href="http://localhost:8080/book/cartServlet?action=updateItem&id="+id+"&count="+count;
				}else {
					//defaultValue属性是表单项Dom对象的属性。它表示默认的value属性值
					this.value=this.defaultValue;
				}
			})

		});
	</script>


</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%--静态包含登录成功之后的菜单--%>
		<%@ include file="/pages/common/logn_success.meu.jsp" %>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>

			<%--如果购物车为空--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">当前购物车为空!快去浏览商品吧！！</a></td>
				</tr>
			</c:if>

			<%--如果购物车非空输出以下页面--%>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
						<input class="updateItem" style="width:80px"
							   bookId="${entry.value.id}"
							   type="text" value="${entry.value.count}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>



		</table>
		<%--如果购物车非空才输出--%>
		<c:if test="${not empty sessionScope.cart.items}">
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a class="clearItem" href="cartServlet?action=clear">清空购物车</a></span>
			<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
		</div>
		</c:if>
	</div>
	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>