var config = {

    /**
     * 将用户角色ID转换为汉字描述
     * @param rodeId
     * @returns {string}
     */
    formatRodeDesc: function (rodeId) {
        // 用户角色编号， 1老板，2区域经理，3普通会员
        if (rodeId == '1') {
            return '老板';
        } else if (rodeId == '2') {
            return '区域经理';
        } else if (rodeId == '3') {
            return '普通会员';
        } else {
            return '未知用户';
        }
    },

    configRodeId : {
        boss : 1,
        area_user : 2,  //区域经理
        common_user : 3
    },


}