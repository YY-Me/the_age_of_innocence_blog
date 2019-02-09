layui.config({
	base: '../../../js/admin/' //静态资源所在路径
}).extend({
	common: 'common' //公共模块
}).use(['common', 'laydate'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		form = common.form,
		laydate = layui.laydate;
	var admin_update = {
		init: function() {
			//执行一个laydate实例
			laydate.render({
				elem: '#birthday',
				max: 0,
				format: 'yyyy-MM-dd HH:mm:ss'
			});
			admin_update.initData();
			admin_update.initFormCheck();
			admin_update.initSubmit();
		},
		initData: function() {
			var uid = common.getUrlParam("uid");
			//状态
			common.showDictSelect("userStatus", "userStatus");
			common.showDictRadio("sex", "sex");
			//角色
			common.showRolesCheckbox("role");
			//用户数据
			admin_update.initUserData(uid);
			form.render();
		},
		initUserData: function(uid) {
			$.ajax({
				url: common.IP + '/api/blog-admin/sysUser/' + uid,
				type: 'GET',
				success: function(result, status, xhr) {
					layer.closeAll('loading');
					form.val("admin_base_info", result.data)
					$("input[name='sex'][value='" + result.data.sex + "']").attr("checked", "checked")
					$.each(result.data.roles, function(idx, obj) {
						$("input[type='checkbox'][data-id='" + obj.id + "']").attr("checked", "checked")
					});
					form.render();
				}
			});
		},
		initFormCheck: function() {
			form.verify({
				username: function(value, item) {
					if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
						return '用户名不能有特殊字符';
					}
					if(!(/^[\S]{2,16}$/.test(value))) {
						return '用户名必须2到16位，且不能出现空格';
					}
				},
				nickname: function(value, item) {
					if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
						return '昵称不能有特殊字符';
					}
					if(!(/^[\S]{2,16}$/.test(value))) {
						return '昵称必须2到16位，且不能出现空格';
					}
				},
				mypassword: function(value, item) {
					if(!(/^[\S]{5,18}$/.test(value))) {
						return '密码必须5到18位，且不能出现空格';
					}
				},
				myphone: function(value, item) {
					if(value) {
						if(!(/^[1][3,4,5,7,8][0-9]{9}$/.test(value))) {
							return '请填写正确的电话';
						}
					}
				},
				myemail: function(value, item) {
					if(value) {
						if(!(/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value))) {
							return "请填写正确的邮箱"
						}
					}
				}
			});
		},
		initSubmit: function() {
			//表单监听提交
			form.on('submit(formDemo)', function(data) {
				var user_data = data.field;
				var roleIds = [];
				$.each($('#role').children('input:checked'), function(idx, obj) {
					roleIds.push($(this).data('id'))
				});
				user_data.roleIds = roleIds;
				user_data = JSON.stringify(user_data)
				$.ajax({
					url: common.IP + '/api/blog-admin/sysUser',
					type: 'POST',
					data: user_data,
					success: function(result, status, xhr) {
						layer.closeAll('loading');
						layer.msg(result.message, {
							icon: 1,
							time: 1000
						}, function() {
							window.history.back(-1);
						});
					}
				});
				return false;
			});
		}
	};
	$(function() {
		admin_update.init();
	});
});