<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <c:import url="/style/common/header.jsp"/>
    <link href="<c:url value='/style/index/css/index.css'/>" rel="stylesheet">

</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear">
                                <span class="block m-t-xs"><strong class="font-bold menu_user_code">${sessionScope.userInfo.realUserName}</strong></span>
                                <span class="text-muted text-xs block" id="rode_desc">未知用户角色<b class="caret"></b></span>
                            </span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a class="J_menuItem"
                                   href="<c:url value="/restful/login/registerConfirm?userCode=${sessionScope.userInfo.userCode}"/>">个人资料</a>
                            </li>
                            <li><a class="J_menuItem" href="<c:url value="/views/user/user_info_update"/> ">修改资料</a>
                            </li>
                            <%--<li><a class="J_menuItem" href="mailbox.html">修改密码</a>--%>
                            <%--</li>--%>
                            <li class="divider"></li>
                            <li><a href="<c:url value="/restful/login/logout"/> ">安全退出</a>
                            </li>
                        </ul>
                    </div>

                </li>

                <li>
                    <a class="J_menuItem" href="main"><i class="fa fa-home"></i> <span class="nav-label">系统首页</span></a>
                </li>

                <li>
                    <a href="#">
                        <i class="fa fa-user"></i>
                        <span class="nav-label">会员管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li id="menu_boss_verify_user">
                            <a class="J_menuItem" href="<c:url value="/restful/userInfo/bossVerifyUserView"/> ">会员审核</a>
                        </li>
                        <li>
                            <a class="J_menuItem"
                               href="<c:url value="/restful/userInfo/registerViewContent?refereeUserCode=${sessionScope.userInfo.userCode}"/>" >会员注册</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="<c:url value="/restful/userInfo/queryRefereeUserList"/> ">直推列表</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="<c:url value="/views/user/referee_all_user_tree"/> ">推荐结构</a>
                        </li>
                        <%--<li >--%>
                            <%--<a class="J_menuItem" href="<c:url value="/404"/> ">网络结构</a>--%>
                        <%--</li>--%>
                    </ul>
                </li>

                <!-- 财务管理 -->
                <%--<li>--%>
                    <%--<a href="#">--%>
                        <%--<i class="fa fa fa-bar-chart-o"></i>--%>
                        <%--<span class="nav-label">财务管理</span>--%>
                        <%--<span class="fa arrow"></span>--%>
                    <%--</a>--%>
                    <%--<ul class="nav nav-second-level">--%>
                        <%--<li>--%>
                            <%--<a class="J_menuItem" href="<c:url value="/404"/> ">奖金记录</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<a class="J_menuItem" href="<c:url value="/404"/> ">奖金提现</a>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</li>--%>

                <!--信息中心 -->
                <%--<li>--%>
                    <%--<a href="#">--%>
                        <%--<i class="fa fa fa-bar-chart-o"></i>--%>
                        <%--<span class="nav-label">信息中心</span>--%>
                        <%--<span class="fa arrow"></span>--%>
                    <%--</a>--%>
                    <%--<ul class="nav nav-second-level">--%>
                        <%--<li>--%>
                            <%--<a class="J_menuItem" href="<c:url value="/404"/> ">公司简介</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<a class="J_menuItem" href="<c:url value="/404"/> ">奖金制度</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<a class="J_menuItem" href="<c:url value="/404"/> ">汇款账户</a>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</li>--%>

            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->

    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                    <form class="navbar-form-custom system_title_content ">
                        <strong class=" system_title">会员管理系统</strong>
                        <span style="width:20px;"></span>
                        <div id="time" class="system_title"></div>
                    </form>
                </div>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="main">系统首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="<c:url value="/restful/login/logout"/> " class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="main" frameborder="0"
                    data-id="main" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">&copy; 2018-2028 会员管理系统</div>
        </div>
    </div>

</div>


<script src="<c:url value='/style/hplus/js/plugins/metisMenu/jquery.metisMenu.js'/>"></script>
<script src="<c:url value='/style/hplus/js/plugins/slimscroll/jquery.slimscroll.min.js'/>"></script>
<script src="<c:url value='/style/hplus/js/plugins/layer/layer.min.js'/>"></script>

<!-- 自定义的js -->
<script src="<c:url value='/style/hplus/js/hplus.js?v=4.1.0'/>"></script>
<script src="<c:url value='/style/hplus/js/contabs.js'/>"></script>

<!-- 第三方插件 -->
<script src="<c:url value='/style/hplus/js/plugins/pace/pace.min.js'/>"></script>

<script src="<c:url value='/style/index/js/index.js'/>"></script>

<script>
    index.init('${sessionScope.userInfo.rodeId}');
</script>

</body>

</html>