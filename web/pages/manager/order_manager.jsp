<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--静态包含base标签，css样式、jquery标签--%>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
		<%--静态包含manager管理模块的菜单--%>
		<%@include file="/pages/common/manager.meu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>
			<c:forEach items="${sessionScope.allOrders}" var="allOrders">
			<tr>
				<td>${allOrders.orderId}</td>
				<td>${allOrders.createTime}</td>
				<td>${allOrders.price}</td>
				<td>
					<c:if test="${allOrders.status==0}">
						已发货
					</c:if>
					<c:if test="${allOrders.status==1}">
						未发货
					</c:if>
					<c:if test="${allOrders.status==2}">
						已签收
					</c:if>
				</td>
				<td><a href="orderServlet?action=showOrderDetails&orderId=${allOrders.orderId}">查看详情</a></td>
				<td><a href="orderServlet?action=sendOrder&orderId=${allOrders.orderId}">点击发货</a></td>
			</tr>
			</c:forEach>
		</table>

	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>