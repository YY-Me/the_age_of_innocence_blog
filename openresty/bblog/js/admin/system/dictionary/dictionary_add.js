layui.config({
	base: '../../../js/admin/' //静态资源所在路径
}).extend({
	common: 'common' //公共模块
}).use(['common'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		form = common.form;
	var dict_add = {
		init: function() {
			dict_add.initSubmit();
		},
		initSubmit: function() {
			//表单监听提交
			form.on('submit(formDemo)', function(data) {
				var dict_data = data.field;
				sessionStorage.removeItem(dict_data.type);
				dict_data = JSON.stringify(dict_data)
				$.ajax({
					url: common.IP + '/api/blog-admin/dictionary',
					type: 'POST',
					data: dict_data,
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
		dict_add.init();
	});

});