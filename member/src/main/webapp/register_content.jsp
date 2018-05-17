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
                                <div class="input-group">
                                    <input type="text" class="form-control" id="referee_user_code" value="${refereeUserCode==null ? param.refereeUserCode : refereeUserCode}"> <span class="input-group-btn">
                                    <button type="button" onclick="register.checkRefereeUserCode()" class="btn btn-primary">检查
                                        </button> </span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" hidden>
                            <label class="col-sm-2 control-label">区域经理编号</label>
                            <div class="col-sm-10">
                                <div class="input-group">
                                    <input type="text" class="form-control" id="higher_user_code" value="${higherUserCode}"> <span class="input-group-btn">
                                    <button type="button" onclick="register.checkHigherUserCode()" class="btn btn-primary">检查
                                        </button> </span>
                                </div>
                            </div>
                        </div>

                        <div class="form-group" >
                            <label class="col-sm-2 control-label">所属区域<span class="color_red"> *</span></label>
                                <div class="col-sm-10 ">
                                    <select class="form-control m-b" id="area_name">
                                        <option value="A" selected="selected" >A区域</option>
                                        <option value="B" >B区域</option>
                                    </select>
                                </div>
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
                                <div class="input-group">
                                    <input type="text" class="form-control" id="user_code"> <span class="input-group-btn"> <button
                                        type="button" class="btn btn-primary" onclick="register.checkUserCode()">检查
                                        </button> </span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group"  id="login_pwd_group">
                            <label class="col-sm-2 control-label">密码<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="login_pwd">
                            </div>
                        </div>
                        <div class="form-group"  id="login_pwd_confirm_group">
                            <label class="col-sm-2 control-label">确认密码<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="login_pwd_confirm">
                            </div>
                        </div>
                        <div class="form-group"  id="real_user_name_group">
                            <label class="col-sm-2 control-label">会员姓名<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="real_user_name">
                            </div>
                        </div>
                        <div class="form-group"  id="identity_id_group">
                            <label class="col-sm-2 control-label">身份证号码<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="identity_id">
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
                                <div class="col-sm-4 ">
                                    <select class="form-control m-b" id="address_province" onchange="changeSelectAddress(this);">
                                        <option value="000000" >-请选择省-</option>
                                    </select>
                                </div>
                                <div class="col-sm-4 ">
                                    <select class="form-control m-b" id="address_city" onchange="changeSelectAddress(this);">
                                        <option value="000000" >-请选择市-</option>
                                    </select>
                                </div>
                                <div class="col-sm-4 ">
                                    <select class="form-control m-b" id="address_area">
                                        <option value="000000" >-请选区-</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">收货详细地址<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="address_detail">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">联系电话<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" maxlength="12" class="form-control" required="" id="mobile_num">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">微信号</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="wx">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">QQ号</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="qq">
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
                                <input type="text" class="form-control" required="" id="bank_name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">银行卡卡号<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="bank_code">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">开户人姓名<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="bank_user_name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">开户所属分行<span class="color_red"> *</span></label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" required="" id="bank_address">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <button type="submit" class="btn btn-primary block full-width m-b" onclick="register.registerConfirm()">
        ${registerBtn  == null ? "注 册" : registerBtn}
        </button>

</div>

<script src="<c:url value='/style/common/js/address_name.js'/>"></script>
<script src="<c:url value='/style/common/js/address_func.js'/>"></script>
<script src="<c:url value='/style/index/js/register.js'/>"></script>

</body>

</html>
