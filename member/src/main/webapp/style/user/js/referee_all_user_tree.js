var referee_all_user_tree = {
    init : function () {
        common.sendPostJson(url.queryRefereeAllUserTreeList, null, function (data) {
            if (data.success){
                $('#using_json').jstree({
                    'core': {
                        'data': data.data
                    }
                });
            }else {
                dialog.alertW(data.msg);
            }
        })
    }
};

referee_all_user_tree.init();