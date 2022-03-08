<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/2/28
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷管理员登录页面</title>
    <%--静态包含base标签，css样式、jquery标签--%>
    <%@include file="/pages/common/head.jsp"%>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>管理员登录</h1>
<%--                    <a href="pages/user/regist.jsp">立即注册</a>--%>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
                        ${empty requestScope.msg?"请输入正确的管理员账号或密码:":requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="managerLogin"/>

                        <label>账号名称：</label>
                        <input class="itxt" type="text" placeholder="请输入管理员账号名"
                               autocomplete="off" tabindex="1" name="username"
                               value="${requestScope.username}"/>
                        <br />
                        <br />
                        <label>账号密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
                        <br />
                        <br />
                        <input type="submit" value="登录" id="sub_btn" />
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%--静态包含页脚内容--%>
<%@include file="/pages/common/foot.jsp"%>
</body>
</html>
