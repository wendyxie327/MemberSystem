<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="/style/common/header.jsp"/>
    <link href="<c:url value="/style/index/css/register.css"/>" rel="stylesheet">
</head>
<body class="gray-bg">

<div class="wrapper wrapper-content loginscreen  animated fadeInRight">

    <!-- 推荐人信息确认-->
    <div class="col-sm-12">
        <div class="row">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>推荐人信息确认</h5>
                </div>
                <div class="ibox-content">
                    <form class=" form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">推荐人编号</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="referee_user_code" value="${userInfo.refereeUserCode}" disabled>
                            </div>
                        </div>
                        <div class="form-group" hidden>
                            <label class="col-sm-2 control-label">区域经理编号</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="higher_user_code" value="${userInfo.higherUserCode}" disabled>
                            </div>
                        </div>
                        <div class="form-group" >
                            <label class="col-sm-2 control-label">所属区域</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="area_name" value="${userInfo.areaName}区域" disabled>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- 会员资料-->
        <div class="row">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>会员资料</h5>
                </div>
                <div class="ibox-content">
                    <form class=" form-horizontal">
                        <div class="form-group" id="user_code_group">
                            <label class="col-sm-2 control-label">会员编号<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="user_code" value="${userInfo.userCode}" disabled >
                            </div>
                        </div>
                        <div class="form-group"  id="login_pwd_group">
                            <label class="col-sm-2 control-label">密码<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="login_pwd" value="${userInfo.loginPwd}" disabled >
                            </div>
                        </div>
                        <div class="form-group"  id="real_user_name_group">
                            <label class="col-sm-2 control-label">会员姓名<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="real_user_name" value="${userInfo.realUserName}" disabled>
                            </div>
                        </div>
                        <div class="form-group"  id="identity_id_group">
                            <label class="col-sm-2 control-label">身份证号码<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="identity_id" value="${userInfo.identityId}" disabled>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <!-- 通信地址-->
        <div class="row">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>通信地址</h5>
                </div>
                <div class="ibox-content">
                    <form class=" form-horizontal">
                        <div class="form-group" id="address_group">
                            <label class="col-sm-2 control-label">所在区域<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="address" value="${userInfo.addressProvince} - ${userInfo.addressCity }- ${userInfo.addressArea}" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">收货详细地址<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="address_detail" value="${userInfo.addressDetail}" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">联系电话<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="mobile_num" value="${userInfo.mobileNum}" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">微信号</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="wx" value="${userInfo.wx}" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">QQ号</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="qq" value="${userInfo.qq}" disabled="">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <!-- 银行账户资料 -->
        <div class="row">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>银行账户资料</h5>
                </div>
                <div class="ibox-content">
                    <form class=" form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">开户银行<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="bank_name" value="${userInfo.bankName}" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">银行卡卡号<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="bank_code" value="${userInfo.bankCode}" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">开户人姓名<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="bank_user_name" value="${userInfo.bankUserName}" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">开户所属分行<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="bank_address" value="${userInfo.bankAddress}" disabled>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>

</body>

</html>
