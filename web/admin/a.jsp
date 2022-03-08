<%@ page import="com.gdx.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/2/26
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
User user = (User) session.getAttribute("user");
    if(user==null){
        request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        return;
    }

%>


是一个jsp文件
</body>
</html>
