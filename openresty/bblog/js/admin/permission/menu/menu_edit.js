layui.config({
	base: '../../../js/admin/' //静态资源所在路径
}).extend({
	common: 'common' //公共模块
}).use(['common'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		form = common.form;
	var menu_edit = {
		init: function() {
			menu_edit.initData();
			menu_edit.bindEvent();
			menu_edit.initSubmit();
		},
		initData: function() {
			var id = common.getUrlParam("id");
			var html = "<option value='0'>根目录</option>";
			var menus = common.getMenus(1);
			$.each(menus, function(idx, obj) {
				html += "<option value='" + obj.id + "'>" + obj.name + "</option>";
			});
			$('#menu').html(html);
			form.render();
			//获取数据
			$.ajax({
				url: common.IP + '/api/blog-admin/menus',
				type: 'GET',
				data: {
					id: id
				},
				async: false,
				success: function(result, status, xhr) {
					layer.closeAll('loading');
					var data = result.data[0];
					form.val("formTest", data)
					form.render();
				}
			});
		},
		bindEvent: function() {
			$('#chooseico').on('click', function() {
				layer.msg("适配中...")
			})
			//判断菜单按钮
			menu_edit.checkMenuType($('select[name="type"]').val());
			form.on('select(menu_type)', function(data) {
				//1菜单，2按钮
				menu_edit.checkMenuType(data.value);
			});
		},
		initSubmit: function() {
			//表单监听提交
			form.on('submit(formDemo)', function(data) {
				$("button[lay-filter='formDemo']").attr('disabled', true);
				setTimeout(function() {
					$("button[lay-filter='formDemo']").attr('disabled', false);
				}, 10000);
				var user_data = data.field;
				user_data = JSON.stringify(user_data)
				$.ajax({
					url: common.IP + '/api/blog-admin/menus',
					type: 'PUT',
					data: user_data,
					success: function(result, status, xhr) {
						common.refreshToken();
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
		menu_edit.init();
	});
});