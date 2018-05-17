$().ready(function () {
    document.getElementById('time').innerHTML = '   ' + new Date().toLocaleString()
        + ' 星期' + '日一二三四五六'.charAt(new Date().getDay());
    setInterval(
        "document.getElementById('time').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",
        1000);
});

var index = {
    init : function (rodeId) {
        // 显示用户角色
        $('#rode_desc').html(config.formatRodeDesc(rodeId) + '<b class="caret"></b>');

        // 处理菜单
        if (config.configRodeId.boss == rodeId){ // 角色- 老板
            $('#menu_boss_verify_user').show(); // 会员审核

        }else if (config.configRodeId.area_user == rodeId){ // 角色 - 区域经理
            $('#menu_boss_verify_user').hide();
            
        }else { // 角色 - 普通会员
            $('#menu_boss_verify_user').hide();
        }
    }
}