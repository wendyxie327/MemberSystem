// sign_in.js
var login = {
    init : function() {
        login.hide('login_message');
        document.onkeydown = function(event) { //回车键的键值为13
            if (event.keyCode == 13) {
                login.login();
            }
        };
    },
    login : function() {
        login.hide('login_message');
        var loginUsername = $('#user_code').val();
        var loginPassword = $('#login_pwd').val();
        if (check.isNull(loginUsername)) {
            login.show('login_message', '用户名不能为空!');
            return;
        }
        if (check.isNull(loginPassword)) {
            login.show('login_message', '密码不能为空!');
            return;
        }
        if (!check.isLoginName(loginUsername)) {
            login.show('login_message', '用户名或密码不正确');
            return;
        }
        if (!check.isLoginPwd(loginPassword)) {
            console.log(loginPassword);
            login.show('login_message', '用户名或密码不正确');
            return;
        }
        $('#login').html('正在校验信息...');
        $('#login').addClass('disabled');
        common.sendPost( url.login, {
            loginUsername : loginUsername,
            loginPassword : loginPassword
        }, function(data) {
            if (data.success) {
                $('#login').html('正在登录...');
                window.location.href = url.href_index;
            } else {
                $('#login').html("登 录");
                login.show('login_message', data.msg);
                $('#login').removeClass('disabled');
            }
        });
    },

    show : function(id, value) {
        $('#' + id).slideDown();
        $('#' + id).html(value);
    },
    hide : function(id) {
        $('#' + id).html('');
        $('#' + id).hide();
    },
};

login.init();
