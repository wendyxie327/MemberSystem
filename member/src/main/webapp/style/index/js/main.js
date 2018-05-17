var main = {
    init : function () {

    },
    /**
     * 根据传入的内容，显示二维码*
     * @param userCode
     */
    createQRCode : function (userCode) {
        var qrcodeUrl = url.httpUrl + url.base_qrcode_url + "?refereeUserCode=" + userCode;
        $('#qrcode').qrcode({width: 200, height: 200, text: qrcodeUrl});
        $('#qrcode_url').val(qrcodeUrl);
    },

    copyQRCode : function () {
        var qrcodeUrl = $('#qrcode_url').val();
        document.getElementById("qrcode_url").select(); // 选择对象
        var tag = document.execCommand("Copy"); // 执行浏览器复制命令
        console.log(tag);

        if (tag){
            dialog.alertSuccess("复制成功; \n" + qrcodeUrl);
        }else {
            dialog.alertI("该浏览器不支持一键复制功能");
        }
    }
};
main.init();
