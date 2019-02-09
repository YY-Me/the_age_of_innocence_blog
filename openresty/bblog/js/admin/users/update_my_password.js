layui.config({
	base: '../../js/admin/' //静态资源所在路径
}).extend({
	common: 'common' //公共模块
}).use(['common'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		form = common.form;
	var currentUser = {
		init: function() {
			currentUser.initData();
			currentUser.initSubmit();
			currentUser.initFormCheck();
		},
		initData: function() {
			$("input[name='nickname']").val(sessionStorage.getItem("nickname"));
		},
		initSubmit: function() {
			//表单监听提交
			form.on('submit(formDemo)', function(data) {
				$("button[lay-filter='formDemo']").attr('disabled', true);
				setTimeout(function() {
					$("button[lay-filter='formDemo']").attr('disabled', false);
				}, 10000);
				if($('.layui-form input[name="password"]').val() != $('.layui-form input[name="repassword"]').val()) {
					layer.msg("两次密码不一致", {
						icon: 2,
						time: 2000
					});
					return false;
				}
				var user_data = JSON.stringify(data.field);
				$.ajax({
					url: common.IP + '/api/blog-admin/sysUser/current/pwd',
					type: 'PUT',
					data: user_data,
					success: function(result, status, xhr) {
						common.refreshToken()
						layer.closeAll('loading');
						layer.msg(result.message, {
							icon: 1,
							time: 1300
						}, function() {
							window.location.reload();
						});
					}
				});
				return false;
			});
		},
		initFormCheck: function() {
			form.verify({
				password: [
					/^[\S]{5,16}$/, '密码必须5到16位，且不能出现空格'
				]
			});
		}
	};
	$(function() {
		currentUser.init();
	});
});