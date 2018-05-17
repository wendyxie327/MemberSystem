var register = {
    init : function () {
        initAddressList("address_province", "address_city", "address_area");
    },

    /**
     * 检查推荐人编号
     */
    checkRefereeUserCode : function () {
        var refereeUserCode = $('#referee_user_code').val();
        console.log(refereeUserCode);
        if (refereeUserCode == null || refereeUserCode == ''){
            dialog.alertW("请填入推荐人编号");
            return;
        }

        var param = {
            refereeUserCode : refereeUserCode
        };
        common.sendPost(url.checkRefereeUserCode, param, function (data) {
            console.log( param + ","+data);
            if (data.success){
                // 检查通过
                dialog.alertSuccess("推荐人存在");
            }else {
                dialog.alertW(data.msg);
            }
        })
    },

    /**
     * 检查区域经理编号
     */
    checkHigherUserCode : function () {
        var higherUserCode = $('#higher_user_code').val();
        console.log(higherUserCode);
        if (higherUserCode == null || higherUserCode == ''){
            dialog.alertW("请填入区域经理编号");
            return;
        }

        var param = {
            higherUserCode : higherUserCode
        };
        common.sendPost(url.checkHigherUserCode, param, function (data) {
            console.log( param + ","+data);
            if (data.success){
                // 检查通过
                dialog.alertSuccess("区域经理存在");
            }else {
                dialog.alertW(data.msg);
            }
        })
    },

    /**
     * 检查会员编号是否存在
     */
    checkUserCode : function () {
        var userCode = $('#user_code').val();
        console.log(userCode);
        if (userCode == null || userCode == ''){
            dialog.alertW("请填入会员编号");
            return;
        }

        var param = {
            userCode : userCode
        };
        common.sendPost(url.checkUserCode, param, function (data) {
            console.log( param + ","+data);
            if (data.success){
                // 检查通过
                dialog.alertSuccess("此会员编号可用");
            }else {
                dialog.alertW(data.msg);
            }
        })
    },

    /**
     * 确认注册
     */
    registerConfirm : function () {

        var refereeUserCode = $('#referee_user_code').val();
        var higherUserCode = $('#higher_user_code').val();
        var userCode = $('#user_code').val();
        var loginPwd = $('#login_pwd').val();
        var loginPwdConfirm = $('#login_pwd_confirm').val();
        var realUserName = $('#real_user_name').val();
        var identityId = $('#identity_id').val();
        var addressProvince = $('#address_province option:selected').text();
        var addressCity = $('#address_city option:selected').text();
        var addressArea = $('#address_area option:selected').text();
        var addressProvinceVal = $('#address_province option:selected').val();
        var addressCityVal = $('#address_city option:selected').val();
        var addressAreaVal = $('#address_area option:selected').val();
        var addressDetail = $('#address_detail').val();
        var mobileNum = $('#mobile_num').val();
        var wx = $('#wx').val();
        var qq = $('#qq').val();
        var bankName = $('#bank_name').val();
        var bankCode = $('#bank_code').val();
        var bankUserName = $('#bank_user_name').val();
        var bankAddress = $('#bank_address').val();
        var areaName = $('#area_name option:selected').val();

        // 1. 检查各参数格式： 推荐人编号和会员编号由后台进行校验
        if (!check.isLoginName(userCode)){
            $('#user_code_group').addClass("has-error");
            dialog.alertW("会员编号必须由6-20位数字、字符组成");
            return;
        }else {
            $('#user_code_group').removeClass("has-error");
        }

        if (!check.isLoginPwd(loginPwd)){
            $('#login_pwd_group').addClass("has-error");
            dialog.alertW("密码必须由6-20位数字、字符组成");
            return;
        }else {
            $('#login_pwd_group').removeClass("has-error");
        }

        if ( loginPwd != loginPwdConfirm){
            $('#login_pwd_confirm_group').addClass("has-error");
            dialog.alertW("两次密码不一致");
            return;
        }else {
            $('#login_pwd_confirm_group').removeClass("has-error");
        }

        if (check.isNull(realUserName)){
            $('#real_user_name_group').addClass("has-error");
            dialog.alertW("请填写会员姓名");
            return;
        }else {
            $('#real_user_name_group').removeClass("has-error");
        }

        if (!check.isIdentityCard(identityId)){
            $('#identity_id_group').addClass("has-error");
            dialog.alertW("请填写正确的身份证号");
            return;
        }else {
            $('#identity_id_group').removeClass("has-error");
        }

        // 通讯地址
        if (addressProvinceVal == addressDefultValue || addressCityVal == addressDefultValue || addressAreaVal == addressDefultValue){
            $('#address_group').addClass("has-error");
            dialog.alertW("请选择完整的区域地址");
            return;
        }else {
            $('#address_group').removeClass("has-error");
        }

        if (check.isNull(addressDetail)){
            $('#address_detail').parent().parent().addClass("has-error");
            dialog.alertW("请填写详细地址");
            return;
        }else {
            $('#address_detail').parent().parent().removeClass("has-error");
        }

        if (!check.isPhone(mobileNum)){
            $('#mobile_num').parent().parent().addClass("has-error");
            dialog.alertW("请填写正确的联系电话");
            return;
        }else {
            $('#mobile_num').parent().parent().removeClass("has-error");
        }

        if (check.isNull(bankName)){
            $('#bank_name').parent().parent().addClass("has-error");
            dialog.alertW("请填写开户银行");
            return;
        }else {
            $('#bank_name').parent().parent().removeClass("has-error");
        }

        if (check.isNull(bankCode)){
            $('#bank_code').parent().parent().addClass("has-error");
            dialog.alertW("请填写银行卡卡号");
            return;
        }else {
            $('#bank_code').parent().parent().removeClass("has-error");
        }

        if (check.isNull(bankUserName)){
            $('#bank_user_name').parent().parent().addClass("has-error");
            dialog.alertW("请填写开户人姓名");
            return;
        }else {
            $('#bank_user_name').parent().parent().removeClass("has-error");
        }

        if (check.isNull(bankAddress)){
            $('#bank_address').parent().parent().addClass("has-error");
            dialog.alertW("请填写开户所属分行");
            return;
        }else {
            $('#bank_address').parent().parent().removeClass("has-error");
        }

        var param = {
            refereeUserCode: refereeUserCode,
            higherUserCode: higherUserCode,
            userCode: userCode,
            loginPwd: loginPwd,
            realUserName: realUserName,
            identityId: identityId,
            addressProvince: addressProvince,
            addressCity: addressCity,
            addressArea: addressArea,
            addressDetail: addressDetail,
            mobileNum: mobileNum,
            wx: wx,
            qq: qq,
            bankName: bankName,
            bankCode: bankCode,
            bankUserName: bankUserName,
            bankAddress: bankAddress,
            areaName : areaName
        };

        // 2. 提交注册
        common.sendPostJson(url.register, param, function (data) {
            console.log(data);
            if (data.success){
                // 提交注册成功，注册信息展示页面
                window.location.href = url.registerConfirm + "?userCode="+ param.userCode;
            }else {
                dialog.alertW(data.msg);
            }
        })
    }

};

register.init();