<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<!-- 推荐结构 -->
<head>
    <c:import url="/style/common/header.jsp"/>
    <link href="<c:url value="/style/hplus/css/plugins/jsTree/style.min.css" />" rel="stylesheet">
    <link href="<c:url value="/style/user/css/referee_all_user_tree.css" />" rel="stylesheet">
</head>

<body class="gray-bg">
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-4">
        <h2>推荐结构</h2>
    </div>
</div>

<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">

            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>推荐结构</h5>
                    </div>
                    <div class="ibox-content" style="min-height: 500px">
                        <div id="using_json"></div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jsTree plugin javascript -->
<script src="<c:url value="/style/hplus/js/plugins/jsTree/jstree.min.js"/> "></script>

<script src="<c:url value="/style/user/js/referee_all_user_tree.js"/> "></script>
</body>

</html>
