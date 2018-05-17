var user_info_update = {
    init : function () {
        initAddressList("address_province", "address_city", "address_area");

        common.sendPostJson(url.queryLoginUserInfoDetail, null, function (data) {
            if (data.success){
                // 展示用户基本信息
                $('#user_code').val(data.data.userCode);
                $('#real_user_name').val(data.data.realUserName);
                $('#identity_id').val(data.data.identityId);
                var addressProvinceCode = getPlaceCode("address_province", data.data.addressProvince);
                var addressCityCode =getPlaceCode("address_city", data.data.addressCity);
                var addressAreaCode = getPlaceCode("address_area", data.data.addressArea);
                // 省
                $("#address_province").val(addressProvinceCode);
                $("#address_province").change();
                // 市
                $("#address_city").val(addressCityCode);
                $("#address_city").change();
                // 区
                $("#address_area").val(addressAreaCode);

                $('#address_detail').val(data.data.addressDetail);
                $('#mobile_num').val(data.data.mobileNum);
                $('#wx').val(data.data.wx);
                $('#qq').val(data.data.qq);
                $('#bank_name').val(data.data.bankName);
                $('#bank_code').val(data.data.bankCode);
                $('#bank_address').val(data.data.bankAddress);
                $('#bank_user_name').val(data.data.bankUserName);
            }else {
                console.log("初始化失败");
            }
        })
    },

    /**
     * 更新用户基本信息
     */
    updateUserInfo : function () {

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

        // 1. 检查各参数格式：
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
        console.log(addressProvinceVal);
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
        };

        common.sendPostJson(url.updateLoginUserInfo, param, function (data) {
            if (data.success){
                // 更新成功
                dialog.alertSuccess("更新成功", function () {
                    window.parent.closeActiveTab();
                });
            }else {
                dialog.alertW(data.msg);
            }
        })

    }
};

user_info_update.init();
