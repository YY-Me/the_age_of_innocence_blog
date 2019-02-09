layui.config({
	base: '../../../js/admin/' //静态资源所在路径
}).extend({
	common: 'common' //公共模块
}).use(['common'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		form = common.form;
	var menu_add = {
		init: function() {
			menu_add.initData();
			menu_add.bindEvent();
			menu_add.initSubmit();
		},
		initData: function() {
			var html = "<option value='0'>根目录</option>";
			var menus = common.getMenus(1);
			console.log(menus)
			$.each(menus, function(idx, obj) {
				html += "<option value='" + obj.id + "'>" + obj.name + "</option>";
			});
			$('#menu').html(html);
			form.render();
		},
		bindEvent: function() {
			$('#chooseico').on('click', function() {
				layer.msg("适配中...")
			})
			//判断菜单按钮
			menu_add.checkMenuType($('select[name="type"]').val());
			form.on('select(menu_type)', function(data) {
				//1菜单，2按钮
				menu_add.checkMenuType(data.value);
			});
		},
		initSubmit: function() {
			//表单监听提交
			form.on('submit(formDemo)', function(data) {
				var user_data = data.field;
				user_data = JSON.stringify(user_data)
				$.ajax({
					url: common.IP + '/api/blog-admin/menus',
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
		},
		checkMenuType: function(value) {
			$('.menu_href,.menu_permission,.menu_ico').addClass('layui-hide')
			if(value == 1) {
				$('.menu_href').removeClass('layui-hide')
				$('.menu_ico').removeClass('layui-hide')
			} else if(value == 2) {
				$('.menu_permission').removeClass('layui-hide')
			}
		}
	};
	$(function() {
		menu_add.init();
	});
});