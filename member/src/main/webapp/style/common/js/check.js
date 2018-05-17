// check.js
var check = {
	init : function() {
		
	},
	isNull : function(value) {
		if (typeof(value) == 'undefined')
			return true;
		if (null == value || '' == value)
			return true;
		return false;
	},
    isLoginName : function(value) {
        if (check.expression.loginName.test(value))
            return true;
        return false;
    },
    isLoginPwd : function(value) {
        if (check.expression.loginPwd.test(value))
            return true;
        return false;
    },
	isUrl : function(value) {
		if (check.expression.urlRegex.test(value)) {
			return true;
		} else {
			return false;
		}
	},
	isPhone : function(value) {
		if (check.expression.phone.test(value))
			return true;
		return false;
	},
	/**
	 * @param value
	 * @returns {Boolean} 有特殊符号返回true
	 */
	special : function(value) {
		if (check.expression.special.test(value))
			return true;
		return false;
	},
	isIP : function(value) {
		if (check.expression.ip.test(value))
			return true;
		return false;
	},
	isPositiveInteger : function(value) {
		if (check.expression.positiveInteger.test(value))
			return true;
		return false;
	},
	isEmail : function(value) {
		if (check.expression.email.test(value))
			return true;
		return false;
	},
	isQQ : function(value) {
		if (check.expression.qq.test(value))
			return true;
		return false;
	},
	isWx : function(value) {
		if (check.expression.wx.test(value))
			return true;
		return false;
	},
	isAliAccount : function(value) {//校验是否为支付宝账号
		if (check.isEmail(value)||check.isPhone(value))
			return true;
		return false;
	},
	isIdentityCard : function(value) {//校验是否为身份证号
		if (check.expression.identityCard.test(value))
			return true;
		return false;
	},
	/**
	 * 表达式
	 */
	expression : {
		/**
		 * phone校验表达式；
		 * !(/^1[34578]\d{9}$/.test(phone))
		 * "手机号码有误，请重填"
		 */
		// phone : /^1[34578]\d{9}$/,
		/** Email校验表达式； */
		email : /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/,
		/** 手机号正则表达式*/
		// phone : /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/,
		//phone : /^((13[0-9])|(15[^4])|(18[0-3,5-9])|(17[0-8])|(147))\d{8}$/,
		phone : /^1[3|4|5|7|8|9][0-9]{9}$/,
        /**登陆名: 6-20位字母或数字 */
        loginName :/^[a-zA-Z0-9]{4,20}$/,
		/**密码: 6-20位字母或数字 */
        loginPwd :/^[a-zA-Z0-9]{6,20}$/,
		/** qq校验表达式； */
		qq : /^[1-9]\d{4,10}$/,
		/** 微信校验表达式； */
		wx : /^[a-zA-Z\d_]{5,}$/,
		/** 校验特殊字符 */
		special : /[`~!@#\$%\^\&\*\(\)_\+<>\?:"\{\},\.\\\/;'\[\]]/im,
		/** 校验IP */
		ip : /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/,
		identityCard: /(^[A-Z]\d{9}$)|(^\d{15}$)|(^\d{18}$)|(^\d{17}(X|x)$)/,
		positiveInteger:/^[0-9]+$/,
		/** 返回url的正则表达式 */
		urlRegex : /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?/
	}
};


check.init();