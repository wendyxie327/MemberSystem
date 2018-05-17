// dialog.js
/**
 * dialog弹出层
 * wzhz
 * 2016-11-24
 */
var dialog = {

	alertI : function (msg) {
		swal({
			title : "",
            text: msg
		});
    },

    alertSuccess : function (msg, func) {
        swal({
            title: "",
            text: msg,
            type: "success",
        }, func);
    },

    alertW : function (msg) {
        swal({
            title: "",
            text: msg,
            type: "warning"
        });
    },
    alertW_T : function (title, msg) {
        swal({
            title: title,
            text: msg,
            type: "warning"
        });
    },
		/**
		 * 提示消息框Alert()（无回调）success info warning danger
		 */
		htmlAlert : function(msg, status) {
			var _messageDialog = $('#message-dialog');
			var _message;
			if (status == 's') {
				_message = $('.alert-success');
			} else if (status == 'w') {
				_message = $('.alert-warning');
			} else if (status == 'd') {
				_message = $('.alert-danger');
			} else if (status == 'i') {
				_message = $('.alert-info');
			} else {
				_message = $('#message');
			}
			_message.html(msg);
			_message.show();
			_messageDialog.modal('show').on('hidden.bs.modal', function () {
				// 执行一些动作...
				_messageDialog.modal('hide');
				_message.hide();
				_message.html('');
			});
		},
	/**
	 * 提示消息框Alert()（无回调）success info warning danger
	 */
	alert : function(msg, status) {
		var _messageDialog = $('#message-dialog');
		var _message;
		if (status == 's') {
			_message = $('.alert-success');
		} else if (status == 'w') {
			_message = $('.alert-warning');
		} else if (status == 'd') {
			_message = $('.alert-danger');
		} else if (status == 'i') {
			_message = $('.alert-info');
		} else {
			_message = $('#message');
		}
		_message.text(msg);
		_message.show();
		_messageDialog.modal('show').on('hidden.bs.modal', function () {
			// 执行一些动作...
			_messageDialog.modal('hide');
			_message.hide();
			_message.text('');
		});
	},
	/**
	 * 提示消息框（回调）在调用 show 方法后触发。show.bs.modal
	 */
	showAlertTrigger : function(msg, callback) {
		var _messageDialog = $('#message-dialog');
		var _message = $('#message');
		_message.show();
		_message.text(msg);
		var _back = callback;
		_messageDialog.modal('show').on('show.bs.modal', function () {
			// 执行一些动作...
			_back();
			_message.hide();
			_message.text('');
		});
	},
	/**
	 * 提示消息框（回调）当模态框对用户可见时触发（将等待 CSS 过渡效果完成）。shown.bs.modal
	 */
	shownAlertTrigger : function(msg, callback) {
		var _messageDialog = $('#message-dialog');
		var _message = $('#message');
		_message.show();
		_message.text(msg);
		var _back = callback;
		_messageDialog.modal('show').on('shown.bs.modal', function () {
			// 执行一些动作...
			_back();
			_message.hide();
			_message.text('');
		});
	},
	/**
	 * 提示消息框（回调）当调用 hide 实例方法时触发。hide.bs.modal
	 */
	hideAlertTrigger : function(msg, callback) {
		var _messageDialog = $('#message-dialog');
		var _message = $('#message');
		_message.show();
		_message.text(msg);
		var _back = callback;
		_messageDialog.modal('show').on('hide.bs.modal', function () {
			  // 执行一些动作...
			_back();
			_message.hide();
			_message.text('');
		}).on('hidden.bs.modal', function () {
			// 执行一些动作...
			/*_messageDialog.modal('hide');*/
			
		});
	},
	/**
	 * 提示消息框（回调）当模态框完全对用户隐藏时触发。hidden.bs.modal
	 */
	hiddenAlertTrigger : function(msg, callback) {
		var _messageDialog = $('#message-dialog');
		var _message = $('#message');
		_message.show();
		_message.text(msg);
		var _back = callback;
		_messageDialog.modal('show').on('hidden.bs.modal', function () {
			// 执行一些动作...
			_messageDialog.modal('hide');
			_back();
			_message.hide();
			_message.text('');
		});
	},
	/**
	 * 选择消息框（回调）当调用 hide 实例方法时触发。hide.bs.modal
	 */
	selectAlertTrigger : function(msg, callback) {
		var _messageDialog = $('#message-dialog-callback');
		var _message = $('#message-dialog-callback-message');
		var _btn = $('#message-dialog-callback_btnSubmit');
		_message.show();
		_message.text(msg);
		var _back = callback;
		/*_btn.click(function() {*/
		_btn.unbind("click").click(function(e) {
			// 执行一些动作...
			_back();
			_messageDialog.modal('hide');
		});
		_messageDialog.modal('show').on('hide.bs.modal', function () {
			// 执行一些动作...
			_message.hide();
			_message.text('');
		});
	},
	/**
	 * 页面初始化函数
	 */
	init : function() {
		// 测试
		/*message.selectAlertTrigger("选择", function() {
			message.alert('你好');
		});*/
		/*message.showAlertTrigger("选择", function() {
			message.alert('你好', 'i');
		});*/
		/*message.shownAlertTrigger("选择", function() {
			message.alert('你好', 'd');
		});*/
		/*message.hideAlertTrigger("选择", function() {
			message.alert('你好', 'w');
		});*/
		/*message.hideAlertTrigger("选择", function() {
			message.alert('你好', 's');
		});*/
	}
};