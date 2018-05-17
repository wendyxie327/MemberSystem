// common.js
var common = {
    project_name: '/member',
    init: function () {

    },
    /**
     * Ajax-POST方式请求 - Json传输方式
     * @param url
     * @param param
     * @param callback
     */
    sendPostJson: function (url, param, callback) {
        $.ajax({
            url: url,
            data: JSON.stringify(param),
            type: "POST",
            dataType: 'json',
            contentType: "application/json", //必须有
            success: function (data) {
                console.log(data);
                callback(data);
            },
            error: function (e) {
                console.log(e);
                if (e.status == 404) {
                    dialog.alertW("页面没有找到！");
                } else if (e.status == 500) {
                    dialog.alertW_T(
                        "系统内部发生错误，请及时联系管理员！",
                        '错误信息：' + e.statusText + '<br />错误类型：' + e.status + '<br />');
                } else {
                    dialog.alertW("发生未知错误，请及时联系管理员" + e.status);
                }
            }
        });
    },

    /**
     * Ajax-POST方式请求
     * @param url
     * @param param
     * @param callback
     */
    sendPost: function (url, param, callback) {
        $.ajax({
            url: url,
            data: param,
            type: "POST",
            success: function (data) {
                callback(data);
            },
            error: function (e) {
                if (e.status == 404) {
                    dialog.alertW("页面没有找到！");
                } else if (e.status == 500) {
                    dialog.alertW_T(
                        "系统内部发生错误，请及时联系管理员！",
                        '错误信息：' + e.statusText + '<br />错误类型：' + e.status + '<br />');
                } else {
                    dialog.alertW("发生未知错误，请及时联系管理员" + e.status);
                }
            }
        });
    },
    /**
     * Ajax-GET方式请求
     * @param url
     * @param callback
     */
    sendGet: function (url, callback) {
        $.ajax({
            url: url,
            type: "GET",
            dataType: 'json',
            success: function (data) {
                callback(data);
            },
            error: function (e) {
                if (e.status == 404) {
                    dialog.alertW("页面没有找到！");
                } else if (e.status == 500) {
                    dialog.alertW_T(
                        "系统内部发生错误，请及时联系管理员！",
                        '错误信息：' + e.statusText + '<br />错误类型：' + e.status + '<br />');
                } else {
                    dialog.alertW("发生未知错误，请及时联系管理员" + e.status);
                }
            }
        });
    },
    error: function (data) {
        // dialog.hideAlertTrigger(data.msg, function () {
        //     if (data.code == 'NOT_LOGIN')
        //         window.location.href = common.project_name + '/sign_in';
        // });
    },
    /**
     * 去除全部空格
     * @param str 去除字段
     * @returns 没有空格的字符串
     * @update wzhz（原来的有错误）
     */
    removeAllSpace: function (str) {
        return str.replace(/\s/g, "");
        // return str.replace(/(^\s*)|(\s*$)/g, "");
        // return str.replace(/\s+/g, "");
    },
    formatDateTime: function (inputTime) {
        if (null == inputTime) return '-';
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
    },


    setShowMessage: function (elementID, mess) {
        document.getElementById(elementID).innerHTML = mess;
    },

    locationData: [116.397428, 39.90923],
};

common.init();
