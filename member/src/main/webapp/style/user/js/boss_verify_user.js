var boss_verify_user = {
    init : function () {
        // 初始化表格
        $('.dataTables-example').dataTable({
            ordering: false//是否启用排序
        });
    },

    agreeUserVerify : function (userId) {
        console.log(userId);
        // 通过审核
        var param = {
            userId: userId
        };
        common.sendPost(url.agreeUserVerify, param, function (data) {
            if (data.success){
                // 成功，刷新页面
                dialog.alertSuccess("该用户已成为会员", function () {
                    history.go(0);
                });

            }else {
                dialog.alertW(data.msg);
            }
        })
    }
};

boss_verify_user.init();