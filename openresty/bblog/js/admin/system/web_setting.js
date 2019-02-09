layui.config({
	base: '../../../js/admin/' //静态资源所在路径
}).extend({
	common: 'common' //公共模块
}).use(['common'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		form = common.form;
	initData();
	//表单监听提交
	form.on('submit(set_website)', function(data) {
		var user_data = JSON.stringify(data.field);
		$.ajax({
			url: common.IP + '/api/blog-admin/system/baseInfo',
			type: 'PUT',
			data: user_data,
			success: function(result, status, xhr) {
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

	function initData() {
		$.ajax({
			type: 'GET',
			async: false,
			url: common.IP + '/api/blog-admin/system/baseInfo',
			success: function(data) {
				layer.closeAll('loading');
				if(data.data) {
					form.val("webSiteBaseInfo", data.data);
				}
			}
		});
	}
});