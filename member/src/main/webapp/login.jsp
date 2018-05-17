<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head >
    <c:import url="style/common/header.jsp"/>
    <link href="<c:url value='/style/index/css/login.css'/>"  rel="stylesheet">
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h6 class="logo-name">会员系统</h6>

            </div>
            <h3>欢迎使用 会员管理系统+</h3>
            <form class="m-t" role="form">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" required="" id="user_code">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" required="" id="login_pwd">
                </div>

                <div id="login_message" class="login_message" ></div>

                <a href="javascript:;"  class="btn btn-primary block full-width m-b" id="login" onclick="login.login()" >登 录</a>

                <p class="text-muted text-center"> <a href="javascript:;"><small>忘记密码了？</small></a> | <a href="<c:url value="/register"/> ">注册一个新账号</a>
                </p>

            </form>
        </div>
    </div>


    <script src="<c:url value='/style/index/js/login.js'/>"></script>


</body>

</html>
