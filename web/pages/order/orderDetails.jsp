<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>  <%--自己加入一个底订单详情页用来返回订单详情数据--%>
    <%--静态包含base标签，css样式、jquery标签--%>
    <%@include file="/pages/common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">订单详情</span>
    <%--静态包含登录成功之后的菜单--%>
    <%@ include file="/pages/common/logn_success.meu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品编号</td>
            <td>商品名称</td>
            <td>商品数量</td>
            <td>商品单价</td>
            <td>商品总价</td>
            <td>订单编号</td>
        </tr>
        <c:forEach items="${sessionScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.id}</td>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
                <td>${orderItem.orderId}</td>
            </tr>
        </c:forEach>

    </table>


</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/foot.jsp"%>
</body>
</html>