<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%--<c:import url="/style/common/header.jsp"/>--%>
    <%--<link href="<c:url value="/style/index/css/register.css"/>" rel="stylesheet">--%>
</head>
<body class="gray-bg">

<div class="wrapper wrapper-content loginscreen  animated fadeInRight">

    <h3 class="text-center ">欢迎注册</h3>


    <!-- 推荐人信息确认-->
    <c:import url="/register_content.jsp"/>

    <p class="text-muted text-center">
        <small>已经有账户了？</small>
        <a href="<c:url value="login"/> ">点此登录</a>
    </p>

</div>

</body>

</html>
