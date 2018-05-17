<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<!-- 老板审核用户 -->
<head>
    <c:import url="/style/common/header.jsp"/>
    <!-- Data Tables -->
    <link href="<c:url value="/style/hplus/css/plugins/dataTables/dataTables.bootstrap.css"/> " rel="stylesheet">
</head>

<body class="gray-bg">
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-sm-4">
        <h2>直推列表</h2>

    </div>
</div>

<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>直推列表</h5>
                </div>
                <div class="ibox-content">

                    <table class="table table-striped table-bordered table-hover dataTables-example">
                        <thead>
                        <tr>
                            <th>会员编号</th>
                            <th>会员姓名</th>
                            <th>联系电话</th>
                            <th>会员状态</th>
                            <th>注册时间</th>
                            <th>开户银行</th>
                            <th>银行卡号</th>
                            <th>开户人姓名</th>
                            <th>开户所属分行</th>
                        </tr>
                        </thead>

                        <tbody id="table_body">
                        <c:if test="${userInfos !=null}">
                            <c:forEach var="userInfo" items="${userInfos}">
                                <tr class="gradeA">
                                    <td>${userInfo.userCode }</td>
                                    <td>${userInfo.realUserName}</td>
                                    <td>${userInfo.mobileNum}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${userInfo.verifyStatus=='0'}">
                                                老板
                                            </c:when>
                                            <c:when test="${userInfo.verifyStatus=='1'}">
                                                <span class="color_red">等待成为普通会员</span>
                                            </c:when>
                                            <c:when test="${userInfo.verifyStatus=='2'}">
                                                普通会员
                                            </c:when>
                                            <c:when test="${userInfo.verifyStatus=='3'}">
                                                <span class="color_red">等待成为区域经理</span>
                                            </c:when>
                                            <c:when test="${userInfo.verifyStatus=='4'}">
                                                区域经理
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td><fmt:formatDate value="${userInfo.createTime}" pattern="yyyy-MM-dd  HH:mm"/></td>
                                    <td>${userInfo.bankName}</td>
                                    <td>${userInfo.bankCode}</td>
                                    <td>${userInfo.bankUserName}</td>
                                    <td>${userInfo.bankAddress}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>

                    </table>

                </div>
            </div>
        </div>
    </div>
</div>
</div>

<%--<script src="<c:url value='/style/hplus/js/plugins/jeditable/jquery.jeditable.js'/>"></script>--%>

<!-- Data Tables -->
<script src="<c:url value='/style/hplus/js/plugins/dataTables/jquery.dataTables.js'/>"></script>
<script src="<c:url value='/style/hplus/js/plugins/dataTables/dataTables.bootstrap.js'/>"></script>

<script src="<c:url value='/style/user/js/referee_user_list.js'/>"></script>

</body>

</html>
